package com.clocken.roguewright;

import com.natamus.starterkit_common_fabric.functions.StarterGearFunctions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameMode;

public class Roguewright implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, player, alive) -> {
            ServerWorld world = player.getServerWorld();

            int survivorsCount = 0;
            for (ServerPlayerEntity p : world.getPlayers()) {
                if (p != player && !p.isInCreativeMode()) {
                    survivorsCount++;
                }
            }
            if (survivorsCount > 0) {
                player.changeGameMode(GameMode.SPECTATOR);
            } else {
                for (ServerPlayerEntity p : world.getPlayers()) {
                    p.changeGameMode(GameMode.ADVENTURE);
                }
                StarterGearFunctions.initStarterKitHandle(world, player, world.getServer().getCommandSource());
            }
        });

        ServerPlayerEvents.JOIN.register(player -> {
            for (String tag : player.getCommandTags()) {
                if (tag.equals("roguewright:joined")) {
                    return;
                }
            }
            player.changeGameMode(GameMode.ADVENTURE);
            player.addCommandTag("roguewright:joined");
        });
    }
}
