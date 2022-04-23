package com.csixtyone.minecraft_infection.effect;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MinecraftInfection.MOD_ID);

    public static final RegistryObject<MobEffect> INFECTION = MOB_EFFECTS.register("infection",
            () -> new InfectionEffect(MobEffectCategory.HARMFUL, 3124687));

    public static final RegistryObject<MobEffect> PURIFY = MOB_EFFECTS.register("purify",
            () -> new PurifyEffect(MobEffectCategory.BENEFICIAL, 3124687));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
