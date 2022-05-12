package com.csixtyone.minecraft_infection.infection_system.data.client;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import com.csixtyone.minecraft_infection.infection_system.network.PacketDecreaseInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketIncreaseInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketSetInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.setup.Messages;
import net.minecraft.server.level.ServerPlayer;

//This class just exists as a nicer way to interact with the infection level on the server without having to deal
//with sending messages
public class InfectionLevelHandler {
;

    public static int get(ServerPlayer player){
            int[] infectionLevel = new int[1];
            player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(cap->{
                infectionLevel[0] = cap.get();
            });

            return  infectionLevel[0];
    }

    public static void set(int amount, ServerPlayer player){
        player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(cap->{
            cap.set(amount);
        });

    }

    public static void increase(int amount, ServerPlayer player){
        player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(cap ->{
            cap.increase(amount);
        });

    }

    public static void decrease(int amount, ServerPlayer player){
        player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(cap->{
            cap.decrease(amount);
        });
    }

    //gets the value by percentage rather than the absolute value
    public static int getPercent(ServerPlayer player){
        float infectionLevel = (float)get(player);
        return (int)((infectionLevel/PlayerInfectionLevel.MAX_INFECTION_LEVEL)*100);
    }
    //increases by percentage rather than absolute value
    public static void increasePercent(int percent, ServerPlayer player){
        int amount = (int)(PlayerInfectionLevel.MAX_INFECTION_LEVEL*(percent/100f));
        increase(amount, player);

    }

    //decrease by percentage rather than absolute value
    public static void decreasePercent(int percent, ServerPlayer player){
        int amount = (int)(PlayerInfectionLevel.MAX_INFECTION_LEVEL*(percent/100f));
        decrease(amount, player);
    }

    //sets to a percentage rather than absolute value
    public static void setPercent(int percent, ServerPlayer player){
        int amount = (int)(PlayerInfectionLevel.MAX_INFECTION_LEVEL*(percent)/100f);
        set(amount, player);
    }
}
