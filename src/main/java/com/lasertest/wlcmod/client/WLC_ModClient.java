package com.lasertest.wlcmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class WLC_ModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        ClientPlayNetworking.registerGlobalReceiver(ModPackets.LOCK_MOVEMENT_PACKET, (client, handler, buf, responseSender) -> {
//            int duration = buf.readInt();
//            if (client.player != null) {
//                client.player.sendMessage(Text.of("Debuggg BEFORE LOOP! lockTicks = " + lockTicks),true);
//            }
//            client.execute(() -> lockTicks = duration);
//        });
//
//        // Tick event to count down lock duration and stop movement
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            if (lockTicks > 0) {
//                lockTicks--;
//                if (client.player != null) {
//                    client.player.sendMessage(Text.of("WLC_ModClient.java Debuggg In Loop! lockTicks = " + lockTicks),true);
//                    client.player.sendMessage(Text.of("(ModClient, BEFORE APPLYING) pressingForward: " + String.valueOf(client.player.input.pressingForward)),false);
//                    client.player.input.pressingForward = false;
//                    client.player.input.pressingBack = false;
//                    client.player.input.pressingLeft = false;
//                    client.player.input.pressingRight = false;
//                    client.player.input.movementForward = 0;
//                    client.player.input.movementSideways = 0;
//                    client.player.input.jumping = false;
//                    client.player.input.sneaking = false;
//                    client.player.sendMessage(Text.of("(ModClient, AFTER APPLYING) pressingForward: " + String.valueOf(client.player.input.pressingForward)),false);
//                }
//            }
//        });
    }

//    @Override
//    public void onInitializeClient() {
//        WLC_Mod.LOGGER.info("Client-side logic initialized.");
//    }
}
