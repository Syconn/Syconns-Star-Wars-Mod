package mod.syconn.swm.util.client;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IItemExtensions {

    default boolean shouldCauseReequipAnimation(@NotNull ItemStack from, @NotNull ItemStack to, boolean changed) {
        return false;
    }
}
