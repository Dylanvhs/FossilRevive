package net.dylanvhs.fossil_revive.entity.client;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModPlayerRenderer extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public ModPlayerRenderer(EntityRendererProvider.Context pContext, boolean pUseSlimModel) {
        super(pContext, new PlayerModel<>(pContext.bakeLayer(pUseSlimModel ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), pUseSlimModel), 0.5F);
        this.addLayer(new MicroraptorOnShoulderLayer<>(this, pContext.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer pEntity) {
        return pEntity.getSkinTextureLocation();
    }
}
