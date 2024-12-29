package fr.citedesiles.plugincite;

import fr.citedesiles.plugincite.commands.AdminCommand;
import fr.citedesiles.plugincite.customsItems.ItemManager;
import fr.citedesiles.plugincite.listener.OnPlayerChat;
import fr.citedesiles.plugincite.npcs.NPCManager;
import fr.citedesiles.plugincite.npcs.NPCs;
import fr.citedesiles.plugincite.postgresql.CheckTable;
import fr.citedesiles.plugincite.postgresql.DatabaseManager;
import fr.citedesiles.plugincite.shop.ShopManager;
import fr.citedesiles.plugincite.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PluginCite extends JavaPlugin {

    private static PluginCite INSTANCE;
    private static ConfigManager configManager;
    private static NPCManager npcManager;
    private static ItemManager itemManager;
    private static ShopManager shopManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        npcManager = new NPCManager();
        getLogger().info("PluginCite enabled");
        configManager = new ConfigManager(this);
        DatabaseManager.initAllDataBaseConnections();
        CheckTable.checkTables();

        itemManager = new ItemManager();
        shopManager = new ShopManager();

        //itemManager.initCustomsItems();


        getServer().getPluginManager().registerEvents(new OnPlayerChat(this), this);

        getCommand("admin").setExecutor(new AdminCommand(this));
        try {
            configManager.loadConfig();
        } catch (IOException e) {
            getLogger().severe("An error occurred while loading config.yml");
        }
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
}
