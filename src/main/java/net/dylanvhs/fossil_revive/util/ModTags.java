package net.dylanvhs.fossil_revive.util;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    private static TagKey<Block> tag(String name) {
        return BlockTags.create(new ResourceLocation(FossilRevive.MOD_ID, name));
    }

    public static class Items {



        private static TagKey<Item> registerItemTag(String name) {
            return ItemTags.create(new ResourceLocation(FossilRevive.MOD_ID, name));
        }
    }
}

