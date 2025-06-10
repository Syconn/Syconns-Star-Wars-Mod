package mod.syconn.swm.fabric.client;

import mod.syconn.swm.client.StarWarsClient;
import net.fabricmc.api.ClientModInitializer;

public final class StarWarsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        StarWarsClient.setup();
    }
}
