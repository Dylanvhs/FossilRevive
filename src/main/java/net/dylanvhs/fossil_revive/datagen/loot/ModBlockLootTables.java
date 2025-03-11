package net.dylanvhs.fossil_revive.datagen.loot;

import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.ANALYZER.get());
        this.dropSelf(ModBlocks.CULTIVATOR.get());
        this.dropSelf(ModBlocks.CRUMBLED_SILTSTONE.get());
        this.dropSelf(ModBlocks.FERTILE_MOSS.get());
        this.dropWhenSilkTouch(ModBlocks.DILOPHOSAURUS_EGG.get());
        this.dropOther(ModBlocks.SUSPICIOUS_CRUMBLED_SILTSTONE.get(), Items.AIR);

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
