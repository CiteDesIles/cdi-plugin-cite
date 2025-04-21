package fr.citedesiles.plugincite.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BoatRaceUtility {
    public static boolean boatRaceMode = false;
    public static HashMap<UUID, Boolean> isInBoatRace = new HashMap<>();
    public static HashMap<UUID, Long> time = new HashMap<>();

    public long startBoatRaceTime(UUID playerUUID) {
        long startTime = System.currentTimeMillis();
        time.put(playerUUID, startTime);
        isInBoatRace.put(playerUUID, true);
        return startTime;
    }

    public void showRaceTimeToAll() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(isInBoatRace.containsKey(player.getUniqueId())) {
                if(isInBoatRace.get(player.getUniqueId())) {
                    long startTime = time.get(player.getUniqueId());
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - startTime;
                    // Format: Temps: 00:00.00
                    player.sendMessage("§a§lTemps: " + String.format("%02d:%02d.%02d", (elapsedTime / 60000), (elapsedTime / 1000) % 60, (elapsedTime % 1000) / 10));
                }
            }
        }
    }

    public void quitBoatRace(UUID playerUUID) {
        isInBoatRace.remove(playerUUID);
        time.remove(playerUUID);
    }


}
