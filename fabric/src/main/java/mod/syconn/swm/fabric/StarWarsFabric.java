package mod.syconn.swm.fabric;

import dev.architectury.extensions.injected.InjectedItemExtension;
import mod.syconn.swm.StarWars;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.Item;

public final class StarWarsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        StarWars.init();
    }
}
