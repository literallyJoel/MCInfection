package com.csixtyone.minecraft_infection.InfectionLevelSystem.data;

import net.minecraft.nbt.CompoundTag;

public class PlayerInfectionLevel {
    private static int infectionLevel;

    public static int getInfectionLevel() {
        return infectionLevel;
    }

    public void setInfectionLevel(int infectionLevel) {
        PlayerInfectionLevel.infectionLevel = infectionLevel;
    }

    public void increaseInfectionLevel(int increaseAmount){
        if(infectionLevel + increaseAmount <= 100){
            infectionLevel += increaseAmount;
        }else{
            infectionLevel = 100;
        }
    }

    public void decreaseInfectionLevel(int decreaseAmount){
        if(infectionLevel - decreaseAmount >=0){
            infectionLevel -= decreaseAmount;
        }else{
            infectionLevel = 0;
        }
    }

    public void saveNBTData(CompoundTag tag){
        tag.putInt("infectionLevel", infectionLevel);
    }

    public void loadNBTData(CompoundTag tag){
        infectionLevel = tag.getInt("infectionLevel");
    }
}
