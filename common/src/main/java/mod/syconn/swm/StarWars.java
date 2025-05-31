package mod.syconn.swm;

import mod.syconn.swm.registry.ItemRegistrar;

public final class StarWars {
    public static void init() {
        ItemRegistrar.ITEMS.register();
        ItemRegistrar.TABS.register();
    }
}
