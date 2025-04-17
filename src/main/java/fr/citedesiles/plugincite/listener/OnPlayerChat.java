package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.utils.DiscordWebhooksUtility;
import io.papermc.paper.event.player.AsyncChatEvent;
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
    }
}
