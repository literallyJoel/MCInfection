package com.csixtyone.minecraft_infection.item;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //Creates the register for storing the newly created items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftInfection.MOD_ID);

    public static final RegistryObject<Item> INFECTED_INGOT = ITEMS.register("infected_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
