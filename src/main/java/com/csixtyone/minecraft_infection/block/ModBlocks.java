package com.csixtyone.minecraft_infection.block;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.custom.InfectedBlock;
import com.csixtyone.minecraft_infection.block.custom.PurifiedBlock;
import com.csixtyone.minecraft_infection.block.custom.PurifierStationBlock;
import com.csixtyone.minecraft_infection.block.custom.WaterTank;
import com.csixtyone.minecraft_infection.item.ModCreativeModeTab;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;



public class ModBlocks {
    private static Map<String, String> blockPairs = new HashMap<>();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MinecraftInfection.MOD_ID);

    //Method used to register the item variant of a block. The item variant is used pretty much any time the block isn't placed
    //e.g. crafting, in the inventory etc.
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    //This method is used to register the custom block into the game, specifying the name, tab (for the creative menu), and register.
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> output = BLOCKS.register(name, block);
        registerBlockItem(name, output, tab);

        /*Creates a dictionary of the infected ores and their original counterparts for use in the infection spread
        code later*/
        String originalOre = "block.minecraft." + name.replace("infected_", "");
        if(!originalOre.equals("block.minecraft.ore")){
            System.out.println("Adding dictionary pair: " + originalOre + ", " + name);
            blockPairs.put(originalOre, name);
        }



        return output;
    }

    public static Map<String, String>getBlockPairs() {
        return blockPairs;
    }

    //Custom block definitions

    //Infected Gold Ore
    public static final RegistryObject<Block> INFECTED_GOLD_ORE = registerBlock("infected_gold_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);


    //Infected Diamond Ore
    public static final RegistryObject<Block> INFECTED_DIAMOND_ORE = registerBlock("infected_diamond_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Iron Ore
    public static final RegistryObject<Block> INFECTED_IRON_ORE = registerBlock("infected_iron_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Copper Ore
    public static final RegistryObject<Block> INFECTED_COPPER_ORE = registerBlock("infected_copper_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Lapis Ore
    public static final RegistryObject<Block> INFECTED_LAPIS_ORE = registerBlock("infected_lapis_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Redstone Ore
    public static final RegistryObject<Block> INFECTED_REDSTONE_ORE = registerBlock("infected_redstone_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().randomTicks().lightLevel((state) -> 3).strength(3.0F, 3.0F)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Coal Ore
    public static final RegistryObject<Block> INFECTED_COAL_ORE = registerBlock("infected_coal_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Emerald Ore
    public static final RegistryObject<Block> INFECTED_EMERALD_ORE = registerBlock("infected_emerald_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.EMERALD_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Ore
    public static final RegistryObject<Block> INFECTED_ORE = registerBlock("infected_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 5).strength(15f)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected CobbleStone
    public static final RegistryObject<Block> INFECTED_COBBLESTONE = registerBlock("infected_cobblestone",
            () -> new InfectedBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().lightLevel((state) -> 5).strength(9F)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Deepslate Gold Ore
    public static final RegistryObject<Block>   INFECTED_DEEPSLATE_GOLD_ORE = registerBlock("infected_deepslate_gold_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 5).strength(7F)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Deepslate Iron Ore
    public static final RegistryObject<Block> INFECTED_DEEPSLATE_IRON_ORE = registerBlock("infected_deepslate_iron_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().lightLevel((state) -> 5).strength(9F)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Deepslate RedStone Ore
    public static final RegistryObject<Block> INFECTED_DEEPSLATE_REDSTONE_ORE = registerBlock("infected_deepslate_redstone_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().lightLevel((state) -> 5).strength(8F)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Deepslate Lapis Ore
    public static final RegistryObject<Block> INFECTED_DEEPSLATE_LAPIS_ORE = registerBlock("infected_deepslate_lapis_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().lightLevel((state) -> 5).strength(8F)),
            ModCreativeModeTab.INFECTION_TAB);

    //PureDust ore
    public static final RegistryObject<Block> PUREDUST_ORE = registerBlock("puredust_ore",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.REDSTONE_ORE).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Puredust Block
    public static final RegistryObject<Block> PUREDUST_BLOCK = registerBlock("puredust_block",
            () -> new PurifiedBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 3)),
            ModCreativeModeTab.INFECTION_TAB);

    //Infected Block
    public static final RegistryObject<Block> INFECTED_BLOCK = registerBlock("infected_block",
            () -> new InfectedBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 5)),
            ModCreativeModeTab.INFECTION_TAB);

    //Broken Purifier Block
    public static final RegistryObject<Block> BROKEN_PURIFIER = registerBlock("broken_purifier",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.INFECTION_TAB);

    //Broken Purifier Input Block
    public static final RegistryObject<Block> BROKEN_PURIFIER_INPUT = registerBlock("broken_purifier_input",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.INFECTION_TAB);

    //Broken Purifier Output Block
    public static final RegistryObject<Block> BROKEN_PURIFIER_OUTPUT = registerBlock("broken_purifier_output",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.INFECTION_TAB);

    //Purifier Block
    public static final RegistryObject<Block> PURIFIER = registerBlock("purifier",
            () -> new PurifierStationBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.INFECTION_TAB);

    //Purifier Input Block
    public static final RegistryObject<Block> PURIFIER_INPUT = registerBlock("purifier_input",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.INFECTION_TAB);

    //Purifier Output Block
    public static final RegistryObject<Block> PURIFIER_OUTPUT = registerBlock("purifier_output",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.INFECTION_TAB);

    //Water Tank
    public static final RegistryObject<Block> WATER_TANK = registerBlock("water_tank",
            () -> new WaterTank(BlockBehaviour.Properties.of(Material.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 2)),
            ModCreativeModeTab.INFECTION_TAB);
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
