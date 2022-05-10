package com.csixtyone.minecraft_infection.world.feature;

import com.csixtyone.minecraft_infection.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_INFECTED_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.INFECTED_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_PUREDUST_ORES = List.of(
    OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PUREDUST_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_INFECTED_DIAMOND_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.INFECTED_DIAMOND_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> INFECTED_ORE = FeatureUtils.register("infected_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_INFECTED_ORES, 4));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PUREDUST_ORE = FeatureUtils.register("puredust_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_PUREDUST_ORES, 7));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> INFECTED_DIAMOND_ORE = FeatureUtils.register("infected_diamond_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_INFECTED_DIAMOND_ORES, 4, 0.5F));

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> INFECTED_TREE =
            FeatureUtils.register("infected", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.INFECTED_LOGS.get()),
                    new StraightTrunkPlacer(5,6,3),
                    BlockStateProvider.simple(ModBlocks.INFECTED_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final Holder<PlacedFeature> INFECTED_TREE_CHECKED = PlacementUtils.register("infected_tree_checked", INFECTED_TREE);

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> INFECTED_PATCH_GRASS= FeatureUtils.register("infected_patch_grass", Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(ModBlocks.INFECTED_GRASS.get()), 32));


    private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
        return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
    }
}
