package com.csixtyone.minecraft_infection.block.custom;

import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class InfectedWater extends LiquidBlock{
    public InfectedWater(Supplier<? extends FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
    }



    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        //First we check if it's a player standing on the block
        if (pEntity instanceof Player) {
            if(!((Player) pEntity).isCreative()) {
                InfectionLevelHandler.increasePercent(1);
            }
        }
    }
    @Override
    public void animateTick(@NotNull BlockState stateIn, @NotNull Level level, @NotNull BlockPos pos, Random rand) {
        //implements purple particles for all infected blocks to show
        float particleChance = 0.45f;
        //todo: implement custom particles (can't find clear resources on how to do so)
        if (particleChance < rand.nextFloat()) {
            level.addParticle(ParticleTypes.PORTAL, pos.getX() + rand.nextDouble(), pos.getY() + 0.50, pos.getZ() + rand.nextDouble(), 0d, 0.05d, 0d);
        }
        super.animateTick(stateIn, level, pos, rand);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(pEntity instanceof ServerPlayer player){
            InfectionLevelHandler.increase(1);
        }
        super.entityInside(pState, pLevel, pPos, pEntity);

    }
}
