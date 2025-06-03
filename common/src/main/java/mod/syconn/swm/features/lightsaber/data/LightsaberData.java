package mod.syconn.swm.features.lightsaber.data;

import com.google.gson.JsonObject;
import mod.syconn.swm.registry.ModItems;
import mod.syconn.swm.util.json.JsonUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record LightsaberData(int model, boolean stable, double length, double radius, int color, List<Vec3> emitterPositions) {

    public LightsaberTag toTag() {
        return new LightsaberTag(model, stable, length, 1, radius, color, emitterPositions);
    }

    public LightsaberTag toTag(double lengthScalar) {
        return new LightsaberTag(model, stable, length, lengthScalar, radius, color, emitterPositions);
    }

    public ItemStack toItem() {
        var stack = new ItemStack(ModItems.LIGHTSABER.get());
        toTag().change(stack);
        return stack;
    }

    public ItemStack toItem(String name) {
        var stack = new ItemStack(ModItems.LIGHTSABER.get());
        toTag().change(stack);
        stack.setHoverName(Component.literal(name.substring(0, 1).toUpperCase() + name.substring(1) + " Lightsaber"));
        return stack;
    }

    public static LightsaberData fromJson(JsonObject json) {
        return new LightsaberData(json.get("model").getAsInt(), json.get("stable").getAsBoolean(), json.get("length").getAsDouble(), json.get("radius").getAsDouble(),
                json.get("color").getAsInt(), JsonUtils.getArray(json.getAsJsonObject("emitterPositions"), JsonUtils::getVec3));
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("model", model);
        json.addProperty("stable", stable);
        json.addProperty("length", length);
        json.addProperty("radius", radius);
        json.addProperty("color", color);
        json.add("emitterPositions", JsonUtils.addArray(emitterPositions, JsonUtils::addVec3));
        return json;
    }
}
