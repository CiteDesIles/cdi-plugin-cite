package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import fr.citedesiles.plugincite.utils.DiscordWebhooksUtility;
import io.papermc.paper.event.player.AsyncChatEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.CompletableFuture;

public class OnPlayerChat implements Listener {
    private PluginCite plugin;

    public OnPlayerChat(PluginCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        CompletableFuture.runAsync(() -> {
            DiscordWebhooksUtility discordWebhooksUtility = new DiscordWebhooksUtility(plugin);
            discordWebhooksUtility.onPlayerSendMessage(event.getPlayer(), event.signedMessage().message());
        });
        event.setCancelled(true);
        Player player = event.getPlayer();
        String team = plugin.playerManager().get(player).getTeam();
        CDITeam cdiTeam = plugin.teamManager().getTeam(team);
        if (cdiTeam == null) {
            return;
        }
        String teamColor = cdiTeam.getColor();
        String playerName = player.getName();
        String message = event.signedMessage().message();
        String chat = "";
        chat+= teamColor + " " + playerName + "§7: §f" + message;
        for(Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(chat);
        }
    }
}
