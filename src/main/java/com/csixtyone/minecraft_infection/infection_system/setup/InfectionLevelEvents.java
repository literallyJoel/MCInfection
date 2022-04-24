package com.csixtyone.minecraft_infection.infection_system.setup;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.infection_system.data.InfectionLevelManager;
import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevel;
import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import com.csixtyone.minecraft_infection.infection_system.data.client.InfectionLevelHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.util.Arrays;
import java.util.List;

import static com.csixtyone.minecraft_infection.item.ModItems.*;


public class InfectionLevelEvents {

    //This fires when capabilities are first attached to the player (on entering the world)
    //It checks if the player has an infection level capability and if they don't, it attaches one.
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof Player) {
            if (!e.getObject().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).isPresent()) {
                e.addCapability(new ResourceLocation(MinecraftInfection.MOD_ID, "player_infection_level"), new PlayerInfectionLevelProvider());
            }
        }
    }

    /*When a player dies or changes dimension the game 'clones' them, creating a new copy of the entity
      This removes its capabilities, so we make sure they haven't died (meaning they must have changed dimension) and
      copy the capabilities over from the old instance of the player*/
    public static void onPlayerCloned(PlayerEvent.Clone e) {
        if (!e.isWasDeath()) {
            e.getOriginal().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(xOld -> {
                e.getPlayer().getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(xNew -> {
                    xNew.copy(xOld);
                });
            });
        }
    }

    //This fires when capability classes are registered, and registers the custom capability class
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent e) {
        e.register(PlayerInfectionLevel.class);
    }

    //When the world ticks, it creates a new instance of the infection level manager and calls its tick method
    public static void onWorldTick(TickEvent.WorldTickEvent e) {
        if (!e.world.isClientSide) {
            if (e.phase != TickEvent.Phase.START) {
                InfectionLevelManager manager = new InfectionLevelManager();
                manager.tick(e.world);
            }
        }
    }

    //Declaring this outside the method to save on recreating the list every time a player attacks another player.
    private static final List<Item> infectedAttackItems = Arrays.asList(INFECTED_AXE.get(), INFECTED_HOE.get(), INFECTED_PICKAXE.get(), INFECTED_SHOVEL.get(), INFECTED_SWORD.get());

    /*If a player attacks, it checks that both the attacking player is server side and that the entity it attacked
      is a player that is server side. If it is, and the attacking player has an item that can increase infection levels
      it increases infection level of other player by 5
     */
    public static void onPlayerAttack(AttackEntityEvent e) {
        //ToDo: Test Balancing with new increase value.
        if (e.getTarget() instanceof ServerPlayer attackedPlayer && e.getPlayer() instanceof ServerPlayer attackingPlayer) {
            Item attackingItem = attackingPlayer.getMainHandItem().getItem();
            if (infectedAttackItems.contains(attackingItem)) {
                attackedPlayer.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(cap -> {
                    cap.increase(10000);
                });
            }
        }


    }

    public static void onWorldLoad(WorldEvent.Load e){
        InfectionLevelHandler.isSafe = false;
    }


}





