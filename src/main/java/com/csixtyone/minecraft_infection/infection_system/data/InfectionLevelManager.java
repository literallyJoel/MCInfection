package com.csixtyone.minecraft_infection.infection_system.data;

import com.csixtyone.minecraft_infection.infection_system.network.PacketSyncInfectionLevelToClient;
import com.csixtyone.minecraft_infection.infection_system.setup.Messages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class InfectionLevelManager {

    //Count is used to limit how often the tick code runs because once per tick is more than we need
    private int count = 0;
    public void tick(Level level){
        count--;
        if(count<=0){
            count = 10;

            //Goes through every player currently connected to the server, gets their infection level, and syncs it to their clients
            //Lets the client know that the server is loaded and ready to receive packets
            level.players().stream().filter(player -> player instanceof ServerPlayer).map(player -> (ServerPlayer) player).forEach(serverPlayer -> {
                int infectionLevel = serverPlayer.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL)
                        .map(PlayerInfectionLevel::get)
                        .orElse(-1);
                Messages.sendToPlayer(serverPlayer, new PacketSyncInfectionLevelToClient(infectionLevel));
            });
        }


    }
}
