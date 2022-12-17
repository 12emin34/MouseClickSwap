package me._12emin34.mouseclickswap.client;

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
                InputUtil.Key attackKey = KeyBindingHelper.getBoundKeyOf(attackKeybinding);
                InputUtil.Key useKey = KeyBindingHelper.getBoundKeyOf(useKeybinding);

                options.setKeyCode(attackKeybinding, useKey);
                options.setKeyCode(useKeybinding,attackKey);
                KeyBinding.updateKeysByCode();
            }
        });
    }
}
