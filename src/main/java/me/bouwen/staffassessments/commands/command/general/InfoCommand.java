package me.bouwen.staffassessments.commands.command.general;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class InfoCommand {
    public static void execute(Player player, OfflinePlayer target) {
        Staff staff = StaffTeam.getStaffMember(target.getUniqueId());
        if (staff == null) {
            player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is not a " + ChatColor.RED + "staff member.");
            return;
        }

        player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is a " + ChatColor.RED + "staff member.");
        player.sendMessage(ChatColor.RED + "Warns: " + ChatColor.WHITE + staff.getWarnCount());

        if (staff.getWarnedBy1() != null) {
            OfflinePlayer warner = Bukkit.getOfflinePlayer(staff.getWarnedBy1());
            player.sendMessage(ChatColor.RED + "Warn Reason (1): " + ChatColor.WHITE + staff.getReason1());
            player.sendMessage(ChatColor.RED + "Warned by (1): " + ChatColor.WHITE + warner.getName());
        }

        if (staff.getWarnedBy2() != null) {
            OfflinePlayer warner = Bukkit.getOfflinePlayer(staff.getWarnedBy2());
            player.sendMessage(ChatColor.RED + "Warn Reason (2): " + ChatColor.WHITE + staff.getReason2());
            player.sendMessage(ChatColor.RED + "Warned by (2): " + ChatColor.WHITE + warner.getName());
        }

        if (staff.getWarnedBy3() != null) {
            OfflinePlayer warner = Bukkit.getOfflinePlayer(staff.getWarnedBy3());
            player.sendMessage(ChatColor.RED + "Warn Reason (3): " + ChatColor.WHITE + staff.getReason3());
            player.sendMessage(ChatColor.RED + "Warned by (3): " + ChatColor.WHITE + warner.getName());
        }
    }
}
