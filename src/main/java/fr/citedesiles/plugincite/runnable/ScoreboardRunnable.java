package fr.citedesiles.plugincite.runnable;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.fastboard.adventure.FastBoard;
import fr.citedesiles.plugincite.objects.CDITeam;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardRunnable extends BukkitRunnable {

    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private final PluginCite plugin;

    public ScoreboardRunnable(PluginCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            // Get or create board for this player
            FastBoard board = boards.computeIfAbsent(player.getUniqueId(), uuid -> new FastBoard(player));

            // Set title
            board.updateTitle(Component.text("Cité des îles", NamedTextColor.GOLD, TextDecoration.BOLD));

            // Get player's team
            CDITeam cdiTeam = plugin.teamManager().getTeam(plugin.playerManager().get(player).getTeam());

            // Create lines
            board.updateLines(
                Component.text("").color(NamedTextColor.GRAY),
                Component.text("Votre équipe :").color(NamedTextColor.WHITE),
                Component.text(cdiTeam.getDisplayName()).color(NamedTextColor.YELLOW),
                Component.text(""),
                Component.text("Stats :").color(NamedTextColor.WHITE),
                Component.text(cdiTeam.getMoney() + " G").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD),
                Component.text(cdiTeam.getSupportPoints() + " SP").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD),
                Component.text(""),
                Component.text("Position: ").color(NamedTextColor.WHITE)
                    .append(Component.text(plugin.teamManager().position(cdiTeam)).color(NamedTextColor.RED))
            );
        }

        // Remove boards for offline players
        boards.entrySet().removeIf(entry -> Bukkit.getPlayer(entry.getKey()) == null);
    }

    public void onDisable() {
        // Clean up all boards when plugin disables
        for (FastBoard board : boards.values()) {
            board.delete();
        }
        boards.clear();
    }
}