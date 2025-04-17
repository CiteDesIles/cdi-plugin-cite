package fr.citedesiles.plugincite.towerbuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

public class BoatRaceBuildRunnable extends BukkitRunnable {
    public static int x1 = -370;
    public static int y1 = 30;
    public static int z1 = -595;

    public static int x2 = 520;
    public static int y2 = 319;
    public static int z2 = 80;

    public static int currentX = x1;
    public static int currentY = y1;
    public static int currentZ = z1;

    public static int blocks = 0;

    public static int blocsPerTick = 100;

    private static final UUID RANDOM_UUID = UUID.fromString("92864445-51c5-4c3b-9039-517c9927d1b4");

    String url = "http://textures.minecraft.net/texture/ac906d688e65802569d9705b579bce56edc86ea5c36bdd6d6fc35516a77d4";

    public static World toWorld;
    public static World fromWorld;

    public BoatRaceBuildRunnable(int blocsPerTick, World toWorld, World fromWorld, int y1, int y2) {
        this.blocsPerTick = blocsPerTick;
        this.toWorld = toWorld;
        this.fromWorld = fromWorld;
        TowerBuildRunnable.y1 = y1;
        TowerBuildRunnable.currentY = y1;
        TowerBuildRunnable.y2 = y2;
    }

    @Override
    public void run() {
        for (int y = currentY; y < y2; y++) {
            for (int x = currentX; x < x2; x++) {
                for (int z = currentZ; z < z2; z++) {
                    if(fromWorld.getBlockAt(z, x, y).getType().equals(Material.AIR)) {
                        continue;
                    }
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
        //copyEntities();
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
                    Location location = entity.getLocation();
                    if(location.getBlockX() >= x1 && location.getBlockX() <= x2 && location.getBlockY() >= y1 && location.getBlockY() <= y2 && location.getBlockZ() >= z1 && location.getBlockZ() <= z2) {
                        Location newLocation = new Location(toWorld, location.getX(), location.getY(), location.getZ());
                        entity.teleport(newLocation);
                    }
                }
            }
        }
    }
    public static URL getUrlFromBase64(String base64) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(base64));
        // We simply remove the "beginning" and "ending" part of the JSON, so we're left with only the URL. You could use a proper
        // JSON parser for this, but that's not worth it. The String will always start exactly with this stuff anyway
        return new URL(decoded.substring("{\"textures\":{\"SKIN\":{\"url\":\"".length(), decoded.length() - "\"}}}".length()));
    }

    private static PlayerProfile getProfile(String url) {
    PlayerProfile profile = Bukkit.createPlayerProfile(RANDOM_UUID); // Get a new player profile
    PlayerTextures textures = profile.getTextures();
    URL urlObject;
    try {
        urlObject = new URL(url); // The URL to the skin, for example: https://textures.minecraft.net/texture/18813764b2abc94ec3c3bc67b9147c21be850cdf996679703157f4555997ea63a
    } catch (MalformedURLException exception) {
        throw new RuntimeException("Invalid URL", exception);
    }
    textures.setSkin(urlObject); // Set the skin of the player profile to the URL
    profile.setTextures(textures); // Set the textures back to the profile
    return profile;
}
}