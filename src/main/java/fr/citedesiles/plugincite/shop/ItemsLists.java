package fr.citedesiles.plugincite.shop;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemsLists {
    Map<String, Map<ItemStack, Long>> itemsLists = new HashMap<>();

    public Map<ItemStack, Long> getItemsList(String itemsList) {
        return itemsLists.get(itemsList);
    }

    public void createItemsList(String itemsList) {
        itemsLists.put(itemsList, new HashMap<>());
    }

    public void putItemsList(String itemsList, Map<ItemStack, Long> items) {
        itemsLists.put(itemsList, items);
    }

    public void initLists() {
        createItemsList("confiserie");
        Map<ItemStack, Long> confiserie = new HashMap<>();
        confiserie.put(PluginCite.instance().itemManager().get("bonbonViolette"), 400L);
        confiserie.put(new ItemStack(Material.SUGAR, 1), 50L);
        confiserie.put(new ItemStack(Material.COOKIE, 1), 75L);
        confiserie.put(new ItemStack(Material.SWEET_BERRIES, 1), 10L);
        confiserie.put(new ItemStack(Material.RESIN_BRICK, 1), 200L);
        confiserie.put(new ItemStack(Material.PUMPKIN_PIE, 1), 300L);
        confiserie.put(new ItemStack(Material.CAKE, 1), 500L);
        confiserie.put(new ItemStack(Material.CHORUS_FRUIT, 1), 50L);
        confiserie.put(new ItemStack(Material.MELON, 1), 75L);
        confiserie.put(new ItemStack(Material.GOLDEN_APPLE, 1), 500L);
        confiserie.put(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), 10000L);
        confiserie.put(new ItemStack(Material.HONEY_BOTTLE, 1), 300L);
        confiserie.put(new ItemStack(Material.HONEYCOMB, 1), 80L);
        putItemsList("confiserie", confiserie);

        createItemsList("upgrade");
        Map<ItemStack, Long> upgrade = new HashMap<>();
        upgrade.put(new ItemStack(Material.PODZOL), 2000L);
        upgrade.put(new ItemStack(Material.MOSS_BLOCK), 1144L);
        upgrade.put(new ItemStack(Material.DRIPSTONE_BLOCK), 207L);
        upgrade.put(new ItemStack(Material.WAXED_OXIDIZED_COPPER_TRAPDOOR), 498L);
        upgrade.put(new ItemStack(Material.END_ROD), 399L);
        upgrade.put(new ItemStack(Material.GLOW_BERRIES), 269L);
        upgrade.put(new ItemStack(Material.BOOKSHELF), 168L);
        upgrade.put(new ItemStack(Material.RED_NETHER_BRICKS), 100L);
        upgrade.put(new ItemStack(Material.CAULDRON), 100L);
        upgrade.put(new ItemStack(Material.REPEATER), 85L);
        upgrade.put(new ItemStack(Material.WHITE_CANDLE), 100L);
        upgrade.put(new ItemStack(Material.BEEHIVE), 13L);
        upgrade.put(new ItemStack(Material.HEAVY_CORE), 1L);
        putItemsList("upgrade", upgrade);

        createItemsList("mineur");
        Map<ItemStack, Long> mineur = new HashMap<>();
        mineur.put(new ItemStack(Material.STONE), 5L);
        mineur.put(new ItemStack(Material.COBBLESTONE), 1L);
        mineur.put(new ItemStack(Material.QUARTZ), 25L);
        mineur.put(new ItemStack(Material.IRON_ORE), 80L);
        mineur.put(new ItemStack(Material.GOLD_ORE), 80L);
        mineur.put(new ItemStack(Material.DIAMOND_ORE), 500L);
        mineur.put(new ItemStack(Material.EMERALD_ORE), 500L);
        mineur.put(new ItemStack(Material.ANCIENT_DEBRIS), 2500L);
        mineur.put(new ItemStack(Material.DEEPSLATE_COAL_ORE), 10000L);
        mineur.put(new ItemStack(Material.DEEPSLATE_IRON_ORE), 150L);
        mineur.put(new ItemStack(Material.DEEPSLATE_GOLD_ORE), 150L);
        mineur.put(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE), 400L);
        mineur.put(new ItemStack(Material.DEEPSLATE_EMERALD_ORE), 800L);
        mineur.put(new ItemStack(Material.COAL), 15L);
        mineur.put(new ItemStack(Material.COPPER_INGOT), 20L);
        mineur.put(new ItemStack(Material.IRON_INGOT), 50L);
        mineur.put(new ItemStack(Material.GOLD_INGOT), 60L);
        mineur.put(new ItemStack(Material.DIAMOND), 200L);
        mineur.put(new ItemStack(Material.EMERALD), 200L);
        mineur.put(new ItemStack(Material.NETHERITE_INGOT), 1000L);
        mineur.put(new ItemStack(Material.REDSTONE), 20L);
        mineur.put(new ItemStack(Material.LAPIS_LAZULI), 25L);
        putItemsList("mineur", mineur);

        createItemsList("coppernic");
        Map<ItemStack, Long> coppernic = new HashMap<>();
        coppernic.put(new ItemStack(Material.COPPER_INGOT, 1), 50L);
        coppernic.put(new ItemStack(Material.COPPER_BLOCK, 1), 500L);
        coppernic.put(new ItemStack(Material.COPPER_BULB, 1), 500L);
        coppernic.put(new ItemStack(Material.WAXED_COPPER_BLOCK, 1), 550L);
        coppernic.put(new ItemStack(Material.OXIDIZED_COPPER, 1), 600L);
        coppernic.put(new ItemStack(Material.WAXED_OXIDIZED_COPPER_DOOR, 1), 600L);
        putItemsList("coppernic", coppernic);

        createItemsList("gefroid");
        Map<ItemStack, Long> gefroid = new HashMap<>();
        gefroid.put(new ItemStack(Material.ICE, 1), 50L);
        gefroid.put(new ItemStack(Material.PACKED_ICE, 1), 75L);
        gefroid.put(new ItemStack(Material.BLUE_ICE, 1), 100L);
        gefroid.put(new ItemStack(Material.SNOW_BLOCK, 1), 50L);
        gefroid.put(new ItemStack(Material.SNOWBALL, 1), 10L);
        putItemsList("gefroid", gefroid);

        createItemsList("sakura");
        Map<ItemStack, Long> sakura = new HashMap<>();
        sakura.put(new ItemStack(Material.CHERRY_LOG, 1), 50L);
        sakura.put(new ItemStack(Material.CHERRY_PLANKS, 1), 15L);
        sakura.put(new ItemStack(Material.CHERRY_SAPLING, 1), 150L);
        sakura.put(new ItemStack(Material.CHERRY_LEAVES, 1), 100L);
        sakura.put(new ItemStack(Material.CHERRY_SIGN, 1), 50L);
        sakura.put(new ItemStack(Material.CHERRY_DOOR, 1), 150L);
        sakura.put(new ItemStack(Material.CHERRY_FENCE, 1), 50L);
        putItemsList("sakura", sakura);

        createItemsList("fechaud");
        Map<ItemStack, Long> fechaud = new HashMap<>();
        fechaud.put(new ItemStack(Material.SOUL_SAND), 10L);
        fechaud.put(new ItemStack(Material.LAVA_BUCKET), 400L);
        fechaud.put(new ItemStack(Material.SHROOMLIGHT), 100L);
        fechaud.put(new ItemStack(Material.WARPED_STEM), 50L);
        fechaud.put(new ItemStack(Material.BASALT), 10L);
        fechaud.put(new ItemStack(Material.NETHERRACK), 2L);
        fechaud.put(new ItemStack(Material.NETHER_BRICK_FENCE), 50L);
        fechaud.put(new ItemStack(Material.TWISTING_VINES), 50L);
        fechaud.put(new ItemStack(Material.NETHER_WART_BLOCK), 75L);
        fechaud.put(new ItemStack(Material.CRIMSON_FUNGUS), 200L);
        putItemsList("fechaud", fechaud);

        createItemsList("warden");
        Map<ItemStack, Long> warden = new HashMap<>();
        warden.put(new ItemStack(Material.SCULK), 10L);
        warden.put(new ItemStack(Material.SCULK_SENSOR), 200L);
        warden.put(new ItemStack(Material.SCULK_CATALYST), 600L);
        warden.put(new ItemStack(Material.SCULK_SHRIEKER), 1000L);
        putItemsList("warden", warden);

        createItemsList("enderitefox");
        Map<ItemStack, Long> enderitefox = new HashMap<>();
        enderitefox.put(new ItemStack(Material.END_STONE), 8L);
        enderitefox.put(new ItemStack(Material.PURPUR_STAIRS), 150L);
        enderitefox.put(new ItemStack(Material.END_ROD), 150L);
        enderitefox.put(new ItemStack(Material.ENDER_PEARL), 50L);
        enderitefox.put(new ItemStack(Material.ENDER_CHEST), 500L);
        enderitefox.put(new ItemStack(Material.OBSIDIAN), 45L);
        enderitefox.put(new ItemStack(Material.CRYING_OBSIDIAN), 80L);
        enderitefox.put(new ItemStack(Material.CHORUS_FLOWER), 50L);
        enderitefox.put(new ItemStack(Material.POPPED_CHORUS_FRUIT), 75L);
        enderitefox.put(new ItemStack(Material.END_CRYSTAL), 550L);
        putItemsList("enderitefox", enderitefox);


        Map<ItemStack, Long> begaydocrime = new HashMap<>();
        begaydocrime.put(new ItemStack(Material.BEACON), 10000L);
        begaydocrime.put(new ItemStack(Material.TOTEM_OF_UNDYING), 1000L);
        begaydocrime.put(new ItemStack(Material.GRAY_BANNER), 50L);
        begaydocrime.put(new ItemStack(Material.MAGENTA_CARPET), 10L);
        begaydocrime.put(new ItemStack(Material.CYAN_BUNDLE), 100L);
        begaydocrime.put(new ItemStack(Material.YELLOW_CONCRETE_POWDER), 60L);
        begaydocrime.put(new ItemStack(Material.LIME_CANDLE), 70L);
        begaydocrime.put(new ItemStack(Material.PINK_STAINED_GLASS_PANE), 20L);
        begaydocrime.put(new ItemStack(Material.MUSIC_DISC_OTHERSIDE), 20000L);
        begaydocrime.put(new ItemStack(Material.MUSIC_DISC_CREATOR), 20000L);
        begaydocrime.put(new ItemStack(Material.MUSIC_DISC_CREATOR_MUSIC_BOX), 20000L);
        begaydocrime.put(new ItemStack(Material.PURPLE_STAINED_GLASS), 80L);
        begaydocrime.put(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA), 70L);
        begaydocrime.put(new ItemStack(Material.LIME_TERRACOTTA), 60L);
        begaydocrime.put(new ItemStack(Material.ORANGE_CONCRETE), 60L);
        begaydocrime.put(new ItemStack(Material.RED_WOOL), 50L);
        begaydocrime.put(new ItemStack(Material.CAMPFIRE), 60L);
        begaydocrime.put(new ItemStack(Material.RESIN_BLOCK), 80L);
        begaydocrime.put(new ItemStack(Material.WHITE_SHULKER_BOX), 500L);
        begaydocrime.put(new ItemStack(Material.TNT), 100L);
        begaydocrime.put(new ItemStack(Material.TRIPWIRE_HOOK), 60L);
        begaydocrime.put(new ItemStack(Material.LEATHER), 50L);
        begaydocrime.put(new ItemStack(Material.BARREL), 150L);
        begaydocrime.put(new ItemStack(Material.BREEZE_ROD), 100L);
        begaydocrime.put(new ItemStack(Material.EGG), 25L);
        begaydocrime.put(new ItemStack(Material.SLIME_BALL), 150L);
        begaydocrime.put(new ItemStack(Material.HONEY_BOTTLE), 400L);
        begaydocrime.put(new ItemStack(Material.TROPICAL_FISH), 200L);

    }

    public void editPrice(String itemsList, ItemStack item, long price) {
        itemsLists.get(itemsList).put(item, price);
    }
}
