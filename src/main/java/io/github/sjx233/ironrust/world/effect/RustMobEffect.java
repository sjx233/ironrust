package io.github.sjx233.ironrust.world.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class RustMobEffect extends StatusEffect {
  public RustMobEffect() {
    super(StatusEffectType.NEUTRAL, 0x240d00);
    this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "393b55b6-1891-45b1-afa9-fad6c4188ac4", -0.15d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, "d1995d5a-e0cd-4f74-a6f3-dfbeca1a2cc6", -0.1d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "a1709e60-c6fb-4623-8ccf-391a0299407a", +0.05d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, "524045da-546e-4200-9824-baf32d94ade0", +0.1d, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
  }

  @Override
  public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    entity.damage(DamageSource.MAGIC, 1f);
  }

  @Override
  public boolean canApplyUpdateEffect(int duration, int amplifier) {
    int i = 100 >> amplifier;
    return i <= 0 || duration % i == 0;
  }
}
