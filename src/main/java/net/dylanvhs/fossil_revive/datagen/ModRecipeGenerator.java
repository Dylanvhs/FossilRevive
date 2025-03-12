package net.dylanvhs.fossil_revive.datagen;

import net.dylanvhs.fossil_revive.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
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
        cultivating(consumer, ModItems.AMARGASAURUS_DNA.get(), Items.EGG, ModItems.DILOPHOSAURUS_EGG.get(), 1000);
    }


    protected static void cultivating(Consumer<FinishedRecipe> finishedRecipeConsumer, Item dna, Item egg, Item result, int processTime){
        Ingredient dnaIng = Ingredient.of(dna);
        Ingredient eggIng = Ingredient.of(egg);

        CustomRecipeBuilder.cultivating(dnaIng, eggIng, result, processTime).save(finishedRecipeConsumer, prefix("cultivating/" + getItemName(result) + "_from_cultivating"));
    }
}
