package com.c777.dogwhistle.item.whistlebehaviors;

import com.c777.dogwhistle.lib.LibBehaviors;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class PacifyBehavior extends WhistleBehavior {

    @Override
    public String getTag() {
        return LibBehaviors.PACIFY_BEHAVIOR;
    }

    @Override
    public TextFormatting getTextFormat() {
        return TextFormatting.LIGHT_PURPLE;
    }

    @Override
    public void execute(World worldIn, PlayerEntity playerIn, Hand handIn, float wolfRadius) {
        for(WolfEntity wolf : getNearbyOwnedWolves(worldIn, playerIn, wolfRadius)) {
            // Setting the attack target to null will cancel their current attack
            wolf.setAttackTarget(null);
        }
    }
}
