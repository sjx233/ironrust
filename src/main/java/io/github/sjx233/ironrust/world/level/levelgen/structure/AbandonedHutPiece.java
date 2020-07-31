package io.github.sjx233.ironrust.world.level.levelgen.structure;

import java.util.Random;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.world.level.levelgen.feature.IronRustStructurePieceType;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class AbandonedHutPiece extends SimpleStructurePiece {
  private static final Identifier STRUCTURE = IronRustMod.makeId("abandoned_hut");
  private BlockRotation rotation;

  public AbandonedHutPiece(StructureManager manager, BlockPos pos, BlockRotation rotation) {
    super(IronRustStructurePieceType.ABANDONED_HUT, 0);
    this.pos = pos;
    this.rotation = rotation;
    this.loadTemplate(manager);
  }

  public AbandonedHutPiece(StructureManager manager, CompoundTag tag) {
     super(IronRustStructurePieceType.ABANDONED_HUT, tag);
     this.rotation = BlockRotation.valueOf(tag.getString("Rot"));
     this.loadTemplate(manager);
  }

  private void loadTemplate(StructureManager manager) {
    this.setStructureData(manager.getStructureOrBlank(STRUCTURE), this.pos, new StructurePlacementData()
      .setRotation(this.rotation)
      .addProcessor(new BlockRotStructureProcessor(0.9f))
      .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS));
  }

  @Override
  protected void toNbt(CompoundTag compound) {
    super.toNbt(compound);
    compound.putString("Rot", this.rotation.name());
  }

  @Override
  public boolean generate(StructureWorldAccess world, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
    BlockPos pos = this.pos;
    this.pos = pos.add(0, world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, pos.getX(), pos.getZ()) - 90, 0);
    boolean success = super.generate(world, accessor, generator, random, box, chunkPos, blockPos);
    this.pos = pos;
    return success;
  }

  @Override
  protected void handleMetadata(String id, BlockPos pos, ServerWorldAccess world, Random random, BlockBox box) {
    if ("furnace".equals(id)) {
      world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
      BlockEntity inv = world.getBlockEntity(pos.down());
      if (inv instanceof Inventory) ((Inventory) inv).setStack(1, new ItemStack(Items.COAL, random.nextInt(8) + 1));
    }
  }
}
