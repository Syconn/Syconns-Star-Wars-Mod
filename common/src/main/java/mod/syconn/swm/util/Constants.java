package mod.syconn.swm.util;

import net.minecraft.resources.ResourceLocation;

public class Constants {

    public static final String MOD = "swm";

    public static ResourceLocation withId(String s) {
        return new ResourceLocation(MOD, s);
    }
}
