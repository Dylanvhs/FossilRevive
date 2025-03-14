package net.dylanvhs.fossil_revive.datagen;


import net.dylanvhs.fossil_revive.FossilRevive;

import net.dylanvhs.fossil_revive.block.ModBlocks;
import net.dylanvhs.fossil_revive.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FossilRevive.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.FOSSIL);
        simpleItem(ModItems.PLANT_FOSSIL);
        simpleItem(ModItems.CRUMBlED_FOSSIL);
        simpleItem(ModItems.DNA_BOTTLE);
        simpleItem(ModItems.HYBRID_DNA_BOTTLE);
        simpleItem(ModItems.SYRINGE);
        simpleItem(ModItems.AMARGASAURUS_DNA);
        simpleItem(ModItems.CARNOTAURUS_DNA);
        simpleItem(ModItems.DILOPHOSAURUS_DNA);
        simpleItem(ModItems.DODO_DNA);
        simpleItem(ModItems.KELENKEN_DNA);
        simpleItem(ModItems.KELENTAURUS_DNA);
        simpleItem(ModItems.LIOPLEURODON_DNA);
        simpleItem(ModItems.OVIRAPTOR_DNA);
        simpleItem(ModItems.SMILODON_DNA);
        simpleItem(ModItems.TAPEJARA_DNA);
        simpleItem(ModItems.TASMANIAN_TIGER_DNA);
        simpleItem(ModItems.TROODON_DNA);
        simpleItem(ModItems.QUETZALCOATLUS_DNA);
        simpleItem(ModItems.MICRORAPTOR_DNA);
        simpleItem(ModItems.SMILODON_EMBRYO);
        simpleItem(ModItems.LIOPLEURODON_EMBRYO);
        simpleItem(ModItems.TASMANIAN_TIGER_EMBRYO);
        simpleItem(ModItems.BONES_MUSIC_DISC);
        simpleItem(ModItems.WHEN_YOURE_THE_LAST_MUSIC_DISC);
        simpleItem(ModItems.DIRTY_CLOTH);
        simpleItem(ModItems.RAW_XENACANTHUS);
        simpleItem(ModItems.COOKED_XENACANTHUS);
        simpleItem(ModItems.XENACANTHUS_BUCKET);
        simpleItem(ModItems.DILOPHOSAURUS_EGG);
        simpleItem(ModItems.DODO_EGG);
        simpleItem(ModItems.HORSETAIL_SEEDS);
        simpleItem(ModItems.MONTSECHIA_SEEDS);
        simpleItem(ModItems.OSMUNDA_SEEDS);
        simpleItem(ModItems.PINKSPARK_SEEDS);
        simpleItem(ModItems.SPIRALFLOWER_SEEDS);
        simpleItem(ModItems.FOSSIL_TORCH);

        withExistingParent(ModItems.LIOPLEURODON_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.QUETZALCOATLUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.MICRORAPTOR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.DILOPHOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.XENACANTHUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.DODO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.RANALOPHOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FossilRevive.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleToolItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(FossilRevive.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FossilRevive.MOD_ID,"block/" + item.getId().getPath()));
    }

}


