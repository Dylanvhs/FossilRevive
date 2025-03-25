package net.dylanvhs.fossil_revive.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.SpinosaurusEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpinosaurusRenderer extends GeoEntityRenderer<SpinosaurusEntity> {

    private static final ResourceLocation DEFAULT = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurus.png");
    private static final ResourceLocation DEFAULT_BABY = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurus_baby.png");
    private static final ResourceLocation BLUE = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurus0.png");
    private static final ResourceLocation BLUE_BABY = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurus0_baby.png");
    private static final ResourceLocation JP = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurusjp3.png");
    private static final ResourceLocation JP_BABY = new ResourceLocation(FossilRevive.MOD_ID, "textures/entity/spinosaurusjp3_baby.png");


    public SpinosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SpinosaurusModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SpinosaurusEntity animatable) {
        if(animatable.isJP() && animatable.isBaby()){
            return JP_BABY;
        } else if(animatable.isJP() && !animatable.isBaby()){
            return JP;
        }
        if (animatable.isBaby() && !animatable.isJP()) {
            return switch (animatable.getVariant()) {
                case 1 -> BLUE_BABY;
                default -> DEFAULT_BABY;
            };
        } else  {
            return switch (animatable.getVariant()) {
                case 1 -> BLUE;
                default -> DEFAULT;
            };
        }
    }

    @Override
    public void render(SpinosaurusEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}