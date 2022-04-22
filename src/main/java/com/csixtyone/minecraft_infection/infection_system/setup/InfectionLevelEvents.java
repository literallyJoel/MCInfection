package com.csixtyone.minecraft_infection.infection_system.setup;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.commands.SetInfectionLevelCmd;
import com.csixtyone.minecraft_infection.infection_system.data.InfectionLevelManager;
import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.server.command.ConfigCommand;


public class InfectionLevelEvents {
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof Player) {
            if (!e.getObject().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).isPresent()) {
                e.addCapability(new ResourceLocation(MinecraftInfection.MOD_ID, "player_infection_level"), new PlayerInfectionLevelProvider());
            }
        }
    }

    public static void onPlayerCloned(PlayerEvent.Clone e) {
        if (!e.isWasDeath()) {
            e.getOriginal().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(xOld ->{
                e.getPlayer().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(xNew ->{
                    xNew.copy(xOld);
                });
            });
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent e){
        e.register(PlayerInfectionLevel.class);
    }

    public static void onWorldTick(TickEvent.WorldTickEvent e){
        if(!e.world.isClientSide){
            if(e.phase != TickEvent.Phase.START){
                InfectionLevelManager manager = new InfectionLevelManager();
                manager.tick(e.world);
            }
        }
    }




}
