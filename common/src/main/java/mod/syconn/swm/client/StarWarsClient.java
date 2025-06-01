package mod.syconn.swm.client;

import mod.syconn.swm.features.lightsaber.client.LightsaberItemRender;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.util.client.render.IItemRenderer;

public class StarWarsClient {

    public static void init() {
        IItemRenderer.register(LightsaberItem.class, new LightsaberItemRender());
    }
}
