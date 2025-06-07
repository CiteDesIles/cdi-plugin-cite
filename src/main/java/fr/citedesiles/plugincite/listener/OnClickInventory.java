package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import fr.citedesiles.plugincite.shop.UpgradeManager;
import fr.citedesiles.plugincite.utils.DiscordWebhooksUtility;
import fr.citedesiles.plugincite.utils.JoinFunctionUtility;
import org.bukkit.Material;
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

        if (event.getView().getTitle().startsWith("§c§lChanger de serveur")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            if (event.getCurrentItem().getType().equals(Material.YELLOW_CONCRETE)) {
                event.getWhoClicked().sendMessage("§fVous allez être redirigé vers le serveur §e§lLumen");
                JoinFunctionUtility.connect("lumen", (Player) event.getWhoClicked());
                event.getWhoClicked().closeInventory();
                return;
            }
            if (event.getCurrentItem().getType().equals(Material.PURPLE_CONCRETE)) {
                event.getWhoClicked().sendMessage("§fVous allez être redirigé vers le serveur §5§lShadow's Garden");
                JoinFunctionUtility.connect("kaelum", (Player) event.getWhoClicked());
                event.getWhoClicked().closeInventory();
                return;
            }
        }

        if (event.getView().getTitle().startsWith("§5")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();
            if (event.getCurrentItem().getType().equals(Material.DIAMOND)) {
                CDITeam cteam = PluginCite.instance().teamManager().getTeam(PluginCite.instance().playerManager().get(player).getTeam());
                UpgradeManager upgradeManager = new UpgradeManager();
                long price = upgradeManager.getSlotUpgradePrices().get(cteam.getSlots() + 1);
                if (!player.getUniqueId().equals(cteam.getOwner())) {
                    player.sendMessage("§cVous devez être le propriétaire de l'équipe pour améliorer les slots");
                    return;
                }
                if (cteam.getSlots() >= 16) {
                    player.sendMessage("§cVotre équipe a déjà atteint le nombre maximum de slots");
                    return;
                }
                if (cteam.getMoney() < price) {
                    player.sendMessage("§cVotre équipe n'a pas assez d'argent pour acheter un slot supplémentaire");
                    return;
                }
                cteam.setSlots(cteam.getSlots() + 1);
                cteam.setMoney(cteam.getMoney() - price);
                player.sendMessage("§aVous avez acheté un slot supplémentaire pour votre équipe");
                player.sendMessage("§e§l[-" + price + " G]");
                player.sendMessage("§c§lAttention, cela peut prendre 30 secondes pour que le changement soit effectif");

                DiscordWebhooksUtility discordWebhooksUtility = new DiscordWebhooksUtility(PluginCite.instance());
                discordWebhooksUtility.sendCustomMessage("Achat Slot", "§a" + player.getName() + " a acheté un slot supplémentaire pour son équipe " + cteam.getName() + " pour " + price + " golds");

                player.closeInventory();
            }
            return;
        }

        if (event.getView().getTitle().startsWith("§6")) {

            // TODO: Implement buy logic

            event.setCancelled(true);
            String itemList = event.getView().getTitle().replace("§6", "");
            ItemStack item = event.getCurrentItem();
            ItemStack originalItem = PluginCite.instance().shopManager().getOriginalItemFromList(item, itemList);
            if (item == null || item.getItemMeta() == null) return;
            if (!PluginCite.instance().shopManager().isInItemsList(itemList, originalItem)) {
                event.getWhoClicked().sendMessage("§cCet item n'est pas à vendre/à déposer.");
                return;
            }
            long price = PluginCite.instance().shopManager().getPrice(itemList, originalItem);
            if (price == -1) {
                event.getWhoClicked().sendMessage("§cErreur lors de la récupération du prix de l'item/quantité d'objets.");
                return;
            }
            long count = PluginCite.instance().shopManager().countItemInInventory((Player) event.getWhoClicked(), originalItem);
            if (count == 0) {
                event.getWhoClicked().sendMessage("§cVous n'avez pas cet item dans votre inventaire.");
                return;
            }

            if (event.isRightClick() && !event.isShiftClick()) count = Math.min(count, 64);
            else if (event.isLeftClick() && !event.isShiftClick()) count = 1;
            else if (!event.isShiftClick()) return;

            if (event.getView().getTitle().contains("upgrade")) {

                if (count > price) count = price;
                if (count == 0) return;

                removeItemAmount((Player) event.getWhoClicked(), originalItem, (int) count);
                PluginCite.instance().teamManager().addSPToTeam(PluginCite.instance().playerManager().get((Player) event.getWhoClicked()).getTeam(), count);
                event.getWhoClicked().sendMessage("§aVous avez déposé " + count + " " + originalItem.getType() + " §apour §b§l" + count + " SP§a.");
                plugin.shopManager().itemsLists().editPrice("upgrade", originalItem, price - count);
                PluginCite.instance().shopManager().openShop((Player) event.getWhoClicked(), "upgrade");

                DiscordWebhooksUtility discordWebhooksUtility = new DiscordWebhooksUtility(PluginCite.instance());
                discordWebhooksUtility.sendCustomMessage("Dépot SP", "§a" + event.getWhoClicked().getName() + " a déposé " + count + " " + originalItem.getType() + " pour " + count + " SP");
                if (!needItemForUpgrade()) discordWebhooksUtility.sendCustomMessage("Amélioration Tour", "@everyone la tour doit être améliorée, il n'y a plus d'items à déposer");
            } else {

                removeItemAmount((Player) event.getWhoClicked(), originalItem, (int) count);
                String team = PluginCite.instance().playerManager().get((Player) event.getWhoClicked()).getTeam();
                PluginCite.instance().teamManager().addGoldToTeam(team, count * price);
                String itemName = originalItem.getItemMeta().hasDisplayName() ? originalItem.getItemMeta().getDisplayName() : originalItem.getType().name();
                event.getWhoClicked().sendMessage("§aVous avez vendu " + count + " " + itemName + " §apour §e§l" + count * price + " golds§a.");
                DiscordWebhooksUtility discordWebhooksUtility = new DiscordWebhooksUtility(plugin);
                discordWebhooksUtility.sendCustomMessage("Vente d'items", "§a" + event.getWhoClicked().getName() + " a vendu " + count + " " + itemName + " pour " + count * price + " golds");
            }
        }
    }

    public void removeItemAmount(Player player, ItemStack item, int amount) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.isSimilar(item)) {
                if (itemStack.getAmount() >= amount) {
                    itemStack.setAmount(itemStack.getAmount() - amount);
                    return;
                } else {
                    amount -= itemStack.getAmount();
                    itemStack.setAmount(0);
                }
            }
        }
    }

    public boolean needItemForUpgrade() {
        for (Long item : PluginCite.instance().shopManager().itemsLists().getItemsList("upgrade").values())
            if (item > 0) return true;
        return false;
    }
}
