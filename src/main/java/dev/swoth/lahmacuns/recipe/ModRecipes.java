package dev.swoth.lahmacuns.recipe;

import dev.swoth.lahmacuns.swothsLahmacuns;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, swothsLahmacuns.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, swothsLahmacuns.MOD_ID);

    public static final Supplier<RecipeSerializer<MinceRecipe>> MINCE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("mince", () -> new SimpleCraftingRecipeSerializer<>(MinceRecipe::new));
    public static final Supplier<RecipeSerializer<FlourRecipe>> FLOUR_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("flour", () -> new SimpleCraftingRecipeSerializer<>(FlourRecipe::new));
    public static final Supplier<RecipeSerializer<DoughRecipe>> DOUGH_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("dough", () -> new SimpleCraftingRecipeSerializer<>(DoughRecipe::new));
    public static final Supplier<RecipeSerializer<RolledDough>> ROLLED_DOUGH_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("rolled_dough", () -> new SimpleCraftingRecipeSerializer<>(RolledDough::new));

}
