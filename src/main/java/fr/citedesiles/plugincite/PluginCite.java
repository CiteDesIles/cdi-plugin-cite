package fr.citedesiles.plugincite;

import fr.citedesiles.plugincite.commands.AdminCommand;
import fr.citedesiles.plugincite.customsItems.ItemManager;
import fr.citedesiles.plugincite.listener.OnClickInventory;
import fr.citedesiles.plugincite.listener.OnNPCInteract;
import fr.citedesiles.plugincite.listener.OnPlayerChat;
import fr.citedesiles.plugincite.listener.OnPlayerJoin;
import fr.citedesiles.plugincite.mysql.TeamSyncSQL;
import fr.citedesiles.plugincite.npcs.NPCManager;
import fr.citedesiles.plugincite.mysql.CheckTable;
import fr.citedesiles.plugincite.mysql.DatabaseManager;
import fr.citedesiles.plugincite.objects.CDIPlayerManager;
import fr.citedesiles.plugincite.objects.CDITeamManager;
import fr.citedesiles.plugincite.runnable.TeamSyncSaveRunnable;
import fr.citedesiles.plugincite.shop.ShopManager;
import fr.citedesiles.plugincite.utils.ConfigManager;
import fr.citedesiles.plugincite.utils.ScoreboardTeamManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class PluginCite extends JavaPlugin {

    private static PluginCite INSTANCE;
    private static ConfigManager configManager;
    private static NPCManager npcManager;
    private static ItemManager itemManager;
    private static ShopManager shopManager;
    private static CDIPlayerManager playerManager;
    private static CDITeamManager teamManager;
    private static ScoreboardTeamManager scoreboardTeamManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        npcManager = new NPCManager();
        getLogger().info("PluginCite enabled");
        configManager = new ConfigManager(this);

        itemManager = new ItemManager();
        shopManager = new ShopManager();

        playerManager = new CDIPlayerManager(this);
        teamManager = new CDITeamManager(this);

        getServer().getPluginManager().registerEvents(new OnPlayerChat(this), this);
        getServer().getPluginManager().registerEvents(new OnNPCInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnClickInventory(this), this);

        getCommand("admin").setExecutor(new AdminCommand(this));
        try {
            configManager.loadConfig();
        } catch (IOException e) {
            getLogger().severe("An error occurred while loading config.yml");
        }

        DatabaseManager.initAllDataBaseConnections();
        CheckTable.checkTables();
        TeamSyncSQL.getAllTeamsFromDB(this);

        TeamSyncSaveRunnable teamSyncSaveRunnable = new TeamSyncSaveRunnable();
        teamSyncSaveRunnable.runTaskTimerAsynchronously(this, 0, 20 * 5);

        scoreboardTeamManager = new ScoreboardTeamManager(this);
        scoreboardTeamManager.initAllTeams();
    }

    @Override
    public void onDisable() {
        npcManager().removeAllNPC();
        getLogger().info("PluginCite disabled");
    }

    public static PluginCite instance() {
        return INSTANCE;
    }

    public ConfigManager configManager() {
        return configManager;
    }

    public NPCManager npcManager() {
        return npcManager;
    }

    public ShopManager shopManager() {
        return shopManager;
    }

    public ItemManager itemManager() {
        return itemManager;
    }

    public CDIPlayerManager playerManager() {
        return playerManager;
    }

    public CDITeamManager teamManager() {
        return teamManager;
    }
}
