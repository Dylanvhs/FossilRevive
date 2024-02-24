package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.RanalophosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RanalophosaurusRenderer extends MobRenderer<RanalophosaurusEntity, Ranalophosaurus<RanalophosaurusEntity>> {
    public RanalophosaurusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Ranalophosaurus<>(pContext.bakeLayer(ModModelLayers.RANA_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(RanalophosaurusEntity pEntity) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/ranalophosaurus.png");
    }

    @Override
    public void render(RanalophosaurusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
