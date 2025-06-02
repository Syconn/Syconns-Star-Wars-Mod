package mod.syconn.swm.forge.client.datagen;

import mod.syconn.swm.features.lightsaber.data.LightsaberData;
import mod.syconn.swm.registry.ModTags;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class DatapackProvider {

    public static final RegistrySetBuilder DAMAGE_BUILDER = new RegistrySetBuilder().add(Registries.DAMAGE_TYPE, DatapackProvider::bootstrapDamageType);
    public static final RegistrySetBuilder LIGHTSABER_BUILDER = new RegistrySetBuilder().add(ModTags.LIGHTSABER_DATA, DatapackProvider::bootstrapLightsaber);

    private static void bootstrapDamageType(BootstapContext<DamageType> context) {
        context.register(ModTags.LIGHTSABER_DAMAGE, new DamageType("lightsaber", 0.1F));
    }

    private static void bootstrapLightsaber(BootstapContext<LightsaberData> context) {

    }
}
