package com.csixtyone.minecraft_infection.block.custom;

import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PurifiedBlock extends Block {
    public PurifiedBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        //First we check if it's a player standing on the block
        if (pEntity instanceof Player player) {
            if(!player.isCreative()) {
                InfectionLevelHandler.decrease(50);
            }
        }
    }
}
