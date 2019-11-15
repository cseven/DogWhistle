package com.c777.dogwhistle.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemNBTUtil {

    public static void setBoolean(ItemStack stack, String tag, boolean value) {
        stack.getOrCreateTag().putBoolean(tag, value);
    }

    public static void setInt(ItemStack stack, String tag, int value) {
        stack.getOrCreateTag().putInt(tag, value);
    }

    public static void setFloat(ItemStack stack, String tag, float value) {
        stack.getOrCreateTag().putFloat(tag, value);
    }

    public static void setCompound(ItemStack stack, String tag, CompoundNBT compound) {
        stack.getOrCreateTag().put(tag, compound);
    }

    private static boolean hasTag(ItemStack stack, String tag) {
        return !stack.isEmpty() && stack.getOrCreateTag().contains(tag);
    }

    public static boolean getBoolean(ItemStack stack, String tag, boolean fallback) {
        return hasTag(stack, tag) ? stack.getOrCreateTag().getBoolean(tag) : fallback;
    }

    public static int getInt(ItemStack stack, String tag, int fallback) {
        return hasTag(stack, tag) ? stack.getOrCreateTag().getInt(tag) : fallback;
    }

    public static float getFloat(ItemStack stack, String tag, int fallback) {
        return hasTag(stack, tag) ? stack.getOrCreateTag().getFloat(tag) : fallback;
    }

    public static CompoundNBT getCompound(ItemStack stack, String tag) {
        return hasTag(stack, tag) ? stack.getOrCreateTag().getCompound(tag) : null;
    }

}
