package io.github.sjx233.ironrust.world.level;

import io.github.sjx233.ironrust.IronRustMod;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules.Category;
import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.Key;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.Type;

public enum IronRustGameRules {
  ;
  public static final Key<IntRule> WATER_WEIGHT = register("waterWeight", Category.UPDATES, GameRuleFactory.createIntRule(5, 0));
  public static final Key<IntRule> AIR_WEIGHT = register("airWeight", Category.UPDATES, GameRuleFactory.createIntRule(1, 0));

  private static <T extends Rule<T>> Key<T> register(String name, Category category, Type<T> type) {
    return GameRuleRegistry.register(IronRustMod.MODID + '.' + name, category, type);
  }

  public static void init() { }
}
