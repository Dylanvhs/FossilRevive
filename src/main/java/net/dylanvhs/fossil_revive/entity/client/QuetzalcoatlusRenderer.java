package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.QuetzalcoatlusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class QuetzalcoatlusRenderer extends MobRenderer<QuetzalcoatlusEntity, Quetzalcoatlus<QuetzalcoatlusEntity>> {
    public QuetzalcoatlusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Quetzalcoatlus<>(pContext.bakeLayer(ModModelLayers.QUETZALCOATLUS_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(QuetzalcoatlusEntity pEntity) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/quetzalcoatlus.png");
    }

    @Override
    public void render(QuetzalcoatlusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
