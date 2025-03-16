package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.RanalophosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RanalophosaurusRenderer extends GeoEntityRenderer<RanalophosaurusEntity> {

    private static final ResourceLocation ADULT_TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/ranalophosaurus.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/ranalophosaurus_baby.png");

    public RanalophosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RanalophosaurusModel());
    }

    @Override
    public ResourceLocation getTextureLocation(RanalophosaurusEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_TEXTURE;
        } else return ADULT_TEXTURE;
    }

    @Override
    public void render(RanalophosaurusEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
