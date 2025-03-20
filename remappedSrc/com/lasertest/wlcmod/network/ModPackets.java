package com.lasertest.wlcmod.network;

import net.minecraft.util.Identifier;

public class ModPackets {
    public static final Identifier LOCK_MOVEMENT_PACKET = new Identifier("wlcmod", "lock_movement");
    public static final Identifier UNLOCK_MOVEMENT_PACKET = new Identifier("wlcmod", "unlock_movement");

    public static void registerServerPackets() {
        // No handling needed on the server side since it's only sending packets
    }
}
