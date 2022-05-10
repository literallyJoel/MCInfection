package com.csixtyone.minecraft_infection.particle;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MinecraftInfection.MOD_ID);

    //Particles for infected substance
    public static final RegistryObject<SimpleParticleType> INFECTED_PARTICLES =
            PARTICLE_TYPES.register("infected_particles", () -> new SimpleParticleType(true));



    public static void register (IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
