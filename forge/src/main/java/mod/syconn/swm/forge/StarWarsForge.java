package mod.syconn.swm.forge;

import mod.syconn.swm.StarWars;
import dev.architectury.platform.forge.EventBuses;
import mod.syconn.swm.util.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD)
public final class StarWarsForge {
    public StarWarsForge() {
        EventBuses.registerModEventBus(Constants.MOD, FMLJavaModLoadingContext.get().getModEventBus());

        StarWars.init();
    }
}
