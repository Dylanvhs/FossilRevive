package net.dylanvhs.fossil_revive.entity.custom;

import net.dylanvhs.fossil_revive.entity.ai.CustomRandomSwimGoal;
import net.dylanvhs.fossil_revive.entity.ai.SmoothSwimmingMoveControlButNotBad;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Predicate;

public class XenacanthusEntity extends AbstractFish implements Bucketable, NeutralMob, GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final Predicate<LivingEntity> SCARY_MOB = (p_289442_) -> {
        if (p_289442_ instanceof Player && ((Player)p_289442_).isCreative()) {
            return false;
        } else {
            return p_289442_.getType() == EntityType.AXOLOTL || p_289442_.getMobType() != MobType.WATER;
        }
    };
    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(XenacanthusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(XenacanthusEntity.class, EntityDataSerializers.INT);

    public XenacanthusEntity(EntityType<? extends XenacanthusEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControlButNotBad(this, 1000, 4, 0.1F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.moveControl = new MoveHelperController(this);
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(ModItems.XENACANTHUS_SPAWN_EGG.get());
    }


    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "coral_stalker";
            case 2 -> "gilded_stealth";
            case 3 -> "azalea_cruiser";
            default -> "shell_crusher";
        };
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 2D)
                .add(Attributes.ARMOR_TOUGHNESS, 0f)
                .add(Attributes.ATTACK_DAMAGE, 3f)
                .add(Attributes.ATTACK_SPEED, 0.5f)
                .add(Attributes.ATTACK_KNOCKBACK, 0f)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new CustomRandomSwimGoal(this, 1, 1, 20, 20, 3));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Cod.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Salmon.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Axolotl.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TropicalFish.class, true));
        this.goalSelector.addGoal(1, new XenacanthusEntity.XenacanthusMeleeAttackGoal());
    }

    public void aiStep() {
        super.aiStep();
        if (this.isAlive()) {
            for(Mob mob : this.level().getEntitiesOfClass(Mob.class, this.getBoundingBox().inflate(0.3D), (p_149013_) -> {
                return targetingConditions.test(this, p_149013_);
            })) {
                if (mob.isAlive()) {
                    this.touch(mob);
                }
            }
        }
    }

    public void playerTouch(Player pEntity) {
        if (pEntity instanceof ServerPlayer && pEntity.hurt(this.damageSources().mobAttack(this), (float)(1 ))) {
            if (!this.isSilent()) {
                ((ServerPlayer)pEntity).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            }

            pEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }

    }

    private void touch(Mob pMob) {
        if (pMob.hurt(this.damageSources().mobAttack(this), (float)(1))) {
            pMob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ModItems.XENACANTHUS_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Override
    public void saveToBucketTag(@Nonnull ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
    }

    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
        if (compound.contains("BucketVariantTag", 3)) {
            this.setVariant(compound.getInt("BucketVariantTag"));
        }
    }

    @Override
    @Nonnull
    protected InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("FromBucket", this.fromBucket());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean p_203706_1_) {
        this.entityData.set(FROM_BUCKET, p_203706_1_);
    }
    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (dataTag == null) {
            setVariant(random.nextInt(4));
        } else {
            if (dataTag.contains("Variant", 4)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }


    public MobType getMobType() {
        return MobType.WATER;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }


    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

    static class MoveHelperController extends MoveControl {
        private final XenacanthusEntity liopleurodon;

        public MoveHelperController(XenacanthusEntity dolphinIn) {
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

    class XenacanthusMeleeAttackGoal extends MeleeAttackGoal {
        public XenacanthusMeleeAttackGoal() {
            super(XenacanthusEntity.this, 2D, true);
        }
        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            float f = XenacanthusEntity.this.getBbWidth() - 0.1F;
            return (f * 2.0F * f * 2.0F + pAttackTarget.getBbWidth());
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 4, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<GeoAnimatable> geoAnimatableAnimationState) {

        if (geoAnimatableAnimationState.isMoving()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.xenacanthus.swim", Animation.LoopType.LOOP));
            geoAnimatableAnimationState.getController().setAnimationSpeed(1.75F);
            return PlayState.CONTINUE;
        }
        else geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.xenacanthus.swim", Animation.LoopType.LOOP));
        geoAnimatableAnimationState.getController().setAnimationSpeed(1.75F);
        return PlayState.CONTINUE;
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return tickCount;
    }

}
