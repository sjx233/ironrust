package io.github.sjx233.ironrust.world.level.levelgen.feature;

import io.github.sjx233.ironrust.IronRustMod;
import io.github.sjx233.ironrust.world.level.levelgen.structure.AbandonedHutPiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustStructurePieceType {
  ;
  public static final StructurePieceType ABANDONED_HUT = register("abandoned_hut", AbandonedHutPiece::new);

  static StructurePieceType register(String id, StructurePieceType type) {
    return register(IronRustMod.makeId(id), type);
  }

  static StructurePieceType register(Identifier id, StructurePieceType type) {
    return Registry.register(Registry.STRUCTURE_PIECE, id, type);
  }

  public static void init() { }
}
