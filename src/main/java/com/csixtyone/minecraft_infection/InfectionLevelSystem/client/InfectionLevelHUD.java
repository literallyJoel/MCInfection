package com.csixtyone.minecraft_infection.InfectionLevelSystem.client;

import com.csixtyone.minecraft_infection.InfectionLevelSystem.InfectionLevelConfig;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.gui.IIngameOverlay;

public class InfectionLevelHUD {
    public static ResourceLocation infectionHudBar = new ResourceLocation(MinecraftInfection.MOD_ID, "textures/gui/infection_meter.png");
    public static final IIngameOverlay INFECTION_LEVEL_HUD = (gui, poseStack, partialTicks, width, height) ->{
        Minecraft minecraft = Minecraft.getInstance();

        //Tells the renderer where to place the infection meter vertically on the screen
        /*We adjust it upwards a little if the player isn't at max air or wearing armour, so it doesn't overlap
          with the vanilla gui elements. It could just be this height all the time, but it looks awkward if there's a gap.*/

        //This places it just above the experience bar
        int yPos = minecraft.getWindow().getGuiScaledHeight()-50;
        //Check if the player is wearing any armour
        //TODO: see if this can be implemented as listener just when the armour slots are changed. Constantly checking seems inefficient.
        boolean wearingArmour = false;
        NonNullList<ItemStack> playerArmour = minecraft.player.getInventory().armor;
        for (ItemStack itemStack : playerArmour) {
            if (!itemStack.is(Items.AIR)) {
                wearingArmour = true;
            }
        }

        System.out.println(PlayerInfectionLevel.getInfectionLevel());
        if(wearingArmour || minecraft.player.getAirSupply() < minecraft.player.getMaxAirSupply()){
            //This places it just above the armour/oxygen bars
            yPos -=10;
        }

        //Stops the HUD from loading if the player is in creative or has the GUI disabled
        if(minecraft.options.hideGui || minecraft.player.isCreative()){
            return;
        }

        //Tells the render system to use the infection meter texture and not to colour it
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, infectionHudBar);
        //This renders the empty bar, and is set to 182 as the full width of the image should alwasy be present
        //pVoffset is set to 0 as it is the first texture in the file, 0 pixels from the top
        render(poseStack, yPos, 0, 182);

        /*This creates a percentage of the infection level, so we can scale the 1-100 infection level to the 182 pixels
        that would represent the full image of a fully infected player*/
        int infectionLevelPercent = (int) (182*(PlayerInfectionLevel.getInfectionLevel()/100f));

        /*This renders the level of infection in the infection meter. It just renders the image up to the percentage
          of the player infection. The pVOffset here is 7, as the texture for this is 7 pixels from the top in the file*/
        render(poseStack, yPos, 7, infectionLevelPercent);
    };

    /*This method is just because when rendering there are some parameters that we always keep the same
      So it's neater to use this to always define them rather the defining them every time.
      It also makes the code above more understandable as GuiComponent.blit isn't very descriptive.*/
    private static void render(PoseStack poseStack, int yPos, int pVoffset, int infectionLevel){
        //Centers the infection meter on the players screen
        int x = (Minecraft.getInstance().getWindow().getGuiScaledWidth()/2) - 91;
        GuiComponent.blit(poseStack, x, yPos, 0, pVoffset, infectionLevel, 5, 256, 256);
    }




}
