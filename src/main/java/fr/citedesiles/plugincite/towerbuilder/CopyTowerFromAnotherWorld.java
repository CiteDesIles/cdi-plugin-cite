package fr.citedesiles.plugincite.towerbuilder;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

public class CopyTowerFromAnotherWorld {

    public static void copyTowerFromAnotherWorld(World toWorld, World fromWorld, int blocsPerTick, int y1, int y2) {
        TowerBuildRunnable towerBuildRunnable = new TowerBuildRunnable(blocsPerTick, toWorld, fromWorld, y1, y2);
        BukkitTask task = towerBuildRunnable.runTaskTimer(PluginCite.instance(), 0, 0);
    }
}
