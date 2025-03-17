package net.dylanvhs.fossil_revive.entity.ai;

import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class LiopleurodonPrepareJumpGoal extends Goal {
    protected final LiopleurodonEntity longhorn;

    public LiopleurodonPrepareJumpGoal(LiopleurodonEntity longhorn) {
        this.longhorn = longhorn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.longhorn.getTarget();
        if (target == null || !target.isAlive() || !this.longhorn.isWithinYRange(target) || this.longhorn.isBaby()) {
            this.longhorn.resetChargeCooldownTicks();
            return false;
        }
        return target instanceof Player && longhorn.hasChargeCooldown() ;
    }

    @Override
    public void start() {
        LivingEntity target = this.longhorn.getTarget();
        if (target == null) {
            return;
        }
        this.longhorn.setHasTarget(true);
        this.longhorn.resetChargeCooldownTicks();
    }

    @Override
    public void stop() {
        this.longhorn.setHasTarget(false);
    }

    @Override
    public void tick() {
        LivingEntity target = this.longhorn.getTarget();
        if (target == null) {
            return;
        }
        this.longhorn.setChargeCooldownTicks(Math.max(0, this.longhorn.getChargeCooldownTicks() - 1));
    }
}