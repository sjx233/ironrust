package io.github.sjx233.ironrust.world.level.biome;

import io.github.sjx233.ironrust.IronRustMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public enum IronRustBiomes {
  ;
  public static final Biome RUST = register("rust", new RustBiome());

  private static Biome register(String id, Biome biome) {
    return register(IronRustMod.makeId(id), biome);
  }

  private static Biome register(Identifier id, Biome biome) {
    return Registry.register(Registry.BIOME, id, biome);
  }

  public static void init() { }
}
