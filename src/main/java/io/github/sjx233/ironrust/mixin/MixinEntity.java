package io.github.sjx233.ironrust.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.sjx233.ironrust.world.level.dimension.IronRustDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

@Mixin(Entity.class)
public abstract class MixinEntity {
  @Shadow
  private World world;
  @Shadow
  private float yaw;
  @Shadow
  private float pitch;
  @Shadow
  private Vec3d pos;
  @Shadow
  private BlockPos blockPos;
  @Shadow
  private Vec3d velocity;

  @Inject(method = "getTeleportTarget", at = @At("HEAD"), cancellable = true)
  private void ironrust_findDimensionEntryPoint(ServerWorld destination, CallbackInfoReturnable<TeleportTarget> ci) {
    if (this.world.getRegistryKey() == IronRustDimensions.RUST || destination.getRegistryKey() == IronRustDimensions.RUST) {
      BlockPos pos = this.blockPos;
      BlockPos.Mutable newPos = pos.mutableCopy();
      while (!ironrust_isSolid(destination, newPos.down()))
        newPos.move(Direction.DOWN);
      for (;;) {
        if (ironrust_isSolid(destination, newPos.up())) {
          newPos.move(Direction.UP, 2);
          continue;
        }
        if (ironrust_isSolid(destination, newPos)) {
          newPos.move(Direction.UP);
          continue;
        }
        break;
      }
      ci.setReturnValue(new TeleportTarget(this.pos.add(0, newPos.getY() - pos.getY(), 0), this.velocity, this.yaw, this.pitch));
    }
  }

  private static boolean ironrust_isSolid(World world, BlockPos pos) {
    return world.getBlockState(pos).getMaterial().isSolid();
  }
}
