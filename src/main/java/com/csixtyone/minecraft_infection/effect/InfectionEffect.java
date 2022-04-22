package com.csixtyone.minecraft_infection.effect;

import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class InfectionEffect extends MobEffect {


    protected InfectionEffect(MobEffectCategory MobEffectCategory, int color) {
        super(MobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide() && pLivingEntity instanceof Player) {
            InfectionLevelHandler.increase(1);
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
