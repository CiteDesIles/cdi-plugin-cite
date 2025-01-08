package fr.citedesiles.plugincite.shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShopManager {

    ItemsLists itemsLists = new ItemsLists();

    public ShopManager() {
        itemsLists.initLists();
    }

    public void openShop(Player player, String itemsList) {
        Inventory inv = Bukkit.createInventory(null, 54, "§6" + itemsList);
        for(int i = 0; i < 54; i++) {
            if(itemsLists.getItemsList(itemsList).size() > i) {
                ItemStack item = ((ItemStack) itemsLists.getItemsList(itemsList).keySet().toArray()[i]).clone();
                int price = itemsLists.getItemsList(itemsList).get(item);
                int count = countItemInInventory(player, item);
                ItemMeta itemMeta = item.getItemMeta();
                if(count >= 64) {
                    itemMeta.setLore(List.of(
                        "§fPrix Unité: §e" + price + " golds",
                        "§fQuantité dans votre inventaire: " + count,
                        "§c ",
                        "§fClique gauche pour vendre §e1 §funité",
                        "§fClique droit pour vendre §e64 §funités",
                        "§fClique milieu pour vendre §eTOUT §fce que vous avez (soit " + count * price + " golds)"
                    ));
                } else {
                    itemMeta.setLore(List.of(
                        "§fPrix Unité: §e" + price + " golds",
                        "§fQuantité dans votre inventaire: " + count,
                        "§c ",
                        "§fClique gauche pour vendre §e1 §funité",
                        "§fClique milieu pour vendre §eTOUT §fce que vous avez (soit " + count * price + " golds)"
                    ));
                }
                itemMeta.setLocalizedName(itemsList);
                item.setItemMeta(itemMeta);
                inv.setItem(i, item);
            }
        }
        player.openInventory(inv);
    }


    public int countItemInInventory(Player player, ItemStack item) {
        int count = 0;
        for(ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null && itemStack.isSimilar(item)) {
                count += itemStack.getAmount();
            }
        }
        return count;
    }
}