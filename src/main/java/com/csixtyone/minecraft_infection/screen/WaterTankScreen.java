package com.csixtyone.minecraft_infection.screen;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.entity.custom.WaterTankEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;


import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;

public class WaterTankScreen extends AbstractContainerScreen<WaterTankMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MinecraftInfection.MOD_ID,
            "textures/gui/water_tank.png");

    public WaterTankScreen(WaterTankMenu container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);

        final int count = this.menu.data.get(0);

        int scaledHeight = (int)(62*(count/5f));
        bindTexture();

        int pUOffset = 176;
        if(this.menu.blockEntity.getCurrentFluid() == Fluids.WATER){
            pUOffset = 207;
        }
        blit(stack, this.leftPos + 118, this.topPos + 75 - scaledHeight, pUOffset, 0, 30, scaledHeight);

        this.font.draw(stack, this.title, this.leftPos + 20, this.topPos + 5, 0x404040);
        this.font.draw(stack, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 75, 0x404040);
        this.font.draw(stack, count + "", this.leftPos + 105, this.topPos + 12, 0x404040);
    }

    @Override
    protected void renderBg(PoseStack stack, float mouseX, int mouseY, int partialTicks) {
        renderBackground(stack);
        bindTexture();
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
    }

    public static void bindTexture() {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
    }

    public static double mapNumber(double value, double rangeMin, double rangeMax, double resultMin, double resultMax) {
        return (value - rangeMin) / (rangeMax - rangeMin) * (resultMax - resultMin) + resultMin;
    }
}
