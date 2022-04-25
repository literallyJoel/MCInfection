package com.csixtyone.minecraft_infection.potion;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, MinecraftInfection.MOD_ID);

    public static final RegistryObject<Potion> INFECTION_POTION = POTIONS.register("infection_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.INFECTION.get(), 200, 0)));

    public static final RegistryObject<Potion> PURIFY_POTION = POTIONS.register("purify_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.PURIFY.get(), 200, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
