package fr.citedesiles.plugincite.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ChangeServerMenu {
    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§c§lChanger de serveur");
        ItemStack lumenItem = new ItemStack(Material.YELLOW_CONCRETE);
        ItemMeta lumenMeta = lumenItem.getItemMeta();
        lumenMeta.setDisplayName("§e§lLumen");
        lumenMeta.setLore(
            List.of(
                "§7Cliquez pour aller sur §e§lLumen"
            )
        );
        lumenItem.setItemMeta(lumenMeta);
        inv.setItem(3, lumenItem);
        ItemStack kaelum = new ItemStack(Material.PURPLE_CONCRETE);
        ItemMeta kaelumMeta = kaelum.getItemMeta();
        kaelumMeta.setDisplayName("§5§lShadow's Garden");
        kaelumMeta.setLore(
            List.of(
                "§7Cliquez pour aller sur §5§lShadow's Garden"
            )
        );
        kaelum.setItemMeta(kaelumMeta);
        inv.setItem(5, kaelum);
        player.openInventory(inv);
    }
}
