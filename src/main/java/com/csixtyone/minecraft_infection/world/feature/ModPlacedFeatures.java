package com.csixtyone.minecraft_infection.world.feature;

import com.csixtyone.minecraft_infection.world.gen.ModOreGeneration;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> INFECTED_ORE_PLACED = PlacementUtils.register("infected_ore_placed",
            ModConfiguredFeatures.INFECTED_ORE, ModOrePlacement.commonOrePlacement(4, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> PUREDUST_ORE_PLACED = PlacementUtils.register("puredust_ore_placed",
            ModConfiguredFeatures.PUREDUST_ORE, ModOrePlacement.commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> INFECTED_DIAMOND_ORE_PLACED = PlacementUtils.register("infected_diamond_ore_placed",
            ModConfiguredFeatures.INFECTED_DIAMOND_ORE, ModOrePlacement.commonOrePlacement(7,
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> INFECTED_PLANT_ONE_PLACED = PlacementUtils.register("infected_plant_one_placed",
            VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> INFECTED_PLANT_TWO_PLACED = PlacementUtils.register("infected_plant_two_placed",
            VegetationFeatures.PATCH_TALL_GRASS, NoiseThresholdCountPlacement.of(-0.8D, 0, 7),
            RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> INFECTED_BIOME_GRASS = PlacementUtils.register("infected_biome_grass",
            ModConfiguredFeatures.INFECTED_PATCH_GRASS, NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());


}
