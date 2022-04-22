package com.csixtyone.minecraft_infection.commands;

import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class DecreaseInfectionLevelCmd {
    //NOTE: The event listener for this and all others is infection_system.setup.InfectionLevelEvents
    public DecreaseInfectionLevelCmd(CommandDispatcher<CommandSourceStack> disp){
        disp.register(Commands.literal("infectionlevel").then(Commands.literal("remove").then(Commands.argument("Decrease Amount", IntegerArgumentType.integer(0, 100))
                .executes(command -> decreaseInfectionLevel(IntegerArgumentType.getInteger(command, "Decrease Amount"))))));
    }

    private int decreaseInfectionLevel(int amount){
        InfectionLevelHandler.decrease(amount);
        return InfectionLevelHandler.get();
    }
}
