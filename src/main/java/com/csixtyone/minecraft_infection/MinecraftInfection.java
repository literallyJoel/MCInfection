package com.csixtyone.minecraft_infection;


import com.csixtyone.minecraft_infection.block.ModBlocks;
import com.csixtyone.minecraft_infection.block.entity.ModBlockEntities;
import com.csixtyone.minecraft_infection.commands.RegisterCommands;
import com.csixtyone.minecraft_infection.fluid.ModFluids;
import com.csixtyone.minecraft_infection.effect.ModEffects;
import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionEffects;
import com.csixtyone.minecraft_infection.infection_system.setup.InfectionLevelEvents;
import com.csixtyone.minecraft_infection.infection_system.setup.ClientSetup;
import com.csixtyone.minecraft_infection.infection_system.setup.Messages;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import com.csixtyone.minecraft_infection.potion.ModPotions;
import com.csixtyone.minecraft_infection.recipe.ModRecipes;
import com.csixtyone.minecraft_infection.screen.ModMenuTypes;
import com.csixtyone.minecraft_infection.screen.PurifierScreen;
import com.csixtyone.minecraft_infection.util.BetterBrewingRecipe;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftInfection.MOD_ID)
public class MinecraftInfection {
    //Set up constant for the MOD ID to prevent mistyping later.
    public static final String MOD_ID = "minecraft_infection";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();


    public MinecraftInfection() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Registers the custom blocks and items as implemented in the ModBlocks and ModItems classes.
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModFluids.register(eventBus);
        ModEffects.register(eventBus);
        ModPotions.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.register(this);
        Messages.register();
        forgeBus.addGenericListener(Entity.class, InfectionLevelEvents::onAttachCapabilitiesPlayer);
        forgeBus.addListener(InfectionLevelEvents::onPlayerCloned);
        forgeBus.addListener(InfectionLevelEvents::onRegisterCapabilities);
        forgeBus.addListener(InfectionLevelEvents::onWorldTick);
        forgeBus.addListener(RegisterCommands::onCommandsRegister);
        forgeBus.addListener(InfectionEffects::onPlayerTick);
        forgeBus.addListener(InfectionLevelEvents::onPlayerAttack);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(ClientSetup:: init));
    }


    private void clientSetup(final FMLClientSetupEvent e){
        ItemBlockRenderTypes.setRenderLayer(ModFluids.INFECTED_WATER_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.INFECTED_WATER_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.INFECTED_WATER_FLOWING.get(), RenderType.translucent());
        MenuScreens.register(ModMenuTypes.PURIFIER_MENU.get(), PurifierScreen::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURIFIER.get(), RenderType.translucent());
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                ModItems.PUREDUST.get(), ModPotions.PURIFY_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD,
                ModItems.INFECTED_INGOT.get(), ModPotions.INFECTION_POTION.get()));
    }


}
