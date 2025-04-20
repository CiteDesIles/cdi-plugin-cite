package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class OnBlockUpdate implements Listener {
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if (!PluginCite.instance().islandManager().isInIsland(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        if (!PluginCite.instance().islandManager().isInIsland(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void OnBlockPhysics(BlockPhysicsEvent event) {
        if (!PluginCite.instance().islandManager().isInIsland(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void OnEntityChangeBlock(EntityChangeBlockEvent event) {
        if (!PluginCite.instance().islandManager().isInIsland(event.getBlock().getLocation())) event.setCancelled(true);
    }
}
