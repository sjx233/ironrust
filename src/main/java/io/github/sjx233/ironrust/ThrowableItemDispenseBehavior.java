package io.github.sjx233.ironrust;

import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class ThrowableItemDispenseBehavior extends ProjectileDispenserBehavior {
  private final Factory factory;

  public ThrowableItemDispenseBehavior(Factory factory) {
    this.factory = factory;
  }

  @Override
  protected ProjectileEntity createProjectile(World world, Position pos, ItemStack stack) {
    ThrownItemEntity entity = this.factory.create(world, pos.getX(), pos.getY(), pos.getZ());
    entity.setItem(stack);
    return entity;
  }

  @FunctionalInterface
  public static interface Factory {
    public ThrownItemEntity create(World world, double x, double y, double z);
  }
}
