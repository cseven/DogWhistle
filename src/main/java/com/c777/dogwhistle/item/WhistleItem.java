package com.c777.dogwhistle.item;

import com.c777.dogwhistle.item.whistlebehaviors.PacifyBehavior;
import com.c777.dogwhistle.item.whistlebehaviors.RecallBehavior;
import com.c777.dogwhistle.item.whistlebehaviors.TargetBehavior;
import com.c777.dogwhistle.item.whistlebehaviors.WhistleBehavior;
import com.c777.dogwhistle.sound.ModSounds;
import com.c777.dogwhistle.util.ItemNBTUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


public class WhistleItem extends Item {
    public WhistleItem(Properties properties) {
        super(properties);
    }

    private int WOLF_RANGE = 16;
    private static String SELECTED_BEHAVIOR_TAG = "selected_behavior";

    private static WhistleBehavior[] BEHAVIORS = {new TargetBehavior(), new PacifyBehavior(), new RecallBehavior()};

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if(playerIn.isSneaking()) {
            if(!worldIn.isRemote()) {
                cycleBehavior(stack);
            } else {
                playerIn.playSound(SoundEvents.BLOCK_DISPENSER_FAIL, 1.0f, .75f);
            }
        }
        else {
            if(!worldIn.isRemote()) {
                BEHAVIORS[getSelectedBehavior(stack)].execute(worldIn, playerIn, handIn, getRange());
            } else {
                float pitch = .75f + random.nextFloat() * .5f;
                playerIn.playSound(ModSounds.whistle, 1.0f, pitch);
            }
        }

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

    private static void cycleBehavior(ItemStack stack) {
        int selectedBehavior = (getSelectedBehavior(stack) + 1) % BEHAVIORS.length;
        setSelectedBehavior(stack, selectedBehavior);
    }

    private static int getSelectedBehavior(ItemStack stack) {
        return ItemNBTUtil.getInt(stack, SELECTED_BEHAVIOR_TAG, 0);
    }

    private static void setSelectedBehavior(ItemStack stack, int selectedBehavior) {
        ItemNBTUtil.setInt(stack, SELECTED_BEHAVIOR_TAG, selectedBehavior);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(stack.getTranslationKey(), new TranslationTextComponent(getBehaviorString(stack)).applyTextStyle(getBehaviorFormatting(stack)));
    }

    private static String getBehaviorString(ItemStack stack) {
        return "dogwhistle.behavior." + BEHAVIORS[getSelectedBehavior(stack)].getTag();
    }

    private static TextFormatting getBehaviorFormatting(ItemStack stack) {
        return BEHAVIORS[getSelectedBehavior(stack)].getTextFormat();
    }

    protected int getRange() {
        return this.WOLF_RANGE;
    }
}
