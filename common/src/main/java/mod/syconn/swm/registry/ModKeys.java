package mod.syconn.swm.registry;

import mod.syconn.swm.util.Constants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeys {

    public static final KeyMapping TOGGLE_ITEM = new KeyMapping(keyId("toggle_item"), GLFW.GLFW_KEY_V, modCategory());

    public static String modCategory() {
        return "key.categories." + Constants.MOD;
    }

    private static String keyId(String id) {
        return "key." + Constants.MOD + "." + id;
    }
}
