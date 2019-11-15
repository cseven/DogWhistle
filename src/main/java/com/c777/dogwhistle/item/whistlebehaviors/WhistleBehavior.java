package com.c777.dogwhistle.item.whistlebehaviors;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public abstract class WhistleBehavior {
    public abstract String getTag();
    public abstract TextFormatting getTextFormat();

    public abstract void execute(World worldIn, PlayerEntity playerIn, Hand handIn, float wolfRadius);

    protected List<WolfEntity> getNearbyOwnedWolves(World worldIn, PlayerEntity owner, float searchRange) {
        List<WolfEntity> ownedWolves = worldIn.getEntitiesWithinAABB(WolfEntity.class, new AxisAlignedBB(owner.getPosition()).grow(searchRange));
        ownedWolves.removeIf(p -> !(p.isTamed() && p.getOwner() == owner));
        return ownedWolves;
    }
}