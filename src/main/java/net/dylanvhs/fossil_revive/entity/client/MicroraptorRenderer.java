package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MicroraptorRenderer extends GeoEntityRenderer<MicroraptorEntity> {

    private static final ResourceLocation TEXTURE_1 = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor.png");
    private static final ResourceLocation TEXTURE_2 = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor_green.png");
    private static final ResourceLocation TEXTURE_3 = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor_red.png");
    private static final ResourceLocation TEXTURE_4 = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor_yellow.png");


    public MicroraptorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MicroraptorModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MicroraptorEntity animatable) {
        return switch (animatable.getVariant()) {
            case 1 -> TEXTURE_2;
            case 2 -> TEXTURE_3;
            case 3 -> TEXTURE_4;
            default -> TEXTURE_1;
        };
    }

    @Override
    public void render(MicroraptorEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
