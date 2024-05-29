package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DilophosaurusModel extends GeoModel<DilophosaurusEntity> {
    private static final ResourceLocation ADULT_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/dilophosaurus.geo.json");
    private static final ResourceLocation BABY_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/dilophosaurus_baby.geo.json");

    private static final ResourceLocation ADULT_ANIMATION = new ResourceLocation(FossilRevive.MOD_ID, "animations/dilophosaurus.animation.json");
    private static final ResourceLocation BABY_ANIMATION = new ResourceLocation(FossilRevive.MOD_ID, "animations/dilophosaurus_baby.animation.json");

    @Override
    public ResourceLocation getModelResource(DilophosaurusEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_MODEL;
        } else return ADULT_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(DilophosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DilophosaurusEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_ANIMATION;
        } else return ADULT_ANIMATION;
    }

    @Override
    public void setCustomAnimations(DilophosaurusEntity animatable, long instanceId, AnimationState<DilophosaurusEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        if (!animatable.isSprinting()) {
            head.setRotY(extraDataOfType.netHeadYaw() * ((float) Math.PI / 180F));
            head.setRotX(extraDataOfType.headPitch() * ((float) Math.PI / 180F));
        }
    }
}

