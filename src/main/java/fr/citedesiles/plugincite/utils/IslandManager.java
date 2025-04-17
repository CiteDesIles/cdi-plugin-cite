package fr.citedesiles.plugincite.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class IslandManager {
    HashMap<String, Location> islandLocations = new HashMap<>();

    public IslandManager() {
        // Put all pos
        islandLocations.put("team1_1", new Location(Bukkit.getWorld("world"), -10, 10, 50));
        islandLocations.put("team1_2", new Location(Bukkit.getWorld("world"), -40, 50, 60));
    }

    public Boolean isInIsland(String team, Location loc) {
        if (islandLocations.containsKey(team + "_1") && islandLocations.containsKey(team + "_2")) {
            Location cornerOne = new Location(
                Bukkit.getWorld("world"),
                islandLocations.get(team + "_1").getX(),
                islandLocations.get(team + "_1").getY(),
                islandLocations.get(team + "_1").getZ());
            Location cornerTwo = new Location(
                Bukkit.getWorld("world"),
                islandLocations.get(team + "_2").getX(),
                islandLocations.get(team + "_2").getY(),
                islandLocations.get(team + "_2").getZ());
            if (loc.getX() >= min(cornerOne.getX(), cornerTwo.getX()) && loc.getX() <= max(cornerOne.getX(), cornerTwo.getX())
                && loc.getY() >= min(cornerOne.getY(), cornerTwo.getY()) && loc.getY() <= max(cornerOne.getY(), cornerTwo.getY())
                && loc.getZ() >= min(cornerOne.getZ(), cornerTwo.getZ()) && loc.getZ() <= max(cornerOne.getZ(), cornerTwo.getZ())) {
                return true;
            }
        }
        return false;
    }


    public double min(double d, double de) {
        if (d < de) {
            return d;
        } else {
            return de;
        }
    }

    public double max(double d, double de) {
        if (d > de) {
            return d;
        } else {
            return de;
        }
    }

    public Location getIslandMiddleLocation(String team) {
        if (islandLocations.containsKey(team + "_1") && islandLocations.containsKey(team + "_2")) {
            Location cornerOne = new Location(
                Bukkit.getWorld("world"),
                islandLocations.get(team + "_1").getX(),
                islandLocations.get(team + "_1").getY(),
                islandLocations.get(team + "_1").getZ());
            Location cornerTwo = new Location(
                Bukkit.getWorld("world"),
                islandLocations.get(team + "_2").getX(),
                islandLocations.get(team + "_2").getY(),
                islandLocations.get(team + "_2").getZ());
            return new Location(Bukkit.getWorld("world"), (cornerOne.getX() + cornerTwo.getX()) / 2, (cornerOne.getY() + cornerTwo.getY()) / 2, (cornerOne.getZ() + cornerTwo.getZ()) / 2);
        }
        return null;
    }
}