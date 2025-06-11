package mod.syconn.swm.neoforge.client.datagen;

import mod.syconn.swm.util.Constants;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@EventBusSubscriber(modid = Constants.MOD, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new LangProvider(generator.getPackOutput(), "en_us"));
        generator.addProvider(event.includeClient(), new ItemModels(generator.getPackOutput(), event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new DatapackBuiltinEntriesProvider(generator.getPackOutput(), event.getLookupProvider(), DatapackProvider.BUILDER, Set.of(Constants.MOD)));
        generator.addProvider(event.includeClient(), new LightsaberDataGenerator(generator.getPackOutput()));
    }
}
