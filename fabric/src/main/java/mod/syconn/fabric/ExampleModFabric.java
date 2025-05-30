package mod.syconn.fabric;

import net.fabricmc.api.ModInitializer;

import mod.syconn.ExampleMod;

public final class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}
