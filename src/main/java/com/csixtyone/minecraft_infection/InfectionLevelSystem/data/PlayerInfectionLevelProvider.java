package com.csixtyone.minecraft_infection.InfectionLevelSystem.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerInfectionLevelProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerInfectionLevel> PLAYER_INFECTION_LEVEL = CapabilityManager.get(new CapabilityToken<>(){});
    private PlayerInfectionLevel playerInfectionLevel = null;
    private final LazyOptional<PlayerInfectionLevel> opt = LazyOptional.of(this::createPlayerInfection);

    @NotNull
    private PlayerInfectionLevel createPlayerInfection(){
        if(playerInfectionLevel == null){
            playerInfectionLevel = new PlayerInfectionLevel();
        }
        return playerInfectionLevel;
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }
    @Override

    @NotNull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == PLAYER_INFECTION_LEVEL) {
            return opt.cast();
        }
        return LazyOptional.empty();
    }
    @Override
    public CompoundTag serializeNBT(){
        CompoundTag nbt = new CompoundTag();
        createPlayerInfection().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt){
        createPlayerInfection().loadNBTData(nbt);
    }

}
