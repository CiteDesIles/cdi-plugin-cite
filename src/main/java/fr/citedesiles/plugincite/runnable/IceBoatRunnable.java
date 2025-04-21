package fr.citedesiles.plugincite.runnable;

import fr.citedesiles.plugincite.utils.BoatRaceUtility;
import org.bukkit.scheduler.BukkitRunnable;

public class IceBoatRunnable extends BukkitRunnable {
    @Override
    public void run() {
        BoatRaceUtility.showRaceTimeToAll();

        // Detecter si un joueur est sur les barrier
        BoatRaceUtility.removePlayerOnBarrier();

        BoatRaceUtility.detectFinishLineForAllPlayers();
    }
}
