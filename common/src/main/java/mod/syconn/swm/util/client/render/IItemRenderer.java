package mod.syconn.swm.util.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public interface IItemRenderer {

    Map<Class<? extends Item>, IItemRenderer> INSTANCES = new HashMap<>();

    static void register(Class<? extends Item> clazz, IItemRenderer renderer) {
        INSTANCES.put(clazz, renderer);
    }

    void render(LivingEntity entity, ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, BakedModel model);
}
