package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.SpinosaurusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SpinosaurusModel extends GeoModel<SpinosaurusEntity> {
    private static final ResourceLocation ADULT_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/spinosaurus.geo.json");
    private static final ResourceLocation BABY_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/spinosaurus.geo.json");

    private static final ResourceLocation ADULT_ANIMATION = new ResourceLocation(FossilRevive.MOD_ID, "animations/dilophosaurus.animation.json");
    private static final ResourceLocation BABY_ANIMATION = new ResourceLocation(FossilRevive.MOD_ID, "animations/dilophosaurus.animation.json");

    @Override
    public ResourceLocation getModelResource(SpinosaurusEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_MODEL;
        } else return ADULT_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SpinosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpinosaurusEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_ANIMATION;
        } else return ADULT_ANIMATION;
    }

    @Override
    public void setCustomAnimations(SpinosaurusEntity animatable, long instanceId, AnimationState<SpinosaurusEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head");
        CoreGeoBone root = this.getAnimationProcessor().getBone("Spinosaurus");

        if (!animatable.isSprinting()) {
            head.setRotY(extraDataOfType.netHeadYaw() * ((float) Math.PI / 180F));
            head.setRotX(extraDataOfType.headPitch() * ((float) Math.PI / 180F));
        }

        if (animatable.isInWater()) {
            if (animatable.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
                root.setRotY(extraDataOfType.netHeadYaw() * ((float) Math.PI / 180F));
                root.setRotX(extraDataOfType.headPitch() * ((float) Math.PI / 180F));
            }
        }
    }
}
