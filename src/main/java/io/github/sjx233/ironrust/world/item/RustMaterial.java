package io.github.sjx233.ironrust.world.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum RustMaterial implements ToolMaterial {
  INSTANCE;

  @Override
  public int getDurability() {
    return 46;
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return 4f;
  }

  @Override
  public float getAttackDamage() {
    return 1f;
  }

  @Override
  public int getMiningLevel() {
    return 1;
  }

  @Override
  public int getEnchantability() {
    return 56;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return RustPowderItem.ingredient.get();
  }
}
