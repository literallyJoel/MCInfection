package com.csixtyone.minecraft_infection.infection_system.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInfectionLevelProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerInfectionLevel> PLAYER_INFECTION_LEVEL = CapabilityManager.get(new CapabilityToken<PlayerInfectionLevel>() {});
    private PlayerInfectionLevel playerInfectionLevel = null;
    private final LazyOptional<PlayerInfectionLevel> opt = LazyOptional.of(this::createPlayerInfectionLevel);

    @NotNull
    private PlayerInfectionLevel createPlayerInfectionLevel(){
        if(playerInfectionLevel == null){
            playerInfectionLevel = new PlayerInfectionLevel();
        }
        return playerInfectionLevel;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap){
        if(cap == PLAYER_INFECTION_LEVEL){
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side){
        return getCapability(cap);
    }

    @Override
    public CompoundTag serializeNBT(){
        CompoundTag tag = new CompoundTag();
        createPlayerInfectionLevel().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag){
        createPlayerInfectionLevel().loadNBTData(tag);
    }
}
