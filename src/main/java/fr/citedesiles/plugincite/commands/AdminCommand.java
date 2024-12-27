package fr.citedesiles.plugincite.commands;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.npcs.NPCs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

public class AdminCommand implements CommandExecutor {

    private PluginCite plugin;

    public AdminCommand(PluginCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande");
            return false;
        }
        switch (strings[0]) {
            case "spawnNPC":
                NPCs npcs = new NPCs();
                try {
                    plugin.npcManager().addNPC("cdi-confiseur", npcs.confiseur());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "removeAllNPC":
                plugin.npcManager().removeAllNPC();
                break;
        }
        return true;
    }
}
