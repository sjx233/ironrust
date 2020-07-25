package io.github.sjx233.ironrust.world.entity;

import io.github.sjx233.ironrust.IronRustMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustEntityType {
  ;
  public static final EntityType<RustyCow> RUSTY_COW = register("rusty_cow", FabricEntityTypeBuilder
    .create(SpawnGroup.MONSTER, RustyCow::new)
    .dimensions(EntityDimensions.changing(0.9f, 1.4f))
    .build());
  public static final EntityType<RustPowder> RUST_POWDER = register("rust_powder", FabricEntityTypeBuilder
    .<RustPowder>create(SpawnGroup.MISC, RustPowder::new)
    .dimensions(EntityDimensions.changing(0.25f, 0.25f))
    .trackable(4, 10)
    .build());

  static {
    FabricDefaultAttributeRegistry.register(RUSTY_COW, HostileEntity.createHostileAttributes()
      .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2d)
      .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5d));
  }

  private static <T extends Entity> EntityType<T> register(String id, EntityType<T> type) {
    return register(IronRustMod.makeId(id), type);
  }

  private static <T extends Entity> EntityType<T> register(Identifier id, EntityType<T> type) {
    return Registry.register(Registry.ENTITY_TYPE, id, type);
  }

  public static void init() { }
}
