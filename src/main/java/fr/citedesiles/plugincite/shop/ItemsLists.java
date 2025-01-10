package fr.citedesiles.plugincite.shop;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemsLists {
    Map<String, Map<ItemStack, Integer>> itemsLists = new HashMap<>();

    public Map<ItemStack, Integer> getItemsList(String itemsList) {
        return itemsLists.get(itemsList);
    }

    public void createItemsList(String itemsList) {
        itemsLists.put(itemsList, new HashMap<>());
    }

    public void putItemsList(String itemsList, Map<ItemStack, Integer> items) {
        itemsLists.put(itemsList, items);
    }

    public void initLists() {
        createItemsList("confiserie");
        Map<ItemStack, Integer> confiserie = new HashMap<>();
        confiserie.put(PluginCite.instance().itemManager().get("bonbonViolette"), 500);
        confiserie.put(new ItemStack(Material.SUGAR, 1), 50);
        confiserie.put(new ItemStack(Material.COOKIE, 1), 100);
        putItemsList("confiserie", confiserie);

        createItemsList("upgrade");
        Map<ItemStack, Integer> upgrade = new HashMap<>();
        upgrade.put(new ItemStack(Material.STONE, 1), 5000);
        putItemsList("upgrade", upgrade);
    }

    public void editPrice(String itemsList, ItemStack item, int price) {
        itemsLists.get(itemsList).put(item, price);
    }
}
