package fr.citedesiles.plugincite.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
                long price = itemsLists.getItemsList(itemsList).get(item);
                long count = countItemInInventory(player, item);
                ItemMeta itemMeta = item.getItemMeta();
                if(!itemsList.equals("upgrade")) {
                    if (count >= 64) {
                        itemMeta.setLore(List.of(
                            "§fPrix Unité: §e" + price + " golds",
                            "§fQuantité dans votre inventaire: " + count,
                            "§c ",
                            "§fClique gauche pour vendre §e1 §funité",
                            "§fClique droit pour vendre §e64 §funités",
                            "§fShift-Click pour vendre §eTOUT §fce que vous avez (soit " + count * price + " golds)"
                        ));
                    } else {
                        itemMeta.setLore(List.of(
                            "§fPrix Unité: §e" + price + " golds",
                            "§fQuantité dans votre inventaire: " + count,
                            "§c ",
                            "§fClique gauche pour vendre §e1 §funité",
                            "§fShift-Click pour vendre §eTOUT §fce que vous avez (soit " + count * price + " golds)"
                        ));
                    }
                    item.setItemMeta(itemMeta);
                    inv.setItem(i, item);
                } else {
                    itemMeta.setLore(List.of(
                        "§fReste nécessaire: §c§l" + price + " objets",
                        "§fClique gauche pour déposer §e1 §fobjet",
                        "§fShift-Click pour déposer §eTOUT §fce que vous avez",
                        "§f",
                        "§fCela vous rapportera §b§l1 SP"
                    ));
                }
                if(price > 0) {
                    item.setItemMeta(itemMeta);
                    inv.setItem(i, item);
                }
            }
        }
        player.openInventory(inv);
    }


    public long countItemInInventory(Player player, ItemStack item) {
        long count = 0;
        for(ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null && itemStack.isSimilar(item)) {
                count += itemStack.getAmount();
            }
        }
        return count;
    }

    public ItemStack getOriginalItemFromList(ItemStack item, String itemList) {
        for(ItemStack itemStack : itemsLists.getItemsList(itemList).keySet()) {
            if(isSimilar(item, itemStack)) {
                return itemStack;
            }
        }
        return null;
    }

    public boolean isInItemsList(String itemsList, ItemStack item) {
        boolean found = false;
        for(ItemStack itemStack : itemsLists.getItemsList(itemsList).keySet()) {
            if(isSimilar(item, itemStack)) {
                found = true;
            }
        }
        return found;
    }

    public long getPrice(String itemsList, ItemStack item) {
        for(ItemStack itemStack : itemsLists.getItemsList(itemsList).keySet()) {
            if(isSimilar(item, itemStack)) {
                return itemsLists.getItemsList(itemsList).get(itemStack);
            }
        }
        return -1;
    }

    public boolean isSimilar(ItemStack item1, ItemStack item2) {
        if(item1 == null) {
            return false;
        }
        if(item2 == null) {
            return false;
        }
        if(item1.getType() != item2.getType()) {
            //Bukkit.broadcastMessage("§cType");
            return false;
        }
        if(item1.hasItemMeta() && item2.hasItemMeta()) {
            if(item1.getItemMeta().hasDisplayName() && item2.getItemMeta().hasDisplayName()) {
                if(!item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) {
              //      Bukkit.broadcastMessage("§cName");
                    return false;
                }
            }
            if(item1.getItemMeta().hasCustomModelData() && item2.getItemMeta().hasCustomModelData()) {
                if(item1.getItemMeta().getCustomModelData() != item2.getItemMeta().getCustomModelData()) {
                //    Bukkit.broadcastMessage("§cModel");
                    return false;
                }
            }
        }
        return true;
    }

    public ItemsLists itemsLists() {
        return itemsLists;
    }
}