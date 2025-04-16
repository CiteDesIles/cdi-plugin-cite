package fr.citedesiles.plugincite.towerbuilder;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.scheduler.BukkitTask;

public class CopyTowerFromAnotherWorld {

    public static void copyTowerFromAnotherWorld(World toWorld, String fromWorld, int blocsPerTick, int y1, int y2) {
        TowerBuildRunnable towerBuildRunnable = new TowerBuildRunnable(blocsPerTick, toWorld, Bukkit.getWorld(fromWorld), y1, y2);
        BukkitTask task = towerBuildRunnable.runTaskTimer(PluginCite.instance(), 0, 0);
    }
}
