package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LiopleurodonRenderer extends MobRenderer<LiopleurodonEntity, Liopleurodon<LiopleurodonEntity>> {
    public LiopleurodonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Liopleurodon<>(pContext.bakeLayer(ModModelLayers.LIOPLEURODON_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(LiopleurodonEntity pEntity) {
        return new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/liopleurodon.png");
    }

    @Override
    public void render(LiopleurodonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
