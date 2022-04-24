package com.csixtyone.minecraft_infection.infection_system.network;

import com.csixtyone.minecraft_infection.infection_system.data.client.ClientInfectionLevelData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//Sends the infection level for the player from the server to the client so the client can access the player's infection level
public class PacketSyncInfectionLevelToClient {
    private final int infectionLevel;

    public PacketSyncInfectionLevelToClient(int infectionLevel){
        this.infectionLevel = infectionLevel;
    }

    public PacketSyncInfectionLevelToClient(FriendlyByteBuf buf){
        infectionLevel = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(infectionLevel);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> ClientInfectionLevelData.set(infectionLevel));
        return true;
    }
}
