package mod.syconn.swm.util.client;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IItemExtensions {

    default boolean shouldCauseReequipAnimation(@NotNull ItemStack from, @NotNull ItemStack to, boolean changed) {
        return true;
    }

//    default Multimap<Attribute, AttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) { TODO NEED TO FIGURE OUT HOW TO DO THIS IN NEW VERSION
//        return ((Item) this).getDefaultAttributeModifiers(slot);
//    }
}
