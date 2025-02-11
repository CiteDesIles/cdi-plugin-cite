package fr.citedesiles.plugincite.shop;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class UpgradeManager {
    HashMap<Integer, Integer> slotUpgradePrices = new HashMap<>();

    public UpgradeManager() {
        slotUpgradePrices.put(6,   10000);
        slotUpgradePrices.put(7,   50000);
        slotUpgradePrices.put(8,   100000);
        slotUpgradePrices.put(9,   500000);
        slotUpgradePrices.put(10,  1000000);
        slotUpgradePrices.put(11,  5000000);
        slotUpgradePrices.put(12,  10000000);
        slotUpgradePrices.put(13,  50000000);
        slotUpgradePrices.put(14,  100000000);
        slotUpgradePrices.put(15,  500000000);
        slotUpgradePrices.put(16,  Integer.MAX_VALUE-1);
    }


    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§5§lAméliorer votre équipe");
        // Get player team
        CDITeam cteam = PluginCite.instance().teamManager().getTeam(
            PluginCite.instance().playerManager().get(player).getTeam()
        );
        // Get team slot
        int slot = cteam.getSlots();
        // Get upgrade price

        if(player.getUniqueId() != cteam.getOwner()) {
            player.sendMessage("§cVous devez être le propriétaire de l'équipe pour améliorer les slots");
            return;
        }
        if(slot >= 16) {
            player.sendMessage("§cVotre équipe a déjà atteint le nombre maximum de slots");
            return;
        }
        int price = slotUpgradePrices.get(slot+1);
        ItemStack item = new ItemStack(Material.DIAMOND);
        item.setAmount(slot+1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lSlot d'équipe supplémentaire");
        meta.setLore(
            List.of(
                "§7Prix: §6"+price+" §7$",
                "§7Cliquez pour acheter"
            )
        );
        item.setItemMeta(meta);
        inv.setItem(13, item);
        player.openInventory(inv);
    }

    public HashMap<Integer, Integer> getSlotUpgradePrices() {
        return slotUpgradePrices;
    }
}
