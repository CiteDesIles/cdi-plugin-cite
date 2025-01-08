package fr.citedesiles.plugincite.holograms;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.HologramData;
import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.hologram.Hologram;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainHologram {
    public static void init() {
        HologramManager manager = FancyHologramsPlugin.get().getHologramManager();
        TextHologramData hologramData = new TextHologramData("cdi.main", new Location(Bukkit.getWorld("world"), 90.5, 134, -349.5, 90, 0));
        hologramData.setBackground(Color.fromRGB(80, 10, 9));
        hologramData.setPersistent(false);
        hologramData.setBillboard(Display.Billboard.FIXED);
        hologramData.setScale(new Vector3f(1.2f));
        hologramData.setSeeThrough(false);
        hologramData.setVisibilityDistance(64);
        hologramData.setText(List.of("§6§lCité des îles", "§7Classement principal loading..."));
        Hologram hologram = manager.create(hologramData);
        manager.addHologram(hologram);

        TextHologramData hologramData2 = new TextHologramData("cdi.main2", new Location(Bukkit.getWorld("world"), 90.5, 134, -349.5, -90, 0));
        hologramData2.setBackground(Color.fromRGB(80, 10, 9));
        hologramData2.setPersistent(false);
        hologramData2.setScale(new Vector3f(1.2f));
        hologramData2.setSeeThrough(false);
        hologramData2.setVisibilityDistance(64);
        hologramData2.setBillboard(Display.Billboard.FIXED);
        hologramData2.setText(List.of("§6§lCité des îles", "§7Classement principal loading..."));
        Hologram hologram2 = manager.create(hologramData2);
        manager.addHologram(hologram2);
    }

    public static void refresh() {
        Optional<Hologram> ohologram1 = FancyHologramsPlugin.get().getHologramManager().getHologram("cdi.main");
        Optional<Hologram> ohologram2 = FancyHologramsPlugin.get().getHologramManager().getHologram("cdi.main2");

        if (ohologram1.isEmpty()) {
            return;
        }

        if (ohologram2.isEmpty()) {
            return;
        }

        Hologram hologram1 = ohologram1.get();
        Hologram hologram2 = ohologram2.get();

        List<String> text = new ArrayList<>();
        text.add("§6§lCité des îles");
        List<CDITeam> rankedTeams = new ArrayList<>();
        int positiona = 1;
        for(CDITeam team : PluginCite.instance().teamManager().getTeams()) {
            CDITeam currentTeamBest = null;
            long currentBest = Long.MIN_VALUE;
            for(CDITeam _team : PluginCite.instance().teamManager().getTeams()) {
                if(_team.getMoney() > currentBest && !rankedTeams.contains(_team)) {
                    currentTeamBest = _team;
                    currentBest = _team.getMoney();
                }
            }
            if(currentTeamBest != null) {
                rankedTeams.add(currentTeamBest);
                if(currentTeamBest.getName().equals("adminTeam") || currentTeamBest.getName().equals("modTeam")) {
                    continue;
                }
                text.add("§7" + positiona + "e. " + currentTeamBest.getDisplayName() + " §e" + currentTeamBest.getMoney() + " golds");
                positiona++;
            }
        }

        HologramData hologramData1 = hologram1.getData();
        HologramData hologramData2 = hologram2.getData();

        ((TextHologramData) hologramData1).setText(text);
        ((TextHologramData) hologramData2).setText(text);
    }
}
