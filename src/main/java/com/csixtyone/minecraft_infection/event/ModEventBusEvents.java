package com.csixtyone.minecraft_infection.event;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.particle.ModParticles;
import com.csixtyone.minecraft_infection.particle.custom.InfectedParticle;
import com.csixtyone.minecraft_infection.recipe.PurifierRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinecraftInfection.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, PurifierRecipe.Type.ID, PurifierRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.INFECTED_PARTICLES.get(), InfectedParticle.Provider::new);
    }
}
