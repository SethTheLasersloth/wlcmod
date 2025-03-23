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

    public static final Identifier MOD_PRESENCE = Identifier.of("wlcmod3", "mod_presence");

    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
//            buf.writeBoolean(true); // Just to send some data
            buf.writeShort(WLC_Mod.mcVersion);

            //? <=1.20.1 {
                ClientPlayNetworking.send(MOD_PRESENCE, buf);
            //?}
            //? >=1.21 {
//                buf.writeIdentifier(MOD_PRESENCE);
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


//    public static void registerHandlers(){
//        //? <=1.20.1 {
////        ServerPlayNetworking.registerGlobalReceiver(OpenBoatUtils.settingsChannel, (server, player, handler, buf, responseSender) -> {
////            handlePacket(buf);
////        });
//        //?}
//        //? >=1.21 {
//        ServerPlayNetworking.registerGlobalReceiver(WLC_Mod.BytePayload3.ID3, ((payload, context) ->
//                context.server().execute(() ->
//                    handlePacket(payload.data()) )));
//        //?}
//    }
//
//    public static void handlePacket(ByteBuf buf) {
//
//    }

}
