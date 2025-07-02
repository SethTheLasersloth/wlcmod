package com.lasertest.wlcmod;  // Ensure this matches your package structure

import com.lasertest.wlcmod.network.PosAndTimePackets;
import com.lasertest.wlcmod.network.ModPresencePacket;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.PacketByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lasertest.wlcmod.network.LockInputsPackets;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

//? >=1.21 {
//import net.minecraft.network.codec.PacketCodec;
//import net.minecraft.network.packet.CustomPayload;
//import io.netty.buffer.ByteBuf;
//?}

public class WLC_Mod implements ModInitializer {

    public static short mcVersion = 12001; // 1.20.1 - 1.20.4
//    public static short mcVersion = 12101; // 1.21 - 1.21.1
//    public static short mcVersion = 12102; // 1.21.2 - 1.21.3
//    public static short mcVersion = 12104; // 1.21.4


    public static int lockTicks = 0;
    public static void setLockTicks(int newLockTicks) {
        lockTicks = newLockTicks;
    }

    public static boolean blockInputs = false;

    public static final String MOD_ID = "wlcmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final String path_LockMovement = "lock_movement";
    public static final String path_UnlockMovement = "unlock_movement";
    public static final String path_ModPresence = "mod_presence";
    public static final String path_PosAndTime = "pos_and_time";


    public static final Identifier wlcmodChannel = Identifier.of(MOD_ID, path_LockMovement);
    public static final Identifier wlcmod2Channel = Identifier.of(MOD_ID, path_UnlockMovement);
    public static final Identifier wlcmod3Channel = Identifier.of(MOD_ID, path_ModPresence);
    public static final Identifier wlcmodChannelPosAndTime = Identifier.of(MOD_ID, path_PosAndTime);


    @Override
    public void onInitialize() {
        LockInputsPackets.registerCodecs();
        LockInputsPackets.registerHandlers();

        ModPresencePacket.registerCodecs();
        ModPresencePacket.register();

        PosAndTimePackets.registerCodecs();
        PosAndTimePackets.register();


        // Tick event listener to lock movement
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (lockTicks > 0) {
                blockInputs = true;
                lockTicks--;
            }
            else {
                blockInputs = false;
            }
        });
    }


    //? >=1.21 {
//
//    public record BytePayload(ByteBuf data) implements CustomPayload {
//        public static final PacketCodec<PacketByteBuf, BytePayload> CODEC = CustomPayload.codecOf(BytePayload::write, BytePayload::new);
//        public static final Id<BytePayload> ID = new Id<>(wlcmodChannel);
//
//        public BytePayload(PacketByteBuf buf) {
//            this(buf.copy());
//            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
//        }
//
//        public void write(PacketByteBuf buf) {
//            buf.writeBytes(data);
//        }
//
//        @Override
//        public Id<? extends CustomPayload> getId() {
//            return ID;
//        }
//
//    }
//
//    public record BytePayload2(ByteBuf data) implements CustomPayload {
//        public static final PacketCodec<PacketByteBuf, BytePayload2> CODEC = CustomPayload.codecOf(BytePayload2::write, BytePayload2::new);
//        public static final CustomPayload.Id<BytePayload2> ID2 = new CustomPayload.Id<>(wlcmod2Channel);
//
//        public BytePayload2(PacketByteBuf buf) {
//            this(buf.copy());
//            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
//        }
//
//        public void write(PacketByteBuf buf) {
//            buf.writeBytes(data);
//        }
//
//        @Override
//        public CustomPayload.Id<? extends CustomPayload> getId() {
//            return ID2;
//        }
//    }
//
//    public record BytePayload3(ByteBuf data) implements CustomPayload {
//        public static final PacketCodec<PacketByteBuf, BytePayload3> CODEC = CustomPayload.codecOf(BytePayload3::write, BytePayload3::new);
//        public static final CustomPayload.Id<BytePayload3> ID3 = new CustomPayload.Id<>(wlcmod3Channel);
//
//        public BytePayload3(PacketByteBuf buf) {
//            this(buf.copy());
//            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
//        }
//
//        public void write(PacketByteBuf buf) {
//            buf.writeBytes(data);
//        }
//
//        @Override
//        public CustomPayload.Id<? extends CustomPayload> getId() {
//            return ID3;
//        }
//    }
//
//    public record BytePayload4(ByteBuf data) implements CustomPayload {
//        public static final PacketCodec<PacketByteBuf, BytePayload4> CODEC = CustomPayload.codecOf(BytePayload4::write, BytePayload4::new);
//        public static final CustomPayload.Id<BytePayload4> ID4 = new CustomPayload.Id<>(wlcmodChannelPosAndTime);
//
//        public BytePayload4(PacketByteBuf buf) {
//            this(buf.copy());
//            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
//        }
//
//        public void write(PacketByteBuf buf) {
//            buf.writeBytes(data);
//        }
//
//        @Override
//        public CustomPayload.Id<? extends CustomPayload> getId() {
//            return ID4;
//        }
//    }
    //?}
}