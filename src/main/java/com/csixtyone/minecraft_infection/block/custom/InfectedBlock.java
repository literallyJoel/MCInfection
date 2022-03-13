package com.csixtyone.minecraft_infection.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Random;

public class InfectedBlock extends Block {
    public InfectedBlock(Properties properties) {
        super(properties);
    }

    //implements purple particles for all infected blocks to show
        @Override
        public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {

        float chance  = 0.45f;
        //todo: implement custom particles (can't find clear resources on how to do so)
        if(chance < rand.nextFloat()){
            level.addParticle(ParticleTypes.PORTAL, pos.getX() + rand.nextDouble(), pos.getY() + 0.50, pos.getZ() + rand.nextDouble(), 0d, 0.05d, 0d);
        }

        super.animateTick(stateIn,level, pos, rand);
    }
}
