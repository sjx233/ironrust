package io.github.sjx233.ironrust.world.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum RustArmorMaterial implements ArmorMaterial {
  INSTANCE;
  private static final int[] durability = { 39, 45, 48, 33 };
  private static final int[] protectionAmounts = { 1, 3, 4, 2 };

  @Override
  public int getDurability(EquipmentSlot slot) {
    return durability[slot.getEntitySlotId()];
  }

  @Override
  public int getProtectionAmount(EquipmentSlot slot) {
    return protectionAmounts[slot.getEntitySlotId()];
  }

  @Override
  public int getEnchantability() {
    return 30;
  }

  @Override
  public SoundEvent getEquipSound() {
    return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return RustPowderItem.ingredient.get();
  }

  @Override
  public String getName() {
    return "ironrust:rust";
  }

  @Override
  public float getToughness() {
    return 0f;
  }

  @Override
  public float getKnockbackResistance() {
    return 0f;
  }
}
