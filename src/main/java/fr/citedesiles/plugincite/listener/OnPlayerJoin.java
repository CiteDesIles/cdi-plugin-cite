package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.mysql.DatabaseManager;
import fr.citedesiles.plugincite.objects.CDIPlayer;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OnPlayerJoin implements Listener {

    private PluginCite plugin;

    public OnPlayerJoin(PluginCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        try {
            Connection connection = DatabaseManager.MAIN_DB.getDatabaseAccess().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PLAYER WHERE uuid = ?");
            preparedStatement.setString(1, event.getPlayer().getUniqueId().toString());
            if (preparedStatement.executeQuery().next()) {
                String discordId = preparedStatement.getResultSet().getString("discordID");
                String team = preparedStatement.getResultSet().getString("team");
                if(team.equals("none")) {
                    event.getPlayer().kickPlayer("Vous n'êtes pas dans une équipe");
                    return;
                }
                CDIPlayer cdiPlayer = new CDIPlayer(event.getPlayer().getUniqueId(), discordId, team);
                plugin.playerManager().add(cdiPlayer);
                event.getPlayer().sendMessage("Bienvenue sur le serveur " + event.getPlayer().getName() + " !");
                event.getPlayer().sendMessage("Vous êtes dans l'équipe " + team);

            } else {
                event.getPlayer().kickPlayer("Vous n'êtes pas enregistré dans la base de données");
                return;
            }
        } catch (SQLException e) {
            event.getPlayer().kickPlayer("Erreur de connexion à la base de données");
            e.printStackTrace();
            return;
        }

        event.setJoinMessage("§7(§a+§7) " + event.getPlayer().getName());
        Player player = event.getPlayer();
        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(plugin.playerManager().get(player).getTeam())
            .addPlayer(player);
    }
}
