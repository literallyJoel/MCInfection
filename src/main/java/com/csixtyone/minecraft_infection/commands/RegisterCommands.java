package com.csixtyone.minecraft_infection.commands;

import com.csixtyone.minecraft_infection.infection_system.setup.InfectionLevelEvents;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.server.command.ConfigCommand;

//Registers custom commands with the game
public class RegisterCommands {

    public static void register(IEventBus bus){
        bus.addListener(InfectionLevelEvents::onPlayerAttack);
    }
    public static void onCommandsRegister(RegisterCommandsEvent e){
        new SetInfectionLevelCmd(e.getDispatcher());
        new DecreaseInfectionLevelCmd(e.getDispatcher());
        new IncreaseInfectionLevelCmd(e.getDispatcher());
        ConfigCommand.register(e.getDispatcher());

    }
}
