package io.github.sjx233.ironrust.world.item;

import net.minecraft.item.PickaxeItem;

public class RustPickaxeItem extends PickaxeItem {
  public RustPickaxeItem(int attackDamage, float attackSpeed, Settings settings) {
    super(RustMaterial.INSTANCE, attackDamage, attackSpeed, settings);
  }
}
