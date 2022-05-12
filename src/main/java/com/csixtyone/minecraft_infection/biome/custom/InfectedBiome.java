package com.csixtyone.minecraft_infection.biome.custom;

import com.csixtyone.minecraft_infection.world.feature.ModConfiguredFeatures;
import com.csixtyone.minecraft_infection.world.feature.ModOrePlacement;
import com.csixtyone.minecraft_infection.world.feature.ModPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;

public class InfectedBiome{

    @Nullable
    public static final Music NORMAL_MUSIC = null;

    protected static int calculateSkyColour(float colour){
        float cl = colour/3.0f;
        cl = Mth.clamp(cl, -1.0f, 1.0f);
        return Mth.hsvToRgb(0.62222224F - cl * 0.05F, 0.5F + cl * 0.1F, 1.0F);
    }
    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return biome(precipitation, category, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }
    private static Biome biome(Biome.Precipitation precipitation, Biome.BiomeCategory category, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
    {
        return (new Biome.BiomeBuilder()).precipitation(precipitation).biomeCategory(category).temperature(temperature).downfall(downfall).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(calculateSkyColour(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome infectedBiome(){
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        addInfectedSpawns(biomeBuilder);
        BiomeDefaultFeatures.addFossilDecoration(biomeBuilder);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);



        return biome(Biome.Precipitation.NONE, Biome.BiomeCategory.PLAINS, 1F, 0.5F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
    }



    private static void addInfectedSpawns(BiomeGenerationSettings.Builder biomeBuilder){
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.INFECTED_ORE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.INFECTED_DIAMOND_ORE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.INFECTED_PLANT_ONE_PLACED);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.INFECTED_PLANT_TWO_PLACED);;
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.INFECTED_TREE_CHECKED);

    }
}
