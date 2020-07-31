package io.github.sjx233.ironrust.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.sjx233.ironrust.world.level.block.IronRustBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(DeadBushBlock.class)
public abstract class MixinDeadBushBlock {
  @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
  private void ironrust_mayPlaceOn(BlockState floor, BlockView view, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
    if (floor.getBlock() == IronRustBlocks.RUST_DIRT) ci.setReturnValue(true);
  }
}
