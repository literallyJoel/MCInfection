package com.csixtyone.minecraft_infection.commands;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.server.command.ConfigCommand;

public class RegisterCommands {
    public static void onCommandsRegister(RegisterCommandsEvent e){
        new SetInfectionLevelCmd(e.getDispatcher());
        new DecreaseInfectionLevelCmd(e.getDispatcher());
        new IncreaseInfectionLevelCmd(e.getDispatcher());
        ConfigCommand.register(e.getDispatcher());

    }
}
