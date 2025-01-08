package fr.citedesiles.plugincite.holograms;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.hologram.Hologram;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;

import java.util.List;

public class MainHologram {
    public static void init() {
        HologramManager manager = FancyHologramsPlugin.get().getHologramManager();
        TextHologramData hologramData = new TextHologramData("cdi.main", new Location(Bukkit.getWorld("world"), 0, 100, 0));
        hologramData.setBackground(Color.fromRGB(80, 10, 9));
        hologramData.setPersistent(false);
        hologramData.setBillboard(Display.Billboard.FIXED);
        hologramData.setText(List.of("§6§lCité des îles", "§7Classement principal loading..."));

        Hologram hologram = manager.create(hologramData);
        manager.addHologram(hologram);
    }

    public static void refresh() {

    }
}
