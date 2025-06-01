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

        switch (renderMode) {
            case NONE:
            case GROUND:
            case HEAD:
            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
                break;
            case FIXED:
            case GUI:
                poseStack.scale(0.25f, 0.25f, 0.25f);
                poseStack.translate(0, -2.85, 0);
                break;
        }

        renderDirect(stack, renderMode, poseStack, bufferSource, light, overlay, true);

        poseStack.popPose();
    }

    public void renderDirect(ItemStack stack, ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean useHandPos) {
        if (!(stack.getItem() instanceof LightsaberItem li)) return;

//        poseStack.pushPose();
        var unstable = false;
        var baseLength = 1.6f;
        var lengthCoefficient = 1;

//        var handPos = new Vec3(0, 0, 0);
//        if (useHandPos) handPos = new Vec3(0, -0.85f, 0);
//        poseStack.translate(-handPos.x, -handPos.y, -handPos.z); // CUSTOM ITEM RENDER HERE
//        poseStack.popPose();

        var handPos = new Vec3(0f, 4, 0f);
        poseStack.scale(0.2f, 0.2f, 0.2f);
        poseStack.translate(-handPos.x, -handPos.y, -handPos.z);
        poseStack.scale(5, 5, 5);
        renderBlade(renderMode, poseStack, bufferSource, light, overlay, unstable, lengthCoefficient, baseLength);
    }

    private static void renderBlade(ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean unstable, float lengthCoefficient, float length) {
        PlasmaRenderer.renderPlasma(renderMode, poseStack, bufferSource, light, overlay, unstable, length, lengthCoefficient, 0.80f, true, ColorUtil.packHsv(0.6f, 0.85f, 0.5f));
    }
}
