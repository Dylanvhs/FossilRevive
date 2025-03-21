package net.dylanvhs.fossil_revive.block;


import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.block.entity.AnalyzerEntity;
import net.dylanvhs.fossil_revive.block.entity.CultivatorEntity;
import net.dylanvhs.fossil_revive.block.entity.SuspiciousCrumbledSiltstoneEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FossilRevive.MOD_ID);

    public static final RegistryObject<BlockEntityType<AnalyzerEntity>> ANALYZER_BE =
            BLOCK_ENTITIES.register("analyzer_be", () ->
                    BlockEntityType.Builder.of(AnalyzerEntity::new,
                            ModBlocks.ANALYZER.get()).build(null));

    public static final RegistryObject<BlockEntityType<CultivatorEntity>> CULTIVATOR_BE =
            BLOCK_ENTITIES.register("cultivator_be", () ->
                    BlockEntityType.Builder.of(CultivatorEntity::new,
                            ModBlocks.CULTIVATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<SuspiciousCrumbledSiltstoneEntity>> SUSPICIOUS_CRUMBLED_SILTSTONE_BE =
            BLOCK_ENTITIES.register("suspicious_crumbled_siltstone_be", () ->
                    BlockEntityType.Builder.of(SuspiciousCrumbledSiltstoneEntity::new,
                            ModBlocks.SUSPICIOUS_CRUMBLED_SILTSTONE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
