package com.csixtyone.minecraft_infection.item.custom;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class InfectedCoalItem extends Item {
        public InfectedCoalItem(Properties pProperties) {
            super(pProperties);
        }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
            // TODO Auto-generated method stub
            return 3200;
        }
    }
