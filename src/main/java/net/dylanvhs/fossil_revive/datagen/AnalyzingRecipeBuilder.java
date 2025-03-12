package net.dylanvhs.fossil_revive.datagen;

import net.dylanvhs.fossil_revive.recipe.WeightedOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.dylanvhs.fossil_revive.FossilRevive.prefix;

// New builder class for analyzing recipes
public class AnalyzingRecipeBuilder {
    private final Ingredient input1;
    private final Item inp;
    private final Ingredient input2;
    private final int processTime;
    private final List<WeightedOutput> outputs = new ArrayList<>();


    private AnalyzingRecipeBuilder(Item input1, Item input2, int processTime) {
        this.input1 = Ingredient.of(input1);
        this.inp = input1;
        this.input2 = Ingredient.of(input2);
        this.processTime = processTime;
    }

    public static AnalyzingRecipeBuilder analyzing(Item input1, Item input2, int processTime) {
        return new AnalyzingRecipeBuilder(input1, input2, processTime);
    }

    // Fluent method to add an output with its weight
    public AnalyzingRecipeBuilder addOutput(Item output, int weight) {
        outputs.add(new WeightedOutput(weight, output));
        return this;
    }

    // Builds the weighted output list and saves the recipe using the consumer
    public void build(Consumer<FinishedRecipe> consumer) {
        WeightedRandomList<WeightedOutput> weightedList = WeightedRandomList.create(outputs);
        CustomRecipeBuilder.analyzing(input1, input2, weightedList, processTime)
                .save(consumer, prefix("analyzing/analyzing_" + getItemName(inp) + "_items"));
    }


    protected static String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }

}