package io.github.sjx233.ironrust.world.entity;

import io.github.sjx233.ironrust.IronRustMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public enum IronRustEntityType {
  ;
  public static final EntityType<RustyCow> RUSTY_COW = register("rusty_cow",
    FabricEntityTypeBuilder.<RustyCow>createMob()
      .spawnGroup(SpawnGroup.MONSTER)
      .entityFactory(RustyCow::new)
      .dimensions(EntityDimensions.changing(0.9f, 1.4f))
      .defaultAttributes(() ->
        HostileEntity.createHostileAttributes()
          .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2d)
          .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5d))
      .spawnRestriction(SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark)
      .build());
  public static final EntityType<RustPowder> RUST_POWDER = register("rust_powder",
    FabricEntityTypeBuilder.<RustPowder>create()
      .entityFactory(RustPowder::new)
      .dimensions(EntityDimensions.changing(0.25f, 0.25f))
      .trackRangeChunks(4)
      .trackedUpdateRate(10)
      .build());

  private static <T extends Entity> EntityType<T> register(String id, EntityType<T> type) {
    return register(IronRustMod.makeId(id), type);
  }

  private static <T extends Entity> EntityType<T> register(Identifier id, EntityType<T> type) {
    return Registry.register(Registry.ENTITY_TYPE, id, type);
  }

  public static void init() { }
}
