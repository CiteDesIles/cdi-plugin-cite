package fr.citedesiles.plugincite.towerbuilder;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class TowerBuildRunnable extends BukkitRunnable {
    public static int x1 = -326;
    public static int y1 = 30;
    public static int z1 = -595;

    public static int x2 = 400;
    public static int y2 = 319;
    public static int z2 = 80;

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
        TowerBuildRunnable.y1 = y1;
        TowerBuildRunnable.currentY = y1;
        TowerBuildRunnable.y2 = y2;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(currentX + " " + currentY + " " + currentZ);
        for (int y = currentY; y < y2; y++) {
            for (int x = currentX; x < x2; x++) {
                for (int z = currentZ; z < z2; z++) {
                    toWorld.getBlockAt(x, y, z).setType(fromWorld.getBlockAt(x, y, z).getType());
                    toWorld.getBlockAt(x, y, z).setBlockData(fromWorld.getBlockAt(x, y, z).getBlockData());
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
        copyEntities();
        this.cancel();
    }


    public void copyEntities() {
        // Get all entites in chunk in fromWorld
        int chunkX = ((int) x1/16);
        int chunkZ = ((int) z1/16);
        int chunkX2 = ((int) x2/16);
        int chunkZ2 = ((int) z2/16);
        for(int x = chunkX; x < chunkX2; x++) {
            for(int z = chunkZ; z < chunkZ2; z++) {
                Chunk chunk = fromWorld.getChunkAt(x, z);
                chunk.load();
                for(Entity entity : chunk.getEntities()) {
                    Bukkit.broadcastMessage(entity.getType().toString());
                    Location location = entity.getLocation();
                    if(location.getBlockX() >= x1 && location.getBlockX() <= x2 && location.getBlockY() >= y1 && location.getBlockY() <= y2 && location.getBlockZ() >= z1 && location.getBlockZ() <= z2) {
                        Bukkit.broadcastMessage("Teleporting entity");
                        Location newLocation = new Location(toWorld, location.getX(), location.getY(), location.getZ());
                        entity.teleport(newLocation);
                    }
                }
            }
        }
    }
}
