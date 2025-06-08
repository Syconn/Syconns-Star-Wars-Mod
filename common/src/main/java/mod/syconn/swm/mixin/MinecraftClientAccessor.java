package mod.syconn.swm.mixin;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public interface MinecraftClientAccessor {
    @Accessor("pausedTickDelta")
    float getPausedTickDelta();
}
