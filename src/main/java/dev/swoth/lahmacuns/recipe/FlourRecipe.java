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

public class FlourRecipe extends CustomRecipe {
    public FlourRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, @NotNull Level level) {
        ItemStack wheatStack = ItemStack.EMPTY;
        ItemStack rollerStack = ItemStack.EMPTY;
        int empties = 0;

        for (int i = 0; i < craftingInput.size(); i++) {
            ItemStack currentStack = craftingInput.getItem(i);
            if (currentStack.isEmpty()) {
                empties++;
                continue;
            };

            boolean isWheat = currentStack.is(Items.WHEAT);
            if (wheatStack.isEmpty() && isWheat) wheatStack = currentStack;

            boolean isRoller = currentStack.is(ModItems.ROLLER.get());
            if (rollerStack.isEmpty() && isRoller) rollerStack = currentStack;
        }

        return !wheatStack.isEmpty() && !rollerStack.isEmpty() && empties == (craftingInput.size() - 2);
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int index = 0; index < remainders.size(); ++index) {
            ItemStack selectedStack = input.getItem(index);
            if (selectedStack.is(ModItems.ROLLER.get())) {
                ItemStack copy = selectedStack.copy();
                copy.setDamageValue(copy.getDamageValue() + 1);

                if (copy.getDamageValue() >= copy.getMaxDamage()) {
                    remainders.set(index, ItemStack.EMPTY);
                } else {
                    remainders.set(index, copy);
                }
            }
        }

        return remainders;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
        return new ItemStack(ModItems.FLOUR.get());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.FLOUR_RECIPE_SERIALIZER.get();
    }
}