package com.clocken.roguewright.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Shadow
    @Final
    PlayerInventory inventory;

    @Inject(method="dropInventory", at=@At("HEAD"), cancellable = true)
    private void roguewright$onDeath(CallbackInfo ci) {
        this.inventory.clear();
        ci.cancel();
    }
}
