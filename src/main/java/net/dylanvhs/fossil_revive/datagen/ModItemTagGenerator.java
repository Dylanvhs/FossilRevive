package net.dylanvhs.fossil_revive.datagen;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.dylanvhs.fossil_revive.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;


import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, FossilRevive.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.BONES_MUSIC_DISC.get()).add(ModItems.WHEN_YOURE_THE_LAST_MUSIC_DISC.get());

        tag(ModTags.Items.DNA)
                .add(ModItems.AMARGASAURUS_DNA.get())
                .add(ModItems.DILOPHOSAURUS_DNA.get())
                .add(ModItems.MICRORAPTOR_DNA.get())
                .add(ModItems.TROODON_DNA.get())
                .add(ModItems.TAPEJARA_DNA.get())
                .add(ModItems.DODO_DNA.get())
                .add(ModItems.SMILODON_DNA.get())
                .add(ModItems.OVIRAPTOR_DNA.get())
                .add(ModItems.LIOPLEURODON_DNA.get())
                .add(ModItems.KELENTAURUS_DNA.get())
                .add(ModItems.TASMANIAN_TIGER_DNA.get())
                .add(ModItems.QUETZALCOATLUS_DNA.get())
                .add(ModItems.KELENKEN_DNA.get());

    }
}
