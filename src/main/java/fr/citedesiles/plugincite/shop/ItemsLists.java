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
        confiserie.put(new ItemStack(Material.COOKIE, 1), 100L);
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
        upgrade.put(new ItemStack(Material.POLISHED_ANDESITE, 1), 10000L);
        upgrade.put(new ItemStack(Material.CHISELED_BOOKSHELF, 1), 500L);
        upgrade.put(new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 1), 1000L);
        upgrade.put(new ItemStack(Material.DIAMOND_ORE, 1), 100L);
        upgrade.put(new ItemStack(Material.BROWN_MUSHROOM, 1), 1000L);
        upgrade.put(new ItemStack(Material.CHERRY_LOG, 1), 5000L);
        upgrade.put(new ItemStack(Material.PRISMARINE, 1), 100L);
        upgrade.put(new ItemStack(Material.DRIED_KELP_BLOCK, 1), 1000L);
        upgrade.put(new ItemStack(Material.IRON_TRAPDOOR, 1), 100L);
        upgrade.put(new ItemStack(Material.STRIPPED_BAMBOO_BLOCK, 1), 1000L);
        upgrade.put(new ItemStack(Material.TUFF, 1), 5000L);
        upgrade.put(new ItemStack(Material.CLAY, 1), 500L);
        upgrade.put(new ItemStack(Material.REDSTONE_BLOCK, 1), 50L);
        upgrade.put(new ItemStack(Material.NETHERITE_BLOCK, 1), 10L);
        upgrade.put(new ItemStack(Material.CALCITE, 1), 1000L);
        upgrade.put(new ItemStack(Material.ICE, 1), 1000L);
        upgrade.put(new ItemStack(Material.CRIMSON_PLANKS, 1), 5000L);
        upgrade.put(new ItemStack(Material.RED_BANNER, 1), 500L);
        putItemsList("upgrade", upgrade);

        createItemsList("mineur");
        Map<ItemStack, Long> mineur = new HashMap<>();
        mineur.put(new ItemStack(Material.STONE, 1), 5L);
        mineur.put(new ItemStack(Material.COBBLESTONE, 1), 1L);
        mineur.put(new ItemStack(Material.QUARTZ, 1), 25L);
        mineur.put(new ItemStack(Material.IRON_ORE, 1), 80L);
        mineur.put(new ItemStack(Material.GOLD_ORE, 1), 80L);
        mineur.put(new ItemStack(Material.DIAMOND_ORE, 1), 500L);
        mineur.put(new ItemStack(Material.EMERALD_ORE, 1), 500L);
        mineur.put(new ItemStack(Material.ANCIENT_DEBRIS, 1), 2500L);
        mineur.put(new ItemStack(Material.DEEPSLATE_COAL_ORE, 1), 10000L);
        mineur.put(new ItemStack(Material.DEEPSLATE_IRON_ORE, 1), 150L);
        mineur.put(new ItemStack(Material.DEEPSLATE_GOLD_ORE, 1), 150L);
        mineur.put(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, 1), 400L);
        mineur.put(new ItemStack(Material.DEEPSLATE_EMERALD_ORE, 1), 800L);
        mineur.put(new ItemStack(Material.COAL, 1), 15L);
        mineur.put(new ItemStack(Material.COPPER_INGOT, 1), 20L);
        mineur.put(new ItemStack(Material.IRON_INGOT, 1), 50L);
        mineur.put(new ItemStack(Material.GOLD_INGOT, 1), 60L);
        mineur.put(new ItemStack(Material.DIAMOND, 1), 200L);
        mineur.put(new ItemStack(Material.EMERALD, 1), 200L);
        mineur.put(new ItemStack(Material.NETHERITE_INGOT, 1), 1000L);
        mineur.put(new ItemStack(Material.REDSTONE, 1), 20L);
        mineur.put(new ItemStack(Material.LAPIS_LAZULI, 1), 25L);
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

    }

    public void editPrice(String itemsList, ItemStack item, long price) {
        itemsLists.get(itemsList).put(item, price);
    }
}
