package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnInteractWithPlayerSkull implements Listener {

    private PluginCite plugin;

    public OnInteractWithPlayerSkull(PluginCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerInteractEvent event) {

    }
}
