package com.csixtyone.minecraft_infection.block.custom;


import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

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

        //todo: Implement infecting mobs once infected mobs are implemented
        //implements infection spread
        //checks if level is client side, as if the level is client side it cannot make changes to the blocks
        if (!level.isClientSide()) {
            //sets chance of infection spreading to 5%
            float infectionChance = 0.05f;
            if (infectionChance > rand.nextFloat()) {
                System.out.println("first if");
                //creates an array of the surrounding blocks
                BlockState[] blockStates = new BlockState[6];
                blockStates[0] = (level.getBlockState(pos.north()));
                blockStates[1] = (level.getBlockState(pos.south()));
                blockStates[2] = (level.getBlockState(pos.east()));
                blockStates[3] = (level.getBlockState(pos.west()));
                blockStates[4] = (level.getBlockState(pos.above()));
                blockStates[5] = (level.getBlockState(pos.below()));

                //creates an array list of the directions in which the infection can spread
                ArrayList<Integer> infectableDirections = new ArrayList<>();

                for (int i = 0; i < blockStates.length; i++) {
                        /*checks if the given block has an infected form. Always returns ACACIA_BUTTON if it doesn't
                        so this is used as our fail case */
                    if (checkInfectedForm(blockStates[i].getBlock()) != Blocks.ACACIA_BUTTON) {
                        //if it is infectable, it adds the current position in the loop to the infectable directions array

                          /*
                            0 - North
                            1 - South
                            2 - East
                            3 - West
                            4 - Above
                            5 - Below
                         */
                        infectableDirections.add(i);
                    }
                }


                //Chooses a direction and converts it into it's infected form

                //Checks if there are any infectable blocks
                if (infectableDirections.size() > 0) {
                    //Selects a random item from the list of infectable blocks
                    int direction = infectableDirections.get(rand.nextInt(infectableDirections.size()));
                    switch (direction) {
                        //checks the direction given, checks the block in that position and replaces it with its infected form



                        //at this point we've already checked if it can be infected, so we just assume it can be and update the block
                        case 0 -> level.setBlockAndUpdate(pos.north(), checkInfectedForm(level.getBlockState(pos.north()).getBlock()).defaultBlockState());
                        case 1 -> level.setBlockAndUpdate(pos.south(), checkInfectedForm(level.getBlockState(pos.south()).getBlock()).defaultBlockState());
                        case 2 -> level.setBlockAndUpdate(pos.east(), checkInfectedForm(level.getBlockState(pos.east()).getBlock()).defaultBlockState());
                        case 3 -> level.setBlockAndUpdate(pos.west(), checkInfectedForm(level.getBlockState(pos.west()).getBlock()).defaultBlockState());
                        case 4 -> level.setBlockAndUpdate(pos.above(), checkInfectedForm(level.getBlockState(pos.above()).getBlock()).defaultBlockState());
                        case 5 -> level.setBlockAndUpdate(pos.below(), checkInfectedForm(level.getBlockState(pos.below()).getBlock()).defaultBlockState());

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

    //Checks the dictionary to see if the given block has an infected form. Returns the infected form if it exists.
    //If it doesn't exist it returns Acacia Button, which is then used as the fail case, meaning no infected form
    private Block checkInfectedForm(Block block) {
        //grabs the dictionary of blocks and their infected forms
        Map<String, String> blockPairs = ModBlocks.getBlockPairs();
        //Tries to grab the item for the given block ID
        if (blockPairs.get(block.getDescriptionId()) != null) {
            //if it doesn't return null, the item has an infected form which is returned
            return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(MinecraftInfection.MOD_ID, blockPairs.get(block.getDescriptionId())));
        } else {
            //if it does return null, the block doesn't have an infected form. It will return ACACIA_BUTTON as the known fail case
            return Blocks.ACACIA_BUTTON;
        }


    }


}
