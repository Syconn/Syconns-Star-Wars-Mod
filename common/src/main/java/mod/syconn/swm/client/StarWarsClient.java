package mod.syconn.swm.client;

import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import mod.syconn.swm.client.keys.KeyHandler;
import mod.syconn.swm.core.ModEntities;
import mod.syconn.swm.core.ModItems;
import mod.syconn.swm.core.ModKeys;
import mod.syconn.swm.features.lightsaber.client.LightsaberItemRender;
import mod.syconn.swm.features.lightsaber.client.ThrownLightsaberRenderer;
import mod.syconn.swm.features.lightsaber.data.LightsaberTag;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.util.Constants;
import mod.syconn.swm.util.client.render.IItemRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class StarWarsClient {

    public static void init() {
        IItemRenderer.register(LightsaberItem.class, new LightsaberItemRender());

        KeyMappingRegistry.register(ModKeys.TOGGLE_ITEM);
        KeyMappingRegistry.register(ModKeys.POWER_1);

        EntityRendererRegistry.register(ModEntities.THROWN_LIGHTSABER, ThrownLightsaberRenderer::new);
    }

    public static void setup() {
        ItemPropertiesRegistry.register(ModItems.LIGHTSABER.get(), Constants.withId("model"),
                ((stack, level, holder, seed) -> (float) LightsaberTag.getOrCreate(stack).model * 0.1f));
    }

    public static void onClientTick(Player player) {
        KeyHandler.handleKeyMappings(player);
    }

    public static float getTickDelta() {
        return Minecraft.getInstance().getDeltaFrameTime();
    }
}
