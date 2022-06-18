package me.bouwen.staffassessments.commands;

import me.bouwen.staffassessments.commands.managers.PlayerExecutorManager;
import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffAssessments implements CommandExecutor, TabExecutor {

    FileConfiguration config;

    public StaffAssessments(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        PlayerExecutorManager.registerCommand(player, args, config);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return PlayerExecutorManager.getCommands();
        }

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "remove":
                case "info":
                case "setrank":
                case "warn":
                case "removewarn":
                case "setwarn":
                case "removewarning":
                case "unwarn":
                case "note":
                case "setnote": {
                    ArrayList<String> staffMembers = new ArrayList<>();

                    for (Staff staff : StaffTeam.getStaff()) {
                        staffMembers.add(staff.getName());
                    }

                    return staffMembers;
                }
                case "add": {
                    ArrayList<String> players = new ArrayList<>();

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        players.add(p.getName());
                    }

                    return players;
                }
                default: {
                    return new ArrayList<>();
                }
            }
        }

        if (args.length == 3) {
            switch (args[0].toLowerCase()) {
                case "setrank": {
                    ArrayList<String> ranks = new ArrayList<>();

                    ranks.add("Manager");
                    ranks.add("Admin");
                    ranks.add("Moderator");
                    ranks.add("Helper");
                    ranks.add("Builder");

                    return ranks;
                }
                default: {
                    return new ArrayList<>();
                }
            }
        }

        return null;
    }
}
