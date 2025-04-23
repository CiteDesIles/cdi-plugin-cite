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
import java.util.Collections; // Import Collections for potential sorting later if needed
import java.util.Comparator; // Import Comparator for sorting
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Import Stream API for cleaner filtering/sorting

public class MainRankingHologram {

    public static int lineToShow = 0; // This variable controls how many ranked lines are shown (0 = title only, 1 = title + 8th, 2 = title + 7th + 8th, ..., 8 = title + 1st to 8th)

    public static void init() {
        HologramManager manager = FancyHologramsPlugin.get().getHologramManager();

        // --- Hologram 1 ---
        TextHologramData hologramData = new TextHologramData("cdi.main", new Location(Bukkit.getWorld("world"), 90.5, 275, -349.5, 0, 0));
        hologramData.setBackground(Color.fromRGB(80, 10, 9));
        hologramData.setPersistent(false); // Consider setting to true if you want it to survive restarts and manage creation only once
        hologramData.setBillboard(Display.Billboard.FIXED);
        hologramData.setScale(new Vector3f(1.2f));
        hologramData.setSeeThrough(false);
        hologramData.setVisibilityDistance(64);
        hologramData.setText(List.of("§6§lCité des îles", "§7Chargement...")); // Initial text
        Hologram hologram = manager.create(hologramData);
        // Check if hologram already exists before adding (optional, good practice if persistent=true)
        if (manager.getHologram("cdi.main").isEmpty()) {
            manager.addHologram(hologram);
        }


        // --- Hologram 2 ---
        TextHologramData hologramData2 = new TextHologramData("cdi.main2", new Location(Bukkit.getWorld("world"), 90.5, 275, -349.5, -180, 0));
        hologramData2.setBackground(Color.fromRGB(80, 10, 9));
        hologramData2.setPersistent(false); // Consider setting to true
        hologramData2.setScale(new Vector3f(1.2f));
        hologramData2.setSeeThrough(false);
        hologramData2.setVisibilityDistance(64);
        hologramData2.setBillboard(Display.Billboard.FIXED);
        hologramData2.setText(List.of("§6§lCité des îles", "§7Chargement...")); // Initial text
        Hologram hologram2 = manager.create(hologramData2);
        // Check if hologram already exists before adding (optional, good practice if persistent=true)
        if (manager.getHologram("cdi.main2").isEmpty()) {
            manager.addHologram(hologram2);
        }
    }

    public static void refresh() {
        Optional<Hologram> ohologram1 = FancyHologramsPlugin.get().getHologramManager().getHologram("cdi.main");
        Optional<Hologram> ohologram2 = FancyHologramsPlugin.get().getHologramManager().getHologram("cdi.main2");

        // Ensure both holograms exist before proceeding
        if (ohologram1.isEmpty() || ohologram2.isEmpty()) {
            System.err.println("[PluginCite] Error: Main ranking hologram(s) not found during refresh.");
            // Optionally try to re-initialize or log more details
            // init(); // Be careful with re-init loops
            return;
        }

        Hologram hologram1 = ohologram1.get();
        Hologram hologram2 = ohologram2.get();
        HologramData hologramData1 = hologram1.getData();
        HologramData hologramData2 = hologram2.getData();

        // Check if the data is actually TextHologramData
        if (!(hologramData1 instanceof TextHologramData) || !(hologramData2 instanceof TextHologramData)) {
            System.err.println("[PluginCite] Error: Hologram data is not TextHologramData.");
            return;
        }

        // Handle the "broken" state
        if (!PluginCite.shouldShowMainScoreboard) {
            List<String> brokenLines = List.of("§c§lSystème Désactivé"); // More descriptive text
            ((TextHologramData) hologram1.getData()).setText(brokenLines);
            ((TextHologramData) hologram2.getData()).setText(brokenLines);
            return;
        }

        // --- Generate the full ranked list ---
        List<String> fullRankedText = new ArrayList<>();
        fullRankedText.add("§8§l--- §6§lCité des îles §8§l---"); // 0: Title
        // fullRankedText.add("§70e. §3§l§kCorruption" + " §e∞ golds"); // 1: Optional fixed Rank 0

        // Get teams, filter out admin/mod, sort by money (descending)
        List<CDITeam> sortedTeams = PluginCite.instance().teamManager().getTeams().stream()
            .filter(team -> team != null && !team.getName().equals("adminTeam") && !team.getName().equals("modTeam"))
            .sorted(Comparator.comparingLong(CDITeam::getMoney).reversed()) // Sort highest money first
            .collect(Collectors.toList());

        // Add top 8 (or fewer if not enough teams) to the full list
        int rank = 1;
        for (CDITeam team : sortedTeams) {
            if (rank > 8) break; // Limit to top 8 for display purposes
            // Check for null display name just in case
            String displayName = team.getDisplayName() != null ? team.getDisplayName() : "§cÉquipe inconnue";
            fullRankedText.add("§7" + rank + "e. " + displayName + " §e" + team.getMoney() + " golds");
            rank++;
        }

        // If less than 8 teams, you might want placeholder lines, e.g.:
        while (rank <= 8) {
            fullRankedText.add("§7" + rank + "e. §8<Vide>");
            rank++;
        }

        // --- Filter lines based on lineToShow ---
        List<String> finalText = new ArrayList<>();
        finalText.add(fullRankedText.get(0)); // Always add the title

        if (lineToShow > 0 && fullRankedText.size() > 1) { // Check if there are ranked lines available
            // lineToShow = 1 means show rank 8 (index 8 in fullRankedText if 0-based index, assuming title is 0)
            // lineToShow = 2 means show rank 7 (index 7) and rank 8 (index 8)
            // lineToShow = 8 means show rank 1 (index 1) to rank 8 (index 8)

            // Calculate the starting rank index to show (1-based rank)
            // Example: lineToShow=1 -> startRank=8; lineToShow=8 -> startRank=1
            int startRankToShow = 9 - lineToShow; // Rank number (1 to 8)

            // Calculate the corresponding index in `fullRankedText`
            // Index for Rank 1 is 1, Rank 8 is 8 (assuming title is at index 0)
            int startIndexInList = Math.max(1, startRankToShow); // Ensure we don't go below index 1

            // Add lines from the calculated start index up to rank 8 (index 8)
            // Ensure we don't go out of bounds of the generated list
            for (int i = startIndexInList; i < fullRankedText.size() && i <= 8; i++) {
                finalText.add(fullRankedText.get(i));
            }
        }

        // --- Update the holograms ---
        ((TextHologramData) hologramData1).setText(finalText);
        ((TextHologramData) hologramData2).setText(finalText);
    }
}