package com.csixtyone.minecraft_infection.recipe;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MinecraftInfection.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PurifierRecipe>> PURIFIER_SERIALIZER =
            SERIALIZERS.register("purify", () -> PurifierRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
