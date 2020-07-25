package io.github.sjx233.ironrust.world.entity;

import io.github.sjx233.ironrust.sounds.IronRustSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RustyCow extends HostileEntity implements RangedAttackMob {
  public RustyCow(EntityType<? extends RustyCow> type, World world) {
    super(type, world);
  }

  @Override
  protected void initGoals() {
    this.goalSelector.add(1, new SwimGoal(this));
    this.goalSelector.add(2, new ProjectileAttackGoal(this, 1.25d, 80, 16f));
    this.goalSelector.add(3, new WanderAroundGoal(this, 1d));
    this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8f));
    this.goalSelector.add(4, new LookAroundGoal(this));
    this.targetSelector.add(1, new FollowTargetGoal<>(this, PlayerEntity.class, true));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return IronRustSoundEvents.RUSTY_COW_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource source) {
    return IronRustSoundEvents.RUSTY_COW_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return IronRustSoundEvents.RUSTY_COW_DEATH;
  }

  @Override
  protected void playStepSound(BlockPos pos, BlockState state) {
    this.playSound(IronRustSoundEvents.RUSTY_COW_STEP, 0.15f, 1.0f);
  }

  @Override
  public void attack(LivingEntity target, float distance) {
    RustPowder entity = new RustPowder(this.world, this);
    double dx = target.getX() - this.getX();
    double dy = target.getEyeY() - 1.1d - entity.getY();
    double dz = target.getZ() - this.getZ();
    float f = MathHelper.sqrt(dx * dx + dz * dz) * 0.2f;
    entity.setVelocity(dx, dy + f, dz, 1.6f, 12f);
    this.playSound(IronRustSoundEvents.RUST_POWDER_THROW, 1f, 1f / (this.random.nextFloat() * 0.4f + 0.8f));
    this.world.spawnEntity(entity);
  }
}
