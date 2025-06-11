package mod.syconn.swm.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public class ItemStackMixin { // TODO FIX THIS

//    @Inject(method = "getAttributeModifiers", at = @At(value = "RETURN"), cancellable = true)
//    public void getAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<Attribute, AttributeModifier>> cir) {
//        var stack = (ItemStack) (Object) this;
//        if (stack.getItem() instanceof IItemExtensions ext) {
//            if (!stack.hasTag() || !stack.getTag().contains("AttributeModifiers", 9)) {
//                cir.setReturnValue(ext.getAttributeModifiers(stack, slot));
//            }
//        }
//    }
}
