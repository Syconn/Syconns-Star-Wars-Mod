package mod.syconn.swm.features.lightsaber.data;

import com.google.gson.JsonObject;
import mod.syconn.swm.util.json.JsonUtils;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record LightsaberData(boolean stable, float length, float radius, int color, List<Vec3> emitterPositions) {

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("stable", stable);
        json.addProperty("length", length);
        json.addProperty("radius", radius);
        json.addProperty("color", color);
        json.add("emitterPositions", JsonUtils.addArray(emitterPositions, JsonUtils::addVec3));
        return json;
    }
}
