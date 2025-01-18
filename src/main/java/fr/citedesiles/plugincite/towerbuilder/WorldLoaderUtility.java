package fr.citedesiles.plugincite.towerbuilder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldLoaderUtility {
    public static void loadWorld(String worldName) {
        // Load the world
        World world = Bukkit.createWorld(new WorldCreator(worldName));
    }
}
