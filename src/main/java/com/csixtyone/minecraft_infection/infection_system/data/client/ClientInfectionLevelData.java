package com.csixtyone.minecraft_infection.infection_system.data.client;

public class ClientInfectionLevelData {
    //This class just stores the infection level data for the player that has been synced from the server.
    private static int infectionLevel;

    public static void set(int newLevel){
        infectionLevel = newLevel;
    }

    public static int getInfectionLevel(){
        return infectionLevel;
    }
}
