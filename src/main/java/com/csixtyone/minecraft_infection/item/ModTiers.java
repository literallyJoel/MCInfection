package com.csixtyone.minecraft_infection.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier INFECTED = new ForgeTier(3,1400,1.5f,8f,20, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.INFECTED_INGOT.get()));

    public static final ForgeTier PURE = new ForgeTier(3,1800,1.5f,8f,20,BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.PURE_INGOT.get()));
}
