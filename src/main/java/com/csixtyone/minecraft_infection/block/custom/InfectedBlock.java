package com.csixtyone.minecraft_infection.block.custom;


import com.csixtyone.minecraft_infection.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class InfectedBlock extends Block {

    public InfectedBlock(Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void tick(BlockState blockState, ServerLevel level, BlockPos pos, Random rand) {

        //implements infection spread
        //checks if level is client side, as if the level is client side it cannot make changes to the blocks
        if (!level.isClientSide()) {
            //sets chance of infection spreading
            float infectionChance = 0.05f;
            if (infectionChance < rand.nextFloat()) {
                System.out.println("first if");
                //creates an array of the surrounding blocks
                BlockState[] blockStates = new BlockState[4];
                blockStates[0] = (level.getBlockState(pos.north()));
                blockStates[1] = (level.getBlockState(pos.south()));
                blockStates[2] = (level.getBlockState(pos.east()));
                blockStates[3] = (level.getBlockState(pos.west()));

                //creates an array list of the directions in which the infection can spread
                ArrayList<String> infectableDirections = new ArrayList<>();

                    for (int i = 0; i < blockStates.length; i++) {
                        System.out.println("first for");
                        System.out.println(checkInfectedForm(blockStates[i].getBlock()).getName());
                        if (checkInfectedForm(blockStates[i].getBlock()) != Blocks.ACACIA_BUTTON) {
                            System.out.println("second if");
                            switch (i) {
                                case 0 -> infectableDirections.add("North");
                                case 1 -> infectableDirections.add("South");
                                case 2 -> infectableDirections.add("East");
                                case 3 -> infectableDirections.add("West");
                            }
                        }
                    }


                //Chooses a direction and converts it into it's infected form
                if(infectableDirections.size() > 0) {
                    System.out.println("third if");
                    String direction = infectableDirections.get(rand.nextInt(infectableDirections.size() - 1));


                    System.out.println("chosen direction: " + direction);
                    switch (direction) {
                        case "North" -> level.setBlockAndUpdate(pos.north(), checkInfectedForm(level.getBlockState(pos.north()).getBlock()).defaultBlockState());
                        case "South" -> level.setBlockAndUpdate(pos.south(), checkInfectedForm(level.getBlockState(pos.south()).getBlock()).defaultBlockState());
                        case "East" -> level.setBlockAndUpdate(pos.east(), checkInfectedForm(level.getBlockState(pos.east()).getBlock()).defaultBlockState());
                        case "West" -> level.setBlockAndUpdate(pos.west(), checkInfectedForm(level.getBlockState(pos.west()).getBlock()).defaultBlockState());
                    }
                }




            }

        }
        super.tick(blockState, level, pos, rand);
    }

    @Override
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {
        //implements purple particles for all infected blocks to show
        float particleChance = 0.45f;
        //todo: implement custom particles (can't find clear resources on how to do so)
        if (particleChance < rand.nextFloat()) {
            level.addParticle(ParticleTypes.PORTAL, pos.getX() + rand.nextDouble(), pos.getY() + 0.50, pos.getZ() + rand.nextDouble(), 0d, 0.05d, 0d);
        }
        super.animateTick(stateIn, level, pos, rand);
    }

    //dictionary of keys on block setup?
    private Block checkInfectedForm(Block block) {
        Map<Block, Block> blockPairs = ModBlocks.getBlockPairs();
        if(blockPairs.get(block) != null){
            return blockPairs.get(block);
        }else{
            return Blocks.ACACIA_BUTTON;
        }


    }


}
