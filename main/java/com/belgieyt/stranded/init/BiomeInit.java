package com.belgieyt.stranded.init;

import com.belgieyt.stranded.Stranded;
import com.belgieyt.stranded.world.biome.BiomeMaker;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Stranded.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeInit {


    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Stranded.MOD_ID);

    public static final RegistryObject<Biome> VOLCANO =  createBiome("volcano", BiomeMaker::makeBasaltDeltasBiome);


    public static class Keys {
        public static final RegistryKey<Biome> VOLCANO_KEY = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Stranded.MOD_ID, "volcano"));

    }
    public static RegistryObject<Biome> createBiome(String name, Supplier<Biome> biome) {
        return BIOMES.register(name, biome);
    }

    public static void registerBiomes() {
        registerBiome(Keys.VOLCANO_KEY, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);


    }

    private static void registerBiome(RegistryKey<Biome> biome, BiomeDictionary.Type... types) {
        // the line below will make it spawn in the overworld
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
        BiomeDictionary.addTypes(biome, types);
    }
}

