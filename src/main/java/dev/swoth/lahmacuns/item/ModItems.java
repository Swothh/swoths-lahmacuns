package dev.swoth.lahmacuns.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import dev.swoth.lahmacuns.swothsLahmacuns;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(swothsLahmacuns.MOD_ID);

    public static final DeferredItem<Item> ROLLER = ITEMS.registerSimpleItem("roller", new Item.Properties().durability(32));
    public static final DeferredItem<Item> KNIFE = ITEMS.registerSimpleItem("knife", new Item.Properties().durability(32));
    public static final DeferredItem<Item> ROLLED_DOUGH = ITEMS.registerSimpleItem("rolled_dough", new Item.Properties());
    public static final DeferredItem<Item> FLOUR = ITEMS.registerSimpleItem("flour", new Item.Properties());
    public static final DeferredItem<Item> DOUGH = ITEMS.registerSimpleItem("dough", new Item.Properties());

    public static final DeferredItem<Item> RAW_LAHMACUN = ITEMS.registerSimpleItem("raw_lahmacun", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationModifier(4f)
                    .build()
    ));

    public static final DeferredItem<Item> MEGA_LAHMACUN = ITEMS.registerSimpleItem("mega_lahmacun", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(40)
                    .saturationModifier(40f)
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 24000), 1f)
                    .build()
    ));

    public static final DeferredItem<Item> LAHMACUN = ITEMS.registerSimpleItem("lahmacun", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(16)
                    .saturationModifier(12f)
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 6000), 1f)
                    .build()
    ));

    public static final DeferredItem<Item> ROLLED_LAHMACUN = ITEMS.registerSimpleItem("rolled_lahmacun", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(20)
                    .saturationModifier(16f)
                    .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 9600), 1f)
                    .build()
    ));

    public static final DeferredItem<Item> MINCED_MEAT = ITEMS.registerSimpleItem("minced_meat", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(2f)
                    .build()
    ));

    public static final DeferredItem<Item> TOMATO = ITEMS.registerSimpleItem("tomato", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationModifier(1f)
                    .build()
    ));

    public static final DeferredItem<Item> ONION = ITEMS.registerSimpleItem("onion", new Item.Properties().food(
            new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationModifier(1f)
                    .build()
    ));
}