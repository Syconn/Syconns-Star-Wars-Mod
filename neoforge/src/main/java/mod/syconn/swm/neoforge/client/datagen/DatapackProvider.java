package mod.syconn.swm.neoforge.client.datagen;

import mod.syconn.swm.core.ModTags;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageType;

public class DatapackProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.DAMAGE_TYPE, DatapackProvider::bootstrapDamageType);

    private static void bootstrapDamageType(BootstrapContext<DamageType> context) {
        context.register(ModTags.LIGHTSABER_DAMAGE, new DamageType("lightsaber", 0.1F));
    }
}
