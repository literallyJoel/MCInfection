package com.csixtyone.minecraft_infection.commands;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class IncreaseInfectionLevelCmd {
   //Defines the ingame command for increasing infection level
    public IncreaseInfectionLevelCmd(CommandDispatcher<CommandSourceStack> disp){
        disp.register(Commands.literal("infectionlevel").then(Commands.literal("add").then(Commands.argument("Increase Amount", IntegerArgumentType.integer(0, PlayerInfectionLevel.MAX_INFECTION_LEVEL))
                .executes(command -> increaseInfectionLevel(IntegerArgumentType.getInteger(command, "Increase Amount"))))));
    }

    private int increaseInfectionLevel(int amount){
        InfectionLevelHandler.increase(amount);
        return InfectionLevelHandler.get();
    }
}
