package net.dylanvhs.fossil_revive.entity;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.entity.custom.*;
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
                    .sized(3f, 1.75f).build("liopleurodon"));

    public static final RegistryObject<EntityType<QuetzalcoatlusEntity>> QUETZALCOATLUS =
            ENTITY_TYPES.register("quetzalcoatlus", () -> EntityType.Builder.of(QuetzalcoatlusEntity::new, MobCategory.CREATURE)
                    .sized(4.5f, 4.5f).build("quetzalcoatlus"));

    public static final RegistryObject<EntityType<MicroraptorEntity>> MICRORAPTOR =
            ENTITY_TYPES.register("microraptor", () -> EntityType.Builder.of(MicroraptorEntity::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.7f).build("microraptor"));

    public static final RegistryObject<EntityType<DilophosaurusEntity>> DILOPHOSAURUS =
            ENTITY_TYPES.register("dilophosaurus", () -> EntityType.Builder.of(DilophosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1f, 2.4f).build("dilophosaurus"));

    public static final RegistryObject<EntityType<XenacanthusEntity>> XENACANTHUS =
            ENTITY_TYPES.register("xenacanthus", () -> EntityType.Builder.of(XenacanthusEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.5f, 0.3f).build("xenacanthus"));

    public static final RegistryObject<EntityType<DodoEntity>> DODO =
            ENTITY_TYPES.register("dodo", () -> EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 1.25f).build("dodo"));

    public static final RegistryObject<EntityType<RanalophosaurusEntity>> RANALOPHOSAURUS =
            ENTITY_TYPES.register("ranalophosaurus", () -> EntityType.Builder.of(RanalophosaurusEntity::new, MobCategory.CREATURE)
                    .sized(1f, 2f).build("ranalophosaurus"));





    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
