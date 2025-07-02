package com.lasertest.wlcmod.network;

import com.lasertest.wlcmod.WLC_Mod;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;

import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;

//? >=1.21 {
//import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//import net.minecraft.network.codec.PacketCodec;
//import net.minecraft.network.packet.CustomPayload;
//?}


// Serverbound
public class ModPresencePacket {

    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
//            buf.writeBoolean(true); // Just to send some data
            buf.writeShort(WLC_Mod.mcVersion);

            //? <=1.20.1 {
                ClientPlayNetworking.send(WLC_Mod.wlcmod3Channel, buf);
            //?}
            //? >=1.21 {
//                buf.writeIdentifier(WLC_Mod.wlcmod3Channel);
//                WLC_Mod.BytePayload3 payload = new WLC_Mod.BytePayload3(buf);
//                ClientPlayNetworking.send(payload);
            //?}

//            System.out.println("Sent mod presence packet to server.");
        });
    }



    public static void registerCodecs() {
        //? >=1.21 {
//        PayloadTypeRegistry.playC2S().register(WLC_Mod.BytePayload3.ID3, WLC_Mod.BytePayload3.CODEC);
         ///?}
    }
}
