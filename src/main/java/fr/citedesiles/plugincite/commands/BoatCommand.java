package fr.citedesiles.plugincite.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BoatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(strings.length < 1) {
            commandSender.sendMessage("Usage: /boat <spawn/teleport>");
            return true;
        }

        switch (strings[0]) {
            case "spawn" -> {
                commandSender.sendMessage("Boat spawned!");
                // Logic to spawn a boat
            }
            case "teleport" -> {
                commandSender.sendMessage("Boat teleported!");
                // Logic to teleport a boat
            }
        }
        return true;
    }
}
