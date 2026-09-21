package com.clocken.roguewright.client.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @ModifyConstant(method = "renderExperienceLevel", constant = @Constant(intValue = 8453920))
    private int roguewright$replaceColor(int i) {
        return 8246783;
    }
}
