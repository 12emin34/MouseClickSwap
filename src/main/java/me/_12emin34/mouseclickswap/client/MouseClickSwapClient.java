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
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class MouseClickSwapClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MidnightConfig.init("mouseclickswap", ModConfig.class);

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
        });
    }
}
