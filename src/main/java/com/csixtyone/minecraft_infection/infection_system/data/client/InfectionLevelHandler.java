package com.csixtyone.minecraft_infection.infection_system.data.client;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketDecreaseInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketIncreaseInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketSetInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.setup.Messages;

//This class just exists as a nicer way to interact with the infection level on the server without having to deal
//with sending messages
public class InfectionLevelHandler {
    public static boolean isSafe = false;

    public static int get(){
            return ClientInfectionLevelData.getInfectionLevel();
    }

    public static void set(int amount){
        if(isSafe) Messages.sendToServer(new PacketSetInfectionLevel(amount));

    }

    public static void increase(int amount){
        if(isSafe) Messages.sendToServer(new PacketIncreaseInfectionLevel(amount));

    }

    public static void decrease(int amount){
        if(isSafe) Messages.sendToServer(new PacketDecreaseInfectionLevel(amount));
    }

    //gets the value by percentage rather than the absolute value
    public static int getPercent(){
        float infectionLevel = (float)get();
        return (int)((infectionLevel/PlayerInfectionLevel.MAX_INFECTION_LEVEL)*100);
    }
    //increases by percentage rather than absolute value
    public static void increasePercent(int percent){
        int amount = (int)(PlayerInfectionLevel.MAX_INFECTION_LEVEL*(percent/100f));
        if(isSafe) Messages.sendToServer(new PacketIncreaseInfectionLevel(amount));
    }

    //decrease by percentage rather than absolute value
    public static void decreasePercent(int percent){
        int amount = (int)(PlayerInfectionLevel.MAX_INFECTION_LEVEL*(percent/100f));
        if(isSafe) Messages.sendToServer(new PacketDecreaseInfectionLevel(amount));
    }

    //sets to a percentage rather than absolute value
    public static void setPercent(int percent){
        int amount = (int)(PlayerInfectionLevel.MAX_INFECTION_LEVEL*(percent)/100f);
        if(isSafe) Messages.sendToServer(new PacketSetInfectionLevel(amount));
    }
}
