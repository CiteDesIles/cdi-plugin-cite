package fr.citedesiles.plugincite.objects;

import fr.citedesiles.plugincite.PluginCite;

import java.util.ArrayList;
import java.util.List;

public class CDITeamManager {
    private List<CDITeam> teams = new ArrayList<>();
    private PluginCite plugin;

    public CDITeamManager(PluginCite plugin) {
        this.plugin = plugin;
    }

    public void add(CDITeam team) {
        teams.add(team);
    }

    public List<CDITeam> getTeams() {
        return new ArrayList<>(teams);
    }

    public void addGoldToTeam(String teamName, long amount) {
        for (CDITeam team : teams) {
            if (team.getName().equals(teamName)) {
                team.setMoney(team.getMoney() + amount);
            }
        }
    }
}
