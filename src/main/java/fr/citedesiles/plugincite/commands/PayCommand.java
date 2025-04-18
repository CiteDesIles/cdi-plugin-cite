package fr.citedesiles.plugincite.commands;

import fr.citedesiles.plugincite.PluginCite;
import fr.citedesiles.plugincite.objects.CDIPlayer;
import fr.citedesiles.plugincite.objects.CDITeam;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(strings.length == 0) {
            commandSender.sendMessage("Usage: /pay <team> <amount>");
            return true;
        }

        String teamName = strings[0];
        if(teamName.equals("orga") || teamName.equals("orgaTeam") || teamName.equals("adminTeam") || teamName.equals("modTeam") || teamName.equals("admin") || teamName.equals("mod")) {
            commandSender.sendMessage("§cVous ne pouvez pas soudoyer le staff.");
            return true;
        }

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            CDITeam cteam = PluginCite.instance().teamManager().getTeam(
                PluginCite.instance().playerManager().get(player).getTeam()
            );
            if(cteam == null) {
                player.sendMessage("§cVous n'avez pas d'équipe.");
                return true;
            }
            if(!player.getUniqueId().equals(cteam.getOwner())) {
                player.sendMessage("§cVous devez être le propriétaire de l'équipe pour utiliser le /pay.");
                return true;
            }

            CDITeam other = PluginCite.instance().teamManager().getTeam(teamName);
            if(other == null) {
                commandSender.sendMessage("§c§lLa team " + teamName + " n'existe pas");
            }

            if(other.getName().equals(cteam.getName())) {
                player.sendMessage("§cVous ne pouvez pas vous soudoyer vous même.");
                return true;
            }

            if(strings.length < 2) {
                commandSender.sendMessage("Usage: /pay <team> <amount>");
                return true;
            }

            long amount = Long.parseLong(strings[1]);

            if(amount < 0) {
                commandSender.sendMessage("§cL'argent ne peut pas être négatif.");
                return true;
            }

            if(cteam.getMoney() < amount) {
                player.sendMessage("§cVous n'avez pas assez d'argent.");
                return true;
            }

            if(other.getMoney() + amount > Long.MAX_VALUE) {
                player.sendMessage("§cL'argent de la team " + other.getName() + " est déjà au maximum.");
                return true;
            }

            cteam.removeMoney(amount);
            other.addMoney(amount);
            player.sendMessage("§fVous avez donné à la team " + other.getName() + " la somme de §e§l" + amount + " Golds");
            for(Player target : Bukkit.getOnlinePlayers()) {
                CDIPlayer CPlayer = PluginCite.instance().playerManager().get(target);
                if(CPlayer.getTeam().equals(other)) {
                    target.sendMessage("§fLa team " + cteam.getName() + " vous a donné la somme de §e§l" + amount + " Golds");
                }
                if(CPlayer.getTeam().equals(cteam)) {
                    target.sendMessage("§fVote équipe a donné à la team " + other.getName() + " la somme de §e§l" + amount + " Golds");
                }
            }

        }

        return true;
    }
}
