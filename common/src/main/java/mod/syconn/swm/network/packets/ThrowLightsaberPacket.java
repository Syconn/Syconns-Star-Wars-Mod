package mod.syconn.swm.network.packets;

import dev.architectury.networking.NetworkManager;
import mod.syconn.swm.features.lightsaber.entity.ThrownLightsaber;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;

import java.util.function.Supplier;

public class ThrowLightsaberPacket {

    private final InteractionHand hand;

    public ThrowLightsaberPacket(InteractionHand hand) {
        this.hand = hand;
    }

    public ThrowLightsaberPacket(FriendlyByteBuf buf) {
        this(buf.readEnum(InteractionHand.class));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(this.hand) ;
    }

    public void apply(Supplier<NetworkManager.PacketContext> context) {
        context.get().queue(() -> {
            Player player = context.get().getPlayer();

            if (player != null) {
                ThrownLightsaber thrownLightsaber = new ThrownLightsaber(player.level(), player, player.getItemInHand(hand));
                thrownLightsaber.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
                if (player.getAbilities().instabuild) thrownLightsaber.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                player.level().addFreshEntity(thrownLightsaber);
            }
        });
    }
}
