package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LiopleurodonModel extends GeoModel<LiopleurodonEntity> {
    private static final ResourceLocation ADULT_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/liopleurodon.geo.json");
    private static final ResourceLocation BABY_MODEL = new ResourceLocation(FossilRevive.MOD_ID, "geo/liopleurodon_baby.geo.json");


    @Override
    public ResourceLocation getModelResource(LiopleurodonEntity animatable) {
       if (animatable.isBaby()) {
           return BABY_MODEL;
       } else return ADULT_MODEL;
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
        CoreGeoBone root_baby = this.getAnimationProcessor().getBone("BabyLiopleurodon");
        if (animatable.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D && !animatable.isBaby()) {
            root.setRotY(extraDataOfType.netHeadYaw() * ((float)Math.PI / 180F));
            root.setRotX(extraDataOfType.headPitch() * ((float)Math.PI / 180F));
        } else if (animatable.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D && animatable.isBaby()) {
            root_baby.setRotY(extraDataOfType.netHeadYaw() * ((float)Math.PI / 180F));
            root_baby.setRotX(extraDataOfType.headPitch() * ((float)Math.PI / 180F));
        }

    }

}
