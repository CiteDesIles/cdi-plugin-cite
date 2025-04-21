package fr.citedesiles.plugincite.commands;

import fr.citedesiles.plugincite.utils.BoatRaceUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BoatCommand implements CommandExecutor {

    int x = 128;
    int y = 239;
    int z = -350;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(strings.length < 1) {
            commandSender.sendMessage("Usage: /boat <spawn/teleport>");
            return true;
        }

        switch (strings[0]) {
            case "spawn" -> {
                commandSender.sendMessage("Bonne course!");
                // Logic to spawn a boat if player is 10 players away from the point
                Player player = (Player) commandSender;
                if(player.getLocation().distance(new Location(Bukkit.getWorld("world"), x, y, z)) > 10) {
                    player.sendMessage("§cVous devez être proche du point de départ.");
                    return true;
                }
                Boat boat = commandSender.getServer().getWorld("world").spawn(player.getLocation(), Boat.class);
                boat.addPassenger(player);
                BoatRaceUtility.startBoatRaceTime(player.getUniqueId());
            }
            case "teleport" -> {
                Player player = (Player) commandSender;
                if(BoatRaceUtility.isInBoatRace.containsKey(player.getUniqueId())) {
                    BoatRaceUtility.quitBoatRace(player.getUniqueId());
                }
                commandSender.sendMessage("Boat teleported!");
                player.teleport(new Location(Bukkit.getWorld("world"), x, y+10, z));
                // Logic to teleport a boat
            }
        }
        return true;
    }
}
