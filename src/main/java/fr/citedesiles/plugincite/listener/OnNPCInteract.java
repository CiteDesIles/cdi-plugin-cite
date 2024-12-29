package fr.citedesiles.plugincite.listener;

import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import fr.citedesiles.plugincite.PluginCite;
import org.bukkit.event.Listener;

public class OnNPCInteract implements Listener {

    private PluginCite plugin;

    public OnNPCInteract(PluginCite plugin) {
        this.plugin = plugin;
    }

    public void on(NpcInteractEvent event) {
        switch (event.getNpc().getData().getId()) {
            case "cdi-confiseur" -> {

            }
        }
    }
}
