package io.github.sjx233.ironrust.world.level;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.mixin.MixinGameRules;
import io.github.sjx233.ironrust.mixin.MixinIntegerValue;
import net.minecraft.world.GameRules.Category;
import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.Key;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.Type;

public enum IronRustGameRules {
  ;
  public static final Key<IntRule> WATER_WEIGHT = register("waterWeight", Category.UPDATES, createIntRule(5));
  public static final Key<IntRule> AIR_WEIGHT = register("airWeight", Category.UPDATES, createIntRule(1));

  private static <T extends Rule<T>> Key<T> register(String name, Category category, Type<T> type) {
    return MixinGameRules.ironrust_register(IronRustMod.MODID + '.' + name, category, type);
  }

  private static Type<IntRule> createIntRule(int init) {
    return MixinIntegerValue.ironrust_create(init);
  }

  public static void init() { }
}
