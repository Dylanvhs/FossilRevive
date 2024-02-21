package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.DodoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DodoRenderer extends MobRenderer<DodoEntity, Dodo<DodoEntity>> {
    public DodoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Dodo<>(pContext.bakeLayer(ModModelLayers.DODO_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(DodoEntity pEntity) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dodo.png");
    }

    @Override
    public void render(DodoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}