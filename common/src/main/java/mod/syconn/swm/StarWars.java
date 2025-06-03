package mod.syconn.swm;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.ReloadListenerRegistry;
import mod.syconn.swm.addons.LightsaberContent;
import mod.syconn.swm.registry.ModItems;
import net.minecraft.server.packs.PackType;

public final class StarWars {
    public static void init() {
        ModItems.ITEMS.register();
        ModItems.TABS.register();

        CreativeTabRegistry.modify(ModItems.TAB, ModItems::addCreative);

        ReloadListenerRegistry.register(PackType.SERVER_DATA, LightsaberContent.LIGHTSABER_DATA);
    }
}
