package com.csixtyone.minecraft_infection.screen;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
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

        RenderSystem.setShaderTexture(0, new ResourceLocation("minecraft_infection:textures/gui/extras/dustframe.png"));
        this.blit(pPoseStack, this.leftPos + 25, this.topPos + 25, 0, 0, 16, 16, 16, 16);

        /*RenderSystem.setShaderTexture(0, new ResourceLocation("textures/gui/extras/dustframe.png"));
        this.blit(pPoseStack, this.leftPos + 72, this.topPos + 38, 0, 0, 16, 16, 16, 16);

        RenderSystem.setShaderTexture(0, new ResourceLocation("textures/gui/extras/off.png"));
        this.blit(pPoseStack, this.leftPos + 72, this.topPos + 55, 0, 0, 16, 16, 16, 16);

        RenderSystem.setShaderTexture(0, new ResourceLocation("textures/gui/extras/fluidempty.png"));
        this.blit(pPoseStack, this.leftPos + 149, this.topPos + 32, 0, 0, 16, 32, 16, 32);

        RenderSystem.setShaderTexture(0, new ResourceLocation("textures/gui/extras/fluidempty.png"));
        this.blit(pPoseStack, this.leftPos + 4, this.topPos + 31, 0, 0, 16, 32, 16, 32);*/

    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
