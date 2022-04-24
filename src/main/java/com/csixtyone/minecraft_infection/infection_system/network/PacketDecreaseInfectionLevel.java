package com.csixtyone.minecraft_infection.infection_system.network;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketDecreaseInfectionLevel {

    //Sends a message to the server telling it to decrease the infection amount for the player
    private final int decreaseAmount;
    public PacketDecreaseInfectionLevel(int decreaseAmount){
        this.decreaseAmount = decreaseAmount;
    }

    public PacketDecreaseInfectionLevel(FriendlyByteBuf buf){
        decreaseAmount = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(decreaseAmount);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        ServerPlayer player = context.getSender();

        context.enqueueWork(()-> player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(pil -> pil.decrease(decreaseAmount)));

        return true;
    }


}
