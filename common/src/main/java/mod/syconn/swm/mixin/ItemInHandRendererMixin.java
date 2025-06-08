package mod.syconn.swm.mixin;

import mod.syconn.swm.util.client.IItemExtensions;
import mod.syconn.swm.util.client.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Final
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private ItemStack mainHandItem;
    @Shadow
    private float oMainHandHeight;
    @Shadow
    private ItemStack offHandItem;
    @Shadow
    private float oOffHandHeight;
    @Shadow
    private float mainHandHeight;
    @Shadow
    private float offHandHeight;

    @Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
    private void tick(CallbackInfo cir) {
        LocalPlayer localPlayer = this.minecraft.player;
        if (localPlayer != null) {
            ItemStack itemStack = localPlayer.getMainHandItem();
            ItemStack itemStack2 = localPlayer.getOffhandItem();

            if (itemStack.getItem() instanceof IItemExtensions || itemStack2.getItem() instanceof IItemExtensions) {
                this.oMainHandHeight = this.mainHandHeight;
                this.oOffHandHeight = this.offHandHeight;

                if (ItemStack.matches(this.mainHandItem, itemStack)) this.mainHandItem = itemStack;
                if (ItemStack.matches(this.offHandItem, itemStack2))this.offHandItem = itemStack2;

                if (localPlayer.isHandsBusy()) {
                    this.mainHandHeight = Mth.clamp(this.mainHandHeight - 0.4f, 0.0f, 1.0f);
                    this.offHandHeight = Mth.clamp(this.offHandHeight - 0.4f, 0.0f, 1.0f);
                } else {
                    float f = localPlayer.getAttackStrengthScale(1.0f);
                    boolean reequipM = RenderUtil.handleReequipAnimation(this.mainHandItem, itemStack, localPlayer.getInventory().selected);
                    boolean reequipO = RenderUtil.handleReequipAnimation(this.offHandItem, itemStack2, -1);

                    if (!reequipM && this.mainHandItem != itemStack) this.mainHandItem = itemStack;
                    if (!reequipO && this.offHandItem != itemStack2) this.offHandItem = itemStack2;

                    this.mainHandHeight += Mth.clamp((!reequipM ? f * f * f : 0.0f) - this.mainHandHeight, -0.4f, 0.4f);
                    this.offHandHeight += Mth.clamp((float)(!reequipO ? 1 : 0) - this.offHandHeight, -0.4f, 0.4f);
                }

                if (this.mainHandHeight < 0.1F) this.mainHandItem = itemStack;
                if (this.offHandHeight < 0.1F) this.offHandItem = itemStack2;
                cir.cancel();
            }
        }
    }
}
