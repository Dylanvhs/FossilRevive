package net.dylanvhs.fossil_revive.entity.custom;

import com.google.common.collect.Sets;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class MicroraptorEntity extends TamableAnimal implements NeutralMob {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(MicroraptorEntity.class, EntityDataSerializers.INT);

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "red devil";
            case 2 -> "yellow hunter";
            case 3 -> "green swift";
            default -> "blue miracle";
        };
    }

    public MicroraptorEntity(EntityType<? extends MicroraptorEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.setCanPickUpLoot(true);
    }

    static final Predicate<ItemEntity> ALLOWED_ITEMS = (p_289438_) -> !p_289438_.hasPickUpDelay() && p_289438_.isAlive();

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;

    private float interestedAngle;
    private float interestedAngleO;
    private static final Item POISONOUS_FOOD = Items.COOKIE;
    private static final Set<Item> TAME_FOOD = Sets.newHashSet(Items.FERMENTED_SPIDER_EYE);

    private int rideCooldown = 0;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeOut = 0;
    public final AnimationState flapAnimationState = new AnimationState();
    private int flapAnimationTimeOut = 0;
    public final AnimationState sitAnimationState = new AnimationState();
    private int sitAnimationTimeOut = 0;
    private int ticksSinceEaten;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationState();
        }

        this.interestedAngleO = this.interestedAngle;
        if (this.isInterested()) {
            this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
        } else {
            this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        if (variantChange <= 0.40F) {
            this.setVariant(3);
        } else if (variantChange <= 0.50F) {
            this.setVariant(2);
        } else if (variantChange <= 0.60F) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    private void setupAnimationState() {
        if (this.idleAnimationTimeOut <= 0) {
            this.idleAnimationTimeOut = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeOut;
        }
        if (this.getPose() == Pose.FALL_FLYING && flapAnimationTimeOut <= 0) {
            this.flapAnimationTimeOut = this.random.nextInt(40) + 80;
            this.flapAnimationState.start(this.tickCount);
        } else {
            --this.flapAnimationTimeOut;
        }

        if (this.getPose() == Pose.STANDING) {
            flapAnimationState.stop();
        }

        if (this.isOrderedToSit() && sitAnimationTimeOut <= 0) {
            this.sitAnimationTimeOut = this.random.nextInt(40) + 80;
            this.sitAnimationState.start(this.tickCount);
        } else {
            --this.sitAnimationTimeOut;
        }

        if (!this.isOrderedToSit()) {
            sitAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(11, new MicroraptorEntity.MicroraptorSearchForItemsGoal());
        this.goalSelector.addGoal(2, new MicroraptorEntity.MicroraptorMeleeAttackGoal());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 2f)
                .add(Attributes.ATTACK_SPEED, 0.4f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1f);
    }

    public boolean isPushable() {
        return true;
    }

    protected void doPush(Entity pEntity) {
        if (!(pEntity instanceof Player)) {
            super.doPush(pEntity);
        }
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
        if (!this.isTame() && TAME_FOOD.contains(itemstack.getItem())) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (!this.isSilent()) {
                this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.level().isClientSide) {
                if (this.random.nextInt(5) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    this.tame(pPlayer);
                    this.setTarget((LivingEntity) null);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (itemstack.is(POISONOUS_FOOD)) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.addEffect(new MobEffectInstance(MobEffects.POISON, 900));
            if (pPlayer.isCreative() || !this.isInvulnerable()) {
                this.hurt(this.damageSources().playerAttack(pPlayer), Float.MAX_VALUE);


            }
        } else if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(pPlayer) && pPlayer.isShiftKeyDown()) {
            this.setOrderedToSit(!this.isOrderedToSit());
            this.jumping = false;
            this.navigation.stop();
            this.setTarget((LivingEntity) null);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            if (pPlayer.getPassengers().isEmpty() && isTame() && !pPlayer.isShiftKeyDown() && !isOrderedToSit()) {
                this.startRiding(pPlayer);
                rideCooldown = 20;
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                return super.mobInteract(pPlayer, pHand);
            }

        }
        return interactionresult;
    }


    public void rideTick() {
        Entity mount = this.getVehicle();

        if (this.isPassenger() && !mount.isAlive()) {
            this.stopRiding();
        } else if (mount instanceof Player player && this.isPassenger()) {
            this.setDeltaMovement(0, 0, 0);
            //this.yBodyRot = ((LivingEntity) player).yBodyRot;
            //this.setYRot(player.getYRot());
            //this.yHeadRot = ((LivingEntity) player).yHeadRot;
            //this.yRotO = ((LivingEntity) player).yHeadRot;
            float radius = 0F;
            float angle = (0.01745329251F * (((LivingEntity) player).yBodyRot - 180F));
            double extraX = radius * Mth.sin((float) (Math.PI + angle));
            double extraZ = radius * Mth.cos(angle);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 0, false, false));
            this.setPos(player.getX() + extraX, Math.max(player.getY() + player.getBbHeight() + 0.1, player.getY()), player.getZ() + extraZ);
            if (!mount.onGround()) {
                setPose(Pose.FALL_FLYING);
            } else {
                setPose(Pose.STANDING);
            }
            if (!player.isAlive() || rideCooldown == 0 || player.isShiftKeyDown() || !mount.isAlive()) {
                this.stopRiding();
                this.removeEffect(MobEffects.SLOW_FALLING);
            }
            if (mount.isInWater()) {
                this.stopRiding();
                this.removeEffect(MobEffects.SLOW_FALLING);
            }
        } else {
            super.rideTick();
        }

    }


    public void aiStep() {
        super.aiStep();
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
            setPose(Pose.FALL_FLYING);
        } else {
            setPose(Pose.STANDING);
        }
        this.flapping *= 0.9F;
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;

        if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi()) {
            ++this.ticksSinceEaten;
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (this.canEat(itemstack)) {
                if (this.ticksSinceEaten > 600) {
                    ItemStack itemstack1 = itemstack.finishUsingItem(this.level(), this);
                    if (!itemstack1.isEmpty()) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack1);
                    }

                    this.ticksSinceEaten = 0;
                } else if (this.ticksSinceEaten > 560 && this.random.nextFloat() < 0.1F) {
                    this.playSound(this.getEatingSound(itemstack), 1.0F, 1.0F);
                    this.level().broadcastEntityEvent(this, (byte) 45);
                }
            }

            LivingEntity livingentity = this.getTarget();
            if (livingentity == null || !livingentity.isAlive()) {
                this.setIsInterested(false);
            }
        }
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 45) {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                for(int i = 0; i < 8; ++i) {
                    Vec3 vec3 = (new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).xRot(-this.getXRot() * ((float)Math.PI / 180F)).yRot(-this.getYRot() * ((float)Math.PI / 180F));
                    this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, itemstack), this.getX() + this.getLookAngle().x / 2.0D, this.getY(), this.getZ() + this.getLookAngle().z / 2.0D, vec3.x, vec3.y + 0.05D, vec3.z);
                }
            }
        } else {
            super.handleEntityEvent(pId);
        }

    }
    class MicroraptorSearchForItemsGoal extends Goal {
        public MicroraptorSearchForItemsGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (!MicroraptorEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else if (MicroraptorEntity.this.getTarget() == null && MicroraptorEntity.this.getLastHurtByMob() == null) {
                if (!MicroraptorEntity.this.isInWater()) {
                    return false;
                } else if (MicroraptorEntity.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
                    return false;
                } else {
                    List<ItemEntity> list = MicroraptorEntity.this.level().getEntitiesOfClass(ItemEntity.class, MicroraptorEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), MicroraptorEntity.ALLOWED_ITEMS);
                    return !list.isEmpty() && MicroraptorEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
                }
            } else {
                return false;
            }
        }
    }

    protected void usePlayerItem(Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        if (this.isFood(pStack)) {
            this.playSound(this.getEatingSound(pStack), 1.0F, 1.0F);
        }

        super.usePlayerItem(pPlayer, pHand, pStack);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return this.isBaby() ? pSize.height * 0.85F : 0.4F;
    }


    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, Integer.valueOf(variant));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant());
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setIsCrouching(pCompound.getBoolean("Crouching"));
        this.setVariant(pCompound.getInt("Variant"));
    }

    public void setIsCrouching(boolean pIsCrouching) {
        this.setFlag(4, pIsCrouching);
    }

    public void setIsInterested(boolean pIsInterested) {
        this.setFlag(8, pIsInterested);
    }

    public boolean isInterested() {
        return this.getFlag(8);
    }


    private void setFlag(int pFlagId, boolean pValue) {
        if (pValue) {
            this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) | pFlagId));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) & ~pFlagId));
        }
    }

    private boolean getFlag(int pFlagId) {
        return (this.entityData.get(DATA_FLAGS_ID) & pFlagId) != 0;
    }


    public boolean canHoldItem(ItemStack pStack) {
        Item item = pStack.getItem();
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
        return itemstack.isEmpty() || this.ticksSinceEaten > 0 && item.isEdible() && !itemstack.getItem().isEdible();
    }


        private void spitOutItem(ItemStack pStack) {
        if (!pStack.isEmpty() && !this.level().isClientSide) {
            ItemEntity itementity = new ItemEntity(this.level(), this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, pStack);
            itementity.setPickUpDelay(40);
            itementity.setThrower(this.getUUID());
            this.playSound(SoundEvents.FOX_SPIT, 1.0F, 1.0F);
            this.level().addFreshEntity(itementity);
        }
    }

    private void dropItemStack(ItemStack pStack) {
        ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), pStack);
        this.level().addFreshEntity(itementity);
    }

    protected void pickUpItem(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        if (this.canHoldItem(itemstack)) {
            int i = itemstack.getCount();
            if (i > 1) {
                this.dropItemStack(itemstack.split(i - 1));
            }

            this.spitOutItem(this.getItemBySlot(EquipmentSlot.MAINHAND));
            this.onItemPickup(pItemEntity);
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
            this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
            this.take(pItemEntity, itemstack.getCount());
            pItemEntity.discard();
            this.ticksSinceEaten = 0;
        }

    }



    private boolean canEat(ItemStack pStack) {
        return pStack.getItem().isEdible() && this.getTarget() == null && this.onGround() && !this.isSleeping();
    }

    void clearStates() {
        this.setIsInterested(false);
    }

    protected void dropEquipment() { // Forge: move extra drops to dropEquipment to allow them to be captured by LivingDropsEvent
        super.dropEquipment();
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!itemstack.isEmpty()) {
            this.spawnAtLocation(itemstack);
            this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        }

    }

    public void die(DamageSource pCause) {
            this.stopRiding();
            this.removeEffect(MobEffects.SLOW_FALLING);
        super.die(pCause);
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
                this.setPose(Pose.STANDING);
            }

            return super.hurt(pSource, pAmount);
        }
    }

    class MicroraptorMeleeAttackGoal extends MeleeAttackGoal {
        public MicroraptorMeleeAttackGoal() {
            super(MicroraptorEntity.this, 1.2D, true);
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            float f = MicroraptorEntity.this.getBbWidth() - 0.1F;
            return (double)(f * 2.0F * f * 2.0F + pAttackTarget.getBbWidth());
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
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
}
