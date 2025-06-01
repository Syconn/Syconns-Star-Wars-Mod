package mod.syconn.swm.features.lightsaber.data;

import mod.syconn.swm.util.math.ColorUtil;
import mod.syconn.swm.util.nbt.NbtTools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

public class LightsaberTag {

    private static final String ID = "lightsaberData";

    private final String STABLE = "stable";
    private final String LENGTH = "length";
    private final String LENGTH_SCALAR = "length_scalar";
    private final String RADIUS = "radius";
    private final String COLOR = "color";
    private final String EMITTER_POS = "emitter_pos";

    private boolean stable;
    private float length;
    private float lengthScalar;
    private float radius;
    private int color;
    private Vec3 emitterPos;

    private LightsaberTag(CompoundTag tag) {
        this.stable = tag.getBoolean(STABLE);
        this.length = tag.getFloat(LENGTH);
        this.lengthScalar = tag.getFloat(LENGTH_SCALAR);
        this.radius = tag.getFloat(RADIUS);
        this.color = tag.getInt(COLOR);
        this.emitterPos = NbtTools.readVec3(tag.getCompound(EMITTER_POS));
    }

    public LightsaberTag(boolean stable, float length, float lengthScalar, float radius, int color, Vec3 emitterPos) {
        this.stable = stable;
        this.length = length;
        this.lengthScalar = lengthScalar;
        this.radius = radius;
        this.color = color;
        this.emitterPos = emitterPos;
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
        var lT = new LightsaberTag(true, 1.6f, 1, 0.80f, ColorUtil.packHsv(0.6f, 0.85f, 0.5f), new Vec3(0f, 4, 0f));
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
        tag.put(EMITTER_POS, NbtTools.writeVec3(emitterPos));
        stack.getOrCreateTag().put(ID, tag);
    }

    public boolean isStable() {
        return stable;
    }

    public float getLength() {
        return length;
    }

    public float getLengthScalar() {
        return lengthScalar;
    }

    public float getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public Vec3 getEmitterPos() {
        return emitterPos;
    }
}
