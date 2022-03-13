package com.csixtyone.minecraft_infection.block;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.custom.InfectedBlock;
import com.csixtyone.minecraft_infection.item.ModCreativeModeTab;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;



public class ModBlocks {
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
        return output;
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
            () -> new RedStoneOreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().randomTicks().lightLevel((state) -> 3).strength(3.0F, 3.0F)),
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


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
