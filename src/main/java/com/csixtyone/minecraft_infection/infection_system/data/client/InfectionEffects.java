package com.csixtyone.minecraft_infection.infection_system.data.client;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.infection_system.data.DamageTypes;
import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import com.csixtyone.minecraft_infection.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.csixtyone.minecraft_infection.item.ModItems.*;

@Mod.EventBusSubscriber(modid = MinecraftInfection.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InfectionEffects {

    public static void register(IEventBus bus) {
        bus.addListener(InfectionEffects::onPlayerTick);
    }

    //Runs on player ticks
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        Player player = e.player;
        if(player instanceof ServerPlayer serverPlayer) {
            //Only runs if the player isn't creative
            if (!serverPlayer.isCreative()) {
                //Adds the effects for the various levels of infection
                if (InfectionLevelHandler.getPercent(serverPlayer) > 25) {
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS));
                }

                if (InfectionLevelHandler.getPercent(serverPlayer) > 50) {
                    serverPlayer.addEffect(new MobEffectInstance((MobEffects.HUNGER)));
                }

                if (InfectionLevelHandler.getPercent(serverPlayer) > 75) {
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN));
                }

                if (InfectionLevelHandler.getPercent(serverPlayer) == 100) {
                    Random random = new Random();
                    if (0.5f > random.nextFloat()) {
                        serverPlayer.hurt(new DamageSource(DamageTypes.Infection), 0.5f);
                    }

                }
            }



                //Checks what armour the player is wearing. For the infected armour it increases the chance of the player infection increasing
                //If they're wearing pure armour, it increases the chances of the infection decreasing
                int infectionIncreaseAmount = 0;
                int infectionReductionAmount = 0;
                NonNullList<ItemStack> armour = player.getInventory().armor;
                for (ItemStack itemStack : armour) {
                    if (itemStack.is(ModItems.INFECTED_BOOTS.get())) infectionIncreaseAmount += 1;
                    else if (itemStack.is(ModItems.INFECTED_HELMET.get())) infectionIncreaseAmount += 2;
                    else if (itemStack.is(ModItems.INFECTED_LEGGING.get())) infectionIncreaseAmount += 3;
                    else if (itemStack.is(ModItems.INFECTED_CHESTPLATE.get())) infectionIncreaseAmount += 4;
                    else if (itemStack.is(ModItems.Pure_BOOTS.get())) infectionReductionAmount += 1;
                    else if (itemStack.is(ModItems.PURE_HELMET.get())) infectionReductionAmount += 2;
                    else if (itemStack.is(ModItems.PURE_LEGGING.get())) infectionReductionAmount += 3;
                    else if (itemStack.is(ModItems.PURE_CHESTPLATE.get())) infectionReductionAmount += 4;
                }


                InfectionLevelHandler.increase(infectionIncreaseAmount, serverPlayer);
                InfectionLevelHandler.decrease(infectionReductionAmount, serverPlayer);


            }
        }



}
