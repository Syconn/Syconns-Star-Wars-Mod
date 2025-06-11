package mod.syconn.swm.neoforge.client.datagen;

import mod.syconn.swm.core.ModItems;
import mod.syconn.swm.neoforge.client.data.LightsaberDefaults;
import mod.syconn.swm.util.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Constants.MOD, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ItemModelBuilder builder = getBuilder(getItemId(ModItems.LIGHTSABER.get()).toString()).parent(generated("lightsaber/yoda"));

        for (var lightsaber : LightsaberDefaults.LightsaberTypes.values())
            builder.override().predicate(Constants.withId("model"), lightsaber.getData().model() * 0.1f).model(generated("lightsaber/" + lightsaber.getId())).end();
    }

    private ModelFile generated(String loc) {
        return new ModelFile.UncheckedModelFile(modLoc("item/" + loc));
    }

    private ResourceLocation getItemId(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }
}
