package mod.syconn.swm.forge.client.datagen;

import mod.syconn.swm.registry.ItemRegistrar;
import mod.syconn.swm.util.Constants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, Constants.MOD, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ItemModelBuilder builder = withExistingParent(ItemRegistrar.LIGHTSABER.get().toString(), modLoc("item/lightsaber_off/yoda"));

//        for (LightsaberData.HandleType type : LightsaberData.HandleType.values()){
//            builder.override().predicate(new ResourceLocation("model"), type.getId()).predicate(new ResourceLocation("active"), 0.0f).model(generated("item/lightsaber_off/" + type.getType())).end();
//            builder.override().predicate(new ResourceLocation("model"), type.getId()).predicate(new ResourceLocation("active"), 1.0f).model(generated("item/lightsaber_on/" + type.getType())).end();
//        }
    }

    private ModelFile generated(String loc) {
        return new ModelFile.UncheckedModelFile(modLoc(loc));
    }
}
