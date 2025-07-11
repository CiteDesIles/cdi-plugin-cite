package fr.citedesiles.plugincite.commands;

import com.github.t9t.minecraftrconclient.RconClient;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.holograms.MainRankingHologram;
import fr.citedesiles.plugincite.npcs.NPCs;
import fr.citedesiles.plugincite.towerbuilder.CopyIceRaceFromAnotherWorld;
import fr.citedesiles.plugincite.towerbuilder.CopyTowerFromAnotherWorld;
import fr.citedesiles.plugincite.utils.BoatRaceUtility;
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
        switch (strings[0]) {
            case "spawnAllNPC":
                NPCs npcs = new NPCs();
                try {
                    plugin.npcManager().addNPC("cdi-confiseur", npcs.confiseur());
                    plugin.npcManager().addNPC("cdi-repair", npcs.repair());
                    plugin.npcManager().addNPC("cdi-upgrade", npcs.upgrade());
                    plugin.npcManager().addNPC("cdi-change-server", npcs.changeServer());
                    plugin.npcManager().addNPC("cdi-mineur", npcs.mineur());
                    plugin.npcManager().addNPC("cdi-coppernic", npcs.coppernic());
                    plugin.npcManager().addNPC("cdi-sakura", npcs.sakura());
                    plugin.npcManager().addNPC("cdi-gefroid", npcs.gefroid());
                    plugin.npcManager().addNPC("cdi-fechaud", npcs.fechaud());
                    plugin.npcManager().addNPC("cdi-warden", npcs.warden());
                    plugin.npcManager().addNPC("cdi-enderitefox", npcs.enderitefox());
                    plugin.npcManager().addNPC("cdi-begaydocrime", npcs.begaydocrime());
                    plugin.npcManager().addNPC("cdi-poisson", npcs.poisson());
                    plugin.npcManager().addNPC("cdi-fildrong", npcs.fildrong());
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "index": {
                Player player = (Player) commandSender;
                Inventory inv = Bukkit.createInventory(null, 54, "Index");
                for(int i = 0; i < plugin.itemManager().getCustomsItems().size(); i++) {
                    inv.setItem(i, plugin.itemManager().getCustomsItems().get(i));
                }
                player.openInventory(inv);
                break;
            }
            case "removeAllNPC":
                plugin.npcManager().removeAllNPC();
                break;
            case "whatismyteam": {
                Player player = (Player) commandSender;
                player.sendMessage("§aVotre équipe est: " + plugin.playerManager().get(player.getUniqueId()).getTeam());
                break;
            }
            case "copy":
                CopyTowerFromAnotherWorld.copyTowerFromAnotherWorld(Bukkit.getWorld("world"), strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
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
                    case "mineur":
                        try {
                            plugin.npcManager().addNPC("cdi-mineur", new NPCs().mineur());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "coppernic":
                        try {
                            plugin.npcManager().addNPC("cdi-coppernic", new NPCs().coppernic());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "sakura":
                        try {
                            plugin.npcManager().addNPC("cdi-sakura", new NPCs().sakura());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "gefroid":
                        try {
                            plugin.npcManager().addNPC("cdi-gefroid", new NPCs().gefroid());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "fechaud":
                        try {
                            plugin.npcManager().addNPC("cdi-fechaud", new NPCs().fechaud());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "warden":
                        try {
                            plugin.npcManager().addNPC("cdi-warden", new NPCs().warden());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "enderitefox":
                        try {
                            plugin.npcManager().addNPC("cdi-enderitefox", new NPCs().enderitefox());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "begaydocrime":
                        try {
                            plugin.npcManager().addNPC("cdi-begaydocrime", new NPCs().begaydocrime());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "poisson":
                        try {
                            plugin.npcManager().addNPC("cdi-poisson", new NPCs().poisson());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "fildrong":
                        try {
                            plugin.npcManager().addNPC("cdi-fildrong", new NPCs().fildrong());
                        } catch (ExecutionException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        commandSender.sendMessage("§cLe nom de l'NPC est incorrect");
                        break;
                }
                break;
            case "enableIsland":
                if(strings[1].equalsIgnoreCase("true")) {
                    PluginCite.islandEnable = true;
                    commandSender.sendMessage("§aLes îles sont activées");
                } else if(strings[1].equalsIgnoreCase("false")) {
                    PluginCite.islandEnable = false;
                    commandSender.sendMessage("§aLes îles sont désactivées");
                } else {
                    commandSender.sendMessage("§cLa valeur doit être true ou false");
                }
                break;
            case "iceRaceCopy":
                CopyIceRaceFromAnotherWorld.copyTowerFromAnotherWorld(Bukkit.getWorld("world"), strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                break;
            case "mainScoreboard":
                if(strings[1].equalsIgnoreCase("true")) {
                    PluginCite.shouldShowMainScoreboard = true;
                    commandSender.sendMessage("§aLe scoreboard principal est activé");
                }
                else if(strings[1].equalsIgnoreCase("false")) {
                    PluginCite.shouldShowMainScoreboard = false;
                    commandSender.sendMessage("§aLe scoreboard principal est désactivé");
                } else {
                    commandSender.sendMessage("§cLa valeur doit être true ou false");
                }
                break;
            case "objectifScoreboard":
                if(strings[1].equalsIgnoreCase("true")) {
                    PluginCite.shouldShowObjectifScore = true;
                    commandSender.sendMessage("§aLe scoreboard objectif est activé");
                }
                else if(strings[1].equalsIgnoreCase("false")) {
                    PluginCite.shouldShowObjectifScore = false;
                    commandSender.sendMessage("§aLe scoreboard objectif est désactivé");
                } else {
                    commandSender.sendMessage("§cLa valeur doit être true ou false");
                }
                break;
            case "boatRaceMode":
                if(strings[1].equalsIgnoreCase("true")) {
                    BoatRaceUtility.boatRaceMode = true;
                    commandSender.sendMessage("§aLe mode bateau est activé");
                } else if(strings[1].equalsIgnoreCase("false")) {
                    BoatRaceUtility.boatRaceMode = false;
                    commandSender.sendMessage("§aLe mode bateau est désactivé");
                } else {
                    commandSender.sendMessage("§cLa valeur doit être true ou false");
                }
                break;
            case "line":
                int number = Integer.parseInt(strings[1]);
                MainRankingHologram.lineToShow = number;
                commandSender.sendMessage("§aLes lignes affichés: " + number);
        }
        return true;
    }
}
