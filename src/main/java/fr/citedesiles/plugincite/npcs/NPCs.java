package fr.citedesiles.plugincite.npcs;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NPCs {
    private final UUID uuid = UUID.randomUUID();

    public Npc confiseur() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 0, 90, 0);
        NpcData npcData = new NpcData("cdi-confiseur", uuid, location);
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        //npcData.setSkin(skin.get());
        npcData.setDisplayName("§6Confiseur");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc repair() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 0, 93, 0);
        NpcData npcData = new NpcData("cdi-repair", uuid, location);
        npcData.setDisplayName("§6Reconstructeur de la Tour");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        //npcData.setSkin(skin.get());
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc upgrade() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 0, 96, 0);
        NpcData npcData = new NpcData("cdi-upgrade", uuid, location);
        npcData.setDisplayName("§6Améliorateur de l'équipe");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        //npcData.setSkin(skin.get());
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }
}
