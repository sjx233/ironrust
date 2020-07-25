package io.github.sjx233.ironrust.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;
import net.minecraft.world.GameRules.Key;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.Type;

@Mixin(GameRules.class)
public interface MixinGameRules {
  @Invoker("register")
  public static <T extends Rule<T>> Key<T> ironrust_register(String name, Category category, Type<T> type) {
    throw new AssertionError();
  }
}
