package com.clocken.roguewright.mixin;

import net.minecraft.world.GameMode;
import net.minecraft.world.GameModeList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameModeList.class)
public abstract class GameModeListMixin {
    @Redirect(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameModeList;of([Lnet/minecraft/world/GameMode;)Lnet/minecraft/world/GameModeList;")
    )
    private static GameModeList roguewright$redirectList(GameMode[] gameModes) {
        return GameModeList.of(GameMode.ADVENTURE);
    }
}
