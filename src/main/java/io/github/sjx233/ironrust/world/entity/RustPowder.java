package io.github.sjx233.ironrust.world.entity;

import java.util.UUID;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.world.effect.IronRustMobEffects;
import io.github.sjx233.ironrust.world.item.IronRustItems;
import io.github.sjx233.ironrust.world.level.dimension.IronRustDimensions;
import net.minecraft.block.pattern.BlockPattern.TeleportTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.timer.Timer;
import net.minecraft.world.timer.TimerCallback;
import net.minecraft.world.timer.TimerCallbackSerializer;

public class RustPowder extends ThrownItemEntity {
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
    World world = entity.world;
    boolean isServer = world != null && !world.isClient;
    if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(IronRustMobEffects.RUST)) {
      if (isServer) {
        MinecraftServer server = ((ServerWorld) world).getServer();
        RegistryKey<World> source = world.getRegistryKey();
        // Are there better ways to schedule the teleportation?
        server.getSaveProperties().getMainWorldProperties().getScheduledEvents().setEvent(entity.getUuidAsString(), world.getTime() + 1,
          new TeleportTimerCallback(entity.getUuid(), source, IronRustDimensions.RUST.equals(source) ? World.OVERWORLD : IronRustDimensions.RUST));
      }
      return;
    }
    entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 8f);
    if (isServer && entity instanceof LivingEntity && !entity.removed
        && ((LivingEntity) entity).getHealth() > 0f) {
      if (entity.getType() == EntityType.COW) {
        RustyCow newEntity = IronRustEntityType.RUSTY_COW.create(world);
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

  public static class TeleportTimerCallback implements TimerCallback<MinecraftServer> {
    private final UUID entityId;
    private final RegistryKey<World> source;
    private final RegistryKey<World> destination;

    static {
      TimerCallbackSerializer.INSTANCE.registerSerializer(new TeleportTimerCallback.Serializer());
    }

    public TeleportTimerCallback(UUID entityId, RegistryKey<World> source, RegistryKey<World> destination) {
      this.entityId = entityId;
      this.source = source;
      this.destination = destination;
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, TeleportTimerCallback> {
      public Serializer() {
        super(IronRustMod.makeId("teleport"), TeleportTimerCallback.class);
      }

      public void serialize(CompoundTag tag, TeleportTimerCallback callback) {
        tag.putUuid("Entity", callback.entityId);
        tag.putString("Source", callback.source.getValue().toString());
        tag.putString("Destination", callback.destination.getValue().toString());
      }

      public TeleportTimerCallback deserialize(CompoundTag tag) {
        return new TeleportTimerCallback(tag.getUuid("Entity"),
          RegistryKey.of(Registry.DIMENSION, new Identifier(tag.getString("Source"))),
          RegistryKey.of(Registry.DIMENSION, new Identifier(tag.getString("Destination"))));
      }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void call(MinecraftServer server, Timer<MinecraftServer> events, long time) {
      ServerWorld source = server.getWorld(this.source);
      if (source == null) return;
      ServerWorld destination = server.getWorld(this.destination);
      if (destination == null) return;
      Entity entity = source.getEntity(this.entityId);
      if (entity == null) return;
      net.fabricmc.fabric.api.dimension.v1.FabricDimensions.teleport(entity, destination, TeleportTimerCallback::placeEntity);
    }

    private static TeleportTarget placeEntity(Entity teleported, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) {
      BlockPos pos = teleported.getBlockPos();
      BlockPos.Mutable newPos = pos.mutableCopy();
      while (!isSolid(destination, newPos.down()))
        newPos.move(Direction.DOWN);
      for (;;) {
        if (isSolid(destination, newPos.up())) {
          newPos.move(Direction.UP, 2);
          continue;
        }
        if (isSolid(destination, newPos)) {
          newPos.move(Direction.UP);
          continue;
        }
        break;
      }
      return new TeleportTarget(teleported.getPos().add(0, newPos.getY() - pos.getY(), 0), teleported.getVelocity(), (int) teleported.yaw);
    }

    private static boolean isSolid(World world, BlockPos pos) {
      return world.getBlockState(pos).getMaterial().isSolid();
    }
  }
}
