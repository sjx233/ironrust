package io.github.sjx233.ironrust.world.item;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.world.effect.IronRustMobEffects;
import io.github.sjx233.ironrust.world.entity.IronRustEntityType;
import io.github.sjx233.ironrust.world.level.block.IronRustBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustItems {
  ;
  public static final Item RUST_BLOCK = register(IronRustBlocks.RUST_BLOCK, ItemGroup.BUILDING_BLOCKS);
  public static final Item RUST_DIRT = register(IronRustBlocks.RUST_DIRT, ItemGroup.BUILDING_BLOCKS);
  public static final Item RUST_POWDER = register("rust_powder",
    new RustPowderItem(new Item.Settings()
      .maxCount(16)
      .group(ItemGroup.MISC)));
  public static final Item RUST_SWORD = register("rust_sword",
    new SwordItem(RustMaterial.INSTANCE, 3, -2.4f, new Item.Settings()
      .group(ItemGroup.COMBAT)));
  public static final Item RUST_SHOVEL = register("rust_shovel",
    new ShovelItem(RustMaterial.INSTANCE, 1.5f, -3f, new Item.Settings()
      .group(ItemGroup.TOOLS)));
  public static final Item RUST_PICKAXE = register("rust_pickaxe",
    new RustPickaxeItem(1, -2.8f, new Item.Settings()
      .group(ItemGroup.TOOLS)));
  public static final Item RUST_AXE = register("rust_axe",
    new RustAxeItem(6f, -3f, new Item.Settings()
      .group(ItemGroup.TOOLS)));
  public static final Item RUST_HOE = register("rust_hoe",
    new RustHoeItem(0, -3f, new Item.Settings()
      .group(ItemGroup.TOOLS)));
  public static final Item RUST_HELMET = register("rust_helmet",
    new ArmorItem(RustArmorMaterial.INSTANCE, EquipmentSlot.HEAD, new Item.Settings()
      .group(ItemGroup.COMBAT)));
  public static final Item RUST_CHESTPLATE = register("rust_chestplate",
    new ArmorItem(RustArmorMaterial.INSTANCE, EquipmentSlot.CHEST, new Item.Settings()
      .group(ItemGroup.COMBAT)));
  public static final Item RUST_LEGGINGS = register("rust_leggings",
    new ArmorItem(RustArmorMaterial.INSTANCE, EquipmentSlot.LEGS, new Item.Settings()
      .group(ItemGroup.COMBAT)));
  public static final Item RUST_BOOTS = register("rust_boots",
    new ArmorItem(RustArmorMaterial.INSTANCE, EquipmentSlot.FEET, new Item.Settings()
      .group(ItemGroup.COMBAT)));
  public static final Item RUST_FRUIT = register("rust_fruit",
    new Item(new Item.Settings()
      .group(ItemGroup.FOOD)
      .food(new FoodComponent.Builder()
        .hunger(5)
        .saturationModifier(0.4f)
        .statusEffect(new StatusEffectInstance(IronRustMobEffects.RUST, 600, 0), 0.2f)
        .build())));
  public static final Item RUSTY_COW_SPAWN_EGG = register("rusty_cow_spawn_egg",
    new SpawnEggItem(IronRustEntityType.RUSTY_COW, 0x240d00, 0x6d5a31, new Item.Settings()
      .group(ItemGroup.MISC)));

  private static Item register(Block block, ItemGroup group) {
    return register(Registry.BLOCK.getId(block), new BlockItem(block, new Item.Settings().group(group)));
  }

  private static Item register(String id, Item item) {
    return register(IronRustMod.makeId(id), item);
  }

  private static Item register(Identifier id, Item item) {
    if (item instanceof BlockItem) ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
    return Registry.register(Registry.ITEM, id, item);
  }

  public static void init() { }
}
