package fr.citedesiles.plugincite.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class OnBlockFade implements Listener {
    @EventHandler
    public void on(BlockFadeEvent event) {
        event.setCancelled(true);
    }
    
}
