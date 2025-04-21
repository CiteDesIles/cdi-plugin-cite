package fr.citedesiles.plugincite.listener;

import fr.citedesiles.plugincite.utils.BoatRaceUtility;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.OakBoat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class OnPlayerLeaveVehicleListener implements Listener {
    @EventHandler
    public void on(VehicleExitEvent event) {
        if(event.getExited() instanceof Player player) {
            // Detect if player is in boat mode and then delete the boat if true
            if(event.getVehicle() instanceof OakBoat) {
                if(BoatRaceUtility.isInBoatRace.containsKey(player.getUniqueId())) {
                    if (BoatRaceUtility.isInBoatRace.get(player.getUniqueId())) {
                        BoatRaceUtility.quitBoatRace(player.getUniqueId());
                        player.sendMessage("§cVous avez quitté la course.");
                    }
                }
            }
        }
    }
}
