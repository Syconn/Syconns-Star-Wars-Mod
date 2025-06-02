package mod.syconn.swm.features.lightsaber.client;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.syconn.swm.client.render.entity.PlasmaRenderer;
import mod.syconn.swm.features.lightsaber.data.LightsaberDefaults;
import mod.syconn.swm.features.lightsaber.data.LightsaberTag;
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
        renderDirect(stack, renderMode, poseStack, bufferSource, light, overlay);

        poseStack.popPose();
    }

    public void renderDirect(ItemStack stack, ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        if (!(stack.getItem() instanceof LightsaberItem)) return;

        var lT = LightsaberTag.get(stack);

        if (renderMode != ItemDisplayContext.GUI) {
            var handPos = !lT.emitterPositions.isEmpty() ? lT.emitterPositions.get(0) : new Vec3(0, 0, 0);
            poseStack.translate(-handPos.x, -handPos.y, -handPos.z);
            PlasmaRenderer.renderPlasma(poseStack, bufferSource, light, overlay, !lT.stable, lT.length, lT.lengthScalar, lT.radius, true, lT.color);
        }
    }
}
