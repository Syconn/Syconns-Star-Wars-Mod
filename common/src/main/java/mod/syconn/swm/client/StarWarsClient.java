package mod.syconn.swm.client;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import mod.syconn.swm.features.lightsaber.client.LightsaberItemRender;
import mod.syconn.swm.features.lightsaber.data.LightsaberTag;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.registry.ModItems;
import mod.syconn.swm.util.Constants;
import mod.syconn.swm.util.client.render.IItemRenderer;

public class StarWarsClient {

    public static void init() {
        IItemRenderer.register(LightsaberItem.class, new LightsaberItemRender());

        ItemPropertiesRegistry.register(ModItems.LIGHTSABER.get(), Constants.withId("model"),
                ((stack, level, holder, seed) -> (float) LightsaberTag.getOrCreate(stack).model * 0.1f));
    }
}
