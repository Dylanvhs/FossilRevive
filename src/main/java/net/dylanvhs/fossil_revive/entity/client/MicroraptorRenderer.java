package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MicroraptorRenderer extends MobRenderer<MicroraptorEntity, Microraptor<MicroraptorEntity>> {
    private static final ResourceLocation RED = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor_red.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor_yellow.png");
    private static final ResourceLocation GREEN = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor_green.png");
    private static final ResourceLocation BLUE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor.png");
    public MicroraptorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Microraptor<>(pContext.bakeLayer(ModModelLayers.MICRORAPTOR_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MicroraptorEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> RED;
            case 2 -> YELLOW;
            case 3 -> GREEN;
            default -> BLUE;
        };
    }

    @Override
    public void render(MicroraptorEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}

