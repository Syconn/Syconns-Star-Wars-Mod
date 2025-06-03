package mod.syconn.swm.util.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JsonResourceReloader<D> extends SimpleJsonResourceReloadListener {

    private final Map<ResourceLocation, D> resources = new HashMap<>();
    private final Function<JsonObject, D> reader;

    public JsonResourceReloader(String directory, Function<JsonObject, D> reader) {
        super(new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(), directory);
        this.reader = reader;
    }

    protected void apply(Map<ResourceLocation, JsonElement> pJsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
        pJsonMap.forEach(((resourceLocation, jsonElement) -> resources.put(resourceLocation, reader.apply(jsonElement.getAsJsonObject()))));
    }

    public void reload(final Map<ResourceLocation, D> resources) {
        this.resources.clear();
        this.resources.putAll(resources);
    }

    public Collection<D> all() {
        return resources.values();
    }

    public D get(ResourceLocation id) {
        return resources.get(id);
    }

    public D getOrDefault(ResourceLocation id, D _default) {
        return resources.get(id) != null ? resources.get(id) : _default;
    }
}