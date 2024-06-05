package net.dylanvhs.fossil_revive.event;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
    }




}