package me.bouwen.staffassessments.commands.command.general;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {
    public static void execute(Player player) {
        player.sendMessage(ChatColor.RED + " " + ChatColor.STRIKETHROUGH + "------------------------------");
        player.sendMessage(ChatColor.DARK_RED + "Staff Assessments Help");
        player.sendMessage(" ");
        player.sendMessage(ChatColor.RED + "/staffassessments help" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Shows this help menu.");
        player.sendMessage(ChatColor.RED + "/staffassessments list" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Get a list of all the staff members.");
        player.sendMessage(ChatColor.RED + "/staffassessments view" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Get a menu of all staff members.");
        player.sendMessage(ChatColor.RED + "/staffassessments add <player>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Add a player to the staff list.");
        player.sendMessage(ChatColor.RED + "/staffassessments remove <player>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Remove a player from the staff list.");
        player.sendMessage(ChatColor.RED + "/staffassessments warn <player> <id> <reason>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Add a warning to a staff member.");
        player.sendMessage(ChatColor.RED + "/staffassessments unwarn <player> <id> <reason>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Remove a warning from a staff member.");
        player.sendMessage(ChatColor.RED + "/staffassessments note <player> <note>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Write a note for a staff member.");
        player.sendMessage(ChatColor.RED + "/staffassessments setrank <player> <rank>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Set a rank of a staff member.");
        player.sendMessage(ChatColor.RED + "/staffassessments info <player>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Get info about a staff member.");
        player.sendMessage(ChatColor.RED + " " + ChatColor.STRIKETHROUGH + "------------------------------");
    }
}
