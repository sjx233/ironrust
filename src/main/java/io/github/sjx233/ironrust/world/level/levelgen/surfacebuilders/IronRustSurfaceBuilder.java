package io.github.sjx233.ironrust.world.level.levelgen.surfacebuilders;

import io.github.sjx233.ironrust.world.level.block.IronRustBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class IronRustSurfaceBuilder {
  public static final BlockState RUST_DIRT = IronRustBlocks.RUST_DIRT.getDefaultState();
  public static final TernarySurfaceConfig RUST_DIRT_CONFIG = new TernarySurfaceConfig(RUST_DIRT, SurfaceBuilder.DIRT, SurfaceBuilder.GRAVEL);
}
