package fr.citedesiles.plugincite.objects;

import java.util.UUID;

public class CDIPlayer {
    private UUID uuid;
    private String team;

    public CDIPlayer(UUID uuid, String team) {
        this.uuid = uuid;
        this.team = team;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean hasTeam() {
        return team != null;
    }
}
