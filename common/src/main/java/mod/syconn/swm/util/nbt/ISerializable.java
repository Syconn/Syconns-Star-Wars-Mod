package mod.syconn.swm.util.nbt;

import net.minecraft.nbt.Tag;

public interface ISerializable<T extends Tag> {

    T writeTag();
}
