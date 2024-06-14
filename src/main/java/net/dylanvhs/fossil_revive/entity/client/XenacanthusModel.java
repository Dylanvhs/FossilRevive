package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.XenacanthusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class XenacanthusModel extends GeoModel<XenacanthusEntity> {
    @Override
    public ResourceLocation getModelResource(XenacanthusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "geo/xenacanthus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(XenacanthusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_orange.png");
    }

    @Override
    public ResourceLocation getAnimationResource(XenacanthusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "animations/xenacanthus.animation.json");
    }

    @Override
    public void setCustomAnimations(XenacanthusEntity animatable, long instanceId, AnimationState<XenacanthusEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        if (animationState == null) return;
        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        CoreGeoBone root = this.getAnimationProcessor().getBone("xenacanthus");
        if (animatable.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
            root.setRotY(extraDataOfType.netHeadYaw() * ((float)Math.PI / 180F));
            root.setRotX(extraDataOfType.headPitch() * ((float)Math.PI / 180F));
        }
    }
}
