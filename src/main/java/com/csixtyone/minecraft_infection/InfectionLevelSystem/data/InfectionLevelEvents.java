package com.csixtyone.minecraft_infection.InfectionLevelSystem.data;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class InfectionLevelEvents {
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).isPresent()){
                event.addCapability(new ResourceLocation(MinecraftInfection.MOD_ID, "infectionlevel"), new PlayerInfectionLevelProvider());
            }
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerInfectionLevel.class);
    }
}
