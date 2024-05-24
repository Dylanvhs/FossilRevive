package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LiopleurodonRenderer extends GeoEntityRenderer<LiopleurodonEntity> {
    public LiopleurodonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LiopleurodonModel());
    }

    @Override
    public ResourceLocation getTextureLocation(LiopleurodonEntity animatable) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/liopleurodon.png");
    }

    @Override
    public void render(LiopleurodonEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
