package net.dylanvhs.fossil_revive.entity;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.LiopleurodonEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FossilRevive.MOD_ID);

    public static final RegistryObject<EntityType<LiopleurodonEntity>> LIOPLEURODON =
            ENTITY_TYPES.register("liopleurodon", () -> EntityType.Builder.of(LiopleurodonEntity::new, MobCategory.WATER_CREATURE)
                    .sized(3f, 2.5f).build("liopleurodon"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
