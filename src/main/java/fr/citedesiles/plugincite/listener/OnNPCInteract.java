package fr.citedesiles.plugincite.listener;

import de.oliver.fancynpcs.api.actions.ActionTrigger;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.shop.ShopManager;
import fr.citedesiles.plugincite.shop.UpgradeManager;
import fr.citedesiles.plugincite.utils.ChangeServerMenu;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnNPCInteract implements Listener {

    private PluginCite plugin;

    public OnNPCInteract(PluginCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(NpcInteractEvent event) {
//        if(!event.getInteractionType().equals(ActionTrigger.RIGHT_CLICK)) {
//            return;
//        }
//        Bukkit.broadcastMessage("NPC : " + event.getNpc().getData().getName());
        switch (event.getNpc().getData().getName()) {
            case "cdi-confiseur" -> {
                plugin.shopManager().openShop(event.getPlayer(), "confiserie");
            }
            case "cdi-repair" -> {
                plugin.shopManager().openShop(event.getPlayer(), "upgrade");
            }
            case "cdi-upgrade" -> {
                UpgradeManager upgradeManager = new UpgradeManager();
                upgradeManager.open(event.getPlayer());
            }
            case "cdi-change-server" -> {
                ChangeServerMenu changeServerMenu = new ChangeServerMenu();
                changeServerMenu.open(event.getPlayer());
            }
            case "cdi-mineur" -> {
                plugin.shopManager().openShop(event.getPlayer(), "mineur");
            }
            case "cdi-coppernic" -> {
                plugin.shopManager().openShop(event.getPlayer(), "coppernic");
            }
            case "cdi-sakura" -> {
                plugin.shopManager().openShop(event.getPlayer(), "sakura");
            }
            case "cdi-gefroid" -> {
                plugin.shopManager().openShop(event.getPlayer(), "gefroid");
            }
            case "cdi-fechaud" -> plugin.shopManager().openShop(event.getPlayer(), "fechaud");
            case "cdi-warden" -> plugin.shopManager().openShop(event.getPlayer(), "warden");
            case "cdi-enderitefox" -> plugin.shopManager().openShop(event.getPlayer(), "enderitefox");
        }
    }
}
