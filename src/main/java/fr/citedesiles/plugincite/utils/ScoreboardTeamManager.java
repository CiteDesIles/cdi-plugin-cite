package fr.citedesiles.plugincite.utils;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

public class ScoreboardTeamManager {

    private final PluginCite plugin;

    public ScoreboardTeamManager(PluginCite plugin) {
        this.plugin = plugin;
    }

    public void removeAllTeams() {
        for(Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
            team.unregister();
        }
    }

    public void initAllTeams() {
        removeAllTeams();
        for(CDITeam cdiTeam : plugin.teamManager().getTeams()) {
            Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(cdiTeam.getName());
            team.setPrefix(cdiTeam.getColor());
            team.setDisplayName(cdiTeam.getDisplayName());
        }
    }
}
