package com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data;

import net.minecraft.nbt.CompoundTag;

public class PlayerInfectionLevel {
    private static int infectionLevel;

    public PlayerInfectionLevel(){
        
    }
    public static int getInfectionLevel() {
        return infectionLevel;
    }

    public static void setInfectionLevel(int infectionLevel) {
        PlayerInfectionLevel.infectionLevel = infectionLevel;
    }

    public static void increaseInfectionLevel(int increaseAmount){
        //This seems odd, but it fixes a bug where the infection level always starts at 0 when loading into a world.
        int pInfectionLevel = PlayerInfectionLevel.getInfectionLevel();
        int newLevel = pInfectionLevel + increaseAmount;

        infectionLevel = Math.min(newLevel, 100);
    }

    public static void decreaseInfectionLevel(int decreaseAmount){
        //This seems odd, but it fixes a bug where the infection level always starts at 0 when loading into a world.
        int pInfectionLevel = PlayerInfectionLevel.getInfectionLevel();
        int newLevel = pInfectionLevel - decreaseAmount;

        infectionLevel = Math.max(newLevel, 0);
    }


    public void saveNBTData(CompoundTag tag){
        tag.putInt("infectionLevel", infectionLevel);
    }

    public void loadNBTData(CompoundTag tag){
        infectionLevel = tag.getInt("infectionLevel");
    }
}
