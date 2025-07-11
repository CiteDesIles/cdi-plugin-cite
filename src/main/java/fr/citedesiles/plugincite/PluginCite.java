package fr.citedesiles.plugincite;

import fr.citedesiles.plugincite.commands.AdminCommand;
import fr.citedesiles.plugincite.commands.BoatCommand;
import fr.citedesiles.plugincite.commands.EcoCommand;
import fr.citedesiles.plugincite.commands.PayCommand;
import fr.citedesiles.plugincite.customsItems.ItemManager;
import fr.citedesiles.plugincite.holograms.HologramsManager;
import fr.citedesiles.plugincite.listener.*;
import fr.citedesiles.plugincite.mysql.TeamSyncSQL;
import fr.citedesiles.plugincite.npcs.NPCManager;
import fr.citedesiles.plugincite.mysql.CheckTable;
import fr.citedesiles.plugincite.mysql.DatabaseManager;
import fr.citedesiles.plugincite.objects.CDIObjectifManager;
import fr.citedesiles.plugincite.objects.CDIPlayerManager;
import fr.citedesiles.plugincite.objects.CDITeamManager;
import fr.citedesiles.plugincite.runnable.*;
import fr.citedesiles.plugincite.shop.ShopManager;
import fr.citedesiles.plugincite.towerbuilder.WorldLoaderUtility;
import fr.citedesiles.plugincite.utils.*;
import org.bukkit.entity.Boat;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public class PluginCite extends JavaPlugin {

    private static PluginCite INSTANCE;
    private static ConfigManager configManager;
    private static NPCManager npcManager;
    private static ItemManager itemManager;
    private static ShopManager shopManager;
    private static CDIPlayerManager playerManager;
    private static CDITeamManager teamManager;
    private static HologramsManager hologramsManager;
    private static CDIObjectifManager objectifManager;
    private static IslandManager islandManager;

    public static boolean shouldShowMainScoreboard = false;
    public static boolean shouldShowObjectifScore = false;

    public static boolean islandEnable = true;

    @Override
    public void onEnable() {

        WorldLoaderUtility.loadWorld("world");

        INSTANCE = this;
        npcManager = new NPCManager();
        configManager = new ConfigManager(this);

        itemManager = new ItemManager();
        shopManager = new ShopManager();

        objectifManager = new CDIObjectifManager();

        playerManager = new CDIPlayerManager(this);
        teamManager = new CDITeamManager(this);

        islandManager = new IslandManager();

        CustomCraftUtility customCraftUtility = new CustomCraftUtility();
        customCraftUtility.registerCustomCrafts();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        getServer().getPluginManager().registerEvents(new OnPlayerChat(this), this);
        getServer().getPluginManager().registerEvents(new OnNPCInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnClickInventory(this), this);
        getServer().getPluginManager().registerEvents(new OnInteractWithPlayerSkull(this), this);
        getServer().getPluginManager().registerEvents(new OnBlockUpdate(), this);
        getServer().getPluginManager().registerEvents(new OnDamage(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeaveVehicleListener(), this);

        Objects.requireNonNull(getCommand("admin")).setExecutor(new AdminCommand(this));
        Objects.requireNonNull(getCommand("eco")).setExecutor(new EcoCommand());
        Objects.requireNonNull(getCommand("pay")).setExecutor(new PayCommand());
        Objects.requireNonNull(getCommand("boat")).setExecutor(new BoatCommand());


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

        RefreshRunnable refreshRunnable = new RefreshRunnable();
        refreshRunnable.runTaskTimer(this, 0, 20 * 5);

        ScoreboardRunnable scoreboardRunnable = new ScoreboardRunnable(this);
        scoreboardRunnable.runTaskTimer(this, 0, 10);

        ObjectifRunnable objectifRunnable = new ObjectifRunnable();
        objectifRunnable.runTaskTimerAsynchronously(this, 0, 20 * 60 * 5);

        GameModeRunnable gameModeRunnable = new GameModeRunnable();
        gameModeRunnable.runTaskTimer(this, 0, 0);

        ScoreboardTeamManager scoreboardTeamManager = new ScoreboardTeamManager(this);
        scoreboardTeamManager.initAllTeams();

        hologramsManager = new HologramsManager();
        hologramsManager.initAll();
        HeadsFileManager.loadHeads();

        IceBoatRunnable iceBoatRunnable = new IceBoatRunnable();
        iceBoatRunnable.runTaskTimer(this, 0, 0);

        getServer().getScheduler().runTaskTimerAsynchronously(this, HeadsFileManager::saveHeads, 20 * 60 * 5, 20 * 60 * 5);

        getLogger().info("PluginCite enabled");
    }

    @Override
    public void onDisable() {
        npcManager().removeAllNPC();
        HeadsFileManager.saveHeads();
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

    public HologramsManager hologramManager() {
        return hologramsManager;
    }

    public CDIObjectifManager objectifManager() {
        return objectifManager;
    }

    public IslandManager islandManager() {
        return islandManager;
    }
}
