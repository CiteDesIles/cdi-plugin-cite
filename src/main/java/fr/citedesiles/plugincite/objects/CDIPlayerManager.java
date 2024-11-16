package fr.citedesiles.plugincite.objects;

import fr.citedesiles.plugincite.PluginCite;

import java.util.ArrayList;
import java.util.List;

public class CDIPlayerManager {
    private List<CDIPlayer> players = new ArrayList<>();
    private PluginCite plugin;

    public CDIPlayerManager(PluginCite plugin) {
        this.plugin = plugin;
    }
}
