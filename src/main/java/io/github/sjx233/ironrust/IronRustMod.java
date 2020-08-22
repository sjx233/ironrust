package io.github.sjx233.ironrust;

import io.github.sjx233.ironrust.sounds.IronRustSoundEvents;
import io.github.sjx233.ironrust.world.effect.IronRustMobEffects;
import io.github.sjx233.ironrust.world.entity.IronRustEntityType;
import io.github.sjx233.ironrust.world.entity.RustPowder;
import io.github.sjx233.ironrust.world.item.IronRustItems;
import io.github.sjx233.ironrust.world.item.alchemy.IronRustPotions;
import io.github.sjx233.ironrust.world.level.IronRustGameRules;
import io.github.sjx233.ironrust.world.level.block.IronRustBlocks;
import io.github.sjx233.ironrust.world.level.dimension.IronRustDimensions;
import io.github.sjx233.ironrust.world.level.levelgen.feature.IronRustStructureFeature;
import io.github.sjx233.ironrust.world.level.levelgen.feature.IronRustStructurePieceType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.util.Identifier;

public class IronRustMod implements ModInitializer {
  public static final String MODID = "ironrust";

  public static Identifier makeId(String path) {
    return new Identifier(MODID, path);
  }

  @Override
  public void onInitialize() {
    IronRustBlocks.init();
    IronRustItems.init();
    IronRustEntityType.init();
    IronRustMobEffects.init();
    IronRustPotions.init();
    IronRustStructurePieceType.init();
    IronRustStructureFeature.init();
    IronRustDimensions.init();
    IronRustGameRules.init();
    IronRustSoundEvents.init();
    DispenserBlock.registerBehavior(IronRustItems.RUST_POWDER, new ThrowableItemDispenseBehavior(RustPowder::new));
    CompostingChanceRegistry.INSTANCE.add(IronRustItems.RUST_FRUIT, 0.5f);
  }
}
