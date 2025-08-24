package dev.swoth.lahmacuns;

import dev.swoth.lahmacuns.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@Mod(value = swothsLahmacuns.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = swothsLahmacuns.MOD_ID, value = Dist.CLIENT)
public class swothsLahmacunsClient {
    public swothsLahmacunsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onItemTooltip(ItemTooltipEvent event) {
        var item = event.getItemStack().getItem();
        var tooltip = event.getToolTip();

        if (item == ModItems.LAHMACUN.get()) tooltip.add(Component.translatable("tooltip.swothslahmacuns.lahmacun").withStyle(ChatFormatting.GRAY));
        if (item == ModItems.ROLLED_LAHMACUN.get()) tooltip.add(Component.translatable("tooltip.swothslahmacuns.rolled_lahmacun").withStyle(ChatFormatting.GRAY));
        if (item == ModItems.MEGA_LAHMACUN.get()) tooltip.add(Component.translatable("tooltip.swothslahmacuns.mega_lahmacun").withStyle(ChatFormatting.GRAY));
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        swothsLahmacuns.LOGGER.info("HELLO FROM CLIENT SETUP");
        swothsLahmacuns.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
