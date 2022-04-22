package com.csixtyone.minecraft_infection.commands;

import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class IncreaseInfectionLevelCmd {
    //NOTE: The event listener for this and all others is infection_system.setup.InfectionLevelEvents
    public IncreaseInfectionLevelCmd(CommandDispatcher<CommandSourceStack> disp){
        disp.register(Commands.literal("infectionlevel").then(Commands.literal("add").then(Commands.argument("Increase Amount", IntegerArgumentType.integer(0, 100))
                .executes(command -> increaseInfectionLevel(IntegerArgumentType.getInteger(command, "Increase Amount"))))));
    }

    private int increaseInfectionLevel(int amount){
        InfectionLevelHandler.increase(amount);
        return InfectionLevelHandler.get();
    }
}
