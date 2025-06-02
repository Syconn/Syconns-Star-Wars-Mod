package mod.syconn.swm;

import mod.syconn.swm.registry.ModItems;

public final class StarWars {
    public static void init() {
        ModItems.ITEMS.register();
        ModItems.TABS.register();
    }
}
