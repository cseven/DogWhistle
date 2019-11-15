package com.c777.dogwhistle.sound;

import com.c777.dogwhistle.lib.LibMisc;
import com.c777.dogwhistle.lib.LibSounds;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSounds {

    private static ResourceLocation whistle_location = new ResourceLocation(LibMisc.MOD_ID, LibSounds.WHISTLE_SOUND);

    public static final SoundEvent whistle = new SoundEvent(whistle_location).setRegistryName(whistle_location);

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> r = event.getRegistry();
        r.register(whistle);
    }

}
