package mod.syconn.swm.features.lightsaber.client;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.syconn.swm.client.render.entity.PlasmaRenderer;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.util.client.render.IItemRenderer;
import mod.syconn.swm.util.math.ColorUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class LightsaberItemRender implements IItemRenderer {

    @Override
    public void render(LivingEntity entity, ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, BakedModel model) {
        poseStack.pushPose();

        model.getTransforms().getTransform(renderMode).apply(leftHanded, poseStack);
        renderDirect(stack, renderMode, poseStack, bufferSource, light, overlay, true);

        poseStack.popPose();
    }

    public void renderDirect(ItemStack stack, ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean useHandPos) {
        if (!(stack.getItem() instanceof LightsaberItem li)) return;

        var unstable = false;
        var baseLength = 1.6f;
        var lengthCoefficient = 1;

        if (renderMode != ItemDisplayContext.GUI) {
            var handPos = new Vec3(0f, 4, 0f);
            poseStack.scale(0.2f, 0.2f, 0.2f);
            poseStack.translate(-handPos.x, -handPos.y, -handPos.z);
            poseStack.scale(5, 5, 5);
            PlasmaRenderer.renderPlasma(renderMode, poseStack, bufferSource, light, overlay, unstable, baseLength, lengthCoefficient, 0.80f, true, ColorUtil.packHsv(0.6f, 0.85f, 0.5f));
        }
    }
}
