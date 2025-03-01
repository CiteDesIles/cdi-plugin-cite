package fr.citedesiles.plugincite.runnable;

import fr.citedesiles.plugincite.mysql.SyncObjectif;
import org.bukkit.scheduler.BukkitRunnable;

public class ObjectifRunnable extends BukkitRunnable {
    @Override
    public void run() {
        SyncObjectif.getFromDB();
    }
}
