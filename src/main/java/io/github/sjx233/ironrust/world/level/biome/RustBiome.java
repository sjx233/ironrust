package io.github.sjx233.ironrust.world.level.biome;

import java.util.Collections;

import io.github.sjx233.ironrust.world.entity.IronRustEntityType;
import io.github.sjx233.ironrust.world.level.levelgen.surfacebuilders.IronRustSurfaceBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class RustBiome extends Biome {
  public RustBiome() {
    super(new Settings()
      .configureSurfaceBuilder(SurfaceBuilder.DEFAULT, IronRustSurfaceBuilder.RUST_DIRT_CONFIG)
      .precipitation(Precipitation.RAIN)
      .category(Category.MESA)
      .depth(0.125f)
      .scale(0.5f)
      .temperature(0.6f)
      .downfall(0.9f)
      .effects(new BiomeEffects.Builder()
        .waterColor(0x7e3b0f)
        .waterFogColor(0x200f04)
        .fogColor(0xff7820)
        .moodSound(BiomeMoodSound.CAVE)
        .build())
      .parent(null)
      .noises(Collections.singletonList(new Biome.MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 1.0F))));
    DefaultBiomeFeatures.addLandCarvers(this);
    DefaultBiomeFeatures.addDefaultLakes(this);
    DefaultBiomeFeatures.addMineables(this);
    DefaultBiomeFeatures.addDesertDeadBushes(this);
    DefaultBiomeFeatures.addDefaultMushrooms(this);
    DefaultBiomeFeatures.addSprings(this);
    DefaultBiomeFeatures.addFrozenTopLayer(this);
    IronRustBiomeFeatures.addRustBlobs(this);
    IronRustBiomeFeatures.addAbandonedHuts(this);
    this.addSpawn(SpawnGroup.AMBIENT, new SpawnEntry(EntityType.BAT, 10, 8, 8));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.SPIDER, 100, 4, 4));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.SKELETON, 100, 4, 4));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.CREEPER, 100, 4, 4));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.SLIME, 100, 4, 4));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
    this.addSpawn(SpawnGroup.MONSTER, new SpawnEntry(IronRustEntityType.RUSTY_COW, 400, 4, 4));
  }
}
