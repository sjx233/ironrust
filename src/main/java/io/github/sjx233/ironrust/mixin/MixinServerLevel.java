package io.github.sjx233.ironrust.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.sjx233.ironrust.world.entity.RustPowder;
import io.github.sjx233.ironrust.world.level.dimension.IronRustDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

@Mixin(ServerWorld.class)
public abstract class MixinServerLevel {
  @Inject(method = "tick", at = @At(value = "FIELD", target = "net/minecraft/server/world/ServerWorld.inEntityTick:Z", opcode = Opcodes.PUTFIELD, ordinal = 1, shift = Shift.AFTER))
  public void tick(CallbackInfo ci) {
    for (Entity entity : RustPowder.pendingTeleportations) {
      if (entity.removed || entity.hasVehicle()) continue;
      assert entity.world instanceof ServerWorld;
      ServerWorld source = (ServerWorld) entity.world;
      ServerWorld destination = source.getServer().getWorld(source.getRegistryKey() == IronRustDimensions.RUST ? World.OVERWORLD : IronRustDimensions.RUST);
      if (destination != null) entity.moveToWorld(destination);
    }
    RustPowder.pendingTeleportations.clear();
  }
}
