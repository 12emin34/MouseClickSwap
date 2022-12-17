package me._12emin34.mouseclickswap.client;

import eu.midnightdust.lib.config.MidnightConfig;
import me._12emin34.mouseclickswap.config.ModConfig;
import me._12emin34.mouseclickswap.util.KeyBindingUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.atomic.AtomicBoolean;

@Environment(EnvType.CLIENT)
public class MouseClickSwapClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MidnightConfig.init("mouseclickswap", ModConfig.class);
        AtomicBoolean autoSwapped = new AtomicBoolean(false);

        KeyBinding swapKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mouseclickswap.swap",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.mouseclickswap.general"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            GameOptions options = client.options;
            KeyBinding attackKeybinding = options.attackKey;
            KeyBinding useKeybinding = options.useKey;
            while (swapKeybinding.wasPressed()) {
                KeyBindingUtil.swapKeyBinds(options, attackKeybinding, useKeybinding);
            }

            if (!ModConfig.enableAutoSwap) return;
            String item = "";
            if (client.player != null) {
                Identifier value = client.player.getMainHandStack().getRegistryEntry().getKey().get().getValue();
                String namespace = value.getNamespace();
                String path = value.getPath();
                item = namespace + ":" + path;
            }
            if (ModConfig.autoSwapItems.contains(item) && !autoSwapped.get()) {
                autoSwapped.set(true);
                KeyBindingUtil.swapKeyBinds(options, attackKeybinding, useKeybinding);
            } else if (!ModConfig.autoSwapItems.contains(item) && autoSwapped.get()) {
                autoSwapped.set(false);
                KeyBindingUtil.swapKeyBinds(options, attackKeybinding, useKeybinding);
            }
        });
    }
}
