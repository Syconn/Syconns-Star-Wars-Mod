package mod.syconn.swm.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Function;

import static mod.syconn.swm.util.Constants.MOD;

public class ItemRegistrar {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD, Registries.ITEM);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<Item> LIGHTSABER = registerItem("lightsaber", new Item.Properties().stacksTo(1));

    public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("star_wars", () -> CreativeTabRegistry.create(
            Component.translatable("itemGroup." + MOD + ".star_wars"), () -> new ItemStack(Items.ACACIA_LEAVES)));

    @SuppressWarnings("unchecked")
    private static <T extends Item> RegistrySupplier<T> registerItem(String id, Item.Properties properties) {
        return (RegistrySupplier<T>) registerItem(id, Item::new, properties);
    }

    private static <T extends Item> RegistrySupplier<T> registerItem(String id, Function<Item.Properties, T> factory) {
        return registerItem(id, factory, new Item.Properties());
    }

    private static <T extends Item> RegistrySupplier<T> registerItem(String id, Function<Item.Properties, T> factory, Item.Properties properties) {
        return ITEMS.register(id, () -> factory.apply(properties.arch$tab(TAB)));
    }
}
