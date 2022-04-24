package com.csixtyone.minecraft_infection.block.entity.custom;

import com.csixtyone.minecraft_infection.block.custom.PurifiedBlock;
import com.csixtyone.minecraft_infection.block.entity.ModBlockEntities;
import com.csixtyone.minecraft_infection.item.ModItems;
import com.csixtyone.minecraft_infection.recipe.PurifierRecipe;
import com.csixtyone.minecraft_infection.screen.PurifierMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.datafix.fixes.CauldronRenameFix;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

public class PurifierBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 144;
    private int inputCauldron = 0;
    private int outputCauldron = 0;

    public PurifierBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.PURIFIER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return PurifierBlockEntity.this.progress;
                    case 1: return PurifierBlockEntity.this.maxProgress;
                    case 2: return PurifierBlockEntity.this.inputCauldron;
                    case 3: return PurifierBlockEntity.this.outputCauldron;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: PurifierBlockEntity.this.progress = value; break;
                    case 1: PurifierBlockEntity.this.maxProgress = value; break;
                    case 2: PurifierBlockEntity.this.inputCauldron = value; break;
                    case 3: PurifierBlockEntity.this.outputCauldron = value;break;
                }
            }

            public int getCount() {
                return 4;
            }
        };

    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Purifier");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new PurifierMenu(pContainerId,pInventory,this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("purifier.progress", progress);
        tag.putInt("input_water", inputCauldron);
        tag.putInt("output_water", outputCauldron);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("purifier.progress");
        inputCauldron = nbt.getInt("input_water");
        outputCauldron = nbt.getInt("output_water");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, PurifierBlockEntity pBlockEntity) {
        CheckWaterLevel(pPos,pBlockEntity,pLevel);
        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(PurifierBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<PurifierRecipe> match = level.getRecipeManager()
                .getRecipeFor(PurifierRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasPuredustInSlot(entity) && hasRedstoneSlot(entity);
    }

    private boolean hasWaterInput(){
        return PurifierBlockEntity.this.inputCauldron > 0;
    }

    private boolean canIncreaseOutputWaterLevel(){
        return PurifierBlockEntity.this.outputCauldron < 3;
    }

    private static boolean hasPuredustInSlot(PurifierBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getItem() == ModItems.PUREDUST.get();
    }

    private static boolean hasRedstoneSlot(PurifierBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(2).getItem() == Items.REDSTONE;
    }

    private static void craftItem(PurifierBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<PurifierRecipe> match = level.getRecipeManager()
                .getRecipeFor(PurifierRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            entity.itemHandler.extractItem(1,1, false);
            entity.itemHandler.extractItem(2,1,false);
            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }

    private static void CheckWaterLevel(BlockPos pPos, PurifierBlockEntity pBlockEntity, Level pLevel){
        if (pLevel.getBlockState(pPos.offset(1,1,0)).getBlock() == Blocks.WATER_CAULDRON){
            pBlockEntity.inputCauldron = 3;

        }
        else {
            pBlockEntity.inputCauldron = 0;
        }
       if (pLevel.getBlockState(pPos.offset(-1,1,0)).getBlock() == Blocks.WATER_CAULDRON){
           pBlockEntity.outputCauldron = 3;
       }
       else {
           pBlockEntity.outputCauldron = 0;
       }
    }
}
