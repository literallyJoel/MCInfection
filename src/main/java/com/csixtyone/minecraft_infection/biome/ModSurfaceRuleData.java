package com.csixtyone.minecraft_infection.biome;

import com.csixtyone.minecraft_infection.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRuleData {
    private static final SurfaceRules.RuleSource INFECTED_DIRT = makeStateRule(ModBlocks.INFECTED_DIRT.get());
    private static final SurfaceRules.RuleSource INFECTED_GRASS_BLOCK = makeStateRule(ModBlocks.INFECTED_GRASS.get());
    private static final SurfaceRules.RuleSource INFECTED_STONE = makeStateRule(ModBlocks.INFECTED_COBBLESTONE.get());

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, INFECTED_GRASS_BLOCK), INFECTED_DIRT);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.INFECTED_BIOME), grassSurface),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, INFECTED_STONE)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
