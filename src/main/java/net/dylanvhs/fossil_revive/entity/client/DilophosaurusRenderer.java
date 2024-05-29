package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DilophosaurusRenderer extends GeoEntityRenderer<DilophosaurusEntity> {

    public DilophosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DilophosaurusModel());
    }

    private static final ResourceLocation ADULT_TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus_baby.png");
    private static final ResourceLocation JP = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus_jp.png");
    private static final ResourceLocation JP_BABY = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus_baby_jp.png");

    public ResourceLocation getTextureLocation(DilophosaurusEntity animatable) {

        if (animatable.isBaby() && !animatable.isJP()) {
            return BABY_TEXTURE;
        } else if(animatable.isJP() && animatable.isBaby()){
            return JP_BABY;
        } else if(animatable.isJP() && !animatable.isBaby()){
            return JP;
        } else return ADULT_TEXTURE;
    }

    @Override
    public void render(DilophosaurusEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
