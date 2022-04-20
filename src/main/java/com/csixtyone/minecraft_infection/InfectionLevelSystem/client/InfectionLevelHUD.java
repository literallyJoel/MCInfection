package com.csixtyone.minecraft_infection.InfectionLevelSystem.client;

import com.csixtyone.minecraft_infection.InfectionLevelSystem.InfectionLevelConfig;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.data.PlayerInfectionLevel;
import net.minecraftforge.client.gui.IIngameOverlay;

public class InfectionLevelHUD {
    public static final IIngameOverlay INFECTION_LEVEL_HUD = (gui, poseStack, partialTicks, width, height) ->{
        String displayText = "Infection Level: " + PlayerInfectionLevel.getInfectionLevel() + "%";
        int x = InfectionLevelConfig.INFECTION_LEVEL_HUD_X.get();
        int y = InfectionLevelConfig.INFECTION_LEVEL_HUD_Y.get();
        if(x>= 0 && y>=0){
            gui.getFont().draw(poseStack, displayText, x, y, InfectionLevelConfig.INFECTION_HUD_COLOUR.get());
        }
    };
}
