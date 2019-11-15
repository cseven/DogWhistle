package com.c777.dogwhistle.setup;

import com.c777.dogwhistle.item.ModItems;
import com.c777.dogwhistle.lib.LibMisc;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class DogWhistleCreativeTab extends ItemGroup {
    public static final DogWhistleCreativeTab INSTANCE = new DogWhistleCreativeTab();

    private DogWhistleCreativeTab() {
        super(LibMisc.MOD_ID);
    }

    @Override
    @Nonnull
    public ItemStack createIcon() {
        return new ItemStack(ModItems.dogWhistle);
    }
}
