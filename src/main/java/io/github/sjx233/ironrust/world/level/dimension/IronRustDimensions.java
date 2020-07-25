package io.github.sjx233.ironrust.world.level.dimension;

import io.github.sjx233.ironrust.IronRustMod;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public enum IronRustDimensions {
  ;
  public static final RegistryKey<World> RUST = RegistryKey.of(Registry.DIMENSION, IronRustMod.makeId("rust"));

  public static void init() { }
}
