package com.c777.dogwhistle.item;

import com.c777.dogwhistle.lib.LibItems;
import com.c777.dogwhistle.lib.LibMisc;
import com.c777.dogwhistle.setup.DogWhistleCreativeTab;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(LibMisc.MOD_ID)
public class ModItems {

    // Registry helper functions
    public static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> reg, IForgeRegistryEntry<V> entry, ResourceLocation location) {
        reg.register(entry.setRegistryName(location));
    }

    public static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> reg, IForgeRegistryEntry<V> entry, String name) {
        register(reg, entry, new ResourceLocation(LibMisc.MOD_ID, name));
    }
    // End registry helpers

    @ObjectHolder(LibItems.WHISTLE) public static Item dogWhistle;
    @ObjectHolder(LibItems.DIAMOND_WHISTLE) public static Item diamondWhistle;
    @ObjectHolder(LibItems.DOG_TREAT) public static Item dogTreat;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        Item.Properties defaultProperties = new Item.Properties().group(DogWhistleCreativeTab.INSTANCE);
        Item.Properties unstackable = new Item.Properties().group(DogWhistleCreativeTab.INSTANCE).maxStackSize(1);

        register(r, new WhistleItem(unstackable), LibItems.WHISTLE);
        register(r, new DiamondWhistleItem(unstackable), LibItems.DIAMOND_WHISTLE);
        register(r, new DogTreatItem(defaultProperties), LibItems.DOG_TREAT);
    }

}
