package fr.citedesiles.plugincite.npcs;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCManager {
    Map<String, Npc> npcsList = new HashMap<>();

    public void addNPC(String identifier, Npc npc) {
        npcsList.put(identifier, npc);
    }

    public Npc getNPC(String identifier) {
        return npcsList.get(identifier);
    }

    public void removeAllNPC() {
        for(String identifier : npcsList.keySet()) {
            Npc npc = FancyNpcsPlugin.get().getNpcManager().getNpc(identifier);
            npc.removeForAll();
            FancyNpcsPlugin.get().getNpcManager().removeNpc(npc);
        }

    }

    public List<Npc> getNpcs() {
        return new ArrayList<>(npcsList.values());
    }

    public List<String> getNpcsIdentifiers() {
        return new ArrayList<>(npcsList.keySet());
    }

}
