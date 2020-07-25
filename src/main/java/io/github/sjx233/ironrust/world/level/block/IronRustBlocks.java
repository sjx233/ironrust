package io.github.sjx233.ironrust.world.level.block;

import io.github.sjx233.ironrust.IronRustMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustBlocks {
  ;
  public static final Block RUST_BLOCK = register("rust_block",
    new Block(FabricBlockSettings
      .of(Material.METAL, MaterialColor.BROWN)
      .strength(2f, 3f)
      .sounds(BlockSoundGroup.METAL)
      .breakByTool(FabricToolTags.PICKAXES)));
  public static final Block RUST_DIRT = register("rust_dirt",
    new RustDirtBlock(FabricBlockSettings
      .of(Material.SOIL, MaterialColor.DIRT)
      .strength(0.5f, 0.5f)
      .sounds(BlockSoundGroup.GRAVEL)
      .breakByTool(FabricToolTags.SHOVELS)));

  private static Block register(String id, Block block) {
    return register(IronRustMod.makeId(id), block);
  }

  private static Block register(Identifier id, Block block) {
    return Registry.register(Registry.BLOCK, id, block);
  }

  public static void init() { }
}
