package com.csixtyone.minecraft_infection;


import com.csixtyone.minecraft_infection.InfectionLevelSystem.InfectionLevelConfig;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.data.InfectionLevelEvents;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.Setup;
import com.csixtyone.minecraft_infection.block.ModBlocks;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
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
        InfectionLevelConfig.register();
        eventBus.addListener(this::setup);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addGenericListener(Entity.class, InfectionLevelEvents::onAttachCapabilitiesPlayer);
        forgeBus.addListener(InfectionLevelEvents::onRegisterCapabilities);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(Setup:: init));
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


}
