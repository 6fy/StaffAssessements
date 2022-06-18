package me.bouwen.staffassessments.commands.command.staff;

import me.bouwen.staffassessments.events.GUIEvents;
import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import me.bouwen.staffassessments.yaml.StaffData;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ReasonCommand {
    public static void execute(Player player, OfflinePlayer target, String reason) {
        Staff staff = StaffTeam.getStaffMember(target.getUniqueId());
        if (staff == null) {
            player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is not a " + ChatColor.RED + "staff member.");
            return;
        }

        if (!StaffTeam.isBuildingReason(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "You are not building a reason yet.");
            return;
        }

        String[] split = StaffTeam.getReasonBuilder(player.getUniqueId()).split(":");
        StaffTeam.removeReasonBuilder(player.getUniqueId());

        if (reason.equalsIgnoreCase("cancel")) {
            player.sendMessage(ChatColor.RED + "Warning cancelled");
            return;
        }

        player.performCommand("sa setwarn " + split[0] + " " + split[1] + " " + reason);
        GUIEvents.openStaffMemberWarnsGUI(player, split[0]);

        writeToFile(staff);

        player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + "'s note is now " + ChatColor.RED + staff.getNote() + ChatColor.WHITE + ".");
    }

    private static void writeToFile(Staff staff) {
        StaffData staffData = new StaffData();
        staffData.updateStaff(staff);
    }
}
