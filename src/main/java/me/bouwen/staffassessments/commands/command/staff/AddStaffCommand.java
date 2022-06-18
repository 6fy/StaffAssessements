package me.bouwen.staffassessments.commands.command.staff;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import me.bouwen.staffassessments.yaml.StaffData;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class AddStaffCommand {
    public static void execute(Player player, OfflinePlayer target, String rank) {
        if (StaffTeam.getStaffMember(target.getUniqueId()) != null) {
            player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is already a " + ChatColor.RED + "staff member.");
            return;
        }

        Staff staff = new Staff(target.getUniqueId(), rank);
        StaffTeam.addStaffMember(staff);

        writeToFile(staff);

        player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is now a " + ChatColor.RED + "staff member.");
    }

    private static void writeToFile(Staff staff) {
        StaffData staffData = new StaffData();
        staffData.updateStaff(staff);
    }
}
