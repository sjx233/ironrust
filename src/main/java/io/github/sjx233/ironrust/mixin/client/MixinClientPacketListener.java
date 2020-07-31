package io.github.sjx233.ironrust.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.sjx233.ironrust.world.entity.IronRustEntityType;
import io.github.sjx233.ironrust.world.entity.RustPowder;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class MixinClientPacketListener {
  @Shadow
  private ClientWorld world;

  @Inject(method = "onEntitySpawn", at = @At(value = "INVOKE", target = "net/minecraft/network/NetworkThreadUtils.forceMainThread(Lnet/minecraft/network/Packet;Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/util/thread/ThreadExecutor;)V", shift = Shift.AFTER))
  public void ironrust_handleAddEntity(EntitySpawnS2CPacket packet, CallbackInfo ci) {
    if (packet.getEntityTypeId() == IronRustEntityType.RUST_POWDER) {
      int id = packet.getId();
      double x = packet.getX();
      double y = packet.getY();
      double z = packet.getZ();
      Entity entity = new RustPowder(this.world, x, y, z);
      entity.setEntityId(id);
      entity.setUuid(packet.getUuid());
      entity.updateTrackedPosition(x, y, z);
      entity.pitch = packet.getPitch() * 360 / 256f;
      entity.yaw = packet.getYaw() * 360 / 256f;
      this.world.addEntity(id, entity);
    }
  }
}
