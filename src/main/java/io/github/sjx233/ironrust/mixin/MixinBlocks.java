package io.github.sjx233.ironrust.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.sjx233.ironrust.world.level.block.IronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;

@Mixin(Blocks.class)
public abstract class MixinBlocks {
  @Inject(method = "register", at = @At("HEAD"), cancellable = true)
  private static void ironrust_register(String id, Block block, CallbackInfoReturnable<Block> ci) {
    if ("iron_block".equals(id)) ci.setReturnValue(Registry.register(Registry.BLOCK, id, new IronBlock(Block.Settings.copy(block).ticksRandomly())));
  }
}
