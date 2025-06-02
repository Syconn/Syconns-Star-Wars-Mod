package mod.syconn.swm.features.lightsaber.item;

import mod.syconn.swm.registry.ModItems;
import net.minecraft.world.item.Item;

public class LightsaberItem extends Item {

    public LightsaberItem() {
        super(new Properties().arch$tab(ModItems.TAB).stacksTo(1));
    }
}
