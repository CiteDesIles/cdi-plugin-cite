package fr.citedesiles.plugincite.objects;

import java.util.List;
import java.util.UUID;

public class CDITeam {
    private String name;
    private String displayName;
    private UUID owner;
    private List<UUID> members;
    private long money;
    private int supportPoints;
    private int slots;

    public CDITeam(String name, String displayName, UUID owner, List<UUID> members, long money, int supportPoints, int slots) {
        this.name = name;
        this.displayName = displayName;
        this.owner = owner;
        this.members = members;
        this.money = money;
        this.supportPoints = supportPoints;
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UUID getOwner() {
        return owner;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public long getMoney() {
        return money;
    }

    public int getSupportPoints() {
        return supportPoints;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void setMembers(List<UUID> members) {
        this.members = members;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setSupportPoints(int supportPoints) {
        this.supportPoints = supportPoints;
    }

    public void addMoney(long money) {
        this.money += money;
    }

    public void removeMoney(long money) {
        this.money -= money;
    }

    public void addSupportPoints(int supportPoints) {
        this.supportPoints += supportPoints;
    }

    public void removeSupportPoints(int supportPoints) {
        this.supportPoints -= supportPoints;
    }

    public void addMember(UUID member) {
        members.add(member);
    }

    public void removeMember(UUID member) {
        members.remove(member);
    }

    public int getSlots() {
        return slots;
    }
}
