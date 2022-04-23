package com.csixtyone.minecraft_infection.infection_system.data;

import net.minecraft.nbt.CompoundTag;

public class PlayerInfectionLevel {
    int infectionLevel;
    public static final int MAX_INFECTION_LEVEL = 1000;
    //Gets the infection level
    public int get(){
        return infectionLevel;
    }

    //Sets the infection level, keeps it between 0 and max at all times
    public void set(int infectionLevel){
        if(infectionLevel<0){
            this.infectionLevel =0;
        }else{
            this.infectionLevel = Math.min(100, infectionLevel);
        }
    }

    //Increases infection level by specified amount, capped at max
    public void increase(int increaseBy){
        this.infectionLevel = Math.min(MAX_INFECTION_LEVEL, infectionLevel+increaseBy);
    }

    //Decreases infection level by specified amount, caps at 0
    public void decrease(int decreaseBy){
        this.infectionLevel = Math.max(0, infectionLevel-decreaseBy);
    }

    //Copies the infection level from a provided infection level object to the current one
    public void copy(PlayerInfectionLevel s){
        infectionLevel = s.infectionLevel;
    }

    //Deals with saving and loading to disk
    public void saveNBTData(CompoundTag tag){
        tag.putInt("infection_level", infectionLevel);
    }

    public void loadNBTData(CompoundTag tag){
        infectionLevel = tag.getInt("infection_level");
    }
}
