package com.clocken.roguewright.client.mixin.perspective;

import com.mclegoman.perspective.config.ConfigHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfigHelper.class)
public class ConfigHelperMixin {
    @Inject(method = "showToasts", at = @At("HEAD"), cancellable = true)
    private static void roguewright$showToasts(CallbackInfo ci) {
        ci.cancel();
    }
}
