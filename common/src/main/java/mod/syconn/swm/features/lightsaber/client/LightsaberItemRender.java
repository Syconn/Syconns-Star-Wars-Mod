package mod.syconn.swm.features.lightsaber.client;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.syconn.swm.client.render.entity.PlasmaRenderer;
import mod.syconn.swm.features.lightsaber.item.LightsaberItem;
import mod.syconn.swm.util.client.render.IItemRenderer;
import mod.syconn.swm.util.math.MathUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

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
                break;
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
                poseStack.translate(0, 0, 0.08f);
                break;
            case FIXED:
                poseStack.mulPose(new Quaternionf().rotationZ((float)(Math.PI / 4)));
                poseStack.mulPose(new Quaternionf().rotationY((float)(135 * Math.PI / 180)));
                MathUtil.scalePos(poseStack, 2f, 2f, 2f);
                break;
            case GUI:
                poseStack.mulPose(new Quaternionf().rotationZ((float)(Math.PI / -4)));
                poseStack.mulPose(new Quaternionf().rotationY((float)(Math.PI / -4)));
                MathUtil.scalePos(poseStack, 2f, 2f, 2f);
                break;
        }

        renderDirect(stack, renderMode, poseStack, bufferSource, light, overlay, false, true);

        poseStack.popPose();
    }

    public void renderDirect(ItemStack stack, ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean forceBlade, boolean useHandPos) {
        if (!(stack.getItem() instanceof LightsaberItem li)) return;

        poseStack.pushPose();
        MathUtil.scalePos(poseStack, 0.2f, 0.2f, 0.2f);

//        var lt = new LightsaberTag(stack.getOrCreateNbt());

        var unstable = false;
        var baseLength = 1.6f;
//        var lengthCoefficient = forceBlade ? 1 : lt.getSize(Client.getTickDelta()); FOR LOWERING / INCREASING
        var lengthCoefficient = 1;

        poseStack.pushPose();
//        var handSocket = m.transformables().get("main_hand");
        var handPos = new Vec3(0, 0, 0);
        if (useHandPos) handPos = new Vec3(0, -0.85f, 0);

        poseStack.translate(-handPos.x, -handPos.y, -handPos.z);
        poseStack.popPose();

        poseStack.popPose();

        if (renderMode != ItemDisplayContext.GUI) {
            poseStack.scale(0.2f, 0.2f, 0.2f);
            poseStack.translate(-handPos.x, -handPos.y, -handPos.z);
            poseStack.scale(5, 5, 5);
            renderBlade(renderMode, poseStack, bufferSource, light, overlay, unstable, lengthCoefficient, baseLength);
        }
    }

    private static void renderBlade(ItemDisplayContext renderMode, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean unstable, float lengthCoefficient, float length) {
        PlasmaRenderer.renderPlasma(renderMode, poseStack, bufferSource, light, overlay, unstable, length, lengthCoefficient, 1, true, 0xff2a00);
    }
}
