package dev.swoth.lahmacuns.recipe;

import dev.swoth.lahmacuns.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DoughRecipe extends CustomRecipe {
    public DoughRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, @NotNull Level level) {
        if (craftingInput.size() != 9) return false;
        ItemStack waterStack = ItemStack.EMPTY;
        int flours = 0;

        for (int i = 0; i < craftingInput.size(); i++) {
            ItemStack currentStack = craftingInput.getItem(i);
            if (currentStack.isEmpty()) continue;

            boolean isFlour = currentStack.is(ModItems.FLOUR.get());
            if (isFlour) flours++;

            boolean isWater = currentStack.is(Items.WATER_BUCKET);
            if (waterStack.isEmpty() && isWater) waterStack = currentStack;
        }

        return !waterStack.isEmpty() && flours == 8;
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int index = 0; index < remainders.size(); ++index) {
            ItemStack selectedStack = input.getItem(index);
            if (selectedStack.is(Items.WATER_BUCKET)) {
                remainders.set(index, new ItemStack(Items.BUCKET));
            }
        }

        return remainders;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
        return new ItemStack(ModItems.DOUGH.get());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.DOUGH_RECIPE_SERIALIZER.get();
    }
}