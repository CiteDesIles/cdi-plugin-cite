package fr.citedesiles.plugincite.commands;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.npcs.NPCs;
import fr.citedesiles.plugincite.towerbuilder.CopyTowerFromAnotherWorld;
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
        Player player = (Player) commandSender;
        switch (strings[0]) {
            case "spawnNPC":
                NPCs npcs = new NPCs();
                try {
                    plugin.npcManager().addNPC("cdi-confiseur", npcs.confiseur());
                    plugin.npcManager().addNPC("cdi-repair", npcs.repair());
                    plugin.npcManager().addNPC("cdi-upgrade", npcs.upgrade());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "index":
                Inventory inv = Bukkit.createInventory(null, 54, "Index");
                for(int i = 0; i < plugin.itemManager().getCustomsItems().size(); i++) {
                    inv.setItem(i, plugin.itemManager().getCustomsItems().get(i));
                }
                player.openInventory(inv);
                break;
            case "removeAllNPC":
                plugin.npcManager().removeAllNPC();
                break;
            case "whatismyteam":
                player.sendMessage("§aVotre équipe est: " + plugin.playerManager().get(player.getUniqueId()).getTeam());
                break;
            case "copy":
                CopyTowerFromAnotherWorld.copyTowerFromAnotherWorld(player.getWorld(), Bukkit.getWorld(strings[1]), Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                break;
        }
        return true;
    }
}
