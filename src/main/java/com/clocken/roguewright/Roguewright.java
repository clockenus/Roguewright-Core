package com.clocken.roguewright;

import com.clocken.roguewright.registry.RWItems;
import com.serilum.starterkit_common_fabric.functions.StarterGearFunctions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Roguewright implements ModInitializer {
    public static final String MOD_ID = "roguewright";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        RWItems.initialize();

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
