package fr.citedesiles.plugincite.towerbuilder;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

public class CopyTowerFromAnotherWorld {

    public static void copyTowerFromAnotherWorld(World toWorld, World fromWorld, int blocsPerTick) {
        TowerBuildRunnable towerBuildRunnable = new TowerBuildRunnable(blocsPerTick, toWorld, fromWorld);
        BukkitTask task = towerBuildRunnable.runTaskTimer(PluginCite.instance(), 0, 0);
    }
}
