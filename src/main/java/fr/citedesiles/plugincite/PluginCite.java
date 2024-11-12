package fr.citedesiles.plugincite;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginCite extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("PluginCite enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("PluginCite disabled");
    }
}
