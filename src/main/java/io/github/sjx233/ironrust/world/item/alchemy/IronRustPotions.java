package io.github.sjx233.ironrust.world.item.alchemy;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.mixin.MixinPotionBrewing;
import io.github.sjx233.ironrust.world.effect.IronRustMobEffects;
import io.github.sjx233.ironrust.world.item.IronRustItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustPotions {
  ;
  public static final Potion RUST = register("rust", new Potion(new StatusEffectInstance(IronRustMobEffects.RUST, 3600)));
  public static final Potion LONG_RUST = register("long_rust", new Potion("rust", new StatusEffectInstance(IronRustMobEffects.RUST, 9600)));
  public static final Potion STRONG_RUST = register("strong_rust", new Potion("rust", new StatusEffectInstance(IronRustMobEffects.RUST, 1800, 1)));

  static {
    MixinPotionBrewing.ironrust_addMix(Potions.AWKWARD, IronRustItems.RUST_FRUIT, RUST);
    MixinPotionBrewing.ironrust_addMix(RUST, Items.REDSTONE, LONG_RUST);
    MixinPotionBrewing.ironrust_addMix(RUST, Items.GLOWSTONE_DUST, STRONG_RUST);
  }

  private static Potion register(String id, Potion potion) {
    return register(IronRustMod.makeId(id), potion);
  }

  private static Potion register(Identifier id, Potion potion) {
    return Registry.register(Registry.POTION, id, potion);
  }

  public static void init() { }
}
