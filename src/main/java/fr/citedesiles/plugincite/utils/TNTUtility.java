package fr.citedesiles.plugincite.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.EntityType;

public class TNTUtility {

    static int x1 = 40;
    static int y1 = 115;
    static int z1 = -391;

    static int x2 = 121;
    static int y2 = 216;
    static int z2 = -312;

    static int nbTNT = 2500;

    public static void explode() {
        for(int i = 0; i < nbTNT; i++) {
            int x = (int) (Math.random() * (x2 - x1 + 1)) + x1;
            int y = (int) (Math.random() * (y2 - y1 + 1)) + y1;
            int z = (int) (Math.random() * (z2 - z1 + 1)) + z1;

            // Assuming you have a method to get the world
            World world = Bukkit.getWorld("world");
            if(world != null) {
                world.spawnEntity(
                    new Location(world, x, y, z),
                    EntityType.TNT
                );
            }
        }
    }
}
