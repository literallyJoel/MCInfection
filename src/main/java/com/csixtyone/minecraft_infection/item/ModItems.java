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

    public static final RegistryObject<Item> INFECTED_IRON_INGOT = ITEMS.register("infected_iron_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> INFECTED_GOLD_INGOT = ITEMS.register("infected_gold_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> INFECTED_COPPER_INGOT = ITEMS.register("infected_copper_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> INFECTED_DIAMOND = ITEMS.register("infected_diamond", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> INFECTED_EMERALD = ITEMS.register("infected_emerald", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> INFECTED_COAL = ITEMS.register("infected_coal", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> RAW_INFECTED_GOLD = ITEMS.register("raw_infected_gold", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> RAW_INFECTED_IRON = ITEMS.register("raw_infected_iron", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static final RegistryObject<Item> RAW_INFECTED_COPPER = ITEMS.register("raw_infected_copper", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
