package io.github.sjx233.ironrust.world.level.block;

import java.util.Random;

import io.github.sjx233.ironrust.world.level.IronRustGameRules;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class IronBlock extends Block {
  public IronBlock(Settings settings) {
    super(settings);
  }

  @Deprecated
  @Override
  public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    if (shouldRust(world, pos, random)) world.setBlockState(pos, IronRustBlocks.RUST_BLOCK.getDefaultState(), 2);
  }

  private static boolean shouldRust(World world, BlockPos pos, Random random) {
    int waterWeight = world.getGameRules().getInt(IronRustGameRules.WATER_WEIGHT);
    int airWeight = world.getGameRules().getInt(IronRustGameRules.AIR_WEIGHT);
    Direction[] directions = Direction.values();
    int maxChance = Math.max(waterWeight, airWeight) * directions.length;
    if (maxChance <= 0) return true;
    int chance = 0;
    for (Direction side : directions) {
      BlockPos offsetPos = pos.offset(side);
      BlockState offsetState = world.getBlockState(offsetPos);
      if (!offsetState.isSideSolidFullSquare(world, offsetPos, side.getOpposite())) chance += offsetState.getFluidState().isIn(FluidTags.WATER) ? waterWeight : airWeight;
      else if (offsetState.getBlock() == Blocks.WET_SPONGE) chance += waterWeight;
    }
    return random.nextInt(maxChance) < chance;
  }
}
