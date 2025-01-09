package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Material;
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
        if (event.getClickedBlock() == null) {
            return;
        }
        if (event.getClickedBlock().getType().equals(Material.PLAYER_HEAD) || event.getClickedBlock().getType().equals(Material.PLAYER_WALL_HEAD)) {
            event.setCancelled(true);
            CDITeam cdiTeam = plugin.teamManager().getTeam(plugin.playerManager().get(event.getPlayer()).getTeam());
            if (cdiTeam == null) {
                return;
            }
            if(cdiTeam.hasHead(event.getClickedBlock().getLocation())) {
                event.getPlayer().sendMessage("§c§lVous avez déjà trouvé cette tête.");
            } else {
                cdiTeam.addHead(event.getClickedBlock().getLocation());
                event.getPlayer().sendMessage("§a§lVous avez trouvé une tête secrète !");
            }
        }
    }
}
