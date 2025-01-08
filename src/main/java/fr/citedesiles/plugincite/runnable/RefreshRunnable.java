package fr.citedesiles.plugincite.runnable;

import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.scheduler.BukkitRunnable;

public class RefreshRunnable extends BukkitRunnable {
    @Override
    public void run() {
        PluginCite.instance().hologramManager().refreshAll();
    }
}
