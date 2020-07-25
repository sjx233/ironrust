package io.github.sjx233.ironrust.world.level.levelgen.feature;

import io.github.sjx233.ironrust.IronRustMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public enum IronRustStructureFeature {
  ;
  public static final AbandonedHutFeature ABANDONED_HUT = register("abandoned_hut", new AbandonedHutFeature(DefaultFeatureConfig.CODEC));

  private static <F extends StructureFeature<?>> F register(String name, F feature) {
    return register(IronRustMod.makeId(name), feature);
  }

  private static <F extends StructureFeature<?>> F register(Identifier id, F feature) {
    StructureFeature.STRUCTURES.put(id.toString(), feature);
    return Registry.register(Registry.STRUCTURE_FEATURE, id, feature);
  }

  public static void init() { }
}
