package com.dupemod;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = DupeModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DupeKeyHandler {
    private static final int DUPE_KEY = GLFW.GLFW_KEY_EQUAL; // Клавиша "="

    public static void register() {
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        
        if (mc.screen == null && event.getKey() == DUPE_KEY && event.getAction() == 1) {
            Player player = mc.player;
            if (player != null) {
                ItemStack itemInHand = player.containerMenu.getCarried();
                if (!itemInHand.isEmpty()) {
                    ItemStack duplicate = itemInHand.copy();
                    if (player.addItem(duplicate)) {
                        player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("§6Предмет дюпнут!"),
                            true
                        );
                    }
                }
            }
        }
    }
}