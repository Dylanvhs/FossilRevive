package net.dylanvhs.fossil_revive.event;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.block.ModBlockEntityRenderers;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.entity.custom.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.ApiStatus;

@Mod.EventBusSubscriber(modid = FossilRevive.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEvents {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
    }



    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent e) {

    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LIOPLEURODON.get(), LiopleurodonEntity.setAttributes());
        event.put(ModEntities.DILOPHOSAURUS.get(), DilophosaurusEntity.setAttributes());
        event.put(ModEntities.QUETZALCOATLUS.get(), QuetzalcoatlusEntity.setAttributes());
        event.put(ModEntities.DODO.get(), DodoEntity.setAttributes());
        event.put(ModEntities.XENACANTHUS.get(), XenacanthusEntity.setAttributes());
        event.put(ModEntities.RANALOPHOSAURUS.get(), RanalophosaurusEntity.setAttributes());
        event.put(ModEntities.MICRORAPTOR.get(), MicroraptorEntity.setAttributes());
        event.put(ModEntities.SPINOSAURUS.get(), SpinosaurusEntity.setAttributes());
    }

    public static class RegisterRenderers extends EntityRenderersEvent
    {
        @ApiStatus.Internal
        public RegisterRenderers()
        {
        }

        public <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<? extends T> blockEntityType, BlockEntityRendererProvider<T> blockEntityRendererProvider)
        {
            ModBlockEntityRenderers.register(blockEntityType, blockEntityRendererProvider);
        }
    }




}