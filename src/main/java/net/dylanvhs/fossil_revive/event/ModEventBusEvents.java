package net.dylanvhs.fossil_revive.event;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.ModEntities;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.dylanvhs.fossil_revive.entity.custom.MicroraptorEntity;
import net.dylanvhs.fossil_revive.entity.custom.QuetzalcoatlusEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FossilRevive.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LIOPLEURODON.get(), LiopleurodonEntity.createAttributes().build());
        event.put(ModEntities.QUETZALCOATLUS.get(), QuetzalcoatlusEntity.createAttributes().build());
        event.put(ModEntities.MICRORAPTOR.get(), MicroraptorEntity.createAttributes().build());
    }
}
