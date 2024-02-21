package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.dylanvhs.fossil_revive.entity.custom.XenacanthusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class XenacanthusRenderer extends MobRenderer<XenacanthusEntity, Xenacanthus<XenacanthusEntity>> {
    private static final ResourceLocation RED_BLUE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_red_blue.png");
    private static final ResourceLocation BLACK = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_black.png");
    private static final ResourceLocation GREEN = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_green.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_orange.png");
    public XenacanthusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Xenacanthus<>(pContext.bakeLayer(ModModelLayers.XENACANTHUS_LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(XenacanthusEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> RED_BLUE;
            case 2 -> BLACK;
            case 3 -> GREEN;
            default -> ORANGE;
        };
    }

    @Override
    public void render(XenacanthusEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}

