package mod.syconn.swm.neoforge.client.datagen;

import mod.syconn.swm.core.ModItems;
import mod.syconn.swm.core.ModKeys;
import mod.syconn.swm.util.Constants;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static mod.syconn.swm.util.Constants.MOD;

public class LangProvider extends LanguageProvider {

    public LangProvider(PackOutput output, String locale) {
        super(output, Constants.MOD, locale);
    }

    protected void addTranslations() {
        add("itemGroup." + MOD + ".star_wars", "Star Wars");

        add(ModKeys.modCategory(), "Star Wars Controls");
        add("key.swm.toggle_item", "Toggle Held Item");
        add("key.swm.power_1", "Use Force Power 1");

        addItem(ModItems.LIGHTSABER, "Lightsaber");
    }
}
