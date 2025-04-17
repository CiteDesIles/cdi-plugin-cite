package fr.citedesiles.plugincite.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.kyori.adventure.text.Component;

public class OnPlayerQuit implements Listener {
    @EventHandler
    public void on(PlayerQuitEvent event) {
        event.setQuitMessage("§7(§c+§7) " + event.getPlayer().getName());
    }
    
}
