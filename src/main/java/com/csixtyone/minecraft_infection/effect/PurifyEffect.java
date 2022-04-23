package com.csixtyone.minecraft_infection.effect;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class PurifyEffect extends MobEffect {
    protected PurifyEffect(MobEffectCategory MobEffectCategory, int color) {
        super(MobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer Player) {
            Player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(cap -> {
                cap.decrease(1);
            });
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }}

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
