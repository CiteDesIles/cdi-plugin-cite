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
        upgrade.put(new ItemStack(Material.NETHERRACK, 1), 5000L);
        upgrade.put(new ItemStack(Material.STRIPPED_SPRUCE_LOG, 1), 1000L);
        upgrade.put(new ItemStack(Material.RED_TERRACOTTA, 1), 500L);
        upgrade.put(new ItemStack(Material.RED_CONCRETE_POWDER, 1), 500L);
        upgrade.put(new ItemStack(Material.BRICK, 1), 500L);
        upgrade.put(new ItemStack(Material.COPPER_BLOCK, 1), 64L);
        upgrade.put(new ItemStack(Material.LANTERN, 1), 32L);
        upgrade.put(new ItemStack(Material.DIAMOND, 1), 10L);
        upgrade.put(new ItemStack(Material.REDSTONE_LAMP, 1), 1000L);
        upgrade.put(new ItemStack(Material.LIGHT_GRAY_WOOL, 1), 500L);
        upgrade.put(new ItemStack(Material.CHISELED_TUFF, 1), 500L);
        upgrade.put(new ItemStack(Material.MUD_BRICK_WALL, 1), 300L);
        upgrade.put(new ItemStack(Material.LECTERN, 1), 20L);
        upgrade.put(new ItemStack(Material.POLISHED_DIORITE, 1), 500L);
        upgrade.put(new ItemStack(Material.DARK_OAK_PLANKS, 1), 500L);
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
        mineur.put(new ItemStack(Material.COPPER_INGOT, 1), 40L);
        mineur.put(new ItemStack(Material.IRON_INGOT, 1), 50L);
        mineur.put(new ItemStack(Material.GOLD_INGOT, 1), 60L);
        mineur.put(new ItemStack(Material.DIAMOND, 1), 200L);
        mineur.put(new ItemStack(Material.EMERALD, 1), 200L);
        mineur.put(new ItemStack(Material.NETHERITE_INGOT, 1), 1000L);
        mineur.put(new ItemStack(Material.REDSTONE, 1), 20L);
        mineur.put(new ItemStack(Material.LAPIS_LAZULI, 1), 25L);

        putItemsList("mineur", mineur);

    }

    public void editPrice(String itemsList, ItemStack item, long price) {
        itemsLists.get(itemsList).put(item, price);
    }
}
