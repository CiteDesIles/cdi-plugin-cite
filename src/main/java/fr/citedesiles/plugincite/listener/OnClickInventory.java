package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class OnClickInventory implements Listener {

    private PluginCite plugin;

    public OnClickInventory(PluginCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(InventoryClickEvent event) {
        if(event.getView().getTitle().startsWith("§6")) {
            event.setCancelled(true);
            String itemList = event.getView().getTitle().replace("§6", "");
            ItemStack item = event.getCurrentItem();
            ItemStack originalItem = PluginCite.instance().shopManager().getOriginalItemFromList(item, itemList);
            if(item == null) {
                return;
            }
            if(item.getItemMeta() == null) {
                return;
            }
            if(!PluginCite.instance().shopManager().isInItemsList(itemList, originalItem)) {
                event.getWhoClicked().sendMessage("§cCet item n'est pas à vendre.");
                return;
            }
            int price = PluginCite.instance().shopManager().getPrice(itemList, originalItem);
            if(price == -1) {
                event.getWhoClicked().sendMessage("§cErreur lors de la récupération du prix de l'item.");
                return;
            }
            int count = PluginCite.instance().shopManager().countItemInInventory((Player) event.getWhoClicked(), originalItem);
            if(count == 0) {
                event.getWhoClicked().sendMessage("§cVous n'avez pas cet item dans votre inventaire.");
                return;
            }
            if(event.isShiftClick()) {
                int total = count * price;
                removeItemAmount((Player) event.getWhoClicked(), originalItem, count);
                String team = PluginCite.instance().playerManager().get((Player) event.getWhoClicked()).getTeam();
                PluginCite.instance().teamManager().addGoldToTeam(team, total);
                if(originalItem.getItemMeta().hasDisplayName()) {
                    event.getWhoClicked().sendMessage("§aVous avez vendu " + count + " " + item.getItemMeta().getDisplayName() + " §apour §e§l" + price * count + " golds§a.");
                } else {
                    event.getWhoClicked().sendMessage("§aVous avez vendu " + count + " " + item.getType().toString() + " §apour §e§l" + price * count + " golds§a.");
                }
                return;
            }
            if(event.isRightClick()) {
                if(count >= 64) {
                    removeItemAmount((Player) event.getWhoClicked(), originalItem, 64);
                    String team = PluginCite.instance().playerManager().get((Player) event.getWhoClicked()).getTeam();
                    PluginCite.instance().teamManager().addGoldToTeam(team, 64 * price);
                    if(originalItem.getItemMeta().hasDisplayName()) {
                        event.getWhoClicked().sendMessage("§aVous avez vendu 64 " + item.getItemMeta().getDisplayName() + " §apour §e§l" + 64 * price + " golds§a.");
                    } else {
                        event.getWhoClicked().sendMessage("§aVous avez vendu 64 " + item.getType().toString() + " §apour §e§l" + 64 * price + " golds§a.");
                    }
                    return;
                } else {
                    event.getWhoClicked().sendMessage("§cVous n'avez pas assez d'items dans votre inventaire.");
                    return;
                }
            }
            if(event.isLeftClick()) {
                removeItemAmount((Player) event.getWhoClicked(), originalItem, 1);
                String team = PluginCite.instance().playerManager().get((Player) event.getWhoClicked()).getTeam();
                PluginCite.instance().teamManager().addGoldToTeam(team, price);
                if(originalItem.getItemMeta().hasDisplayName()) {
                    event.getWhoClicked().sendMessage("§aVous avez vendu 1 " + item.getItemMeta().getDisplayName() + " §apour §e§l" + price + " golds§a.");
                } else {
                    event.getWhoClicked().sendMessage("§aVous avez vendu 1 " + item.getType().toString() + " §apour §e§l" + price + " golds§a.");
                }
            }


        }
    }

    public void removeItemAmount(Player player, ItemStack item, int amount) {
        for(ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null && itemStack.isSimilar(item)) {
                if(itemStack.getAmount() > amount) {
                    itemStack.setAmount(itemStack.getAmount() - amount);
                    return;
                } else if(itemStack.getAmount() == amount) {
                    player.getInventory().remove(itemStack);
                    return;
                } else {
                    amount -= itemStack.getAmount();
                    player.getInventory().remove(itemStack);
                }
            }
        }
    }
}
