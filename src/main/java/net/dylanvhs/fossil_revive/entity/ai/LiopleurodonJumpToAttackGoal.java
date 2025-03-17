package net.dylanvhs.fossil_revive.entity.ai;

import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class LiopleurodonJumpToAttackGoal extends JumpGoal {
    protected final LiopleurodonEntity mob;

    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    private final double speedModifier;
    private Vec3 chargeDirection;

    private final int interval;
    private boolean breached;

    public LiopleurodonJumpToAttackGoal(LiopleurodonEntity pathfinderMob, double speedModifier, int pInterval) {
        this.mob = pathfinderMob;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.chargeDirection = Vec3.ZERO;
        this.interval = reducedTickDelay(pInterval);
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        if (this.mob.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.mob.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.mob.blockPosition();
            for(int k : STEPS_TO_CHECK) {
                if (!this.waterIsClear(blockpos, i, j, k)) {
                    return false;
                }
            }
            return target instanceof Mob;
        }

    }

    public boolean isInterruptable() {
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.mob.getTarget();
        double d0 = this.mob.getDeltaMovement().y;
        if (target == null || !target.isAlive() || this.mob.hasChargeCooldown()) {
            return false;
        }
        return (!(d0 * d0 < (double)0.03F) || this.mob.getXRot() == 0.0F || !(Math.abs(this.mob.getXRot()) < 10.0F) || !this.mob.isInWater()) && !this.mob.onGround();
    }

    @Override
    public void start() {

        Direction direction = this.mob.getMotionDirection();
        this.mob.setDeltaMovement(this.mob.getDeltaMovement().add((double)direction.getStepX() * 0.6D, 1D, (double)direction.getStepZ() * 0.6D));
        this.mob.setAggressive(true);
        this.mob.setSprinting(true);
    }

    @Override
    public void stop() {
        this.mob.resetChargeCooldownTicks();
        this.mob.setXRot(0.0F);
        this.mob.setSprinting(false);
    }

    private boolean waterIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        BlockPos blockpos = pPos.offset(pDx * pScale, 0, pDz * pScale);
        return this.mob.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.mob.level().getBlockState(blockpos).blocksMotion();
    }


    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target == null) {
            return;
        }
        this.mob.getLookControl().setLookAt(target);
        boolean flag = this.breached;
        if (!flag) {
            FluidState fluidstate = this.mob.level().getFluidState(this.mob.blockPosition());
            this.breached = fluidstate.is(FluidTags.WATER);
        }

        if (this.breached && !flag) {
            this.mob.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vec3 vec3 = this.mob.getDeltaMovement();
        if (vec3.y * vec3.y < (double)0.03F && this.mob.getXRot() != 0.0F) {
            this.mob.setXRot(Mth.rotLerp(0.2F, this.mob.getXRot(), 0.0F));
        } else if (vec3.length() > (double)1.0E-5F) {
            double d0 = vec3.horizontalDistance();
            double d1 = Math.atan2(-vec3.y, d0) * (double)(180F / (float)Math.PI);
            this.mob.setXRot((float)d1);
        };
        this.tryToHurt();
    }

    protected void tryToHurt() {
        List<LivingEntity> nearbyEntities = this.mob.level().getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), this.mob, this.mob.getBoundingBox());
        if (!nearbyEntities.isEmpty()) {
            LivingEntity livingEntity = nearbyEntities.get(0);
            if (!(livingEntity instanceof LiopleurodonEntity)) {
                livingEntity.hurt(this.mob.damageSources().mobAttack(this.mob), (float) this.mob.getAttributeValue(Attributes.ATTACK_DAMAGE));
                float speed = Mth.clamp(this.mob.getSpeed() * 1.65f, 0.2f, 3.0f);
                float shieldBlockModifier = livingEntity.isDamageSourceBlocked(this.mob.damageSources().mobAttack(this.mob)) ? 0.5f : 1.0f;
                livingEntity.knockback(shieldBlockModifier * speed * 2.0D, this.chargeDirection.x(), this.chargeDirection.z());
                double knockbackResistance = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().add(0.0, 0.4f * knockbackResistance, 0.0));
                this.mob.swing(InteractionHand.MAIN_HAND);
                if (livingEntity.equals(this.mob.getTarget())) {
                    this.stop();
                }
            }
        }
    }
}