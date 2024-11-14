package fr.citedesiles.plugincite;

import fr.citedesiles.plugincite.postgresql.DatabaseManager;
import fr.citedesiles.plugincite.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginCite extends JavaPlugin {

    private static PluginCite INSTANCE;
    ConfigManager configManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("PluginCite enabled");
        configManager = new ConfigManager(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PluginCite disabled");
    }

    public static PluginCite instance() {
        return INSTANCE;
    }

    public ConfigManager configManager() {
        return configManager;
    }
}
