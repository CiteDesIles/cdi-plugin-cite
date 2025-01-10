package fr.citedesiles.plugincite.listener;

import de.oliver.fancynpcs.api.actions.ActionTrigger;
import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.shop.ShopManager;
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
            case "cdi-upgrade" -> {
                plugin.shopManager().openShop(event.getPlayer(), "upgrade");
            }
        }
    }
}
