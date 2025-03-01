package fr.citedesiles.plugincite.objects;

public class CDIObjectif {
    String team;
    String name;
    long value;

    public CDIObjectif(String team, String name, long value) {
        this.team = team;
        this.name = name;
        this.value = value;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void addValue(long value) {
        this.value += value;
    }

    public void removeValue(long value) {
        this.value -= value;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setName(String name) {
        this.name = name;
    }
}
