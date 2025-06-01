package mod.syconn.swm.features.lightsaber.item;

import mod.syconn.swm.registry.ItemRegistrar;
import net.minecraft.world.item.Item;

//https://github.com/Parzivail-Modding-Team/GalaxiesParzisStarWarsMod/blob/1.20/projects/pswg/src/main/java/com/parzivail/pswg/features/lightsabers/client/LightsaberItemRenderer.java
//https://github.com/Parzivail-Modding-Team/GalaxiesParzisStarWarsMod/blob/1.20/projects/pswg/src/main/java/com/parzivail/pswg/mixin/ItemRendererMixin.java
//https://github.com/Parzivail-Modding-Team/GalaxiesParzisStarWarsMod/blob/1.20/projects/pswg/src/main/java/com/parzivail/pswg/mixin/WorldRendererMixin.java
//https://github.com/Parzivail-Modding-Team/GalaxiesParzisStarWarsMod/blob/1.20/projects/pswg/src/main/java/com/parzivail/pswg/client/render/entity/EnergyRenderer.java
public class LightsaberItem extends Item {

    public LightsaberItem() {
        super(new Properties().arch$tab(ItemRegistrar.TAB));
    }
}
