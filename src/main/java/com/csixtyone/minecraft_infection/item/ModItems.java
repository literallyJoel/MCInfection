package com.csixtyone.minecraft_infection.item;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.item.custom.InfectedCoalItem;
import net.minecraft.world.item.*;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {


    //Creates the register for storing the newly created items
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftInfection.MOD_ID);

    //Infected Ingot
    public static final RegistryObject<Item> INFECTED_INGOT = ITEMS.register("infected_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));


    //Infected Iron Ingot
    public static final RegistryObject<Item> INFECTED_IRON_INGOT = ITEMS.register("infected_iron_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Gold Ingot
    public static final RegistryObject<Item> INFECTED_GOLD_INGOT = ITEMS.register("infected_gold_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Copper Ingot
    public static final RegistryObject<Item> INFECTED_COPPER_INGOT = ITEMS.register("infected_copper_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Diamond
    public static final RegistryObject<Item> INFECTED_DIAMOND = ITEMS.register("infected_diamond", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Emerald
    public static final RegistryObject<Item> INFECTED_EMERALD = ITEMS.register("infected_emerald", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Coal
    public static final RegistryObject<Item> INFECTED_COAL = ITEMS.register("infected_coal", () -> new InfectedCoalItem(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Redstone
    public static final RegistryObject<Item> INFECTED_REDSTONE = ITEMS.register("infected_redstone", () -> new InfectedCoalItem(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Lapis Lazuli
    public static final RegistryObject<Item> INFECTED_LAPIS_LAZULI = ITEMS.register("infected_lapis_lazuli", () -> new InfectedCoalItem(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Raw Infected Gold
    public static final RegistryObject<Item> RAW_INFECTED_GOLD = ITEMS.register("raw_infected_gold", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Raw Infected Iron
    public static final RegistryObject<Item> RAW_INFECTED_IRON = ITEMS.register("raw_infected_iron", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Raw Infected Copper
    public static final RegistryObject<Item> RAW_INFECTED_COPPER = ITEMS.register("raw_infected_copper", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Puredust
    public static final RegistryObject<Item> PUREDUST = ITEMS.register("puredust", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Pure Ingot
    public static final RegistryObject<Item> PURE_INGOT = ITEMS.register("pure_ingot", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Pure Sword
    public static final RegistryObject<Item> PURE_SWORD = ITEMS.register("pure_sword", () -> new SwordItem(ModTiers.PURE,2,3f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Sword
    public static final RegistryObject<Item> INFECTED_SWORD = ITEMS.register("infected_sword", () -> new SwordItem(ModTiers.INFECTED,4,3f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Pure Pickaxe
    public static final RegistryObject<Item> PURE_PICKAXE = ITEMS.register("pure_pickaxe", () -> new PickaxeItem(ModTiers.PURE,1,1f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Pickaxe
    public static final RegistryObject<Item> INFECTED_PICKAXE = ITEMS.register("infected_pickaxe", () -> new PickaxeItem(ModTiers.INFECTED,1,1f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Pure Axe
    public static final RegistryObject<Item> PURE_AXE = ITEMS.register("pure_axe", () -> new AxeItem(ModTiers.PURE,5,1f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Axe
    public static final RegistryObject<Item> INFECTED_AXE = ITEMS.register("infected_axe", () -> new AxeItem(ModTiers.INFECTED,4,0f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Pure Shovel
    public static final RegistryObject<Item> PURE_SHOVEL = ITEMS.register("pure_shovel", () -> new ShovelItem(ModTiers.PURE,0,2f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Shovel
    public static final RegistryObject<Item> INFECTED_SHOVEL = ITEMS.register("infected_shovel", () -> new ShovelItem(ModTiers.INFECTED,0,1f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Pure Hoe
    public static final RegistryObject<Item> PURE_HOE = ITEMS.register("pure_hoe", () -> new HoeItem(ModTiers.PURE,0,0f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    //Infected Hoe
    public static final RegistryObject<Item> INFECTED_HOE = ITEMS.register("infected_hoe", () -> new HoeItem(ModTiers.INFECTED,0,0f, new Item.Properties().tab(ModCreativeModeTab.INFECTION_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
