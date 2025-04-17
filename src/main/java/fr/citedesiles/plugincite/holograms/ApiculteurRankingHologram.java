package fr.citedesiles.plugincite.holograms;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.hologram.Hologram;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDIObjectif;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class ApiculteurRankingHologram {
    public static void init() {
        HologramManager hologramManager = FancyHologramsPlugin.get().getHologramManager();
        TextHologramData textHologramData = new TextHologramData("cdi.asmielranking", new Location(Bukkit.getWorld("world"), 80.5, 97, -371.5));
        textHologramData.setPersistent(false);
        textHologramData.setScale(new Vector3f(0.8f));
        textHologramData.setSeeThrough(false);
        textHologramData.setBillboard(Display.Billboard.CENTER);
        textHologramData.setText(List.of("§6§k§lXXXXXXXXXX XX X'XXXXXX", "§7Loading..."));
        Hologram prout = hologramManager.create(textHologramData);
        hologramManager.addHologram(prout);
    }

    public static void refresh() {
        // Faire un classement des meilleurs équipes avec CDIOBjectif qui ont comme noms "angel"
        HologramManager hologramManager = FancyHologramsPlugin.get().getHologramManager();
        Hologram hologram = hologramManager.getHologram("cdi.asmielranking").orElse(null);
        if (hologram == null) {
            return;
        }
        List<String> lines = new ArrayList<>();
        lines.add("§6§l§kXXXXXXXXXX XX X'XXXXXX");
        int position = 1;
        List<CDITeam> teamListalreadysorted = new ArrayList<>();
        for(CDITeam team : PluginCite.instance().teamManager().getTeams()) {
            CDITeam curerentBest = null;
            long bestAngel = Long.MIN_VALUE;
            for (CDITeam _team : PluginCite.instance().teamManager().getTeams()) {
                CDIObjectif angel = PluginCite.instance().objectifManager().getObjectif(_team.getName(), "honey");
                if (angel != null && angel.getValue() > bestAngel && !teamListalreadysorted.contains(_team)) {
                    bestAngel = angel.getValue();
                    curerentBest = _team;
                }
            }
            if (curerentBest != null) {
                teamListalreadysorted.add(curerentBest);
                if(curerentBest.getName().equals("adminTeam") || curerentBest.getName().equals("modTeam")) {
                    continue;
                }
                lines.add("§7" + position + "e. " + curerentBest.getDisplayName() + " §8- §7" + bestAngel);
                position++;
            }
        }
        ((TextHologramData) hologram.getData()).setText(lines);
    }
}
