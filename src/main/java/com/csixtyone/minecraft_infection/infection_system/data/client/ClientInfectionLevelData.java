package com.csixtyone.minecraft_infection.infection_system.data.client;

public class ClientInfectionLevelData {
    private static int infectionLevel;

    public static void set(int newLevel){
        infectionLevel = newLevel;
    }

    public static int getInfectionLevel(){
        return infectionLevel;
    }
}
