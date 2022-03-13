package com.csixtyone.minecraft_infection.block.custom;

import com.csixtyone.minecraft_infection.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InfectedBlock extends Block {

    public InfectedBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {
        //implements purple particles for all infected blocks to show
        float particleChance = 0.45f;
        //todo: implement custom particles (can't find clear resources on how to do so)
        if (particleChance < rand.nextFloat()) {
            level.addParticle(ParticleTypes.PORTAL, pos.getX() + rand.nextDouble(), pos.getY() + 0.50, pos.getZ() + rand.nextDouble(), 0d, 0.05d, 0d);
        }

        //implements infection spread
        //checks if level is client side, as if the level is client side it cannot make changes to the blocks
        if (!level.isClientSide()) {
            //sets chance of infection spreading
            float infectionChance = 0.05f;
            if (infectionChance < rand.nextFloat()) {
                //creates a hashmap of the surrounding blocks
                Map<BlockPos, BlockState> blockStates = new HashMap<>();
                blockStates.put(pos.north(), level.getBlockState(pos.north()));
                blockStates.put(pos.south(), level.getBlockState(pos.south()));
                blockStates.put(pos.east(), level.getBlockState(pos.east()));
                blockStates.put(pos.west(), level.getBlockState(pos.west()));

                //Loops through the blocks and has a chance of changing them to their infected form if compatible
                for (Map.Entry<BlockPos, BlockState> blockState : blockStates.entrySet()) {
                    Block block = blockState.getValue().getBlock();
                    if (ModBlocks.getInfectableBlocks().contains(block.getName().getString())) {
                        if (infectionChance < rand.nextFloat()) {
                            int direction = rand.nextInt(3);

                            switch (direction) {
                                case 0:

                            }
                        }
                    }
                }
            }
            super.animateTick(stateIn, level, pos, rand);
        }
    }

    private Block checkInfectedForm(Block block) {
        return block;
    }


}
