package mod.syconn.swm.addons;

import mod.syconn.swm.features.lightsaber.data.LightsaberData;
import mod.syconn.swm.util.json.JsonResourceReloader;

public class LightsaberContent {
    // TODO MIGHT NEED TO Network IT
    public static final JsonResourceReloader<LightsaberData> LIGHTSABER_DATA = new JsonResourceReloader<>("lightsaber/defaults", LightsaberData::fromJson);
}
