package fr.citedesiles.plugincite.utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class IslandManager {
    HashMap<String, Location> islandLocations = new HashMap<>();

    public IslandManager() {
        islandLocations.put("team1_1", new Location(Bukkit.getWorld("world"), 475, 25, 112));
        islandLocations.put("team1_2", new Location(Bukkit.getWorld("world"), 619, 150, -65));

        islandLocations.put("team2_1", new Location(Bukkit.getWorld("world"), 463, 25, 184));
        islandLocations.put("team2_2", new Location(Bukkit.getWorld("world"), 276, 150, 355));

        islandLocations.put("team3_1", new Location(Bukkit.getWorld("world"), 10, 25, 150));
        islandLocations.put("team3_2", new Location(Bukkit.getWorld("world"), -180, 150, 320));
                
        islandLocations.put("team4_1", new Location(Bukkit.getWorld("world"), -190, 25, 115));
        islandLocations.put("team4_2", new Location(Bukkit.getWorld("world"), -367, 150, -70));
                
        islandLocations.put("team5_1", new Location(Bukkit.getWorld("world"), -327, 25, -626));
        islandLocations.put("team5_2", new Location(Bukkit.getWorld("world"), -175, 150, -812));
                
        islandLocations.put("team6_1", new Location(Bukkit.getWorld("world"), -110, 25, -773));
        islandLocations.put("team6_2", new Location(Bukkit.getWorld("world"), 72, 150, -953));
                
        islandLocations.put("team7_1", new Location(Bukkit.getWorld("world"), 282, 25, -882));
        islandLocations.put("team7_2", new Location(Bukkit.getWorld("world"), 465, 150, -703));
                
        islandLocations.put("team8_1", new Location(Bukkit.getWorld("world"), 533, 25, -866));
        islandLocations.put("team8_2", new Location(Bukkit.getWorld("world"), 710, 150, -666));
    }

    public Boolean isInIsland(String team, Location loc) {
        if(team == null || loc == null)
            return false;
        if(team.equals("adminTeam") || team.equals("modTeam"))
            return true;
        //if (islandLocations.containsKey(team + "_1") && islandLocations.containsKey(team + "_2")) {
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
        //}
        return false;
    }

    public String getIsland(Location loc) {
        for (int i = 0; i < 8; i++)
            if (isInIsland("team" + (i + 1), loc)) return "team" + (i + 1);
        return null;
    }

    public boolean isInIsland(Location loc) {
        return getIsland(loc) != null;
    }

    public double min(double d, double de) {
        return Math.min(d, de);
    }

    public double max(double d, double de) {
        return Math.max(d, de);
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