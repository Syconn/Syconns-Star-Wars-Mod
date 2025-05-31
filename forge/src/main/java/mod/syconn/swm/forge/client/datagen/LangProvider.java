package mod.syconn.swm.forge.client.datagen;

import mod.syconn.swm.registry.ItemRegistrar;
import mod.syconn.swm.util.Constants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {

    public LangProvider(PackOutput output, String locale) {
        super(output, Constants.MOD, locale);
    }

    protected void addTranslations() {
        addItem(ItemRegistrar.LIGHTSABER, "Lightsaber");
    }
}
