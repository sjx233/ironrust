package io.github.sjx233.ironrust.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.Type;

@Mixin(IntRule.class)
public interface MixinIntegerValue {
  @Invoker("create")
  public static Type<IntRule> ironrust_create(int init) {
    throw new AssertionError();
  }
}
