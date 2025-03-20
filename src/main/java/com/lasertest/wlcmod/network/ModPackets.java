package com.lasertest.wlcmod.network;

import com.lasertest.wlcmod.WLC_Mod;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import io.netty.buffer.Unpooled;

//? >=1.21 {
    import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
    import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
//?}

// Clientbound
public class ModPackets {

    public static void registerCodecs() {
        //? >=1.21 {
        PayloadTypeRegistry.playS2C().register(WLC_Mod.BytePayload.ID, WLC_Mod.BytePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(WLC_Mod.BytePayload2.ID2, WLC_Mod.BytePayload2.CODEC);
         ///?}
    }


    public static void registerHandlers(){
        //? <=1.20.1 {
//        ClientPlayNetworking.registerGlobalReceiver(new Identifier ("wlcmod", "lock_movement"), (client, handler, buf, responseSender) -> {
//            int duration = buf.readInt();  // Read duration from the packet
//            client.execute(() -> WLC_Mod.setLockTicks(duration));
//        });
//        ClientPlayNetworking.registerGlobalReceiver(new Identifier("wlcmod2", "unlock_movement"), (client, handler, buf, responseSender) -> {
//            client.execute(() -> WLC_Mod.setLockTicks(0));
//        });
        //?}
        //? >=1.21 {
        ClientPlayNetworking.registerGlobalReceiver(WLC_Mod.BytePayload.ID, ((payload, context) ->
                context.client().execute(() ->
                    handlePacket1Laser(new PacketByteBuf(Unpooled.wrappedBuffer(payload.data()))) )));
        ClientPlayNetworking.registerGlobalReceiver(WLC_Mod.BytePayload2.ID2, ((payload, context) ->
                context.client().execute(() ->
                        handlePacket2Laser(new PacketByteBuf(Unpooled.wrappedBuffer(payload.data()))) )));
        //?}
    }


    public static void handlePacket1Laser(PacketByteBuf buf) {
        int duration = buf.readInt();  // Read duration from the packet
        MinecraftClient.getInstance().execute(() -> WLC_Mod.setLockTicks(duration));
    }

    public static void handlePacket2Laser(PacketByteBuf buf) {
        MinecraftClient.getInstance().execute(() -> WLC_Mod.setLockTicks(0));
    }


}
