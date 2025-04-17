package fr.citedesiles.plugincite.npcs;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NPCs {
    private final UUID uuid = UUID.randomUUID();

    public Npc confiseur() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 94.5, 129, -330.5, 180, 0);
        NpcData npcData = new NpcData("cdi-confiseur", uuid, location);
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/4570153c5b1ad58a.png");
        npcData.setDisplayName("§6Confiseur");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc repair() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 60.5, 91, -291.5, -30, 0);
        NpcData npcData = new NpcData("cdi-repair", uuid, location);
        npcData.setDisplayName("§6Reconstructeur de la Tour");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/0c2348c046c49671.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc upgrade() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 118.5, 139, -339.5, 90, 0);
        NpcData npcData = new NpcData("cdi-upgrade", uuid, location);
        npcData.setDisplayName("§6Améliorateur de l'équipe");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/afc2115762db2441.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc changeServer() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 163.5, 64, -259.5, -60, 0);
        NpcData npcData = new NpcData("cdi-change-server", uuid, location);
        npcData.setDisplayName("§6Changer de serveur");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/35e963bd95420336.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc mineur() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 99.5, 128, -366.5, 0, 0);
        NpcData npcData = new NpcData("cdi-mineur", uuid, location);
        npcData.setDisplayName("§6Mineur");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/bef08f02f7e42ed9.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc coppernic() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 76.5, 163, -366.5, -37, 0);
        NpcData npcData = new NpcData("cdi-coppernic", uuid, location);
        npcData.setDisplayName("§6Coppernic");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/def4980cfd8ee8ce.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc sakura() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 68.5, 167, -338.5, -90, 0);
        NpcData npcData = new NpcData("cdi-sakura", uuid, location);
        npcData.setDisplayName("§6Sakura");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/2a2681bd0f946718.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc gefroid() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 107.5, 163, -369.5, 50, 0);
        NpcData npcData = new NpcData("cdi-gefroid", uuid, location);
        npcData.setDisplayName("§6Géfroid");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/a2df55c83fd17c87.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc fechaud() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 110.5, 213.5, -347.5, -90, 0);
        NpcData npcData = new NpcData("cdi-fechaud", uuid, location);
        npcData.setDisplayName("§6Jean-Féchaud");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/a00f58b1c85510aa.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc warden() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 62.5, 217.5, -345.5, -90, 0);
        NpcData npcData = new NpcData("cdi-warden", uuid, location);
        npcData.setDisplayName("§6Fan2Warden");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/70a98fe257a17a78.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc enderitefox() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 90.5, 213, -377.5, 0, 0);
        NpcData npcData = new NpcData("cdi-enderitefox", uuid, location);
        npcData.setDisplayName("§6EnderiteFox");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        //npcData.setSkin(skin.get());
        npcData.setSkin("https://s.namemc.com/i/9770ad44450a63c5.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc begaydocrime() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 89.5, 241, -352.5, 0, 0);
        NpcData npcData = new NpcData("cdi-begaydocrime", uuid, location);
        npcData.setDisplayName("§6Be Gay Do Crime~");
        npcData.setSkin("https://t.novaskin.me/928a7ea4ae73a2708ecedb40744cae8dbe886b412da83b9990ce22e6577d031b");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc poisson() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 76.5, 246.5, -332.5, 180, 25);
        NpcData npcData = new NpcData("cdi-poisson", uuid, location);
        npcData.setDisplayName("§6Poisson");
        npcData.setSkin("https://s.namemc.com/i/06b2bbac0bf08dc9.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }

    public Npc fildrong() throws ExecutionException, InterruptedException {
        Location location = new Location(Bukkit.getWorld("world"), 104.5, 241, -340.5, 90, 0);
        NpcData npcData = new NpcData("cdi-fildrong", uuid, location);
        npcData.setDisplayName("§6Fildrong");
        //CompletableFuture<SkinFetcher.SkinData> skin = SkinFetcher.fetchSkinByURL("https://minesk.in/3ae2a67a962345c8ba049242ee7fc102");
        npcData.setSkin("https://s.namemc.com/i/e176a160f997bced.png");
        Npc npc = FancyNpcsPlugin.get().getNpcAdapter().apply(npcData);
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        return npc;
    }
}
