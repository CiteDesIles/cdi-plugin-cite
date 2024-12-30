package fr.citedesiles.plugincite.runnable;

import fr.citedesiles.plugincite.mysql.TeamSyncSQL;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamSyncSaveRunnable extends BukkitRunnable {

    @Override
    public void run() {
        TeamSyncSQL.updateAllTeamsToDB();
    }
}
