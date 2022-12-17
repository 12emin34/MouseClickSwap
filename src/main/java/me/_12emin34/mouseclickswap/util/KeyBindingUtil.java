package me._12emin34.mouseclickswap.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyBindingUtil {
    public static void swapKeyBinds(GameOptions options, KeyBinding keyBind1, KeyBinding keyBind2) {
        InputUtil.Key key1 = KeyBindingHelper.getBoundKeyOf(keyBind1);
        InputUtil.Key key2 = KeyBindingHelper.getBoundKeyOf(keyBind2);

        options.setKeyCode(keyBind1, key2);
        options.setKeyCode(keyBind2, key1);
        KeyBinding.updateKeysByCode();
    }
}
