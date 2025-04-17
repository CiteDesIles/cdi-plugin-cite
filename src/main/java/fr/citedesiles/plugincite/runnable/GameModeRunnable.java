package fr.citedesiles.plugincite.runnable;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDIPlayer;
import fr.citedesiles.plugincite.objects.CDITeam;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class GameModeRunnable extends BukkitRunnable {

    @Override
    public void run() {
        if(PluginCite.islandEnable) {
            return;
        }
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.isOp()) {
                continue;
            }
            if(player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE)) {
                continue;
            }
            CDIPlayer cdiPlayer = PluginCite.instance().playerManager().get(player.getUniqueId());
            CDITeam cdiTeam = PluginCite.instance().teamManager().getTeam(cdiPlayer.getTeam());
            if(cdiTeam == null) {
                continue;
            }
            if(PluginCite.instance().islandManager().isInIsland(cdiTeam.getName(), player.getLocation())) {
                player.setGameMode(GameMode.SURVIVAL);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy("§aVous êtes dans votre île"));
            } else {
                player.setGameMode(GameMode.ADVENTURE);
            }
        }
    }

    
}
