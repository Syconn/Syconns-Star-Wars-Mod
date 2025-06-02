package mod.syconn.swm.features.lightsaber.data;

import net.minecraft.world.phys.Vec3;

import java.util.List;

public record LightsaberData(boolean stable, float length, float radius, int color, List<Vec3> emitterPositions) {

    public LightsaberTag getTag() {
        return new LightsaberTag(stable, length, 1f, radius, color, emitterPositions);
    }
}
