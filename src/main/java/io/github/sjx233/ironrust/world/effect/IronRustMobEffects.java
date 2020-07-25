package io.github.sjx233.ironrust.world.effect;

import io.github.sjx233.ironrust.IronRustMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustMobEffects {
  ;
  public static final StatusEffect RUST = register("rust", new RustMobEffect());

  private static StatusEffect register(String id, StatusEffect effect) {
    return register(IronRustMod.makeId(id), effect);
  }

  private static StatusEffect register(Identifier id, StatusEffect effect) {
    return Registry.register(Registry.STATUS_EFFECT, id, effect);
  }

  public static void init() { }
}
