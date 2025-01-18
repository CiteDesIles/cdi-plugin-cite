package fr.citedesiles.plugincite.towerbuilder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class TowerBuildRunnable extends BukkitRunnable {
    public static int x1 = -535;
    public static int y1 = 30;
    public static int z1 = -700;

    public static int x2 = 550;
    public static int y2 = 319;
    public static int z2 = 800;

    public static int currentX = x1;
    public static int currentY = y1;
    public static int currentZ = z1;

    public static int blocks = 0;

    public static int blocsPerTick = 100;

    public static World toWorld;
    public static World fromWorld;

    public TowerBuildRunnable(int blocsPerTick, World toWorld, World fromWorld, int y1, int y2) {
        this.blocsPerTick = blocsPerTick;
        this.toWorld = toWorld;
        this.fromWorld = fromWorld;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(currentX + " " + currentY + " " + currentZ);
        for (int y = currentY; y < y2; y++) {
            for (int x = currentX; x < x2; x++) {
                for (int z = currentZ; z < z2; z++) {
                    toWorld.getBlockAt(x, y, z).setType(fromWorld.getBlockAt(x, y, z).getType());
                    blocks++;
                    if (blocks >= blocsPerTick) {
                        currentX = x;
                        currentY = y;
                        currentZ = z;
                        blocks = 0;
                        return;
                    }
                }
                currentZ = z1;
            }
            currentX = x1;
        }
        currentY = y1;
    }
}
