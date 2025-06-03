package mod.syconn.swm.addons;

import mod.syconn.swm.features.lightsaber.data.LightsaberData;
import mod.syconn.swm.util.json.JsonResourceReloader;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LightsaberContent {
    // TODO MIGHT NEED TO NETWORK IT
    public static final JsonResourceReloader<LightsaberData> LIGHTSABER_DATA = new JsonResourceReloader<>("lightsaber/defaults", LightsaberData::fromJson);

    public static List<ItemStack> getLightsabers() {
        var list = new ArrayList<ItemStack>();
        LIGHTSABER_DATA.sets().forEach(entry -> list.add(entry.getValue().toItem(entry.getKey().getPath())));
        return list;
    }
}
