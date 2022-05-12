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
import net.minecraft.world.entity.player.Player;

public class DecreaseInfectionLevelCmd {
    //Defines the ingame command for decreasing infection level
    public DecreaseInfectionLevelCmd(CommandDispatcher<CommandSourceStack> disp) {
        disp.register(Commands.literal("infectionlevel").then(Commands.literal("remove").then(Commands.argument("Decrease Amount", IntegerArgumentType.integer(0, PlayerInfectionLevel.MAX_INFECTION_LEVEL))
                .executes(command -> decreaseInfectionLevel(IntegerArgumentType.getInteger(command, "Decrease Amount"), command)))));
    }

    private int decreaseInfectionLevel(int amount, CommandContext<CommandSourceStack> command) {
        int[] output = new int[1];
        try {
            ServerPlayer serverPlayer = command.getSource().getPlayerOrException();
            InfectionLevelHandler.decrease(amount, serverPlayer);
            output[0] = InfectionLevelHandler.get(serverPlayer);
        } catch (CommandSyntaxException ignored) {
        }

        return output[0];
    }
}
