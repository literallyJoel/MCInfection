package com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.commands.SetInfectionLevelCmd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MinecraftInfection.MOD_ID)
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

    public static void onCommandsRegister(RegisterCommandsEvent event){
        new SetInfectionLevelCmd(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }
}
