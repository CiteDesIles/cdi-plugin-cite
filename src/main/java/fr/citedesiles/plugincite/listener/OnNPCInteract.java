package fr.citedesiles.plugincite.listener;

import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import org.bukkit.event.Listener;

public class OnNPCInteract implements Listener {
    public void on(NpcInteractEvent event) {
        switch (event.getNpc().getData().getId()) {
            case "cdi-confiseur" -> {

            }
        }
    }
}
