package mod.syconn.swm.features.lightsaber.data;

import mod.syconn.swm.util.math.ColorUtil;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LightsaberDefaults {

    public static final int GREEN = ColorUtil.packHsv(0.36f, 1f, 0.5f);
    public static final int BLUE = ColorUtil.packHsv(0.6f, 0.85f, 0.5f);
    public static final int PURPLE = ColorUtil.packHsv(0.8f, 1f, 0.5f);

    public enum LightsaberTypes {
        YODA("yoda", new LightsaberData(true, 1.4f, 0.85f, GREEN, List.of(new Vec3(0f, 0.8f, 0f)))),
        LUKE("luke", new LightsaberData(true, 1.6f, 1f, GREEN, List.of(new Vec3(0.02f, -0.025f, 0.135f)))),
        ANAKIN("anakin", new LightsaberData(true, 1.2f, 1f, BLUE, List.of(new Vec3(-0.025f, -0.15f, 0.03f)))),
        OBI_WAN("obi", new LightsaberData(true, 1.4f, 1f, BLUE, List.of(new Vec3(-0f, -0.21f, 0f)))),
        MACE("mace", new LightsaberData(true, 1.4f, 1f, PURPLE, List.of(new Vec3(0.045f, 0f, -0.04f))));

        private final String id;
        private final LightsaberData data;

        LightsaberTypes(String id, LightsaberData data) {
            this.id = id;
            this.data = data;
        }

        public String getId() {
            return id;
        }

        public LightsaberData getData() {
            return data;
        }
    }
}
