package com.csixtyone.minecraft_infection.infection_system.network;

import com.csixtyone.minecraft_infection.infection_system.data.PlayerInfectionLevelProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSetInfectionLevel {
    private final int newAmount;

    public PacketSetInfectionLevel(int newAmount){
        this.newAmount = newAmount;
    }

    public PacketSetInfectionLevel(FriendlyByteBuf buf){
        newAmount = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(newAmount);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();

        ServerPlayer player = context.getSender();

        player.getCapability(PlayerInfectionLevelProvider.PLAYER_INFECTION_LEVEL).ifPresent(pil ->{
            pil.set(newAmount);
        });
        return true;
    }


}
