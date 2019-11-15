package com.c777.dogwhistle.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

import java.util.Random;

public class DogTreatItem extends Item {
    public DogTreatItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entityIn, Hand handIn) {
        if(entityIn instanceof WolfEntity && !(((WolfEntity) entityIn).isTamed())) {
            if (!playerIn.world.isRemote()) {
                if (!playerIn.isCreative()) {
                    stack.shrink(1);
                }
                ((WolfEntity) entityIn).setTamedBy(playerIn);
            }
            else {
                doTameEffect(entityIn);
            }
            return true;
        }
        return false;
    }

    private void doTameEffect(LivingEntity entityIn) {
        IParticleData iparticledata = ParticleTypes.HEART;
        Random rand = new Random();
        for(int i = 0; i < 7; ++i) {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            entityIn.world.addParticle(iparticledata, entityIn.posX + (double)(rand.nextFloat() * entityIn.getWidth() * 2.0F) - (double)entityIn.getWidth(), entityIn.posY + 0.5D + (double)(rand.nextFloat() * entityIn.getHeight()), entityIn.posZ + (double)(rand.nextFloat() * entityIn.getWidth() * 2.0F) - (double)entityIn.getWidth(), d0, d1, d2);
        }
    }
}
