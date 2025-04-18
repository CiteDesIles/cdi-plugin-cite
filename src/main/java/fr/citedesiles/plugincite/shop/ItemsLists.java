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
        confiserie.put(PluginCite.instance().itemManager().get("bonbonViolette"), 50000000L);
        confiserie.put(new ItemStack(Material.SUGAR, 1), 50L);
        confiserie.put(new ItemStack(Material.COOKIE, 1), 100L);
        putItemsList("confiserie", confiserie);

        createItemsList("upgrade");
        Map<ItemStack, Long> upgrade = new HashMap<>();
        upgrade.put(new ItemStack(Material.COBBLESTONE, 1), 2500L);
        upgrade.put(new ItemStack(Material.OAK_LOG, 1), 500L);
        upgrade.put(new ItemStack(Material.SAND, 1), 256L);
        upgrade.put(new ItemStack(Material.RED_TERRACOTTA, 1), 64L);
        upgrade.put(new ItemStack(Material.DIAMOND, 1), 1L);
        putItemsList("upgrade", upgrade);
    }

    public void editPrice(String itemsList, ItemStack item, long price) {
        itemsLists.get(itemsList).put(item, price);
    }
}
