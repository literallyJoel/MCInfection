package com.csixtyone.minecraft_infection.block.custom;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.InfectionLevelSystem.setup.data.PlayerInfectionLevelProvider;
import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.ModBlocks;
import com.csixtyone.minecraft_infection.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class InfectedBlock extends Block {

    public InfectedBlock(Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState p_49921_) {
        return true;
    }

    @Override
    public void tick(@NotNull BlockState blockState, ServerLevel level, @NotNull BlockPos pos, @NotNull Random rand) {

        //todo: Implement infecting mobs once infected mobs are implemented
        //implements infection spread
        //checks if level is client side, as if the level is client side it cannot make changes to the blocks
        if (!level.isClientSide()) {
            //sets chance of infection spreading to 5%
            float infectionChance = 0.05f;
            if (infectionChance > rand.nextFloat()) {
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
                    //checks if the given block has an infected form.
                    if (isInfectable(blockStates[i])) {
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
                        case 0 -> level.setBlockAndUpdate(pos.north(), getInfectedForm(level.getBlockState(pos.north()).getBlock()).defaultBlockState());
                        case 1 -> level.setBlockAndUpdate(pos.south(), getInfectedForm(level.getBlockState(pos.south()).getBlock()).defaultBlockState());
                        case 2 -> level.setBlockAndUpdate(pos.east(), getInfectedForm(level.getBlockState(pos.east()).getBlock()).defaultBlockState());
                        case 3 -> level.setBlockAndUpdate(pos.west(), getInfectedForm(level.getBlockState(pos.west()).getBlock()).defaultBlockState());
                        case 4 -> level.setBlockAndUpdate(pos.above(), getInfectedForm(level.getBlockState(pos.above()).getBlock()).defaultBlockState());
                        case 5 -> level.setBlockAndUpdate(pos.below(), getInfectedForm(level.getBlockState(pos.below()).getBlock()).defaultBlockState());

                    }
                }

            }

        }
        super.tick(blockState, level, pos, rand);
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

    //Checks the dictionary to see if the given block has an infected form. Returns the infected form if it exists.
    //If it doesn't exist it returns Acacia Button, which is then used as the fail case, meaning no infected form
    private Block getInfectedForm(Block block) {
        //grabs the dictionary of blocks and their infected forms
        Map<String, String> blockPairs = ModBlocks.getBlockPairs();
        //Tries to grab the item for the given block ID
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(MinecraftInfection.MOD_ID, blockPairs.get(block.getDescriptionId())));

    }


    private boolean isInfectable(BlockState block) {
        return block.is(ModTags.Blocks.INFECTABLE_BLOCKS);
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        //First we check if it's a player standing on the block
        if(pEntity instanceof Player){
            //If it is, we check to see if they have an infection level attached to them
            pEntity.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(playerInfection -> {
                //If they do we can increase the infection level
                //We first create a random float to and use it to set the chance of the infection level increasing to 0.1%
                Random random = new Random();
                float infectionChance = 0.01f;
                /*This is done as this function is called constantly whilst the player is standing on an infected block,
                  so it's done to limit the speed in which their infection level increases. It also means that it doesn't
                  increase at a constant rate, which is a cool effect, as it somewhat represents the body fighting off the infection
                 */
                //if(infectionChance> random.nextFloat()){
                    PlayerInfectionLevel.increaseInfectionLevel(1);
                //}
            });
        }
    }
}
