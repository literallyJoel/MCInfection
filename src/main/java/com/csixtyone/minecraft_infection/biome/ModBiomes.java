package com.csixtyone.minecraft_infection.biome;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> INFECTED_BIOME = register("infected_biome");

    private static ResourceKey<Biome> register(String name){
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MinecraftInfection.MOD_ID, name));
    }
}
