package mod.syconn.swm.features.lightsaber.item;

import mod.syconn.swm.addons.LightsaberContent;
import mod.syconn.swm.registry.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LightsaberItem extends Item {

    public LightsaberItem() {
        super(new Properties().arch$tab(ModItems.TAB).stacksTo(1));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        return super.use(level, player, usedHand);
    }
}
