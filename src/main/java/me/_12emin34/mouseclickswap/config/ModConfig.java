package me._12emin34.mouseclickswap.config;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class ModConfig extends MidnightConfig {
    @Entry(name = "Enable auto-swap")
    public static boolean enableAutoSwap = false;
    @Entry(name = "Auto-swap items")
    public static List<String> autoSwapItems = List.of("minecraft:diamond_sword", "minecraft:diamond_axe");
}
