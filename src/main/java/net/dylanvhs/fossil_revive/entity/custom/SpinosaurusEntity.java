package net.dylanvhs.fossil_revive.entity.custom;

import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.entity.ai.CustomRandomSwimGoal;
import net.dylanvhs.fossil_revive.entity.ai.FlyingMoveController;
import net.dylanvhs.fossil_revive.entity.ai.SmoothSwimmingMoveControlButNotBad;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
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

import java.util.UUID;

public class SpinosaurusEntity extends Animal implements NeutralMob, GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(SpinosaurusEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(LiopleurodonEntity.class, EntityDataSerializers.INT);
    private boolean isLandNavigator;

    public SpinosaurusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        switchNavigator(true);
    }


    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "blue";
            default -> "orange";
        };
    }

    public boolean isJP() {
        String n = ChatFormatting.stripFormatting(this.getName().getString());
        return n != null && (n.toLowerCase().contains("cooper"));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(MOISTNESS_LEVEL, 600);
    }

    @Nullable
    public SpinosaurusEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        SpinosaurusEntity spinosaurus = ModEntities.SPINOSAURUS.get().create(pLevel);
        if (spinosaurus != null) {
            int i = this.random.nextBoolean() ? this.getVariant() : ((SpinosaurusEntity) pOtherParent).getVariant();
            spinosaurus.setVariant(i);
            spinosaurus.setPersistenceRequired();
        }
        return spinosaurus;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1){
            @Override
            public boolean canUse() {
                return !isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomRandomSwimGoal(this, 0.8, 1, 40, 30, 3){
            @Override
            public boolean canUse() {
                return isInWater() && super.canUse();
            }
        });
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D) {
            @Override
            public boolean canUse() {
                return isBaby() && super.canUse();
            }
        });
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6F, true) {
            @Override
            public boolean canUse() {
                return !isBaby() && super.canUse();
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return !(p_28879_ instanceof SpinosaurusEntity || p_28879_ instanceof Creeper);
        }));
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120D)
                .add(Attributes.FOLLOW_RANGE, 55D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 8f)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.25f)
                .build();
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, Integer.valueOf(variant));
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(pCompound.getInt("Variant"));
    }

    protected void ageBoundaryReached() {
        if (this.isBaby()) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(60.0D);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(120.0D);
        }

    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        if (variantChange <= 0.60F) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveControl = new MoveControl(this);
            this.lookControl = new LookControl(this);
            this.navigation = new GroundPathNavigation(this, level());
            this.isLandNavigator = true;
        } else {
            this.moveControl = new SmoothSwimmingMoveControlButNotBad(this, 20, 4, 0.02F, 0.1F, true);
            this.lookControl = new SmoothSwimmingLookControl(this, 10);
            this.moveControl = new SpinosaurusEntity.MoveHelperController(this);
            this.navigation = new AmphibiousPathNavigation(this, level());
            navigation.setCanFloat(false);
            this.isLandNavigator = false;
        }
    }

    static class MoveHelperController extends MoveControl {
        private final SpinosaurusEntity liopleurodon;

        public MoveHelperController(SpinosaurusEntity dolphinIn) {
            super(dolphinIn);
            this.liopleurodon = dolphinIn;
        }

        public void tick() {
            if (this.liopleurodon.isInWater()) {
                this.liopleurodon.setDeltaMovement(this.liopleurodon.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.liopleurodon.getNavigation().isDone()) {
                double d0 = this.wantedX - this.liopleurodon.getX();
                double d1 = this.wantedY - this.liopleurodon.getY();
                double d2 = this.wantedZ - this.liopleurodon.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double) 2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                    this.liopleurodon.setYRot(this.rotlerp(this.liopleurodon.getYRot(), f, 10.0F));
                    this.liopleurodon.yBodyRot = this.liopleurodon.getYRot();
                    this.liopleurodon.yHeadRot = this.liopleurodon.getYRot();
                    float f1 = (float) (this.speedModifier * this.liopleurodon.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.liopleurodon.isInWater()) {
                        this.liopleurodon.setSpeed(f1 * 0.02F);
                        float f2 = -((float) (Mth.atan2(d1, Mth.sqrt((float) (d0 * d0 + d2 * d2))) * (double) (180F / (float) Math.PI)));
                        f2 = Mth.clamp(Mth.wrapDegrees(f2), -85.0F, 85.0F);
                        this.liopleurodon.setXRot(this.rotlerp(this.liopleurodon.getXRot(), f2, 5.0F));
                        float f3 = Mth.cos(this.liopleurodon.getXRot() * ((float) Math.PI / 180F));
                        float f4 = Mth.sin(this.liopleurodon.getXRot() * ((float) Math.PI / 180F));
                        this.liopleurodon.zza = f3 * f1;
                        this.liopleurodon.yya = -f4 * f1;
                    } else {
                        this.liopleurodon.setSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.liopleurodon.setSpeed(0.0F);
                this.liopleurodon.setXxa(0.0F);
                this.liopleurodon.setYya(0.0F);
                this.liopleurodon.setZza(0.0F);
            }
        }
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    protected void handleAirSupply(int pAirSupply) {
    }
    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int pMoistnessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, pMoistnessLevel);
    }

    public void tick() {
        super.tick();

        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(2400);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0F);
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 + (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 + (double)f1, 0.0D, 0.0D, 0.0D);
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 - (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 - (double)f1, 0.0D, 0.0D, 0.0D);
                }
            }

        }
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    @Override
    public boolean canAttack(LivingEntity entity) {
        boolean prev = super.canAttack(entity);
        if (isBaby()) {
            return false;
        }
        return prev;
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 4, this::predicate));
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "attackController", 4, this::attackPredicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<GeoAnimatable> geoAnimatableAnimationState) {

        if (geoAnimatableAnimationState.isMoving() && isInWater() && !this.isBaby()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.spinosaurus.swim", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(1F);
            return PlayState.CONTINUE;
        }
        if (geoAnimatableAnimationState.isMoving() && !isInWater() && !this.isBaby()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.spinosaurus.walk", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(1F);
            return PlayState.CONTINUE;
        }
        if (geoAnimatableAnimationState.isMoving() && isInWater() && this.isBaby()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.spinosaurus.swim", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(2F);
            return PlayState.CONTINUE;
        }
        if (geoAnimatableAnimationState.isMoving() && !isInWater() && this.isBaby()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.spinosaurus.walk", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(2F);
            return PlayState.CONTINUE;
        }
        else geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.spinosaurus.idle", Animation.LoopType.LOOP));
        geoAnimatableAnimationState.getController().setAnimationSpeed(1F);
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(software.bernie.geckolib.core.animation.AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        if (this.swinging && geoAnimatableAnimationState.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            geoAnimatableAnimationState.getController().forceAnimationReset();
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.spinosaurus.attack", Animation.LoopType.PLAY_ONCE));
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
