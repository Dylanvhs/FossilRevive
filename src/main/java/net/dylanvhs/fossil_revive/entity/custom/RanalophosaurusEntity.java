package net.dylanvhs.fossil_revive.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.EnumSet;
import java.util.UUID;

public class RanalophosaurusEntity extends PathfinderMob implements NeutralMob, RangedAttackMob, GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final EntityDataAccessor<Integer> CHARGE_COOLDOWN_TICKS = SynchedEntityData.defineId(RanalophosaurusEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(RanalophosaurusEntity.class, EntityDataSerializers.BOOLEAN);
    public RanalophosaurusEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private boolean canBePushed = true;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RanaPrepareSpitGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }

    public static AttributeSupplier setAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 45D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 4f)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1f)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHARGE_COOLDOWN_TICKS, 0);
        this.entityData.define(HAS_TARGET, false);

    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {

        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pRemainingPersistentAngerTime) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID pPersistentAngerTarget) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    public void setChargeCooldownTicks(int ticks) {
        this.entityData.set(CHARGE_COOLDOWN_TICKS, ticks);
    }

    public int getChargeCooldownTicks() {
        return this.entityData.get(CHARGE_COOLDOWN_TICKS);
    }

    public boolean hasChargeCooldown() {
        return this.entityData.get(CHARGE_COOLDOWN_TICKS) > 0;
    }

    public void resetChargeCooldownTicks() {
        this.entityData.set(CHARGE_COOLDOWN_TICKS, 50);
    }

    public void setHasTarget(boolean hasTarget) {
        this.entityData.set(HAS_TARGET, hasTarget);
    }



    private boolean isWithinYRange(LivingEntity target) {
        if (target == null) {
            return false;
        }
        return Math.abs(target.getY() - this.getY()) < 3;
    }

    @Override
    public boolean isPushable() {
        return this.canBePushed;
    }

    class RanaPrepareSpitGoal extends Goal {
        protected final RanalophosaurusEntity rana;


        public RanaPrepareSpitGoal(RanalophosaurusEntity ranalophosaurus) {
            this.rana = ranalophosaurus;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.rana.getTarget();
            if (target == null || !target.isAlive() || !this.rana.isWithinYRange(target)) {
                this.rana.resetChargeCooldownTicks();
                return false;
            }
            return target instanceof Player && rana.hasChargeCooldown();
        }

        @Override
        public void start() {
            LivingEntity target = this.rana.getTarget();
            if (target == null) {
                return;
            }
            this.rana.setHasTarget(true);
            this.rana.resetChargeCooldownTicks();
            this.rana.canBePushed = false;
        }

        @Override
        public void stop() {
            this.rana.setHasTarget(false);
            this.rana.canBePushed = true;
        }

        @Override
        public void tick() {
            LivingEntity target = this.rana.getTarget();
            if (target == null) {
                return;
            }
            this.rana.getLookControl().setLookAt(target);
            this.rana.setChargeCooldownTicks(Math.max(0, this.rana.getChargeCooldownTicks() - 1));
        }
    }
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {

        Snowball snowball = new Snowball(this.level(), this);
        double d0 = pTarget.getEyeY() - (double)1.1F;
        double d1 = pTarget.getX() - this.getX();
        double d2 = d0 - snowball.getY();
        double d3 = pTarget.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        snowball.shoot(d1, d2 + d4, d3, 1.6F, 5.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(snowball);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 4, this::predicate));
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "attackController", 4, this::attackPredicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<GeoAnimatable> geoAnimatableAnimationState) {

        if (geoAnimatableAnimationState.isMoving() && !this.isBaby()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.rana.walk", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(1F);
            return PlayState.CONTINUE;
        }
        if (geoAnimatableAnimationState.isMoving() && this.isBaby()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.rana.walk", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(2F);
            return PlayState.CONTINUE;
        }
        else geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.rana.idle", Animation.LoopType.LOOP));
        geoAnimatableAnimationState.getController().setAnimationSpeed(1F);
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(software.bernie.geckolib.core.animation.AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        if (this.swinging && geoAnimatableAnimationState.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            geoAnimatableAnimationState.getController().forceAnimationReset();
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.rana.spit", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }  return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return tickCount;
    }




}
