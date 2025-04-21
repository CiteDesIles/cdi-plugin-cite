package fr.citedesiles.plugincite.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BoatRaceUtility {
    public static boolean boatRaceMode = false;
    public static HashMap<UUID, Boolean> isInBoatRace = new HashMap<>();
    public static HashMap<UUID, Long> time = new HashMap<>();

    public static long startBoatRaceTime(UUID playerUUID) {
        long startTime = System.currentTimeMillis();
        time.put(playerUUID, startTime);
        isInBoatRace.put(playerUUID, true);
        return startTime;
    }

    public static void showRaceTimeToAll() {
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

    public static void quitBoatRace(UUID playerUUID) {
        isInBoatRace.remove(playerUUID);
        time.remove(playerUUID);
    }

     public static void finishBoatRace(Player player) {
        isInBoatRace.put(player.getUniqueId(), false);
        for(Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage("§6§l" + player.getName() + " §ea fini la course en " + String.format("%02d:%02d.%02d", (time.get(player.getUniqueId()) / 60000), (time.get(player.getUniqueId()) / 1000) % 60, (time.get(player.getUniqueId()) % 1000) / 10));
        }
        isInBoatRace.remove(player.getUniqueId());
     }


}
