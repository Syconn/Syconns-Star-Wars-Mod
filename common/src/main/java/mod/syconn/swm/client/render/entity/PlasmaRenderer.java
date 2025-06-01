package mod.syconn.swm.client.render.entity;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import mod.syconn.swm.util.Constants;
import mod.syconn.swm.util.client.PlasmaBuffer;
import mod.syconn.swm.util.math.ColorUtil;
import mod.syconn.swm.util.math.MathUtil;
import net.minecraft.client.renderer.*;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;

public class PlasmaRenderer {

    public static final RenderType LAYER_ENERGY = RenderType.create(Constants.MOD + ":plasma", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, 256, false, true,
            RenderType.CompositeState.builder().setLayeringState(RenderStateShard.LayeringStateShard.VIEW_OFFSET_Z_LAYERING).setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
            .setShaderState(RenderStateShard.RENDERTYPE_LIGHTNING_SHADER).createCompositeState(true)); // TODO MAY NOT NEED TRUE THING

    public static void renderPlasma(ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean unstable, float baseLength, float lengthCoefficient, float radiusCoefficient, boolean cap, int glowHsv) {
        final VertexConsumer vc = bufferSource.getBuffer(LAYER_ENERGY);

        var totalLength = baseLength * lengthCoefficient;
        var shake = (1.1f - lengthCoefficient) * 0.004f;

        if (unstable) shake *= 2;

        double dX = (float)Constants.RANDOM.nextGaussian() * shake;
        double dY = (float)Constants.RANDOM.nextGaussian() * shake;
        poseStack.translate(dX, 0, dY);

        PlasmaBuffer.RENDER.init(vc, poseStack.last(), 1, 1, 1, 1, overlay, light);
        renderGlow(totalLength, radiusCoefficient, ColorUtil.hsvGetH(glowHsv), ColorUtil.hsvGetS(glowHsv), ColorUtil.hsvGetV(glowHsv), unstable, cap);
    }

    public static void renderGlow(float bladeLength, float radiusCoefficient, float glowHue, float glowSat, float glowVal, boolean unstable, boolean cap) {
        if (bladeLength == 0) return;

        var thicknessBottom = radiusCoefficient * 0.018f;
        var thicknessTop = radiusCoefficient * (cap ? 0.012f : thicknessBottom);
        var mL = 0;
        var xL = 14;
        var deltaThickness = radiusCoefficient * 0.0028f;
        var minOutputLayer = mL * thicknessBottom / deltaThickness;
        var globalTime = ((System.currentTimeMillis()) % Integer.MAX_VALUE) / 4f;

        for (var layer = mL; layer <= xL; layer++) {
            var time = ((System.currentTimeMillis() - layer * 10) % Integer.MAX_VALUE) / 200f;
            var noise = (float) Constants.SIMPLEX.getValue(0, time);
            var hueOffset = unstable ? (noise * 0.02f) : 0;
            var x = MathUtil.remap(layer, mL, xL, minOutputLayer, 60);
            var alpha = ColorUtil.getAlpha(x);

            if (alpha < 16 / 255f) continue;

            var color = ColorUtil.hsvToRgbInt(ColorUtil.getHue(glowHue + hueOffset, x), ColorUtil.getSaturation(x, glowSat), ColorUtil.getValue(x, glowVal));
            var layerThickness = deltaThickness * layer;
            PlasmaBuffer.RENDER.setColor(color, (int)(255 * alpha));

            if (layer > 0) { // glow layers
                PlasmaBuffer.RENDER.invertCull(true);
                PlasmaBuffer.RENDER.drawSolidBoxSkewTaper(thicknessTop + layerThickness, thicknessBottom + layerThickness, 0, bladeLength + layerThickness, 0, 0, -layerThickness, 0);
                PlasmaBuffer.RENDER.invertCull(false);
            } else { // core layer
                final var segments = unstable ? 35 : 1;
                final var dSegments = 1f / segments;
                final var dLength = bladeLength / segments;
                final var dLengthTime = 5;

                for (var i = 0; i < segments; i++) {
                    var topThicknessLerp = Mth.lerp(dSegments * (i + 1), thicknessBottom, thicknessTop);
                    var bottomThicknessLerp = Mth.lerp(dSegments * i, thicknessBottom, thicknessTop);

                    var dTTop = unstable ? (float) Constants.SIMPLEX.getValue(globalTime, dLengthTime * dLength * (i + 1)) * 0.005f : 0;
                    var dTBottom = unstable ? (float) Constants.SIMPLEX.getValue(globalTime, dLengthTime * dLength * i) * 0.005f : 0;

                    noise = (float) Constants.SIMPLEX.getValue(globalTime, 3 * dLength * i);
                    color = ColorUtil.hsvToRgbInt(0, (unstable ? (0.07f - noise * 0.07f) : 0) * glowSat, ColorUtil.getValue(x, glowVal));
                    PlasmaBuffer.RENDER.setColor(color, (int)(255 * ColorUtil.getAlpha(x)));
                    PlasmaBuffer.RENDER.drawSolidBoxSkewTaper(topThicknessLerp + dTTop, bottomThicknessLerp + dTBottom, 0, dLength * (i + 1), 0, 0, dLength * i, 0);
                }
            }
        }
    }

    public static void renderLayer(RenderBuffers buffers) {
        buffers.outlineBufferSource().getBuffer(LAYER_ENERGY).endVertex(); // TODO MAY NOT WORK
    }
}
