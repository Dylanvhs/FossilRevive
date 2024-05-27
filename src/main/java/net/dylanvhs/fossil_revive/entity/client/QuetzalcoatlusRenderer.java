package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.QuetzalcoatlusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class QuetzalcoatlusRenderer extends GeoEntityRenderer<QuetzalcoatlusEntity> {
    public QuetzalcoatlusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new QuetzalcoatlusModel());
    }

    @Override
    public ResourceLocation getTextureLocation(QuetzalcoatlusEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/quetzalcoatlus.png");
    }

    @Override
    public void render(QuetzalcoatlusEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
