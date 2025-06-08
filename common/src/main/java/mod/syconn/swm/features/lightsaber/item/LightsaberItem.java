package mod.syconn.swm.features.lightsaber.item;

import mod.syconn.swm.features.lightsaber.data.LightsaberTag;
import mod.syconn.swm.util.client.IItemExtensions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LightsaberItem extends Item implements IItemExtensions {

    public LightsaberItem() {
        super(new Properties().stacksTo(1));
    }

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        LightsaberTag.update(stack, LightsaberTag::tick);
    }

    public boolean shouldCauseReequipAnimation(@NotNull ItemStack from, @NotNull ItemStack to, boolean changed) {
//        return !ItemStack.isSameItemSameTags(from, to);
        return false;
    }
}
