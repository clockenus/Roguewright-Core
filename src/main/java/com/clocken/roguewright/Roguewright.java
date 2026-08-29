package com.clocken.roguewright;

import com.natamus.starterkit_common_fabric.functions.StarterGearFunctions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.player.PlayerEntity;

public class Roguewright implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, source, number) -> {
            if (entity instanceof PlayerEntity player) {
                player.getInventory().clear();
            }
            return true;
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            StarterGearFunctions.initStarterKitHandle(oldPlayer.getServerWorld(), newPlayer, oldPlayer.getServerWorld().getServer().getCommandSource());
        });
    }

}
