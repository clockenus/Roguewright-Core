package com.clocken.roguewright.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"))
    private void roguewright$swingHand(Hand hand, CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity player) {
            World world = player.getWorld();

            if (!world.isClient ) {
                ItemStack itemStack = player.getStackInHand(player.getActiveHand());

                if (itemStack.getItem() instanceof PotionItem) {
                    ItemCooldownManager cooldownManager = player.getItemCooldownManager();

                    if (cooldownManager.isCoolingDown(itemStack.getItem())) return;
                    world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

                    PotionEntity potionEntity = new PotionEntity(world, player);
                    potionEntity.setItem(itemStack);
                    potionEntity.setVelocity(player, player.getPitch(), player.getYaw(), -20.0F, 0.75F, 1.0F);
                    world.spawnEntity(potionEntity);

                    itemStack.decrement(1);
                    cooldownManager.set(itemStack.getItem(), 20);
                }
            }
        }
    }
}