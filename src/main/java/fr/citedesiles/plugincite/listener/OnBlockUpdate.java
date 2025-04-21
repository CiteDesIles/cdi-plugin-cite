package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDIPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnBlockUpdate implements Listener {
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if (areUpdatesDisabled(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        if (areUpdatesDisabled(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (areUpdatesDisabled(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (areUpdatesDisabled(event.getBlock().getLocation())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().isOp()) return;
        if (event.getBlock().getType() == Material.PLAYER_HEAD) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        String team = PluginCite.instance().islandManager().getIsland(event.getClickedBlock().getLocation());
        if (team == null || event.getPlayer().isOp()) return;
        CDIPlayer cdiPlayer = PluginCite.instance().playerManager().get(event.getPlayer().getUniqueId());
        if (!team.equals(cdiPlayer.getTeam())) event.setCancelled(true);
    }

    private boolean areUpdatesDisabled(Location location) {
        String team = PluginCite.instance().islandManager().getIsland(location);
        return team == null || team.equals("team4");
    }
}
