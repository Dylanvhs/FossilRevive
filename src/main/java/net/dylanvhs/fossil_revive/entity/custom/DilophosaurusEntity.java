package net.dylanvhs.fossil_revive.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class DilophosaurusEntity extends PathfinderMob {

    public DilophosaurusEntity(EntityType<? extends DilophosaurusEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }



    public final AnimationState idleAnimationState = new AnimationState();
    public static final AnimationState attackAnimationState = new AnimationState();
    private int idleAnimationTimeOut = 0;
    private int attackTick;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationState();
        }
    }


    private void setupAnimationState() {
        if (this.idleAnimationTimeOut <= 0) {
            this.idleAnimationTimeOut = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeOut;
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
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new DilophosaurusEntity.DilophosaurusMeleeAttackGoal());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 45D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 4f)
                .add(Attributes.ATTACK_SPEED, 0.5f);
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            this.attackTick = 10;
        }
        super.handleEntityEvent(pId);
    }

    public int getAttackTick() {
        return this.attackTick;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AttackTick", this.attackTick);

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.attackTick = pCompound.getInt("AttackTick");
    }


    class DilophosaurusMeleeAttackGoal extends MeleeAttackGoal {
        public DilophosaurusMeleeAttackGoal() {
            super(DilophosaurusEntity.this, 1.0D, true);
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            float f = DilophosaurusEntity.this.getBbWidth() - 0.1F;
            return (double)(f * 2.0F * f * 2.0F + pAttackTarget.getBbWidth());
        }
    }
}
