package com.csixtyone.minecraft_infection.infection_system.data;

import net.minecraft.nbt.CompoundTag;

public class PlayerInfectionLevel {
    int infectionLevel;

    public int get(){
        return infectionLevel;
    }

    public void set(int infectionLevel){
        if(infectionLevel<0){
            this.infectionLevel =0;
        }else{
            this.infectionLevel = Math.min(100, infectionLevel);
        }
    }

    public void increase(int increaseBy){
        this.infectionLevel = Math.min(100, infectionLevel+increaseBy);
    }

    public void decrease(int decreaseBy){
        this.infectionLevel = Math.max(0, infectionLevel-decreaseBy);
    }

    public void copy(PlayerInfectionLevel s){
        infectionLevel = s.infectionLevel;
    }

    public void saveNBTData(CompoundTag tag){
        tag.putInt("infection_level", infectionLevel);
    }

    public void loadNBTData(CompoundTag tag){
        infectionLevel = tag.getInt("infection_level");
    }
}
