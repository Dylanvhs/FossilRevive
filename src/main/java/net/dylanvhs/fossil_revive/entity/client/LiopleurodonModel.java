package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LiopleurodonModel extends GeoModel<LiopleurodonEntity> {
    @Override
    public ResourceLocation getModelResource(LiopleurodonEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "geo/liopleurodon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LiopleurodonEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/liopleurodon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LiopleurodonEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "animations/liopleurodon.animation.json");
    }

    @Override
    public void setCustomAnimations(LiopleurodonEntity animatable, long instanceId, AnimationState<LiopleurodonEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone root = this.getAnimationProcessor().getBone("Liopleurodon");
        if (animatable.isBaby()) {
            root.setScaleX(0.5F);
            root.setScaleY(0.5F);
            root.setScaleZ(0.5F);
        } else {
            root.setScaleX(1.5F);
            root.setScaleY(1.5F);
            root.setScaleZ(1.5F);
        }
    }
}
