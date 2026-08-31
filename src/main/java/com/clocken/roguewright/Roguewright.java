package com.clocken.roguewright;

import com.natamus.starterkit_common_fabric.functions.StarterGearFunctions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class Roguewright implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            StarterGearFunctions.initStarterKitHandle(newPlayer.getServerWorld(), newPlayer, newPlayer.getServerWorld().getServer().getCommandSource());
        });
    }
}
