package com.csixtyone.minecraft_infection.block.custom;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.ModBlocks;
import com.csixtyone.minecraft_infection.block.entity.ModBlockEntities;
import com.csixtyone.minecraft_infection.block.entity.custom.PurifierBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class PurifierStationBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PurifierStationBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE =  Block.box(0, 0, 0, 16, 16, 16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    /* FACING */

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    /* BLOCK ENTITY */

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof PurifierBlockEntity) {
                ((PurifierBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
            if (!pLevel.isClientSide()) {
                if(canOpenInterface(pLevel,pPos)){
                BlockEntity entity = pLevel.getBlockEntity(pPos);
                if(entity instanceof PurifierBlockEntity) {
                    NetworkHooks.openGui(((ServerPlayer)pPlayer), (PurifierBlockEntity)entity, pPos);
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    private boolean canOpenInterface(Level pLevel,BlockPos pPos){
        boolean HasOutputPlaced = false;
        boolean HasInputPlaced = false;
        BlockState[] blockStates = new BlockState[2];
        blockStates[0] = (pLevel.getBlockState(pPos.east()));
        blockStates[1] = (pLevel.getBlockState(pPos.west()));
        if (blockStates[0].getBlock() == ModBlocks.PURIFIER_INPUT.get() ||blockStates[1].getBlock() == ModBlocks.PURIFIER_INPUT.get()){
            HasInputPlaced = true;
        }
        if (blockStates[0].getBlock() == ModBlocks.PURIFIER_OUTPUT.get() || blockStates[1].getBlock() == ModBlocks.PURIFIER_OUTPUT.get()){
            HasOutputPlaced = true;
        }
        return HasInputPlaced && HasOutputPlaced;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PurifierBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.PURIFIER_BLOCK_ENTITY.get(),
                PurifierBlockEntity::tick);
    }
}
