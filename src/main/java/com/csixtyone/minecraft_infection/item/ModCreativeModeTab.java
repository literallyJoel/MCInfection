package com.csixtyone.minecraft_infection.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab INFECTION_TAB = new CreativeModeTab("infection_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.INFECTED_INGOT.get());
        }
    };
}
