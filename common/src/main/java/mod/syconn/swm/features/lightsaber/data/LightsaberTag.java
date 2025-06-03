package mod.syconn.swm.features.lightsaber.data;

import mod.syconn.swm.addons.LightsaberContent;
import mod.syconn.swm.util.Constants;
import mod.syconn.swm.util.nbt.NbtTools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Consumer;

public class LightsaberTag {

    private static final String ID = "lightsaberData";

    private final String MODEL = "model";
    private final String STABLE = "stable";
    private final String LENGTH = "length";
    private final String LENGTH_SCALAR = "length_scalar";
    private final String RADIUS = "radius";
    private final String COLOR = "color";
    private final String EMITTER_POSITIONS = "emitter_pos";

    public int model;
    public boolean stable;
    public double length;
    public double lengthScalar;
    public double radius;
    public int color;
    public List<Vec3> emitterPositions;

    private LightsaberTag(CompoundTag tag) {
        this.model = tag.getInt(MODEL);
        this.stable = tag.getBoolean(STABLE);
        this.length = tag.getDouble(LENGTH);
        this.lengthScalar = tag.getDouble(LENGTH_SCALAR);
        this.radius = tag.getDouble(RADIUS);
        this.color = tag.getInt(COLOR);
        this.emitterPositions = NbtTools.getArray(tag.getCompound(EMITTER_POSITIONS), NbtTools::getVec3);
    }

    public LightsaberTag(int model, boolean stable, double length, double lengthScalar, double radius, int color, List<Vec3> emitterPositions) {
        this.model = model;
        this.stable = stable;
        this.length = length;
        this.lengthScalar = lengthScalar;
        this.radius = radius;
        this.color = color;
        this.emitterPositions = emitterPositions;
    }

    public static LightsaberTag getOrCreate(ItemStack stack) {
        if (!stack.getOrCreateTag().contains(ID)) return create(stack);
        return new LightsaberTag(stack.getOrCreateTag().getCompound(ID));
    }

    public static void update(ItemStack stack, Consumer<LightsaberTag> consumer) {
        var lT = getOrCreate(stack);
        consumer.accept(lT);
        lT.change(stack);
    }

    private static LightsaberTag create(ItemStack stack) {
        var lT = LightsaberContent.LIGHTSABER_DATA.get(Constants.withId("mace")).toTag(2);
        lT.change(stack);
        return lT;
    }

    public void change(ItemStack stack) {
        var tag = new CompoundTag();
        tag.putInt(MODEL, model);
        tag.putBoolean(STABLE, stable);
        tag.putDouble(LENGTH, length);
        tag.putDouble(LENGTH_SCALAR, lengthScalar);
        tag.putDouble(RADIUS, radius);
        tag.putInt(COLOR, color);
        tag.put(EMITTER_POSITIONS, NbtTools.putArray(emitterPositions, NbtTools::putVec3));
        stack.getOrCreateTag().put(ID, tag);
    }
}
