package io.github.sjx233.ironrust.world.item;

import net.minecraft.item.AxeItem;

public class RustAxeItem extends AxeItem {
  public RustAxeItem(float attackDamage, float attackSpeed, Settings settings) {
    super(RustMaterial.INSTANCE, attackDamage, attackSpeed, settings);
  }
}
