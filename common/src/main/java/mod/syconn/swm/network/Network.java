package mod.syconn.swm.network;

import dev.architectury.networking.NetworkChannel;
import mod.syconn.swm.network.packets.ToggleLightsaberPacket;
import mod.syconn.swm.util.Constants;

public class Network {

    public static NetworkChannel CHANNEL = NetworkChannel.create(Constants.withId("network"));

    public static void init() {
        CHANNEL.register(ToggleLightsaberPacket.class, ToggleLightsaberPacket::encode, ToggleLightsaberPacket::new, ToggleLightsaberPacket::apply);
    }
}
