package net.dylanvhs.fossil_revive.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dylanvhs.fossil_revive.screens.ModRecipeTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Consumer;

public class CustomRecipeBuilder {
    private final Ingredient dna;
    private final Ingredient egg;
    private final RecipeSerializer<?> type;
    private int processTime;
    private final Item result;

    public CustomRecipeBuilder(RecipeSerializer<?> pType, Ingredient dna, Ingredient egg, Item pResult, int processTime) {
        this.type = pType;
        this.dna = dna;
        this.egg = egg;
        this.result = pResult;
        this.processTime = processTime;
    }


    public static CustomRecipeBuilder cultivating(Ingredient dna, Ingredient egg, Item pResult, int processTime) {
        return new CustomRecipeBuilder(ModRecipeTypes.CULTIVATOR.get(), dna, egg, pResult, processTime);
    }

    public void save(Consumer<FinishedRecipe> pRecipeConsumer, String pLocation) {
        this.save(pRecipeConsumer, new ResourceLocation(pLocation));
    }

    public void save(Consumer<FinishedRecipe> pRecipeConsumer, ResourceLocation pLocation) {
        pRecipeConsumer.accept(new CustomRecipeBuilder.Result(pLocation, this.type, this.dna, this.egg, this.result, this.processTime));
    }


    public static record Result(ResourceLocation id, RecipeSerializer<?> type, Ingredient dna, Ingredient egg,
                                Item result, int processTime) implements FinishedRecipe {
        public void serializeRecipeData(JsonObject jsonObject) {
            JsonArray jsonarray = new JsonArray();
            jsonObject.add("dna", dna.toJson());
            jsonObject.add("egg", egg.toJson());
            jsonObject.addProperty("process_time", processTime);
            if (result != null) {
                JsonObject jsonobject = new JsonObject();
                jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
                jsonObject.add("output", jsonobject);
            }
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return this.type;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #getAdvancementJson}
         * is non-null.
         */
        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
