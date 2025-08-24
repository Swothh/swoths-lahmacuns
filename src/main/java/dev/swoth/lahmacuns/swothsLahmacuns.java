package dev.swoth.lahmacuns;

import dev.swoth.lahmacuns.item.ModItems;
import dev.swoth.lahmacuns.recipe.ModRecipes;
import dev.swoth.lahmacuns.tab.ModTabs;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

@Mod(swothsLahmacuns.MOD_ID)
public class swothsLahmacuns {
    public static final String MOD_ID = "swothslahmacuns";
    public static final Logger LOGGER = LogUtils.getLogger();

    public swothsLahmacuns(IEventBus modEventBus, ModContainer modContainer) {
        ModRecipes.RECIPE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        ModTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();

        if (block == Blocks.SHORT_GRASS || block == Blocks.TALL_GRASS) {
            if (event.getLevel().getRandom().nextFloat() < 0.02f) {
                ItemStack dropItem = new ItemStack(event.getLevel().getRandom().nextFloat() < 0.5f ? ModItems.ONION.get() : ModItems.TOMATO.get(), 1);

                ItemEntity itemEntity = new ItemEntity(event.getPlayer().level(),
                        event.getPos().getX() + 0.5,
                        event.getPos().getY() + 0.5,
                        event.getPos().getZ() + 0.5,
                        dropItem);
                event.getLevel().addFreshEntity(itemEntity);
            }
        }
    }
}
