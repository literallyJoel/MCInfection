package com.csixtyone.minecraft_infection.block.entity.custom;


import com.csixtyone.minecraft_infection.block.entity.ModBlockEntities;
import com.csixtyone.minecraft_infection.fluid.ModFluids;
import com.csixtyone.minecraft_infection.item.ModItems;
import com.csixtyone.minecraft_infection.screen.WaterTankMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class WaterTankEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private final int maxCapacity = 5;
    private int currentLevel = 0;
    private Fluid currentFluid = null;
    public WaterTankEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.WATER_TANK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                if (index == 0) {
                    return WaterTankEntity.this.currentLevel;
                }
                return 0;
            }

            public void set(int index, int value) {
                if(index ==0){
                    WaterTankEntity.this.currentLevel = value;
                }
            }

            public int getCount() {
                return 1;
            }
        };

    }


    @Override
    public Component getDisplayName() {
        return new TextComponent("Water Tank");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new WaterTankMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    public int getCurrentLevel(){
        return this.currentLevel;
    }

    public Fluid getCurrentFluid(){
        return this.currentFluid;
    }

    public void setCurrentLevel(int level){
        this.currentLevel = Math.min(maxCapacity, level);
    }

    public void increaseCurrentLevel(int increaseBy){
        this.currentLevel = Math.min(maxCapacity, currentLevel+increaseBy);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("water_tank.level", currentLevel);
        int currentFluid =0;
        if(this.currentFluid!=null){
            if(this.currentFluid.isSame(Fluids.WATER)){
                currentFluid = 1;
            }
        }
        tag.putInt("water_tank.current_fluid", currentFluid);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        currentLevel = nbt.getInt("water_tank.level");
        int currentFluid = nbt.getInt("water_tank.current_fluid");
        this.currentFluid = currentFluid == 0 ? ModFluids.INFECTED_WATER_FLUID.get().getFlowing() : Fluids.WATER.getFlowing();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, WaterTankEntity pBlockEntity) {
        if(hasBucketInSlot(pBlockEntity)){
            Item item = pBlockEntity.itemHandler.getStackInSlot(0).getItem();

            if(((BucketItem) item).getFluid().isSame(pBlockEntity.currentFluid) || pBlockEntity.currentFluid == null){
                System.out.println(pBlockEntity.currentFluid);
                if(pBlockEntity.currentLevel < pBlockEntity.maxCapacity){
                    if(pBlockEntity.currentFluid == null) {
                        pBlockEntity.currentFluid = ((BucketItem) item).getFluid();
                    }

                    if (pBlockEntity.currentFluid.isSame(ModFluids.INFECTED_WATER_FLUID.get())) {
                        pBlockEntity.currentFluid = ModFluids.INFECTED_WATER_FLUID.get();
                        pBlockEntity.data.set(1, 0);
                    } else {
                        pBlockEntity.currentFluid = Fluids.WATER;
                        pBlockEntity.data.set(1, 1);
                    }
                    pBlockEntity.increaseCurrentLevel(1);
                    pBlockEntity.itemHandler.setStackInSlot(0, new ItemStack(Items.BUCKET));
                }

                }
            }
        }




    private static boolean hasBucketInSlot(WaterTankEntity entity) {
        boolean hasWaterBucket = entity.itemHandler.getStackInSlot(0).getItem() == Items.WATER_BUCKET;
        boolean hasInfectedWaterBucket = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.INFECTED_WATER_BUCKET.get();
        return hasWaterBucket||hasInfectedWaterBucket;
    }





}
