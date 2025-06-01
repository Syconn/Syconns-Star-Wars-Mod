package mod.syconn.swm.util.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;

public class NbtTools {

    public static Vec3 readVec3(CompoundTag tag) {
        return new Vec3(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));
    }

    public static CompoundTag writeVec3(Vec3 vec3) {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("x", vec3.x);
        tag.putDouble("y", vec3.y);
        tag.putDouble("z", vec3.z);
        return tag;
    }
}
