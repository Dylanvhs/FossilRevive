package net.dylanvhs.fossil_revive.datagen;

import net.dylanvhs.fossil_revive.item.ModItems;
import net.dylanvhs.fossil_revive.recipe.WeightedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static net.dylanvhs.fossil_revive.FossilRevive.prefix;

public class ModRecipeGenerator extends RecipeProvider {
    public ModRecipeGenerator(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        cultivating(consumer, ModItems.DILOPHOSAURUS_DNA.get(), Items.EGG, ModItems.DILOPHOSAURUS_EGG.get(), 800);
        cultivating(consumer, ModItems.LIOPLEURODON_DNA.get(), ModItems.SYRINGE.get(), ModItems.LIOPLEURODON_EMBRYO.get(), 800);
        cultivating(consumer, ModItems.SMILODON_DNA.get(), ModItems.SYRINGE.get(), ModItems.SMILODON_EMBRYO.get(), 800);
        cultivating(consumer, ModItems.TASMANIAN_TIGER_DNA.get(), ModItems.SYRINGE.get(), ModItems.TASMANIAN_TIGER_EMBRYO.get(), 800);

        AnalyzingRecipeBuilder.analyzing(ModItems.FOSSIL.get(), ModItems.DNA_BOTTLE.get(),750)
                .addOutput(ModItems.AMARGASAURUS_DNA.get(), 15)
                .addOutput(ModItems.DODO_DNA.get(), 15)
                .addOutput(ModItems.KELENKEN_DNA.get(), 15)
                .addOutput(ModItems.QUETZALCOATLUS_DNA.get(), 15)
                .addOutput(ModItems.TASMANIAN_TIGER_DNA.get(), 15)
                .addOutput(ModItems.LIOPLEURODON_DNA.get(), 15)
                .addOutput(ModItems.OVIRAPTOR_DNA.get(), 15)
                .addOutput(ModItems.SMILODON_DNA.get(), 15)
                .addOutput(ModItems.TAPEJARA_DNA.get(), 15)
                .addOutput(ModItems.TROODON_DNA.get(), 15)
                .addOutput(ModItems.MICRORAPTOR_DNA.get(), 15)
                .addOutput(ModItems.CARNOTAURUS_DNA.get(), 15)
                .addOutput(Items.SAND, 30)
                .addOutput(Items.COBBLESTONE, 15)
                .build(consumer);

        AnalyzingRecipeBuilder.analyzing(ModItems.PLANT_FOSSIL.get(), ItemStack.EMPTY.getItem(),750)
                .addOutput(ModItems.HORSETAIL_SEEDS.get(), 15)
                .addOutput(ModItems.MONTSECHIA_SEEDS.get(), 15)
                .addOutput(ModItems.OSMUNDA_SEEDS.get(), 15)
                .addOutput(ModItems.PINKSPARK_SEEDS.get(), 15)
                .addOutput(ModItems.SPIRALFLOWER_SEEDS.get(), 15)
                .addOutput(Items.SAND, 30)
                .addOutput(Items.COBBLESTONE, 15)
                .build(consumer);
    }


    protected static void cultivating(Consumer<FinishedRecipe> finishedRecipeConsumer, Item dna, Item egg, Item result, int processTime){
        Ingredient dnaIng = Ingredient.of(dna);
        Ingredient eggIng = Ingredient.of(egg);

        CustomRecipeBuilder.cultivating(dnaIng, eggIng, result, processTime).save(finishedRecipeConsumer, prefix("cultivating/" + getItemName(result) + "_from_cultivating"));
    }

    protected static void analyzing(Consumer<FinishedRecipe> finishedRecipeConsumer, Item input1, Item input2, WeightedRandomList<WeightedOutput> result, int processTime){
        Ingredient dnaIng = Ingredient.of(input1);
        Ingredient eggIng = Ingredient.of(input2);

        CustomRecipeBuilder.analyzing(dnaIng, eggIng, result, processTime).save(finishedRecipeConsumer, prefix("analyzing/" + "analyzing_" + getItemName(input1) + "_items"));
    }






}
