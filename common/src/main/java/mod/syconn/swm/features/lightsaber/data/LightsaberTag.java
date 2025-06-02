package mod.syconn.swm.features.lightsaber.data;

import mod.syconn.swm.util.nbt.NbtTools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Consumer;

public class LightsaberTag {

    private static final String ID = "lightsaberData";

    private final String STABLE = "stable";
    private final String LENGTH = "length";
    private final String LENGTH_SCALAR = "length_scalar";
    private final String RADIUS = "radius";
    private final String COLOR = "color";
    private final String EMITTER_POSITIONS = "emitter_pos";

    public boolean stable;
    public float length;
    public float lengthScalar;
    public float radius;
    public int color;
    public List<Vec3> emitterPositions;

    private LightsaberTag(CompoundTag tag) {
        this.stable = tag.getBoolean(STABLE);
        this.length = tag.getFloat(LENGTH);
        this.lengthScalar = tag.getFloat(LENGTH_SCALAR);
        this.radius = tag.getFloat(RADIUS);
        this.color = tag.getInt(COLOR);
        this.emitterPositions = NbtTools.getArray(tag.getCompound(EMITTER_POSITIONS), NbtTools::getVec3);
    }

    public LightsaberTag(boolean stable, float length, float lengthScalar, float radius, int color, List<Vec3> emitterPositions) {
        this.stable = stable;
        this.length = length;
        this.lengthScalar = lengthScalar;
        this.radius = radius;
        this.color = color;
        this.emitterPositions = emitterPositions;
    }

    public static LightsaberTag get(ItemStack stack) {
        if (!stack.getOrCreateTag().contains(ID)) return create(stack);
        return new LightsaberTag(stack.getOrCreateTag().getCompound(ID));
    }

    public static void update(ItemStack stack, Consumer<LightsaberTag> consumer) {
        var lT = get(stack);
        consumer.accept(lT);
        lT.change(stack);
    }

    private static LightsaberTag create(ItemStack stack) {
        var lT = LightsaberDefaults.LightsaberTypes.YODA.getData().getTag();
        lT.change(stack);
        return lT;
    }

    private void change(ItemStack stack) {
        var tag = new CompoundTag();
        tag.putBoolean(STABLE, stable);
        tag.putFloat(LENGTH, length);
        tag.putFloat(LENGTH_SCALAR, lengthScalar);
        tag.putFloat(RADIUS, radius);
        tag.putInt(COLOR, color);
        tag.put(EMITTER_POSITIONS, NbtTools.putArray(emitterPositions, NbtTools::putVec3));
        stack.getOrCreateTag().put(ID, tag);
    }
}
