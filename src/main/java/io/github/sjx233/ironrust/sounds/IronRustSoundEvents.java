package io.github.sjx233.ironrust.sounds;

import io.github.sjx233.ironrust.IronRustMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum IronRustSoundEvents {
  ;
  public static final SoundEvent RUST_POWDER_THROW = register("entity.rust_powder.throw");
  public static final SoundEvent RUSTY_COW_AMBIENT = register("entity.rusty_cow.ambient");
  public static final SoundEvent RUSTY_COW_DEATH = register("entity.rusty_cow.death");
  public static final SoundEvent RUSTY_COW_HURT = register("entity.rusty_cow.hurt");
  public static final SoundEvent RUSTY_COW_STEP = register("entity.rusty_cow.step");

  private static SoundEvent register(String id) {
    return register(IronRustMod.makeId(id));
  }

  private static SoundEvent register(Identifier id) {
    return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
  }

  public static void init() { }
}
