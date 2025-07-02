package com.lasertest.wlcmod.network;


import com.lasertest.wlcmod.WLC_Mod;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//? >=1.21 {
//import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//?}
import net.minecraft.network.PacketByteBuf;

import java.time.Instant;

/**
 * This manages the sending of timestamped locations to the WLC Plugin.
 */
public class PosAndTimePackets {

    /**
     * Registers the sending of PosAndTime packets
     */
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null || client.player.getUuid() == null || client.player.getPos() == null) {
                return;
            }

            // GENERATE PACKET
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeDouble(client.player.getPos().x);
            buf.writeDouble(client.player.getPos().y);
            buf.writeDouble(client.player.getPos().z);
            buf.writeInstant(Instant.now());

            // SEND PACKET
            //? <=1.20.1 {
            ClientPlayNetworking.send(WLC_Mod.wlcmodChannelPosAndTime, buf);
            //?}
            //? >=1.21 {
//            buf.writeIdentifier(WLC_Mod.wlcmodChannelPosAndTime);
//            WLC_Mod.BytePayload4 payload = new WLC_Mod.BytePayload4(buf);
//            ClientPlayNetworking.send(payload);
            //?}
        });
    }

    public static void registerCodecs() {
        //? >=1.21 {
//        PayloadTypeRegistry.playC2S().register(WLC_Mod.BytePayload4.ID4, WLC_Mod.BytePayload4.CODEC);
        ///?}
    }

}
