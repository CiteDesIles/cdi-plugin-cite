package fr.citedesiles.plugincite.commands;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.npcs.NPCs;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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
            case "index":
                Player player = (Player) commandSender;
                Inventory inv = Bukkit.createInventory(null, 54, "Index");
                for(int i = 0; i < 54; i++) {
                    inv.setItem(i, plugin.itemManager().getCustomsItems().get(i));
                }

                break;
            case "removeAllNPC":
                plugin.npcManager().removeAllNPC();
                break;
            case "whatismyteam":
                Player player1 = (Player) commandSender;
                player1.sendMessage("§aVotre équipe est: " + plugin.playerManager().get(player1.getUniqueId()).getTeam());
                break;
        }
        return true;
    }
}
