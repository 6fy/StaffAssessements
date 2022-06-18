package me.bouwen.staffassessments.commands.command.general;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class ViewCommand {
    public static void execute(Player player) {
        if (StaffTeam.getStaff().size() == 0) {
            player.sendMessage(ChatColor.RED + "There are no staff members.");
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, 9 * 5, ChatColor.RED + "Staff Members");

        int i = 0;
        for (Staff staff : StaffTeam.getStaff()) {

            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);

            SkullMeta meta = (SkullMeta) item.getItemMeta();
            assert meta != null;

            OfflinePlayer p = Bukkit.getOfflinePlayer(staff.getUuid());
            meta.setOwningPlayer(p);

            meta.setDisplayName(ChatColor.RED + staff.getName());

            meta.setLore(new ArrayList<String>() {{
                add(ChatColor.GRAY + "Rank: " + ChatColor.RED + staff.getRank());
                add(ChatColor.GRAY + "Warnings: " + ChatColor.RED + staff.getWarnCount());
                add(ChatColor.GRAY + "Note: " + ChatColor.RED + staff.getNote());
            }});

            item.setItemMeta(meta);
            inventory.setItem(i, item);

            i++;
        }

        player.openInventory(inventory);
    }
}
