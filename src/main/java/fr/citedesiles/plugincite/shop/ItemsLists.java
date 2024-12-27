package fr.citedesiles.plugincite.shop;

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

    public
}
