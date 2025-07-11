package fr.citedesiles.plugincite.holograms;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.hologram.Hologram;
import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class HeadRankingHologram {
    public static void init() {
        HologramManager hologramManager = FancyHologramsPlugin.get().getHologramManager();
        TextHologramData textHologramData = new TextHologramData("cdi.headranking", new Location(Bukkit.getWorld("world"), 68.5, 97, -339.5));
        textHologramData.setPersistent(false);
        textHologramData.setScale(new Vector3f(0.8f));
        textHologramData.setSeeThrough(false);
        textHologramData.setBillboard(Display.Billboard.CENTER);
        textHologramData.setText(List.of("§6§k§lXXXXXXXXXX XXX XXXXX", "§7Loading..."));
        Hologram prout = hologramManager.create(textHologramData);
        hologramManager.addHologram(prout);

    }

    public static void refresh() {
        HologramManager hologramManager = FancyHologramsPlugin.get().getHologramManager();
        Hologram hologram = hologramManager.getHologram("cdi.headranking").orElse(null);
        if (hologram == null) {
            return;
        }

        if(!PluginCite.shouldShowObjectifScore) {
            List<String> lines = new ArrayList<>();
            lines.add("§7Cassé...");
            ((TextHologramData) hologram.getData()).setText(lines);
            return;
        }

        List<String> lines = new ArrayList<>();
        lines.add("§6§l§kXXXXXXXXXX XXX XXXXX");
        int position = 1;
        List<CDITeam> teamListalreadysorted = new ArrayList<>();
        for(CDITeam team : PluginCite.instance().teamManager().getTeams()) {
            CDITeam curerentBest = null;
            int bestHead = -1;
            for (CDITeam _team : PluginCite.instance().teamManager().getTeams()) {
                if (_team.getHeads().size() > bestHead && !teamListalreadysorted.contains(_team)) {
                    bestHead = _team.getHeads().size();
                    curerentBest = _team;
                }
            }
            if (curerentBest != null) {
                teamListalreadysorted.add(curerentBest);
                if(curerentBest.getName().equals("adminTeam") || curerentBest.getName().equals("modTeam")) {
                    continue;
                }
                lines.add("§7" + position + "e. " + curerentBest.getDisplayName() + " §8- §7" + curerentBest.getHeads().size());
                position++;
            }
        }
        ((TextHologramData) hologram.getData()).setText(lines);
    }
}
