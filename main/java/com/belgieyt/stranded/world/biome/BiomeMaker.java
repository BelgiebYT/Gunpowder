package com.belgieyt.stranded.world.biome;

import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class BiomeMaker {
    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeBasaltDeltasBiome() {
        MobSpawnInfo mobspawninfo = (new MobSpawnInfo.Builder()).withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.GHAST, 40, 1, 1)).withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.MAGMA_CUBE, 100, 2, 5)).withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.STRIDER, 60, 1, 2)).build();
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.SHATTERED_SAVANNA).withStructure(StructureFeatures.RUINED_PORTAL_NETHER).withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE).withStructure(StructureFeatures.FORTRESS).withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.DELTA).withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SPRING_LAVA_DOUBLE).withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.SMALL_BASALT_COLUMNS).withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.LARGE_BASALT_COLUMNS).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.BASALT_BLOBS).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.BLACKSTONE_BLOBS).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.SPRING_DELTA).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.PATCH_FIRE).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.PATCH_SOUL_FIRE).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.GLOWSTONE_EXTRA).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.GLOWSTONE).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.BROWN_MUSHROOM_NETHER).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.RED_MUSHROOM_NETHER).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_MAGMA).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.SPRING_CLOSED_DOUBLE).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_GOLD_DELTAS).withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.ORE_QUARTZ_DELTAS);
        DefaultBiomeFeatures.withDebrisOre(biomegenerationsettings$builder);
        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.NETHER).depth(0.1F).scale(0.2F).temperature(2.0F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(4341314).setFogColor(6840176).withSkyColor(getSkyColorWithTemperatureModifier(2.0F)).setParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.118093334F)).setAmbientSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP).setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D)).setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D)).setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build()).withMobSpawnSettings(mobspawninfo).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }
}
