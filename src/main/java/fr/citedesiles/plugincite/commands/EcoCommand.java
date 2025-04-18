package fr.citedesiles.plugincite.commands;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EcoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(commandSender.isOp()) {
            if(strings.length == 0) {
                commandSender.sendMessage("Usage: /eco <add|remove|set|total|removePercentToAll> <team|percent> <amount>");
                return true;
            }
            String action = strings[0];

            if(action.equals("total")) {
                long countMoney = 0;
                for(CDITeam team : PluginCite.instance().teamManager().getTeams()) {
                    if(team.getName().equals("adminTeam")) continue;
                    if(team.getName().equals("modTeam")) continue;
                    countMoney += team.getMoney();
                }
                commandSender.sendMessage("§fArgent Total: §e§l" + countMoney + " Golds");
                return true;
            }

            if(action.equals("removePercentToAll")) {
                if(strings.length < 2) {
                    commandSender.sendMessage("Usage: /eco removePercentToAll <percent>");
                    return true;
                }
                int percent = Integer.parseInt(strings[1]);
                long totalRemovedMoney = 0;
                for(CDITeam team : PluginCite.instance().teamManager().getTeams()) {
                    if(team.getName().equals("adminTeam")) continue;
                    if(team.getName().equals("modTeam")) continue;
                    long money = team.getMoney();
                    long newMoney = money - (money * percent / 100);
                    totalRemovedMoney += money - newMoney;
                    commandSender.sendMessage("§fArgent Retire de " + team.getName() + ": §e§l" + (money - newMoney) + " Golds");
                    sendMessageToAllPlayerOfATeam(team.getName(), "§c" + percent + "% de l'argent de votre équipe a été retiré. (" + (money - newMoney) + " Golds)");
                    team.setMoney(newMoney);
                }
                commandSender.sendMessage("§fArgent Total Retire: §e§l" + totalRemovedMoney + " Golds, §fcar " + percent + "% de chaque team a été retiré.");
                return true;
            }


            String steam = strings[1];
            int amount = Integer.parseInt(strings[2]);
            if(amount < 0) {
                commandSender.sendMessage("L'argent ne peut pas être négatif.");
                return true;
            }
            CDITeam team = PluginCite.instance().teamManager().getTeam(steam);
            if(team == null) {
                commandSender.sendMessage("La team " + steam + " n'existe pas.");
                return true;
            }

            if(action.equals("add")) {
                team.addMoney(amount);
                commandSender.sendMessage("§fArgent Ajouté à " + team.getName() + ": §e§l" + amount + " Golds");
                sendMessageToAllPlayerOfATeam(team.getName(), "§a" + amount + " Golds ont été ajoutés à l'argent de votre équipe.");
                return true;
            }

            if(action.equals("remove")) {
                team.removeMoney(amount);
                commandSender.sendMessage("§fArgent Retire de " + team.getName() + ": §e§l" + amount + " Golds");
                sendMessageToAllPlayerOfATeam(team.getName(), "§c" + amount + " Golds ont été retirés de l'argent de votre équipe.");
                return true;
            }

            if(action.equals("set")) {
                team.setMoney(amount);
                commandSender.sendMessage("§fArgent Mis à jour de " + team.getName() + ": §e§l" + amount + " Golds");
                sendMessageToAllPlayerOfATeam(team.getName(), "§a" + amount + " Golds est la somme actuel de votre équipe.");
                return true;
            }

            commandSender.sendMessage("Action inconnue: " + action);


        }
        return true;
    }

    public void sendMessageToAllPlayerOfATeam(String teamName, String message) {
        CDITeam team = PluginCite.instance().teamManager().getTeam(teamName);
        if(team == null) return;
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(PluginCite.instance().playerManager().get(player).getTeam() == null) continue;
            if(PluginCite.instance().playerManager().get(player).getTeam().equals(teamName)) {
                player.sendMessage(message);
            }
        }
    }
}
