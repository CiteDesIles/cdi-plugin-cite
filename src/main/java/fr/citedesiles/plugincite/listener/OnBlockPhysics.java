package fr.citedesiles.plugincite.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class OnBlockPhysics implements Listener {
    @EventHandler
    public void OnBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }
}
