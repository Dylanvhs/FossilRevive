package net.dylanvhs.fossil_revive.block;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.block.custom.*;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FossilRevive.MOD_ID);

    public static final RegistryObject<Block> ANALYZER = registerBlock("analyzer",
            () -> new Analyzer(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CULTIVATOR = registerBlock("cultivator",
            () -> new Cultivator(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> CRUMBLED_SILTSTONE = registerBlock("crumbled_siltstone",
            () -> new FallingBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.GRAVEL).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> SUSPICIOUS_CRUMBLED_SILTSTONE = registerBlock("suspicious_crumbled_siltstone",
            () -> new SuspiciousCrumbledSiltstoneBlock(ModBlocks.CRUMBLED_SILTSTONE.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_GRAVEL).pushReaction(PushReaction.DESTROY), SoundEvents.BRUSH_GRAVEL, SoundEvents.BRUSH_GRAVEL_COMPLETED));

    public static final RegistryObject<Block> FERTILE_MOSS = registerBlock("fertile_moss",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.MOSS)));

    public static final RegistryObject<Block> FOSSIL_TORCH = registerBlock("fossil_torch",
            () -> new FossilTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(litBlockEmission(15)).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> WALL_FOSSIL_TORCH = registerBlock("wall_fossil_torch",
            () -> new WallFossilTorchBlock(BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(litBlockEmission(15)).sound(SoundType.WOOD).dropsLike(FOSSIL_TORCH.get()).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> OSMUNDA = registerBlock("osmunda",
            () -> new FlowerBlock(MobEffects.NIGHT_VISION, 5,BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> OSMUNDA_CROP = registerBlock("osmunda_crop",
            () ->new OsmundaCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> HORSETAIL = registerBlock("horsetail",
            () -> new HorsetailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> TALL_HORSETAIL = registerBlock("tall_horsetail",
            () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> HORSETAIL_CROP = registerBlock("horsetail_crop",
            () -> new HorsetailCropBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DILOPHOSAURUS_EGG = registerBlock("dilophosaurus_egg_block",
            () -> new DilophosaurusEggBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).forceSolidOn().strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DODO_EGG = registerBlock("dodo_egg_block",
            () -> new DodoEggBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).forceSolidOn().strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion().pushReaction(PushReaction.DESTROY)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
