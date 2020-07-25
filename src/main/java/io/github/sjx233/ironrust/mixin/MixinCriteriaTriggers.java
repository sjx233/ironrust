package io.github.sjx233.ironrust.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;

@Mixin(Criteria.class)
public interface MixinCriteriaTriggers {
  @Invoker("register")
  public static <T extends Criterion<?>> T ironrust_register(T criterion) {
    throw new AssertionError();
  }
}
