package io.github.sjx233.ironrust.world.item;

import net.minecraft.item.HoeItem;

public class RustHoeItem extends HoeItem {
  public RustHoeItem(int attackDamage, float attackSpeed, Settings settings) {
    super(RustMaterial.INSTANCE, attackDamage, attackSpeed, settings);
  }
}
