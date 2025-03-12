package net.dylanvhs.fossil_revive.block;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.block.custom.*;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


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



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
