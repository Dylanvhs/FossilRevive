package net.dylanvhs.fossil_revive.screens;

import net.dylanvhs.fossil_revive.FossilRevive;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, FossilRevive.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FossilRevive.MOD_ID);

    public static final RegistryObject<RecipeType<CultivatorRecipe>> CULTIVATOR_TYPE = RECIPE_TYPES.register("cultivator_type", () -> new SimpleNamedRecipeType<>("cultivator"));
    public static final RegistryObject<RecipeSerializer<CultivatorRecipe>> CULTIVATOR = RECIPE_SERIALIZERS.register("cultivator", CultivatorRecipe.Serializer::new);


    public record SimpleNamedRecipeType<T extends Recipe<?>>(String name) implements RecipeType<T> {
        @Override
        public String toString() {
            return name;
        }
    }

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
