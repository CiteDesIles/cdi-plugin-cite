package fr.citedesiles.plugincite.shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopManager {

    ItemsLists itemsLists = new ItemsLists();

    public ShopManager() {
        itemsLists.initLists();
    }

    public void openShop(Player player, String itemsList) {
        Inventory inv = Bukkit.createInventory(null, 54, "§6" + itemsList);
        player.openInventory(inv);
    }
}