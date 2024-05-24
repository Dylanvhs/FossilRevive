package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DilophosaurusModel extends GeoModel<DilophosaurusEntity> {
    @Override
    public ResourceLocation getModelResource(DilophosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "geo/dilophosaurus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DilophosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DilophosaurusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "animations/dilophosaurus.animation.json");
    }
}
