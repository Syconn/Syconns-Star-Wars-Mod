package mod.syconn.swm.util.json;

import com.google.gson.JsonObject;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Function;

public class JsonUtils {

//    public static <T> List<T> getArray(CompoundTag tag, Function<CompoundTag, T> function) {
//        List<T> list = new ArrayList<>();
//        for (int i = 0; i < tag.getInt("len"); i++) list.add(function.apply(tag.getCompound(String.valueOf(i))));
//        return list;
//    }

    public static <T> JsonObject addArray(List<T> elements, Function<T, JsonObject> function) {
        JsonObject json = new JsonObject();
        for (var i = 0; i < elements.size(); i++) json.add(String.valueOf(i), function.apply(elements.get(i)));
        return json;
    }

    public static JsonObject addVec3(Vec3 vec3) {
        JsonObject json = new JsonObject();
        json.addProperty("x", vec3.x);
        json.addProperty("y", vec3.y);
        json.addProperty("z", vec3.z);
        return json;
    }
}
