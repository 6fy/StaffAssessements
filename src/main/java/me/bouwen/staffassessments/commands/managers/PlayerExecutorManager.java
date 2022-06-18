package me.bouwen.staffassessments.commands.managers;

import me.bouwen.staffassessments.commands.command.general.HelpCommand;
import me.bouwen.staffassessments.commands.command.general.InfoCommand;
import me.bouwen.staffassessments.commands.command.general.ListCommand;
import me.bouwen.staffassessments.commands.command.general.ViewCommand;
import me.bouwen.staffassessments.commands.command.staff.*;
import me.bouwen.staffassessments.commands.command.warns.RemoveWarningCommand;
import me.bouwen.staffassessments.commands.command.warns.WarnStaffCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerExecutorManager {

    public static void registerCommand(Player player, String[] args, FileConfiguration config) {
        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "You are not allowed to use this command.");
            return;
        }

        if (args.length == 0) {
            ViewCommand.execute(player);
            return;
        }

        switch(args[0].toLowerCase()) {
            case "help": {
                HelpCommand.execute(player);
                break;
            }
            case "list": {
                ListCommand.execute(player);
                break;
            }
            case "remove": {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member to remove.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                RemoveStaffCommand.execute(player, target);
                break;
            }
            case "reason": {
                if (args.length <= 2) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member and a reason.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                String reason = "";
                for (int i = 2; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                ReasonCommand.execute(player, target, reason);
                break;
            }
            case "add": {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member to add and their rank.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                AddStaffCommand.execute(player, target, args[2]);
                break;
            }
            case "view": {
                ViewCommand.execute(player);
                break;
            }
            case "info": {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member to get info.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                InfoCommand.execute(player, target);
                break;
            }
            case "setwarn":
            case "warn": {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member and the warn reason.");
                    return;
                }

                int id = 0;
                try {
                    id = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "You need to specify a valid warn id.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                String reason = "";
                for (int i = 3; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                WarnStaffCommand.execute(player, target, reason, id, config);
                break;
            }
            case "removewarn":
            case "removewarning":
            case "unwarn": {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member and the warn id.");
                    return;
                }

                int id;
                try {
                    id = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "You need to specify a valid warn id.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                RemoveWarningCommand.execute(player, target, id, config);
                break;
            }
            case "note":
            case "setnote": {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member and their new note.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                String reason = "";
                for (int i = 2; i < args.length; i++) {
                    reason += args[i] + " ";
                }

                NoteCommand.execute(player, target, reason);
                break;
            }
            case "setrank": {
                if (args.length < 3) {
                    player.sendMessage(ChatColor.RED + "You need to specify a staff member and their new rank.");
                    return;
                }

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                SetRankCommand.execute(player, target, args[2]);
                break;
            }
            default: {
                ArrayList<String> matches = getClosesMatches(args[0]);
                player.sendMessage(ChatColor.RED + "Unknown command: " + args[0] + "!" + (matches.size() > 0 ? " Did you mean: " + String.join(", ", matches) + "?": ""));
                break;
            }
        }
    }

    public static ArrayList<String> getCommands() {
        ArrayList<String> commands = new ArrayList<>();

        commands.add("help");
        commands.add("list");
        commands.add("remove");
        commands.add("add");
        commands.add("view");
        commands.add("info");
        commands.add("setrank");
        commands.add("setwarn");
        commands.add("note");
        commands.add("warn");
        commands.add("removewarn");

        return commands;
    }

    private static ArrayList<String> getClosesMatches(String argument) {
        ArrayList<String> commands = getCommands();

        ArrayList<String> matches = new ArrayList<>();

        argument = argument.toLowerCase();
        for (String command : commands) {

            ArrayList<Character> chars = new ArrayList<>();
            for (char letter : command.toCharArray()) {

                letter = Character.toLowerCase(letter);
                if (argument.contains(String.valueOf(letter))) {
                    if (!chars.contains(letter)) {
                        chars.add(letter);
                    }
                }

            }

            if (chars.size() >= 2) {
                matches.add(command);
            }
        }

        return matches;
    }
}
