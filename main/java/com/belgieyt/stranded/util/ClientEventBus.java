package com.belgieyt.stranded.util;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.clients.renderer.GunpowderRenderer;
import com.belgieyt.stranded.clients.renderer.StrandedTraderRenderer;
import com.belgieyt.stranded.init.BiomeInit;
import com.belgieyt.stranded.init.ModEntityTypes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Stranded.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBus {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STRANDED_TRADER.get(), StrandedTraderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GUNPOWDER.get(), GunpowderRenderer::new);
    }
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        BiomeInit.registerBiomes();
    }
}