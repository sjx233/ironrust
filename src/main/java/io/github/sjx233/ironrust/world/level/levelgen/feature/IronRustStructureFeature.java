package io.github.sjx233.ironrust.world.level.levelgen.feature;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.world.level.biome.IronRustBiomeDefaultFeatures;
import net.earthcomputer.libstructure.LibStructure;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public enum IronRustStructureFeature {
  ;
  public static final AbandonedHutFeature ABANDONED_HUT = new AbandonedHutFeature(DefaultFeatureConfig.CODEC);

  static {
    register("abandoned_hut", ABANDONED_HUT, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(32, 8, 309368660), IronRustBiomeDefaultFeatures.ABANDONED_HUT);
  }

  private static <FC extends FeatureConfig, F extends StructureFeature<FC>> F register(String name, F feature, GenerationStep.Feature step, StructureConfig defaultConfig, ConfiguredStructureFeature<FC, ? extends StructureFeature<FC>> flatFeature) {
    return register(IronRustMod.makeId(name), feature, step, defaultConfig, flatFeature);
  }

  private static <FC extends FeatureConfig, F extends StructureFeature<FC>> F register(Identifier id, F feature, GenerationStep.Feature step, StructureConfig defaultConfig, ConfiguredStructureFeature<FC, ? extends StructureFeature<FC>> flatFeature) {
    LibStructure.registerStructure(id, feature, step, defaultConfig, flatFeature);
    return feature;
  }

  public static void init() { }
}
