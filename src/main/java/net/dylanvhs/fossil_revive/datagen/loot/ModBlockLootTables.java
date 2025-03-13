package net.dylanvhs.fossil_revive.datagen.loot;

import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.dylanvhs.fossil_revive.item.ModItems;
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
        this.dropWhenSilkTouch(ModBlocks.DODO_EGG.get());
        this.dropOther(ModBlocks.SUSPICIOUS_CRUMBLED_SILTSTONE.get(), Items.AIR);
        this.dropOther(ModBlocks.OSMUNDA_CROP.get(), ModItems.OSMUNDA_SEEDS.get());
        this.dropOther(ModBlocks.HORSETAIL_CROP.get(), ModItems.HORSETAIL_SEEDS.get());
        this.dropSelf(ModBlocks.OSMUNDA.get());
        this.dropSelf(ModBlocks.HORSETAIL.get());
        this.dropSelf(ModBlocks.FOSSIL_TORCH.get());
        this.dropOther(ModBlocks.WALL_FOSSIL_TORCH.get(), ModBlocks.FOSSIL_TORCH.get());
        this.dropSelf(ModBlocks.TALL_HORSETAIL.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
