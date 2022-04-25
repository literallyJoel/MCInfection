package com.csixtyone.minecraft_infection.world.feature;

import com.csixtyone.minecraft_infection.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_INFECTED_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.INFECTED_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_PUREDUST_ORES = List.of(
    OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PUREDUST_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> INFECTED_ORE = FeatureUtils.register("infected_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_INFECTED_ORES, 4));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PUREDUST_ORE = FeatureUtils.register("puredust_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_INFECTED_ORES, 7));
}
