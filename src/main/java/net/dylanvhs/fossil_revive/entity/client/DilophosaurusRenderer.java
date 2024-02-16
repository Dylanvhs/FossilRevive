package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.DilophosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DilophosaurusRenderer extends MobRenderer<DilophosaurusEntity, Dilophosaurus<DilophosaurusEntity>> {

    public static ResourceLocation TEXTURE_JP = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus_jp.png");
    public static ResourceLocation TEXTURE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/dilophosaurus.png");

    public DilophosaurusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Dilophosaurus<>(pContext.bakeLayer(ModModelLayers.DILOPHOSAURUS_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(DilophosaurusEntity entity) {
        if(entity.isJP()){
            return TEXTURE_JP;
        }else{
            return TEXTURE;
        }
    }

    @Override
    public void render(DilophosaurusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
