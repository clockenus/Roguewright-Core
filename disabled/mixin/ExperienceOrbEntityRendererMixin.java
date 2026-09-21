package com.clocken.roguewright.client.mixin;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.ExperienceOrbEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(ExperienceOrbEntityRenderer.class)
public class ExperienceOrbEntityRendererMixin {
    @Inject(method = "vertex", at = @At("HEAD"), cancellable = true)
    private static void roguewright$vertex(VertexConsumer vertexConsumer, MatrixStack.Entry matrix, float x, float y, int red, int green, int blue, float u, float v, int light, CallbackInfo ci) {
        vertexConsumer.vertex(matrix, x, y, 0.0F).color(96, 160, 255, 128).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, 0.0F, 1.0F, 0.0F);
        ci.cancel();
    }
}
