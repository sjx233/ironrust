package io.github.sjx233.ironrust.world.level.levelgen.feature;

import io.github.sjx233.ironrust.IronRustMod;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public enum IronRustStructureFeature {
  ;
  public static final AbandonedHutFeature ABANDONED_HUT =
    create("abandoned_hut", new AbandonedHutFeature(DefaultFeatureConfig.CODEC))
      .step(GenerationStep.Feature.SURFACE_STRUCTURES)
      .defaultConfig(32, 8, 309368660)
      .register();

  private static <FC extends FeatureConfig, F extends StructureFeature<FC>> FabricStructureBuilder<FC, F> create(String id, F feature) {
    return FabricStructureBuilder.create(IronRustMod.makeId(id), feature);
  }

  public static void init() { }
}
