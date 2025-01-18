package fr.citedesiles.plugincite.towerbuilder;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

public class CopyTowerFromAnotherWorld {
    public static BukkitTask task;

    public static void copyTowerFromAnotherWorld(World toWorld, World fromWorld, int blocsPerTick) {
        int x1 = -535;
        int y1 = 30;
        int z1 = -700;

        int x2 = 550;
        int y2 = 319;
        int z2 = 800;

        task = PluginCite.instance().getServer().getScheduler().runTaskTimer(PluginCite.instance(), () -> {
            int blocks = 0;
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    for (int z = z1; z < z2; z++) {
                        toWorld.getBlockAt(x, y, z).setType(fromWorld.getBlockAt(x, y, z).getType());
                        blocks++;
                        if (blocks >= blocsPerTick) {
                            return;
                        }
                    }
                }
            }
        }, 0, 1);
    }
}
