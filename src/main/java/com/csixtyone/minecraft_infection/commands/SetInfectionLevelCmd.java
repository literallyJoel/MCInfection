package com.csixtyone.minecraft_infection.commands;


import com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data.PlayerInfectionLevel;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;


public class SetInfectionLevelCmd {

    public SetInfectionLevelCmd(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("infectionlevel").then(Commands.literal("set")).then(Commands.literal("0").executes(command -> setInfectionLevel(0))));
    }


    public static int setInfectionLevel(int infectionLevel) {
        PlayerInfectionLevel.setInfectionLevel(infectionLevel);
        return PlayerInfectionLevel.getInfectionLevel();
    }
}



