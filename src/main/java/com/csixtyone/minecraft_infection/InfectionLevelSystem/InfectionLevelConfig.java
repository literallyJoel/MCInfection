package com.csixtyone.minecraft_infection.InfectionLevelSystem;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class InfectionLevelConfig {
    public static ForgeConfigSpec.IntValue INFECTION_LEVEL_HUD_X;
    public static ForgeConfigSpec.IntValue INFECTION_LEVEL_HUD_Y;
    public static ForgeConfigSpec.IntValue INFECTION_HUD_COLOUR;

    private static void registerConfig(ForgeConfigSpec.Builder CLIENT_BUILDER){
        CLIENT_BUILDER.comment("Settings for the Infection Level System").push("infectionLevel");
        INFECTION_LEVEL_HUD_X = CLIENT_BUILDER.comment("X coord of the Infection Level HUD").defineInRange("infectionLevelHudX", 10, -1, Integer.MAX_VALUE);
        INFECTION_LEVEL_HUD_Y = CLIENT_BUILDER.comment("Y coord of Infection Level HUD").defineInRange("infectionLevelHudY", 10, -1, Integer.MAX_VALUE);
        INFECTION_HUD_COLOUR = CLIENT_BUILDER.comment("Colour of the Infection Level HUD").defineInRange("infectionLevelHudColour", 0x6100D5, Integer.MIN_VALUE, Integer.MAX_VALUE);
        CLIENT_BUILDER.pop();
    }

    public static void register(){
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        registerConfig(builder);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, builder.build());
    }
}
