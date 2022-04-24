package com.csixtyone.minecraft_infection.infection_system.network;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketIncreaseInfectionLevel {

    //sends a message to the server telling it to increase the infection level for the player
    private final int increaseAmount;
    public PacketIncreaseInfectionLevel(int increaseAmount){
        this.increaseAmount = increaseAmount;
    }

    public PacketIncreaseInfectionLevel(FriendlyByteBuf buf){
        increaseAmount = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(increaseAmount);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        ServerPlayer player = context.getSender();
        context.enqueueWork(()-> player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(pil -> pil.increase(increaseAmount)));

        return true;
    }


}
