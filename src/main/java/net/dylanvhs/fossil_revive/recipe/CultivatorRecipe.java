package net.dylanvhs.fossil_revive.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.dylanvhs.fossil_revive.screens.ModRecipeTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CultivatorRecipe implements Recipe<Container> {
    protected final ResourceLocation id;
    protected final Ingredient dna;
    protected final Ingredient egg;
    protected final ItemStack result;
    protected final int processTime;
    private final NonNullList<Ingredient> recipeItems = NonNullList.create();

    public CultivatorRecipe(ResourceLocation id, Ingredient dna, Ingredient egg, ItemStack result, int processTime) {
        this.id = id;
        this.dna = dna;
        this.egg = egg;
        this.result = result;
        this.processTime = processTime;

        recipeItems.add(dna);
        recipeItems.add(egg);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return
                dna.test(!container.getItem(0).isEmpty() ? container.getItem(0) : ItemStack.EMPTY) &&
                egg.test(!container.getItem(1).isEmpty() ? container.getItem(1) : ItemStack.EMPTY);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }
    public boolean hasFuel(ItemStack stack) {
        return egg.test(stack);
    }

    public int getProcessTime() {
        return processTime;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.CULTIVATOR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.CULTIVATOR_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<CultivatorRecipe> {



        @Override
        public CultivatorRecipe fromJson(ResourceLocation resourceLocation, JsonObject pJson) {
            Ingredient dna = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson,"dna"));
            Ingredient egg = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson, "egg"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "output"));
            int processTime = GsonHelper.getAsInt(pJson, "process_time", 1000);

            return new CultivatorRecipe(resourceLocation, dna, egg, output, processTime);
        }

        @Override
        public @Nullable CultivatorRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient dna = Ingredient.fromNetwork(friendlyByteBuf);
            Ingredient egg = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack result = friendlyByteBuf.readItem();
            int processTime = friendlyByteBuf.readVarInt();
            return new CultivatorRecipe(resourceLocation, dna, egg, result, processTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, CultivatorRecipe recipe) {
            recipe.dna.toNetwork(friendlyByteBuf);
            recipe.egg.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(recipe.result);
            friendlyByteBuf.writeVarInt(recipe.processTime);
        }
    }
}
