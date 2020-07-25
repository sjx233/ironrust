package io.github.sjx233.ironrust.world.level.biome;

import io.github.sjx233.ironrust.world.level.block.IronRustBlocks;
import io.github.sjx233.ironrust.world.level.levelgen.feature.IronRustStructureFeature;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ForestRockFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class IronRustBiomeFeatures {
  public static final BlockState RUST_BLOCK = IronRustBlocks.RUST_BLOCK.getDefaultState();
  public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> ABANDONED_HUT = IronRustStructureFeature.ABANDONED_HUT.configure(DefaultFeatureConfig.INSTANCE);

  public static void addRustBlobs(Biome biome) {
    biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Feature.FOREST_ROCK
      .configure(new ForestRockFeatureConfig(RUST_BLOCK, 0))
      .createDecoratedFeature(Decorator.FOREST_ROCK
        .configure(new CountDecoratorConfig(3))));
  }

  public static void addAbandonedHuts(Biome biome) {
    biome.addStructureFeature(ABANDONED_HUT);
  }
}
