package dev.swoth.lahmacuns.recipe;

import dev.swoth.lahmacuns.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public class MinceRecipe extends CustomRecipe {
    public MinceRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, @NotNull Level level) {
        ItemStack meatStack = ItemStack.EMPTY;
        ItemStack knifeStack = ItemStack.EMPTY;
        int empties = 0;

        for (int i = 0; i < craftingInput.size(); i++) {
            ItemStack currentStack = craftingInput.getItem(i);
            if (currentStack.isEmpty()) {
                empties++;
                continue;
            };

            boolean isMeat = currentStack.is(Items.BEEF) || currentStack.is(Items.PORKCHOP) || currentStack.is(Items.MUTTON);
            if (meatStack.isEmpty() && isMeat) meatStack = currentStack;

            boolean isKnife = currentStack.is(ModItems.KNIFE.get());
            if (knifeStack.isEmpty() && isKnife) knifeStack = currentStack;
        }

        return !meatStack.isEmpty() && !knifeStack.isEmpty() && empties == (craftingInput.size() - 2);
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        for (int index = 0; index < remainders.size(); ++index) {
            ItemStack selectedStack = input.getItem(index);
            if (selectedStack.is(ModItems.KNIFE.get())) {
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
        return new ItemStack(ModItems.MINCED_MEAT.get());
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.MINCE_RECIPE_SERIALIZER.get();
    }
}

//package dev.swoth.lahmacuns.recipe;
//
//import net.minecraft.core.HolderLookup;
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.level.Level;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public record MinceRecipe(NonNullList<Ingredient> ingredients, Ingredient durabilityInput, ItemStack result, int durabilityLoss) implements Recipe<CraftingInput> {
//    @Override
//    public boolean matches(CraftingInput craftingInput, @NotNull Level level) {
//        List<Ingredient> allRequired = new ArrayList<>(ingredients);
//
//        List<ItemStack> available = new ArrayList<>();
//        for (int i = 0; i < craftingInput.size(); i++) {
//            ItemStack stack = craftingInput.getItem(i);
//            if (!stack.isEmpty()) {
//                available.add(stack);
//            }
//        }
//
//        if (available.size() != allRequired.size()) return false;
//
//        // Durability item'ın ingredients içinde olduğunu kontrol et
//        boolean hasDurabilityItem = false;
//        for (ItemStack stack : available) {
//            if (durabilityInput.test(stack)) {
//                hasDurabilityItem = true;
//                break;
//            }
//        }
//        if (!hasDurabilityItem) return false;
//
//        // Normal ingredient matching
//        for (Ingredient ingredient : allRequired) {
//            boolean found = false;
//            for (int i = 0; i < available.size(); i++) {
//                if (ingredient.test(available.get(i))) {
//                    available.remove(i);
//                    found = true;
//                    break;
//                }
//            }
//            if (!found) return false;
//        }
//        return true;
//    }
//
//    @Override
//    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
//        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
//
//        for (int i = 0; i < input.size(); i++) {
//            ItemStack stack = input.getItem(i);
//
//            if (durabilityInput.test(stack)) {
//                ItemStack copy = stack.copy();
//                copy.setDamageValue(copy.getDamageValue() + durabilityLoss);
//
//                if (copy.getDamageValue() >= copy.getMaxDamage()) {
//                    remaining.set(i, ItemStack.EMPTY); // Kırılırsa sil
//                } else {
//                    remaining.set(i, copy); // Durability azalmış hali döner
//                }
//            }
//            // Diğer itemler normal şekilde tüketilir (ItemStack.EMPTY kalır)
//        }
//        return remaining;
//    }
//
//    @Override
//    public @NotNull ItemStack assemble(@NotNull CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
//        return result.copy();
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int width, int height) {
//        return width * height >= ingredients.size() + 1;
//    }
//
//    @Override
//    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
//        return result.copy();
//    }
//
//    @Override
//    public @NotNull RecipeSerializer<?> getSerializer() {
//        return ModRecipes.MINCE_RECIPE_SERIALIZER.get();
//    }
//
//    @Override
//    public @NotNull RecipeType<?> getType() {
//        return RecipeType.CRAFTING;
//    }
//}
