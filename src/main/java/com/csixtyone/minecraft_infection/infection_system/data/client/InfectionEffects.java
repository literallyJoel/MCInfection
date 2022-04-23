package com.csixtyone.minecraft_infection.infection_system.data.client;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.infection_system.data.DamageTypes;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = MinecraftInfection.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InfectionEffects {

    //Runs on player ticks
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        Player player = e.player;
        //Only runs if the player isn't creative
        if (!player.isCreative()) {
            //Adds the effects for the various levels of infection
            if (InfectionLevelHandler.getPercent() > 25) {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS));
            }

            if (InfectionLevelHandler.getPercent() > 50) {
                player.addEffect(new MobEffectInstance((MobEffects.HUNGER)));
            }

            if (InfectionLevelHandler.getPercent() > 75) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN));
            }

            if (InfectionLevelHandler.getPercent() == 100) {
                Random random = new Random();
                if (0.5f > random.nextFloat()) {
                    player.hurt(new DamageSource(DamageTypes.Infection), 0.5f);
                }

            }
        }

        //Checks what armour the player is wearing. For the infected armour it increases the chance of the player infection increasing
        //If they're wearing pure armour, it increases the chances of the infection decreasing
        int infectionIncreaseMultiplier = 0;
        int infectionReductionMultiplier = 0;
        NonNullList<ItemStack> armour = player.getInventory().armor;
        for (ItemStack itemStack : armour) {
            if (itemStack.is(ModItems.INFECTED_BOOTS.get())) infectionIncreaseMultiplier += 1;
            else if (itemStack.is(ModItems.INFECTED_HELMET.get())) infectionIncreaseMultiplier +=2;
            else if (itemStack.is(ModItems.INFECTED_LEGGING.get())) infectionIncreaseMultiplier += 3;
            else if (itemStack.is(ModItems.INFECTED_CHESTPLATE.get())) infectionIncreaseMultiplier += 4;
            else if (itemStack.is(ModItems.Pure_BOOTS.get())) infectionReductionMultiplier += 1;
            else if (itemStack.is(ModItems.PURE_HELMET.get())) infectionReductionMultiplier += 2;
            else if (itemStack.is(ModItems.PURE_LEGGING.get())) infectionReductionMultiplier+=3;
            else if (itemStack.is(ModItems.PURE_CHESTPLATE.get())) infectionReductionMultiplier+=4;
        }



            InfectionLevelHandler.increase(infectionIncreaseMultiplier);
            InfectionLevelHandler.decrease(infectionReductionMultiplier);

    }


}
