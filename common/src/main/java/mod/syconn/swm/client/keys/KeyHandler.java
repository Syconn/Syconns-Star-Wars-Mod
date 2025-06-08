package mod.syconn.swm.client.keys;

import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.network.Network;
import mod.syconn.swm.network.packets.ToggleLightsaberPacket;
import mod.syconn.swm.registry.ModKeys;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

public class KeyHandler {

    public static void handleKeyMappings(Player player) {
        while (ModKeys.TOGGLE_ITEM.consumeClick()) {
            InteractionHand hand = player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
            if (player.getItemInHand(hand).getItem() instanceof LightsaberItem) {
                Network.CHANNEL.sendToServer(new ToggleLightsaberPacket(hand));
            }
        }
    }
}
