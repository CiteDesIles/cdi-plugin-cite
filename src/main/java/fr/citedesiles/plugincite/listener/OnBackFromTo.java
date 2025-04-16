package fr.citedesiles.plugincite.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class OnBackFromTo implements Listener {
    @EventHandler
    public void on(BlockFromToEvent event) {
        // Cancel the event to prevent water from flowing
        event.setCancelled(true);
    }
}
