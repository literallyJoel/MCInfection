package com.csixtyone.minecraft_infection.screen;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PurifierScreen extends AbstractContainerScreen<PurifierMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MinecraftInfection.MOD_ID, "textures/gui/purifier_gui.png");

    public PurifierScreen(PurifierMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        if(menu.InputWaterLevel() > 3){
            blit(pPoseStack, x + 7, y + 29, 176, 20, 12, 27);
        }
        else if(menu.InputWaterLevel() == 2){
            blit(pPoseStack, x + 7, y + 36, 176, 29, 12, 20);
        }else if(menu.InputWaterLevel() == 1){
            blit(pPoseStack, x + 7, y + 47, 176, 38, 12, 9);
        }
        if (menu.OutputWaterLevel() > 3){
            blit(pPoseStack, x + 146, y+29,176, 50, 12,27 );
        }else if (menu.OutputWaterLevel() == 2){
            blit(pPoseStack, x + 146, y+36,176, 57, 12,20 );
        }else if (menu.OutputWaterLevel() == 1){
            blit(pPoseStack, x + 146, y+47,176, 70, 12,9 );
        }


        if(menu.isCrafting()) {
            blit(pPoseStack, x + 85, y + 33, 176, 0, menu.getScaledProgress(), 18);
            blit(pPoseStack, x + 64, y + 55, 176, 82,12, 12);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
