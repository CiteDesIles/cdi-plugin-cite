package fr.citedesiles.plugincite.utils;

import com.eduardomcb.discord.webhook.WebhookClient;
import com.eduardomcb.discord.webhook.WebhookManager;
import com.eduardomcb.discord.webhook.models.Message;
import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class DiscordWebhooksUtility {
    private final PluginCite plugin;

    public DiscordWebhooksUtility(PluginCite plugin) {
        this.plugin = plugin;
    }

    public void onPlayerSendMessage(Player player, String message) {
        String url = this.plugin.getConfig().getString("webhook-url");
        if (url == null) {
            return;
        }
        Message discordMessage = new Message()
                .setContent(message)
                .setUsername(player.getName() + " - SRV Cite");

        CompletableFuture.runAsync(() -> {
            new WebhookManager()
                .setChannelUrl(url)
                .setMessage(discordMessage)
                .setListener(new WebhookClient.Callback() {
                    @Override
                    public void onSuccess(String response) {
                        return;
                    }

                    @Override
                    public void onFailure(int statusCode, String errorMessage) {
                        plugin.getLogger().severe("Erreur lors de l'envoi du message sur le webhook");
                    }
                })
                .exec();
        });


    }

    public void sendCustomMessage(String name, String message) {
        String url = this.plugin.getConfig().getString("https://discord.com/api/webhooks/1362809329702539344/xxLQN6FkYDEeObd8LnpBBLTyyZnmdDUH6T1qSqsZcwpfWGs-e_Fr-SsoJhqsv2QagX68");
        if (url == null) {
            return;
        }
        Message discordMessage = new Message()
                .setContent(message)
                .setUsername(name + " - SRV Cite");

    CompletableFuture.runAsync(() -> {
        new WebhookManager()
            .setChannelUrl(url)
            .setMessage(discordMessage)
            .setListener(new WebhookClient.Callback() {
                @Override
                public void onSuccess(String response) {
                    return;
                }

                @Override
                public void onFailure(int statusCode, String errorMessage) {
                    plugin.getLogger().severe("Erreur lors de l'envoi du message sur le webhook");
                }
            })
            .exec();
        });
    }


}
