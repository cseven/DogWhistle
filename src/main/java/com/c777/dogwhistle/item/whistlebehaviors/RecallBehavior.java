package com.c777.dogwhistle.item.whistlebehaviors;

import com.c777.dogwhistle.lib.LibBehaviors;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class RecallBehavior extends WhistleBehavior {
    @Override
    public String getTag() {
        return LibBehaviors.RECALL_BEHAVIOR;
    }

    @Override
    public TextFormatting getTextFormat() {
        return TextFormatting.AQUA;
    }

    @Override
    public void execute(World worldIn, PlayerEntity playerIn, Hand handIn, float wolfRadius) {
        for(WolfEntity wolf : getNearbyOwnedWolves(worldIn, playerIn, wolfRadius)) {
            // Sitting dogs should not get their attributes updated
            if(!wolf.isSitting()) {
                // For each wolf, cancel all current motions (attacking or movement) and redirect to where the player currently is
                wolf.setAttackTarget(null);
                wolf.getNavigator().clearPath();
                wolf.getNavigator().setPath(wolf.getNavigator().getPathToEntityLiving(playerIn, 0), 1.0f);
            }
        }
    }
}
