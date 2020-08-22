package io.github.sjx233.ironrust.world.entity;

import java.util.ArrayList;
import java.util.List;

import io.github.sjx233.ironrust.world.effect.IronRustMobEffects;
import io.github.sjx233.ironrust.world.item.IronRustItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class RustPowder extends ThrownItemEntity {
  public static final List<Entity> pendingTeleportations = new ArrayList<>();

  public RustPowder(EntityType<? extends RustPowder> type, World world) {
    super(type, world);
  }

  public RustPowder(World world, LivingEntity thrower) {
    super(IronRustEntityType.RUST_POWDER, thrower, world);
  }

  public RustPowder(World world, double x, double y, double z) {
    super(IronRustEntityType.RUST_POWDER, x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return IronRustItems.RUST_POWDER;
  }

  @Override
  public void handleStatus(byte id) {
    if (id == 3) {
      ParticleEffect particle = new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(IronRustItems.RUST_POWDER));
      for (int i = 0; i < 8; i++)
        this.world.addParticle(particle, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
    }
  }

  @Override
  protected void onCollision(HitResult result) {
    if (result.getType() == HitResult.Type.ENTITY) this.hitEntity(((EntityHitResult) result).getEntity());
    if (!this.world.isClient) {
      this.world.sendEntityStatus(this, (byte) 3);
      this.remove();
    }
  }

  private void hitEntity(Entity entity) {
    boolean isServer = entity.world instanceof ServerWorld;
    boolean isLiving = entity instanceof LivingEntity;
    if (isLiving && ((LivingEntity) entity).hasStatusEffect(IronRustMobEffects.RUST)) {
      if (isServer) pendingTeleportations.add(entity);
      return;
    }
    entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 8f);
    if (isServer && isLiving && entity.isAlive()) {
      if (entity.getType() == EntityType.COW) {
        RustyCow newEntity = IronRustEntityType.RUSTY_COW.create(entity.world);
        newEntity.copyPositionAndRotation(entity);
        newEntity.setVelocity(entity.getVelocity());
        newEntity.fallDistance = entity.fallDistance;
        newEntity.setAiDisabled(((MobEntity) entity).isAiDisabled());
        if (entity.hasCustomName()) {
          newEntity.setCustomName(entity.getCustomName());
          newEntity.setCustomNameVisible(entity.isCustomNameVisible());
        }
        entity.world.spawnEntity(newEntity);
        entity.remove();
      }
    }
  }
}
