package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MicroraptorRenderer extends MobRenderer<MicroraptorEntity, Microraptor<MicroraptorEntity>> {
    public MicroraptorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Microraptor<>(pContext.bakeLayer(ModModelLayers.MICRORAPTOR_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(MicroraptorEntity pEntity) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/microraptor.png");
    }

    @Override
    public void render(MicroraptorEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}

