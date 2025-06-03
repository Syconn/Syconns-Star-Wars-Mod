package mod.syconn.swm.client;

import dev.architectury.registry.ReloadListenerRegistry;
import mod.syconn.swm.addons.LightsaberContent;
import mod.syconn.swm.features.lightsaber.client.LightsaberItemRender;
import mod.syconn.swm.features.lightsaber.data.LightsaberData;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.util.client.render.IItemRenderer;
import mod.syconn.swm.util.json.JsonResourceReloader;
import net.minecraft.server.packs.PackType;

public class StarWarsClient {

    public static void init() {
        IItemRenderer.register(LightsaberItem.class, new LightsaberItemRender());
    }
}
