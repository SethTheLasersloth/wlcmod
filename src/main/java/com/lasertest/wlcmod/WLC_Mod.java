package com.lasertest.wlcmod;  // Ensure this matches your package structure

import com.lasertest.wlcmod.network.ModPresencePacket;
import io.netty.buffer.ByteBuf;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.packet.Packet;
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

//? >=1.21 {
import net.minecraft.block.entity.VaultBlockEntity;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
//?}

public class WLC_Mod implements ModInitializer {
    public static int lockTicks = 0;
    public static void setLockTicks(int newLockTicks) { lockTicks = newLockTicks; }

    public static boolean blockInputs = false;

    public static final String MOD_ID = "wlcmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static final Identifier wlcmodChannel = Identifier.of("wlcmod", "lock_movement");
    public static final Identifier wlcmod2Channel = Identifier.of("wlcmod2", "unlock_movement");
    public static final Identifier wlcmod3Channel = Identifier.of("wlcmod3", "mod_presence");


    @Override
    public void onInitialize() {
        ModPackets.registerCodecs();
        ModPresencePacket.registerCodecs();
        ModPackets.registerHandlers();
        ModPresencePacket.register();

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

    public record BytePayload(ByteBuf data) implements CustomPayload {
        public static final PacketCodec<PacketByteBuf, BytePayload> CODEC = CustomPayload.codecOf(BytePayload::write, BytePayload::new);
        public static final Id<BytePayload> ID = new Id<>(wlcmodChannel);
//        public static final Id<BytePayload> ID2 = new Id<>(wlcmod2Channel);
//        public static final Id<BytePayload> ID3 = new Id<>(wlcmod3Channel);

        public BytePayload(PacketByteBuf buf) {
            this(buf.copy());
            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
        }

        public void write(PacketByteBuf buf) {
            buf.writeBytes(data);
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }

    }

    public record BytePayload2(ByteBuf data) implements CustomPayload {
        public static final PacketCodec<PacketByteBuf, BytePayload2> CODEC = CustomPayload.codecOf(BytePayload2::write, BytePayload2::new);
//        public static final CustomPayload.Id<BytePayload> ID = new CustomPayload.Id<>(wlcmodChannel);
        public static final CustomPayload.Id<BytePayload2> ID2 = new CustomPayload.Id<>(wlcmod2Channel);
//        public static final CustomPayload.Id<BytePayload> ID3 = new CustomPayload.Id<>(wlcmod3Channel);

        public BytePayload2(PacketByteBuf buf) {
            this(buf.copy());
            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
        }

        public void write(PacketByteBuf buf) {
            buf.writeBytes(data);
        }

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID2;
        }
    }

    public record BytePayload3(ByteBuf data) implements CustomPayload {
        public static final PacketCodec<PacketByteBuf, BytePayload3> CODEC = CustomPayload.codecOf(BytePayload3::write, BytePayload3::new);
//        public static final CustomPayload.Id<BytePayload> ID = new CustomPayload.Id<>(wlcmodChannel);
//        public static final CustomPayload.Id<BytePayload> ID2 = new CustomPayload.Id<>(wlcmod2Channel);
        public static final CustomPayload.Id<BytePayload3> ID3 = new CustomPayload.Id<>(wlcmod3Channel);

        public BytePayload3(PacketByteBuf buf) {
            this(buf.copy());
            buf.readerIndex(buf.writerIndex());// so mc doesn't complain we haven't read all the bytes
        }

        public void write(PacketByteBuf buf) {
            buf.writeBytes(data);
        }

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID3;
        }
    }
    //?}




//    public static void sendPacketC2S(PacketByteBuf packet){
//        //? <=1.20.1 {
////        assert settingsChannel != null;
////        ClientPlayNetworking.send(settingsChannel, packet);
//        //?} else {
//
//
//
//        BytePayload payload = new BytePayload(packet);
//        ClientPlayNetworking.send(payload);
//        ///?}
//    }
//
//    public static void sendPacketS2C(ServerPlayerEntity player, PacketByteBuf packet){
//        //? <=1.20.1 {
////        assert settingsChannel != null;
////        ServerPlayNetworking.send(player, settingsChannel, packet);
//        //?} else {
//        BytePayload payload = new BytePayload(packet);
//        ServerPlayNetworking.send(player, payload);
//        ///?}
//    }
}