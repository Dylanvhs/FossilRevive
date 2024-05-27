package net.dylanvhs.fossil_revive.entity.client;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.dylanvhs.fossil_revive.entity.custom.QuetzalcoatlusEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class QuetzalcoatlusModel extends GeoModel<QuetzalcoatlusEntity> {
    @Override
    public ResourceLocation getModelResource(QuetzalcoatlusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "geo/quetzalcoatlus.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(QuetzalcoatlusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/quetzalcoatlus.png");
    }

    @Override
    public ResourceLocation getAnimationResource(QuetzalcoatlusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "animations/quetzalcoatlus.animation.json");
    }
}
