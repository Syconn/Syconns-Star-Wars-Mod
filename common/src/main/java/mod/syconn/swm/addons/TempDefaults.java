package mod.syconn.swm.addons;

import mod.syconn.swm.features.lightsaber.data.LightsaberData;
import mod.syconn.swm.util.math.ColorUtil;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TempDefaults {

    public static final int GREEN = ColorUtil.packHsv(0.36f, 1f, 0.5f);
    public static final int BLUE = ColorUtil.packHsv(0.6f, 0.85f, 0.5f);
    public static final int PURPLE = ColorUtil.packHsv(0.8f, 1f, 0.5f);

    public enum LightsaberTypes {
        YODA("yoda", new LightsaberData(true, 1.4, 0.85, GREEN, List.of(new Vec3(0, 0.8, 0)))),
        LUKE("luke", new LightsaberData(true, 1.6, 1, GREEN, List.of(new Vec3(0.02, -0.025, 0.135)))),
        ANAKIN("anakin", new LightsaberData(true, 1.2, 1, BLUE, List.of(new Vec3(-0.025, -0.15, 0.03)))),
        OBI_WAN("obi", new LightsaberData(true, 1.4, 1, BLUE, List.of(new Vec3(-0, -0.21, 0)))),
        MACE("mace", new LightsaberData(true, 1.4, 1, PURPLE, List.of(new Vec3(0.045, 0, -0.04))));

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
