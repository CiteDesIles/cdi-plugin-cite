package fr.citedesiles.plugincite.utils;

import com.eduardomcb.discord.webhook.WebhookClient;
import com.eduardomcb.discord.webhook.WebhookManager;
import com.eduardomcb.discord.webhook.models.Message;
import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.entity.Player;

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

        WebhookManager webhookManager = new WebhookManager()
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


    }


}
