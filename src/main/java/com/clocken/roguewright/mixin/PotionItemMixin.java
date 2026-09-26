package com.clocken.roguewright.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin implements ProjectileItem {
    @Shadow
    public abstract TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        PotionEntity potionEntity = new PotionEntity(world, pos.getX(), pos.getY(), pos.getZ());
        potionEntity.setItem(stack);
        return potionEntity;
    }

    @Override
    public ProjectileItem.Settings getProjectileSettings() {
        return Settings.builder().uncertainty(Settings.DEFAULT.uncertainty() * 0.5F).power(Settings.DEFAULT.power() * 1.25F).build();
    }
}
