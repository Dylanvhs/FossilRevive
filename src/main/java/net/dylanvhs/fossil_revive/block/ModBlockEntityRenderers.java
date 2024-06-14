package net.dylanvhs.fossil_revive.block;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import net.dylanvhs.fossil_revive.block.client.SuspiciousCrumbledSiltstoneRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;

@OnlyIn(Dist.CLIENT)
public class ModBlockEntityRenderers {
    private static final Map<BlockEntityType<?>, BlockEntityRendererProvider<?>> PROVIDERS = new java.util.concurrent.ConcurrentHashMap<>();

    public static <T extends BlockEntity> void register(BlockEntityType<? extends T> pType, BlockEntityRendererProvider<T> pRenderProvider) {
        PROVIDERS.put(pType, pRenderProvider);
    }

    public static Map<BlockEntityType<?>, BlockEntityRenderer<?>> createEntityRenderers(BlockEntityRendererProvider.Context pContext) {
        ImmutableMap.Builder<BlockEntityType<?>, BlockEntityRenderer<?>> builder = ImmutableMap.builder();
        PROVIDERS.forEach((p_258150_, p_258151_) -> {
            try {
                builder.put(p_258150_, p_258151_.create(pContext));
            } catch (Exception exception) {
                throw new IllegalStateException("Failed to create model for " + BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(p_258150_), exception);
            }
        });
        return builder.build();
    }

    static {
        register(ModBlockEntities.SUSPICIOUS_CRUMBLED_SILTSTONE_BE.get(), SuspiciousCrumbledSiltstoneRenderer::new);
    }
}

