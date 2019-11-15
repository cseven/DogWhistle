package com.c777.dogwhistle.item.whistlebehaviors;

import com.c777.dogwhistle.lib.LibBehaviors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class TargetBehavior extends WhistleBehavior {

    // Only allow targets to be selected within a 32 block radius
    private static final int TARGET_RANGE = 32;

    @Override
    public String getTag() {
        return LibBehaviors.TARGET_BEHAVIOR;
    }

    @Override
    public TextFormatting getTextFormat() {
        return TextFormatting.RED;
    }

    @Override
    public void execute(World worldIn, PlayerEntity playerIn, Hand handIn, float wolfRadius) {
        // Get target entity
        LivingEntity target = null;
        Vec3d look = playerIn.getLook(1.0f);

        look = look.scale(TARGET_RANGE);

        // Shouldn't be able to target entities that are on the same team as the player
        List<Entity> entities = playerIn.getEntityWorld().getEntitiesInAABBexcluding(playerIn,
                playerIn.getBoundingBox().expand(look).grow(1, 1, 1),
                ent -> ent instanceof LivingEntity && !(ent.getTeam() == playerIn.getTeam()));

        // Need to get the closest entity to the player that falls along the look vector
        double bestDistance = TARGET_RANGE;
        for (Entity e : entities) {
            if (playerIn.getDistance(e) < bestDistance) {
                bestDistance = playerIn.getDistance(e);
                target = (LivingEntity) e;
            }
        }

        // Get the list of nearby wolves that are owned by the player
        List<WolfEntity> ownedWolves = getNearbyOwnedWolves(worldIn, playerIn, wolfRadius);

        // Don't want to set the target if it's null because that would cause the wolves to stop attacking whatever
        // they were currently targeting... that's for a different behavior.
        if(target != null) {
            for (WolfEntity wolf : ownedWolves) {
                // Set attack target to be the selected entity from earlier
                wolf.setAttackTarget(target);
            }
        }
    }
}
