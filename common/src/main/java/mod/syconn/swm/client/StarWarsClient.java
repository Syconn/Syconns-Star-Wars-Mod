package mod.syconn.swm.client;

import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import mod.syconn.swm.client.keys.KeyHandler;
import mod.syconn.swm.features.lightsaber.client.LightsaberItemRender;
import mod.syconn.swm.features.lightsaber.data.LightsaberTag;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.mixin.MinecraftClientAccessor;
import mod.syconn.swm.registry.ModItems;
import mod.syconn.swm.registry.ModKeys;
import mod.syconn.swm.util.Constants;
import mod.syconn.swm.util.client.render.IItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class StarWarsClient {

    public static void init() {
        IItemRenderer.register(LightsaberItem.class, new LightsaberItemRender());

        ItemPropertiesRegistry.register(ModItems.LIGHTSABER.get(), Constants.withId("model"),
                ((stack, level, holder, seed) -> (float) LightsaberTag.getOrCreate(stack).model * 0.1f));

        KeyMappingRegistry.register(ModKeys.TOGGLE_ITEM);
    }

    public static void onClientTick(Player player) {
        KeyHandler.handleKeyMappings(player);
    }

    public static float getTickDelta() {
        var mc = Minecraft.getInstance();
        if (mc.isPaused())
            return ((MinecraftClientAccessor)mc).getPausedTickDelta();
        return mc.getDeltaFrameTime();
    }
}
