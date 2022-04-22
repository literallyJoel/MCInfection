package com.csixtyone.minecraft_infection.infection_system.data.client;

import com.csixtyone.minecraft_infection.infection_system.network.PacketDecreaseInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketIncreaseInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.network.PacketSetInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.setup.Messages;

public class InfectionLevelHandler {
    public static int get(){
        return ClientInfectionLevelData.getInfectionLevel();
    }

    public static void set(int amount){
        Messages.sendToServer(new PacketSetInfectionLevel(amount));
    }

    public static void increase(int amount){
        Messages.sendToServer(new PacketIncreaseInfectionLevel(amount));
    }

    public static void decrease(int amount){
        Messages.sendToServer(new PacketDecreaseInfectionLevel(amount));
    }
}
