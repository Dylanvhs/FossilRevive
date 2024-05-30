package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.DodoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DodoRenderer extends GeoEntityRenderer<DodoEntity> {

    private static final ResourceLocation ADULT_TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dodo.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dodo_baby.png");

    public DodoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DodoModel());
    }

    @Override
    public ResourceLocation getTextureLocation(DodoEntity animatable) {
        if (animatable.isBaby()) {
            return BABY_TEXTURE;
        } else return ADULT_TEXTURE;
    }

    @Override
    public void render(DodoEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
