package com.csixtyone.minecraft_infection.util;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> INFECTABLE_BLOCKS = tag("infectable_blocks");

        private static TagKey<Block>tag(String name){
            return BlockTags.create(new ResourceLocation(MinecraftInfection.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }


    public static class Items{

    }
}
