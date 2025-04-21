package fr.citedesiles.plugincite.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§a§lTemps: " + String.format("%02d:%02d.%02d", (elapsedTime / 60000), (elapsedTime / 1000) % 60, (elapsedTime % 1000) / 10)));
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

     public static void removePlayerOnBarrier() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(isInBoatRace.containsKey(player.getUniqueId())) {
                if(isInBoatRace.get(player.getUniqueId())) {
                    if(player.getLocation().add(0,-1,0).getBlock().getType().equals(Material.BARRIER)) {
                        player.sendMessage("§cVous avez touché un mur invisible");
                        player.leaveVehicle();
                    }
                }
            }
        }
     }

     public void detectFinishLineForAllPlayers() {
        Location cornerOne = new Location(Bukkit.getWorld("world"), 86, 92, -314);
        Location cornerTwo = new Location(Bukkit.getWorld("world"), 84, 96, -314);
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(isInBoatRace.containsKey(player.getUniqueId())) {
                if(isInBoatRace.get(player.getUniqueId())) {
                    if(player.getLocation().getBlockX() >= cornerOne.getBlockX() && player.getLocation().getBlockX() <= cornerTwo.getBlockX()) {
                        if(player.getLocation().getBlockZ() >= cornerOne.getBlockZ() && player.getLocation().getBlockZ() <= cornerTwo.getBlockZ()) {
                            finishBoatRace(player);
                        }
                    }
                }
            }
        }
     }


}
