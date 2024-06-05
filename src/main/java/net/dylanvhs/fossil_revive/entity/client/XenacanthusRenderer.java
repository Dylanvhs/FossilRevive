package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.XenacanthusEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class XenacanthusRenderer extends GeoEntityRenderer<XenacanthusEntity> {
    private static final ResourceLocation TEXTURE_CORAL_STALKER = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_red_blue.png");
    private static final ResourceLocation TEXTURE_GILDED_STEALTH = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_black.png");
    private static final ResourceLocation TEXTURE_AZALEA_CRUISER = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_green.png");
    private static final ResourceLocation TEXTURE_SHELL_CRUSHER = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/xenacanthus_orange.png");


    public XenacanthusRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new XenacanthusModel());
    }

    protected void scale(XenacanthusEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(XenacanthusEntity animatable) {
        return switch (animatable.getVariant()) {
            case 1 -> TEXTURE_CORAL_STALKER;
            case 2 -> TEXTURE_GILDED_STEALTH;
            case 3 -> TEXTURE_AZALEA_CRUISER;
            default -> TEXTURE_SHELL_CRUSHER;
        };
    }
}
