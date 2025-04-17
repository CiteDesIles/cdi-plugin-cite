package fr.citedesiles.plugincite.commands;

import com.github.t9t.minecraftrconclient.RconClient;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.npcs.NPCs;
import fr.citedesiles.plugincite.towerbuilder.CopyTowerFromAnotherWorld;
import fr.citedesiles.plugincite.utils.TNTUtility;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
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
            case "spawnAllNPC":
                NPCs npcs = new NPCs();
                try {
                    plugin.npcManager().addNPC("cdi-confiseur", npcs.confiseur());
                    plugin.npcManager().addNPC("cdi-repair", npcs.repair());
                    plugin.npcManager().addNPC("cdi-upgrade", npcs.upgrade());
                    plugin.npcManager().addNPC("cdi-change-server", npcs.changeServer());
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
                CopyTowerFromAnotherWorld.copyTowerFromAnotherWorld(player.getWorld(), strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                break;
            case "testrcon":
                try (RconClient client = RconClient.open("localhost", 25575, "jesuisbo")) {
                    client.sendCommand("say Hello, world depuis Java (CDI-Cite)!");
                    client.close();
                }
                break;
            case "loadWorld":
                WorldCreator creator = new WorldCreator(strings[1]);
                creator.createWorld();
                break;
            case "unloadWorld":
                Bukkit.unloadWorld(strings[1], true);
                break;
            case "runCinematic":
                for(Player target : Bukkit.getOnlinePlayers()) {
                    PluginCite.instance().getServer().dispatchCommand(commandSender, "cinema play trailer " + target.getName());
                }
                break;
            case "tnt":
                TNTUtility.explode();
                break;
            case "spawnNPC":
                switch (strings[1]) {
                    case "confiseur":
                        try {
                            plugin.npcManager().addNPC("cdi-confiseur", new NPCs().confiseur());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "repair":
                        try {
                            plugin.npcManager().addNPC("cdi-repair", new NPCs().repair());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "upgrade":
                        try {
                            plugin.npcManager().addNPC("cdi-upgrade", new NPCs().upgrade());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "change-server":
                        try {
                            plugin.npcManager().addNPC("cdi-change-server", new NPCs().changeServer());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        player.sendMessage("§cLe nom de l'NPC est incorrect");
                        break;
                }
                break;
            case "enableIsland":
                if(strings[1].equalsIgnoreCase("true")) {
                    PluginCite.islandEnable = true;
                    player.sendMessage("§aLes îles sont activées");
                } else if(strings[1].equalsIgnoreCase("false")) {
                    PluginCite.islandEnable = false;
                    player.sendMessage("§aLes îles sont désactivées");
                } else {
                    player.sendMessage("§cLa valeur doit être true ou false");
                }
                break;
        }
        return true;
    }
}
