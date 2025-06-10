package mod.syconn.swm.network;

import dev.architectury.networking.NetworkManager;
import mod.syconn.swm.network.packets.SyncResourceDataPacket;
import mod.syconn.swm.network.packets.ThrowLightsaberPacket;
import mod.syconn.swm.network.packets.ToggleLightsaberPacket;

public class Network {

    public static void registerS2C() {}

    public static void registerC2S() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ToggleLightsaberPacket.TYPE, ToggleLightsaberPacket.STREAM_CODEC, ToggleLightsaberPacket::handle);
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ThrowLightsaberPacket.TYPE, ThrowLightsaberPacket.STREAM_CODEC, ThrowLightsaberPacket::handle);
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SyncResourceDataPacket.TYPE, SyncResourceDataPacket.STREAM_CODEC, SyncResourceDataPacket::handle);
    }
}
