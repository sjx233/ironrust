package io.github.sjx233.ironrust.world.level.levelgen.feature;

import com.mojang.serialization.Codec;

import io.github.sjx233.ironrust.world.level.levelgen.structure.AbandonedHutPiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class AbandonedHutFeature extends StructureFeature<DefaultFeatureConfig> {
  public AbandonedHutFeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
    return Start::new;
  }

  public static class Start extends StructureStart<DefaultFeatureConfig> {
    public Start(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
      super(feature, chunkX, chunkZ, box, references, seed);
    }

    @Override
    public void init(ChunkGenerator generator, StructureManager manager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig config) {
      this.children.add(new AbandonedHutPiece(manager, new BlockPos(chunkX * 16, 90, chunkZ * 16), BlockRotation.random(this.random)));
      this.setBoundingBoxFromChildren();
    }
  }
}
