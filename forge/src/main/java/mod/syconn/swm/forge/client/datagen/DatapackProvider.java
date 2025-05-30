package mod.syconn.swm.forge.client.datagen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class DatapackProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, DatapackProvider::bootstrap);

    private static void bootstrap(BootstapContext<DamageType> context) {
//        context.register(ModDamage.LIGHTSABER, new DamageType("lightsaber", 0.1F));
//        context.register(ModDamage.BLASTER, new DamageType("blaster", 0.1F));
    }
}
