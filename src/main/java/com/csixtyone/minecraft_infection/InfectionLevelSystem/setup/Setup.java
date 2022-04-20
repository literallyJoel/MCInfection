package com.csixtyone.minecraft_infection.InfectionLevelSystem.setup;

import com.csixtyone.minecraft_infection.InfectionLevelSystem.client.InfectionLevelHUD;
import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

@Mod.EventBusSubscriber(modid = MinecraftInfection.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Setup {

    public static void init(FMLClientSetupEvent event){
        OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "InfectionLevel", InfectionLevelHUD.INFECTION_LEVEL_HUD);
    }
}
