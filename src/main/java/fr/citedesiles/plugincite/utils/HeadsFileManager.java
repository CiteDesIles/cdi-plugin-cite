package fr.citedesiles.plugincite.utils;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HeadsFileManager {
    public final static String filename = "heads.yml";

    public static void saveHeads() {
        YamlConfiguration config = new YamlConfiguration();
        for(CDITeam team : PluginCite.instance().teamManager().getTeams()) {
            for(Location head : team.getHeads()) {
                config.set(team.getName() + ".heads." + team.getHeads().indexOf(head) + ".x", head.getX());
                config.set(team.getName() + ".heads." + team.getHeads().indexOf(head) + ".y", head.getY());
                config.set(team.getName() + ".heads." + team.getHeads().indexOf(head) + ".z", head.getZ());
                config.set(team.getName() + ".heads." + team.getHeads().indexOf(head) + ".world", head.getWorld().getName());
            }
        }
        try {
            config.save(PluginCite.instance().getDataFolder() + "/" + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public static void loadHeads() {
    if(!new File(PluginCite.instance().getDataFolder(), filename).exists()) {
        return;
    }
    YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(PluginCite.instance().getDataFolder(), filename));
    for (String teamName : config.getKeys(false)) {
        CDITeam team = PluginCite.instance().teamManager().getTeam(teamName);
        if (team != null) {
            List<Location> heads = new ArrayList<>();
            for (String key : config.getConfigurationSection(teamName + ".heads").getKeys(false)) {
                double x = config.getDouble(teamName + ".heads." + key + ".x");
                double y = config.getDouble(teamName + ".heads." + key + ".y");
                double z = config.getDouble(teamName + ".heads." + key + ".z");
                String worldName = config.getString(teamName + ".heads." + key + ".world");
                Location head = new Location(Bukkit.getWorld(worldName), x, y, z);
                heads.add(head);
            }
            team.setHeads(heads);
        }
    }
}
}
