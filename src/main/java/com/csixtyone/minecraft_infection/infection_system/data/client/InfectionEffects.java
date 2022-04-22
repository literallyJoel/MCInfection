package com.csixtyone.minecraft_infection.infection_system.data.client;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.infection_system.data.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = MinecraftInfection.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InfectionEffects {
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        Player player = e.player;
        if (!player.isCreative()) {
            if (InfectionLevelHandler.get() > 25) {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS));
            }

            if (InfectionLevelHandler.get() > 50) {
                player.addEffect(new MobEffectInstance((MobEffects.HUNGER)));
            }

            if (InfectionLevelHandler.get() > 75) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN));
            }

            if (InfectionLevelHandler.get() == 100) {
                Random random = new Random();
                if (0.5f > random.nextFloat()) {
                    player.hurt(new DamageSource(DamageTypes.Infection), 0.5f);
                }

            }
        }
    }


}
