package fr.citedesiles.plugincite.objects;

import java.util.ArrayList;
import java.util.List;

public class CDIObjectifManager {
    List<CDIObjectif> objectifs = new ArrayList<>();

    public void addObjectif(CDIObjectif objectif) {
        objectifs.add(objectif);
    }

    public void removeObjectif(CDIObjectif objectif) {
        objectifs.remove(objectif);
    }

    public List<CDIObjectif> getObjectifs() {
        return objectifs;
    }

    public CDIObjectif getObjectif(String team, String name) {
        for (CDIObjectif objectif : objectifs) {
            if (objectif.getTeam().equals(team) && objectif.getName().equals(name)) {
                return objectif;
            }
        }
        return null;
    }
}
