package fr.citedesiles.plugincite.objects;

import fr.citedesiles.plugincite.PluginCite;

import java.util.List;

public class CDITeamManager {
    private List<CDITeam> teams;
    private PluginCite plugin;

    public CDITeamManager(PluginCite plugin) {
        this.plugin = plugin;
    }
}
