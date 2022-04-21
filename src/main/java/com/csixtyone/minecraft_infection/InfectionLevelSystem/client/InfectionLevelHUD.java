package com.csixtyone.minecraft_infection.InfectionLevelSystem.client;

import com.csixtyone.minecraft_infection.InfectionLevelSystem.InfectionLevelConfig;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data.PlayerInfectionLevel;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.IIngameOverlay;

public class InfectionLevelHUD {
    public static ResourceLocation infectionHudBar = new ResourceLocation("textures/hud/infection_level_hud.png");
    public static final IIngameOverlay INFECTION_LEVEL_HUD = (gui, poseStack, partialTicks, width, height) ->{
       /* String displayText = "Infection Level: " + PlayerInfectionLevel.getInfectionLevel() + "%";
        int x = InfectionLevelConfig.INFECTION_LEVEL_HUD_X.get();
        int y = InfectionLevelConfig.INFECTION_LEVEL_HUD_Y.get();
        if(x>= 0 && y>=0){
            gui.getFont().draw(poseStack, displayText, x, y, InfectionLevelConfig.INFECTION_HUD_COLOUR.get());
        }*/

        /*RenderSystem.setShaderColor(1F,1F,1F,1F);
        RenderSystem.setShaderTexture(0, infectionHudBar);
        GuiComponent.blit(poseStack, InfectionLevelConfig.INFECTION_LEVEL_HUD_X.get(), InfectionLevelConfig.INFECTION_LEVEL_HUD_Y.get(), 0, 0, 102, 5, 128, 256);
        GuiComponent.blit(poseStack, InfectionLevelConfig.INFECTION_LEVEL_HUD_X.get()+1, InfectionLevelConfig.INFECTION_LEVEL_HUD_Y.get()+1, 0, 5, 100, 3, 256, 256);

        RenderSystem.setShaderColor(96F, 0F, 213F, 255F);
        GuiComponent.blit(poseStack, InfectionLevelConfig.INFECTION_LEVEL_HUD_X.get()+1, InfectionLevelConfig.INFECTION_LEVEL_HUD_Y.get()+1, 0, 5, PlayerInfectionLevel.getInfectionLevel(), 3, 256, 256);
        RenderSystem.setShaderColor(1,1,1,1);*/
        int x = InfectionLevelConfig.INFECTION_LEVEL_HUD_X.get();
        int y = InfectionLevelConfig.INFECTION_LEVEL_HUD_Y.get();

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        render(poseStack, x, y, 0);

        RenderSystem.setShaderColor(97, 0, 213, 255);
        System.out.println(PlayerInfectionLevel.getInfectionLevel());
        render(poseStack, x+1, y+1, PlayerInfectionLevel.getInfectionLevel());

        RenderSystem.setShaderColor(1, 1,1, 1);
    };

    private static void render(PoseStack poseStack, int x, int y, int infectionLevel){
        GuiComponent.blit(poseStack, x, y, 0, 5, infectionLevel, 3, 256, 256);
    }




}
