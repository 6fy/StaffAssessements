package me.bouwen.staffassessments.commands.command.general;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ListCommand {
    public static void execute(Player player) {
        if (StaffTeam.getStaff().size() == 0) {
            player.sendMessage(ChatColor.RED + "There are no staff members.");
            return;
        }

        for (Staff staff : StaffTeam.getStaff()) {
            player.sendMessage(ChatColor.RED + staff.getName() + ChatColor.GRAY + " - " + ChatColor.WHITE + staff.getRank() + ChatColor.GRAY
                    + " " + "(" + staff.getWarnCount() + " warnings)");
        }
    }
}
