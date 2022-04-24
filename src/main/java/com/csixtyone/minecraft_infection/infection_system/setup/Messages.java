package com.csixtyone.minecraft_infection.infection_system.setup;

import com.csixtyone.minecraft_infection.MinecraftInfection;
import com.csixtyone.minecraft_infection.infection_system.network.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class Messages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id(){return packetID++;}

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MinecraftInfection.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;


        //Registers all the messages the mod uses
        net.messageBuilder(PacketIncreaseInfectionLevel.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketIncreaseInfectionLevel::new)
                .encoder(PacketIncreaseInfectionLevel::toBytes)
                .consumer(PacketIncreaseInfectionLevel::handle)
                .add();

        net.messageBuilder(PacketDecreaseInfectionLevel.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketDecreaseInfectionLevel::new)
                .encoder(PacketDecreaseInfectionLevel::toBytes)
                .consumer(PacketDecreaseInfectionLevel::handle)
                .add();

        net.messageBuilder(PacketSetInfectionLevel.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketSetInfectionLevel::new)
                .encoder(PacketSetInfectionLevel::toBytes)
                .consumer(PacketSetInfectionLevel::handle)
                .add();

        net.messageBuilder(PacketSyncInfectionLevelToClient.class, id(),NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketSyncInfectionLevelToClient::new)
                .encoder(PacketSyncInfectionLevelToClient::toBytes)
                .consumer(PacketSyncInfectionLevelToClient::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(ServerPlayer player, MSG msg){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }
}
