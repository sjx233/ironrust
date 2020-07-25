package io.github.sjx233.ironrust.world.item;

import io.github.sjx233.ironrust.sounds.IronRustSoundEvents;
import io.github.sjx233.ironrust.world.entity.RustPowder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Lazy;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RustPowderItem extends Item {
  public static final Lazy<Ingredient> ingredient = new Lazy<>(() -> Ingredient.ofItems(IronRustItems.RUST_POWDER));

  public RustPowderItem(Settings settings) {
    super(settings);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getStackInHand(hand);
    world.playSound(null, player.getX(), player.getY(), player.getZ(), IronRustSoundEvents.RUST_POWDER_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (RANDOM.nextFloat() * 0.4f + 0.8f));
    player.getItemCooldownManager().set(this, 20);
    if (!world.isClient) {
      RustPowder entity = new RustPowder(world, player);
      entity.setItem(stack);
      entity.setProperties(player, player.pitch, player.yaw, 0f, 1.5f, 1f);
      world.spawnEntity(entity);
    }
    player.incrementStat(Stats.USED.getOrCreateStat(this));
    if (!player.abilities.creativeMode) stack.decrement(1);
    return TypedActionResult.success(stack);
  }
}
