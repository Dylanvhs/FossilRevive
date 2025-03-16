package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;

import net.minecraft.resources.ResourceLocation;
import net.dylanvhs.fossil_revive.entity.custom.RanalophosaurusEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RanalophosaurusModel extends GeoModel<RanalophosaurusEntity> {
    private static final ResourceLocation ADULT_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/ranalophosaurus.geo.json");
    private static final ResourceLocation BABY_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/ranalophosaurus_baby.geo.json");


    @Override
    public ResourceLocation getModelResource(RanalophosaurusEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_MODEL;
        } else return ADULT_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(RanalophosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/ranalophosaurus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RanalophosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "animations/ranalophosaurus.animation.json");
    }

    @Override
    public void setCustomAnimations(RanalophosaurusEntity animatable, long instanceId, AnimationState<RanalophosaurusEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        if (animatable.onGround()) {
            head.setRotY(extraDataOfType.netHeadYaw() * ((float) Math.PI / 180F));
            head.setRotX(extraDataOfType.headPitch() * ((float) Math.PI / 180F));
        }
    }
}

