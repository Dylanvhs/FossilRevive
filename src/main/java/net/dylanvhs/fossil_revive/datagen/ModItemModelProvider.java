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
        simpleItem(ModItems.ROUGH_FOSSIL);
        simpleItem(ModItems.DNA_BOTTLE);
        simpleItem(ModItems.HYBRID_DNA_BOTTLE);
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
        simpleItem(ModItems.BONES_MUSIC_DISC);
        simpleItem(ModItems.DIRTY_CLOTH);

        withExistingParent(ModItems.LIOPLEURODON_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.QUETZALCOATLUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FossilRevive.MOD_ID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FossilRevive.MOD_ID,"block/" + item.getId().getPath()));
    }

}


