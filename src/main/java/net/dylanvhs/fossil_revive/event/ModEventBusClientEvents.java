package net.dylanvhs.fossil_revive.event;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.client.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = FossilRevive.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.LIOPLEURODON_LAYER, Liopleurodon::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.QUETZALCOATLUS_LAYER, Quetzalcoatlus::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MICRORAPTOR_LAYER, Microraptor::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DILOPHOSAURUS_LAYER, Dilophosaurus::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.XENACANTHUS_LAYER, Xenacanthus::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DODO_LAYER, Dodo::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RANA_LAYER, Ranalophosaurus::createBodyLayer);
    }

}
