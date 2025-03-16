package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MicroraptorModel extends GeoModel<MicroraptorEntity> {
    private static final ResourceLocation ADULT_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/microraptor.geo.json");

    @Override
    public ResourceLocation getModelResource(MicroraptorEntity animatable) {
       return ADULT_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(MicroraptorEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MicroraptorEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "animations/microraptor.animation.json");
    }

    @Override
    public void setCustomAnimations(MicroraptorEntity animatable, long instanceId, AnimationState<MicroraptorEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head");
        CoreGeoBone root = this.getAnimationProcessor().getBone("Microraptor");

        if (animatable.onGround()) {
            head.setRotY(extraDataOfType.netHeadYaw() * ((float) Math.PI / 180F));
            head.setRotX(extraDataOfType.headPitch() * ((float) Math.PI / 180F));
        }
        if (animatable.isBaby()) {
            root.setScaleX(0.4F);
            root.setScaleY(0.4F);
            root.setScaleZ(0.4F);

            head.setScaleX(1.75F);
            head.setScaleY(1.75F);
            head.setScaleZ(1.75F);

        } else {
            root.setScaleX(1.0F);
            root.setScaleY(1.0F);
            root.setScaleZ(1.0F);

            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
        }
    }
}

