package com.csixtyone.minecraft_infection.fluid;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.ModBlocks;
import com.csixtyone.minecraft_infection.block.custom.InfectedWater;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS
            = DeferredRegister.create(ForgeRegistries.FLUIDS, MinecraftInfection.MOD_ID);
    

    public static final RegistryObject<FlowingFluid> INFECTED_WATER_FLUID
            = FLUIDS.register("infected_water_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.INFECTED_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> INFECTED_WATER_FLOWING
            = FLUIDS.register("infected_water_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.INFECTED_WATER_PROPERTIES));


    public static final ForgeFlowingFluid.Properties INFECTED_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> INFECTED_WATER_FLUID.get(), () -> INFECTED_WATER_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(4).sound(SoundEvents.WATER_AMBIENT).overlay(WATER_OVERLAY_RL)
            .color(0xbf6100D5)).slopeFindDistance(6).levelDecreasePerBlock(3)
            .block(() -> ModFluids.INFECTED_WATER_BLOCK.get()).bucket(() -> ModItems.INFECTED_WATER_BUCKET.get());

    public static final RegistryObject<LiquidBlock> INFECTED_WATER_BLOCK = ModBlocks.BLOCKS.register("infected_water",
            () ->  new InfectedWater(() -> ModFluids.INFECTED_WATER_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));
    //FlowingFluidBlock
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }


}
