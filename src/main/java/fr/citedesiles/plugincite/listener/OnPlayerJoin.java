package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    private PluginCite plugin;

    public OnPlayerJoin(PluginCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        // TODO
    }
}
