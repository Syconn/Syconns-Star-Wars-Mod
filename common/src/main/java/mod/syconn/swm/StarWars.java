package mod.syconn.swm;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import mod.syconn.swm.addons.LightsaberContent;
import mod.syconn.swm.client.StarWarsClient;
import mod.syconn.swm.core.ModEntities;
import mod.syconn.swm.network.Network;
import mod.syconn.swm.core.ModItems;
import net.minecraft.server.packs.PackType;

public final class StarWars {
    public static void init() {
        ModItems.ITEMS.register();
        ModItems.TABS.register();
        ModEntities.ENTITIES.register();

        Network.init();

        CreativeTabRegistry.modify(ModItems.TAB, ModItems::addCreative);

        ReloadListenerRegistry.register(PackType.SERVER_DATA, LightsaberContent.LIGHTSABER_DATA);

        EnvExecutor.runInEnv(Env.CLIENT, () -> StarWarsClient::init);
    }
}
