package me._12emin34.mouseclickswap.config;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class ModConfig extends MidnightConfig {
    @Entry(name = "Enable auto-swap")
    public static boolean enableAutoSwap = false;
    @Entry(name = "Auto-swap items")
    public static List<String> autoSwapItems = Lists.newArrayList(
            "minecraft:wooden_sword",
            "minecraft:stone_sword",
            "minecraft:iron_sword",
            "minecraft:diamond_sword",
            "minecraft:golden_sword",
            "minecraft:netherite_sword",
            "minecraft:wooden_axe",
            "minecraft:stone_axe",
            "minecraft:iron_axe",
            "minecraft:diamond_axe",
            "minecraft:golden_axe",
            "minecraft:netherite_axe"
    );
}
