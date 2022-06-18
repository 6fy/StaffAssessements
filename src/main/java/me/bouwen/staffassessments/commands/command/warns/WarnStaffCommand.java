package me.bouwen.staffassessments.commands.command.warns;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import me.bouwen.staffassessments.util.DiscordWebhook;
import me.bouwen.staffassessments.yaml.StaffData;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class WarnStaffCommand {
    public static void execute(Player player, OfflinePlayer target, String reason, int id, FileConfiguration config) {
        Staff staff = StaffTeam.getStaffMember(target.getUniqueId());
        if (staff == null) {
            player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is not a " + ChatColor.RED + "staff member.");
            return;
        }

        staff.addWarn(player, reason, id);
        writeToFile(staff);

        sendEmbed(player, target, reason, config);

        player.sendMessage(ChatColor.RED + target.getName() + ChatColor.WHITE + " is now warned for " + ChatColor.RED + reason + ChatColor.WHITE + ".");
    }

    private static void writeToFile(Staff staff) {
        StaffData staffData = new StaffData();
        staffData.updateStaff(staff);
    }

    private static void sendEmbed(Player player, OfflinePlayer target, String reason, FileConfiguration config) {
        if (config.contains("webhook")) {
            String webhookStr = config.getString("webhook");
            if (!Objects.equals(webhookStr, "")) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                DiscordWebhook webhook = new DiscordWebhook(webhookStr);
                webhook.setTts(false);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(player.getName() + " warned " + target.getName())
                        .setColor(Color.RED)
                        .addField("Reason", reason, false)
                        .setFooter("Date: " + dtf.format(now), ""));

                try {
                    webhook.execute();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
