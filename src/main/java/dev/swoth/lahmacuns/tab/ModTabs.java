package dev.swoth.lahmacuns.tab;

import dev.swoth.lahmacuns.item.ModItems;
import dev.swoth.lahmacuns.swothsLahmacuns;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, swothsLahmacuns.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("main_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.swothslahmacuns.main_tab"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.LAHMACUN.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.LAHMACUN.get());
                output.accept(ModItems.ROLLED_LAHMACUN.get());
                output.accept(ModItems.MEGA_LAHMACUN.get());
                output.accept(ModItems.RAW_LAHMACUN.get());
                output.accept(ModItems.MINCED_MEAT.get());
                output.accept(ModItems.KNIFE.get());
                output.accept(ModItems.FLOUR.get());
                output.accept(ModItems.DOUGH.get());
                output.accept(ModItems.ROLLED_DOUGH.get());
                output.accept(ModItems.ROLLER.get());
                output.accept(ModItems.ONION.get());
                output.accept(ModItems.TOMATO.get());
            }).build());
}
