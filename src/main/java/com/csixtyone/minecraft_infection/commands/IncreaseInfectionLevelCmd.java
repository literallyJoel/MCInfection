package com.csixtyone.minecraft_infection.commands;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class IncreaseInfectionLevelCmd {
   //Defines the ingame command for increasing infection level
    public IncreaseInfectionLevelCmd(CommandDispatcher<CommandSourceStack> disp){
        disp.register(Commands.literal("infectionlevel").then(Commands.literal("add").then(Commands.argument("Increase Amount", IntegerArgumentType.integer(0, PlayerInfectionLevel.MAX_INFECTION_LEVEL))
                .executes(command -> increaseInfectionLevel(IntegerArgumentType.getInteger(command, "Increase Amount"), command)))));
    }

    private int increaseInfectionLevel(int amount, CommandContext<CommandSourceStack> command){
        int[] output = new int[1];
        try {
            ServerPlayer serverPlayer = command.getSource().getPlayerOrException();
            InfectionLevelHandler.increase(amount, serverPlayer);
            output[0] = InfectionLevelHandler.get(serverPlayer);
        } catch (CommandSyntaxException ignored) {
        }

        return output[0];
    }
}
