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

public class SetInfectionLevelCmd {
    //Implements ingame command for setting infection level
    public SetInfectionLevelCmd(CommandDispatcher<CommandSourceStack> disp) {
        disp.register(Commands.literal("infectionlevel").then(Commands.literal("set").then(Commands.argument("New Infection Level", IntegerArgumentType.integer(0, PlayerInfectionLevel.MAX_INFECTION_LEVEL)).executes(command -> setInfectionLevel(IntegerArgumentType.getInteger(command, "New Infection Level"), command)))));
    }

    private int setInfectionLevel(int newLevel, CommandContext<CommandSourceStack> command) {

        try {
            ServerPlayer serverPlayer = command.getSource().getPlayerOrException();
            InfectionLevelHandler.decrease(newLevel, serverPlayer);

        } catch (CommandSyntaxException ignored) {
        }

        return newLevel;
    }
}
