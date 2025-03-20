package com.lasertest.wlcmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;

public class ModPresencePacket {
    public static final Identifier MOD_PRESENCE = new Identifier("wlcmod3", "mod_presence");

    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeBoolean(true); // Just to send some data

            ClientPlayNetworking.send(MOD_PRESENCE, buf);
            System.out.println("Sent mod presence packet to server.");
        });
    }
}
