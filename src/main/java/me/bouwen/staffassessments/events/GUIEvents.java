package me.bouwen.staffassessments.events;

import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GUIEvents implements Listener {

    @EventHandler
    public void onGUIClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Staff Members")) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

            String itemName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());

            Player player = (Player) e.getWhoClicked();
            if (!player.isOp()) return;

            Staff staff = StaffTeam.getStaffMember(itemName);
            if (staff == null) return;

            openStaffMemberWarnsGUI(player, staff.getName());
        }

        if (e.getView().getTitle().contains(ChatColor.RED + "") && e.getView().getTitle().contains("'s Warnings")) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

            String itemName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            if (!e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.RED + "Warning")) {
                e.setCancelled(true);
                return;
            }

            String id = itemName.split(" ")[1].replace(":", "");
            String target = e.getView().getTitle().replace(ChatColor.RED + "", "").replace("'s Warnings", "");

            Player player = (Player) e.getWhoClicked();
            if (!player.isOp()) return;

            if (e.getClick().isLeftClick()) {
                StaffTeam.setReasonBuilder(player.getUniqueId(), target + ":" + id);
                player.sendMessage(ChatColor.RED + "Type /sa reason <reason> in chat to confirm your warning or /sa reason cancel to cancel the warning for " + target);
                player.closeInventory();
            }
            if (e.getClick().isRightClick()) {
                player.performCommand("sa removewarning " + target + " " + id);
                openStaffMemberWarnsGUI(player, target);
            }
        }
    }

    public static void openStaffMemberWarnsGUI(Player player, String name) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 3, ChatColor.RED + name + "'s Warnings");

        Staff staff = StaffTeam.getStaffMember(name);
        if (staff == null) {
            player.sendMessage(ChatColor.RED + "Staff member not found.");
            return;
        }

        // Head
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + staff.getName());
        meta.setLore(new ArrayList<String>() {{
            add(ChatColor.GRAY + "Rank: " + ChatColor.RED + staff.getRank());
            add(ChatColor.GRAY + "Warnings: " + ChatColor.RED + staff.getWarnCount());
            add(ChatColor.GRAY + "Note: " + ChatColor.RED + staff.getNote());
        }});
        item.setItemMeta(meta);
        inventory.setItem(4, item);

        // Item 1
        ItemStack warn1item = new ItemStack(Material.BOOK, 1);
        ItemMeta warn1meta = warn1item.getItemMeta();
        assert warn1meta != null;

        if (staff.getWarnedBy1() != null) {
            OfflinePlayer warned1 = Bukkit.getOfflinePlayer(staff.getWarnedBy1());

            warn1meta.addEnchant(Enchantment.DURABILITY, 1, true);
            warn1meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            warn1meta.setDisplayName(ChatColor.RED + "Warning 1:" + " " + ChatColor.GRAY + "Warned by " + ChatColor.RED + warned1.getName() +
                    " " + ChatColor.GRAY + "warned for" + " " + ChatColor.RED + staff.getReason1());

            ItemStack wItem = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            inventory.setItem(20, wItem);
        } else {
            warn1meta.setDisplayName(ChatColor.RED + "Warning 1:" + " " + ChatColor.GRAY + "Not warned");
            ItemStack wItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            inventory.setItem(20, wItem);
        }

        warn1item.setItemMeta(warn1meta);

        inventory.setItem(11, warn1item);

        // Item 2
        ItemStack warn2item = new ItemStack(Material.BOOK, 1);
        ItemMeta warn2meta = warn2item.getItemMeta();
        assert warn2meta != null;

        if (staff.getWarnedBy2() != null) {
            OfflinePlayer warned2 = Bukkit.getOfflinePlayer(staff.getWarnedBy2());

            warn2meta.addEnchant(Enchantment.DURABILITY, 1, true);
            warn2meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            warn2meta.setDisplayName(ChatColor.RED + "Warning 2:" + " " + ChatColor.GRAY + "Warned by " + ChatColor.RED + warned2.getName() +
                    " " + ChatColor.GRAY + "warned for" + " " + ChatColor.RED + staff.getReason2());

            ItemStack wItem = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            inventory.setItem(22, wItem);
        } else {
            warn2meta.setDisplayName(ChatColor.RED + "Warning 2:" + " " + ChatColor.GRAY + "Not warned");
            ItemStack wItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            inventory.setItem(22, wItem);
        }
        warn2item.setItemMeta(warn2meta);

        inventory.setItem(13, warn2item);

        // Item 1
        ItemStack warn3item = new ItemStack(Material.BOOK, 1);
        ItemMeta warn3meta = warn3item.getItemMeta();
        assert warn3meta != null;

        if (staff.getWarnedBy3() != null) {
            OfflinePlayer warned3 = Bukkit.getOfflinePlayer(staff.getWarnedBy3());

            warn3meta.addEnchant(Enchantment.DURABILITY, 1, true);
            warn3meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            warn3meta.setDisplayName(ChatColor.RED + "Warning 3:" + " " + ChatColor.GRAY + "Warned by " + ChatColor.RED + warned3.getName() +
                    " " + ChatColor.GRAY + "warned for" + " " + ChatColor.RED + staff.getReason3());

            ItemStack wItem = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
            inventory.setItem(24, wItem);
        } else {
            warn3meta.setDisplayName(ChatColor.RED + "Warning 3:" + " " + ChatColor.GRAY + "Not warned");

            ItemStack wItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            inventory.setItem(24, wItem);
        }
        warn3item.setItemMeta(warn3meta);

        inventory.setItem(15, warn3item);

        player.openInventory(inventory);
    }

}
