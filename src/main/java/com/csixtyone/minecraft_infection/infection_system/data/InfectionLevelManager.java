package com.csixtyone.minecraft_infection.infection_system.data;

import com.csixtyone.minecraft_infection.infection_system.network.PacketSyncInfectionLevelToClient;
import com.csixtyone.minecraft_infection.infection_system.setup.Messages;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.NotNull;

public class InfectionLevelManager {

    private int count = 0;
    public void tick(Level level){
        count--;
        if(count<=0){
            count = 10;

            level.players().forEach(player ->{
              if(player instanceof ServerPlayer serverPlayer){
                  int infectionLevel = serverPlayer.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL)
                          .map(PlayerInfectionLevel::get)
                          .orElse(-1);
                  Messages.sendToPlayer(serverPlayer, new PacketSyncInfectionLevelToClient(infectionLevel));
              }

            });
        }
    }
}
