package com.csixtyone.minecraft_infection.block.entity;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.block.ModBlocks;
import com.csixtyone.minecraft_infection.block.entity.custom.PurifierBlockEntity;
import com.csixtyone.minecraft_infection.block.entity.custom.WaterTankEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MinecraftInfection.MOD_ID);

    public static final RegistryObject<BlockEntityType<PurifierBlockEntity>> PURIFIER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("purifier_block_entity", () ->
                    BlockEntityType.Builder.of(PurifierBlockEntity::new, ModBlocks.PURIFIER.get()).build(null));

    public static final RegistryObject<BlockEntityType<WaterTankEntity>> WATER_TANK_ENTITY =
            BLOCK_ENTITIES.register("water_tank_entity", () ->
                    BlockEntityType.Builder.of(WaterTankEntity::new, ModBlocks.WATER_TANK.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
