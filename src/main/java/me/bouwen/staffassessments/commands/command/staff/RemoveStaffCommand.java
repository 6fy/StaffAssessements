package me.bouwen.staffassessments.commands.command.staff;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import me.bouwen.staffassessments.yaml.StaffData;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class RemoveStaffCommand {
    public static void execute(Player player, OfflinePlayer target) {
        Staff staff = StaffTeam.getStaffMember(target.getUniqueId());
        if (staff == null) {
            player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is not a " + ChatColor.RED + "staff member.");
            return;
        }

        StaffTeam.removeStaff(staff);
        writeToFile(staff);

        player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is no longer a " + ChatColor.RED + "staff member.");
    }

    private static void writeToFile(Staff staff) {
        StaffData staffData = new StaffData();
        staffData.removeStaff(staff.getUuid());
    }
}
