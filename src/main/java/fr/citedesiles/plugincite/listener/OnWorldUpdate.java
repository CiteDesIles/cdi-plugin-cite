package fr.citedesiles.plugincite.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class OnWorldUpdate implements Listener {
    @EventHandler
    public void OnWorldUpdate(EntityChangeBlockEvent event) {
        event.setCancelled(true);
    }
}
