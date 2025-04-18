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
    HashMap<Integer, Long> slotUpgradePrices = new HashMap<>();

    public UpgradeManager() {
        slotUpgradePrices.put(6,   100000L);
        slotUpgradePrices.put(7,   500000L);
        slotUpgradePrices.put(8,   1000000L);
        slotUpgradePrices.put(9,   5000000L);
        slotUpgradePrices.put(10,  10000000L);
        slotUpgradePrices.put(11,  50000000L);
        slotUpgradePrices.put(12,  1000000000L);
        slotUpgradePrices.put(13,  5000000000L);
        slotUpgradePrices.put(14,  10000000000L);
        slotUpgradePrices.put(15,  50000000000L);
        slotUpgradePrices.put(16,  Long.MAX_VALUE-1);
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

        if(!player.getUniqueId().equals(cteam.getOwner())) {
            player.sendMessage("§cVous devez être le propriétaire de l'équipe pour améliorer les slots");
            return;
        }
        if(slot >= 16) {
            player.sendMessage("§cVotre équipe a déjà atteint le nombre maximum de slots");
            return;
        }
        long price = slotUpgradePrices.get(slot+1);
        ItemStack item = new ItemStack(Material.DIAMOND);
        item.setAmount(slot+1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lSlot d'équipe supplémentaire");
        meta.setLore(
            List.of(
                "§7Prix: §e§l"+price+" golds",
                "§7Cliquez pour acheter"
            )
        );
        item.setItemMeta(meta);
        inv.setItem(13, item);
        player.openInventory(inv);
    }

    public HashMap<Integer, Long> getSlotUpgradePrices() {
        return slotUpgradePrices;
    }
}
