package com.clocken.roguewright.client.mixin;

import archives.tater.stagger.PoiseMeterRenderer;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PoiseMeterRenderer.class)
public class PoiseMeterRendererMixin {
    @Inject(
            method = "render",
            at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V")
    )
    private static void roguewright$renderY(DrawContext context, int x, ClientPlayerEntity player, float tickDelta, CallbackInfo ci, @Local(name = "y") LocalIntRef y) {
        y.set(y.get() - 2);
    }

    @Inject(
            method = "shouldRender",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void roguewright$shouldRender(ClientPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
