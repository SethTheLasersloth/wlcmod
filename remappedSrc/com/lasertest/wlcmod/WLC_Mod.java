package com.lasertest.wlcmod;  // Ensure this matches your package structure

import com.lasertest.wlcmod.network.ModPresencePacket;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

import com.lasertest.wlcmod.network.ModPackets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import io.netty.buffer.Unpooled;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.nio.charset.StandardCharsets;

public class WLC_Mod implements ModInitializer {
    private static int lockTicks = 0;

    public static boolean blockInputs = false;

    public static final String MOD_ID = "wlcmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

//    @Override
//    public void onInitialize() {
//        ModPackets.registerServerPackets();
//        System.out.println("WLC_Mod has initialized!");
//    }

    @Override
    public void onInitialize() {
        // Register the listener for the server’s plugin message
        ClientPlayNetworking.registerGlobalReceiver(Identifier.of("wlcmod", "lock_movement"), (client, handler, buf, responseSender) -> {
            int duration = buf.readInt();  // Read duration from the packet
            client.execute(() -> lockTicks = duration);
        });

//        Identifier identA = Identifier.of("wlcmod", "lock_movement");
//        ClientPlayNetworking.registerGlobalReceiver(identA, (client, handler, buf, responseSender) -> {
//            int duration = buf.readInt();  // Read duration from the packet
//            client.execute(() -> lockTicks = duration);
//        });


        // Register the listener for the server’s plugin message
        ClientPlayNetworking.registerGlobalReceiver(new Identifier("wlcmod2", "unlock_movement"), (client, handler, buf, responseSender) -> {
//            int duration = buf.readInt();  // Read duration from the packet
            client.execute(() -> lockTicks = 0);
        });


        ModPresencePacket.register();



        // Tick event listener to lock movement
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (lockTicks > 0) {
                blockInputs = true;
                lockTicks--;
//                if (client.player != null) {
//                    client.player.sendMessage(Text.of("In WLC_Mod.java loop!"));
//                }

//                if (client.player != null) {
//                    client.player.sendMessage(Text.of("WLC_Mod.java Debuggg In Loop! lockTicks = " + lockTicks),true);
//                    client.player.sendMessage(Text.of("(WLC_Mod, BEFORE APPLYING) pressingForward: " + String.valueOf(client.player.input.pressingForward)),false);
//
//                    client.player.input.pressingForward = false;
//                    client.player.input.pressingBack = false;
//                    client.player.input.pressingLeft = false;
//                    client.player.input.pressingRight = false;
//                    client.player.input.movementForward = 0;
//                    client.player.input.movementSideways = 0;
////                    client.player.input.jumping = false;
////                    client.player.input.sneaking = false;
//                    client.player.sendMessage(Text.of("(WLC_Mod, AFTER APPLYING) pressingForward: " + String.valueOf(client.player.input.pressingForward)),false);
//                } // 3/10/25: This might have to be adjusted to match Boat stuff.

            }
            else {
                blockInputs = false;
//                if (client.player != null) {
//                    client.player.sendMessage(Text.of("WLC_Mod.java loop ended!"));
//                }
            }
        });

//        // Tick event listener to lock movement
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            if (lockTicks > 0) {
//                lockTicks--;
//                if (client.player != null) {
//                    client.player.sendMessage(Text.of("WLC_Mod.java Debuggg In Loop! lockTicks = " + lockTicks),true);
//                    client.player.sendMessage(Text.of("(WLC_Mod, BEFORE APPLYING) pressingForward: " + String.valueOf(client.player.input.pressingForward)),false);
//
//                    client.player.input.pressingForward = false;
//                    client.player.input.pressingBack = false;
//                    client.player.input.pressingLeft = false;
//                    client.player.input.pressingRight = false;
//                    client.player.input.movementForward = 0;
//                    client.player.input.movementSideways = 0;
////                    client.player.input.jumping = false;
////                    client.player.input.sneaking = false;
//                    client.player.sendMessage(Text.of("(WLC_Mod, AFTER APPLYING) pressingForward: " + String.valueOf(client.player.input.pressingForward)),false);
//                } // 3/10/25: This might have to be adjusted to match Boat stuff.
//            }
//        });
    }


//    public static void lockPlayerMovement(ServerPlayerEntity player, int duration) {
//        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
//        buf.writeInt(duration); // Send the lock duration in ticks
//
//        ServerPlayNetworking.send(player, ModPackets.LOCK_MOVEMENT_PACKET, buf);
//    }
}