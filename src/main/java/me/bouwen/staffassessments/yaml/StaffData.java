package me.bouwen.staffassessments.yaml;

import me.bouwen.staffassessments.Main;
import me.bouwen.staffassessments.staff.Staff;
import me.bouwen.staffassessments.staff.StaffTeam;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class StaffData {

    private File file;
    private FileConfiguration config;

    public StaffData() {
        Main plugin = Main.getInstance();
        this.file = new File(plugin.getDataFolder(), "staff.yml");
        loadFile();
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public File getFile() {
        return this.file;
    }

    public void loadFile() {
        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeStaff(UUID uuid) {
        config.set("staff." + uuid.toString(), null);
        saveConfig();
    }

    public boolean isStaff(UUID uuid) {
        return config.contains("staff." + uuid.toString());
    }

    public void getStaff() {
        if (config.getConfigurationSection("staff") == null) {
            return;
        }

        for (String uuid : config.getConfigurationSection("staff").getKeys(true)) {
            if (uuid == null) continue;
            String path = "staff." + uuid + ".";

            if (!(config.get(path) instanceof ConfigurationSection)) continue;

            String rank = config.getString(path + "rank");
            String note = config.getString(path + "note");

            boolean warning1 = config.getBoolean(path + "warning1");
            String reason1 = config.getString(path + "reason1");
            String warner1 = config.getString(path + "warner1");
            UUID warnedBy1 = null;
            if (warner1 != null) {
                warnedBy1 = UUID.fromString(warner1);
            }

            boolean warning2 = config.getBoolean(path + "warning2");
            String reason2 = config.getString(path + "reason2");
            String warner2 = config.getString(path + "warner2");
            UUID warnedBy2 = null;
            if (warner2 != null) {
                warnedBy2 = UUID.fromString(warner2);
            }

            boolean warning3 = config.getBoolean(path + "warning3");
            String reason3 = config.getString(path + "reason3");
            String warner3 = config.getString(path + "warner3");
            UUID warnedBy3 = null;
            if (warner3 != null) {
                warnedBy3 = UUID.fromString(warner3);
            }

            Staff staff = new Staff(UUID.fromString(uuid), rank, note,
                    warning1, warning2, warning3,
                    warnedBy1, warnedBy2, warnedBy3,
                    reason1, reason2, reason3
            );
            StaffTeam.addStaffMember(staff);
        }
    }

    public void updateStaff(Staff staff) {
        config.set("staff." + staff.getUuid() + ".uuid", staff.getUuid().toString());
        config.set("staff." + staff.getUuid() + ".rank", staff.getRank());
        config.set("staff." + staff.getUuid() + ".note", staff.getNote());

        config.set("staff." + staff.getUuid() + ".warning1", staff.isWarned1());
        config.set("staff." + staff.getUuid() + ".reason1", staff.getReason1());
        if (staff.getWarnedBy1() != null) {
            config.set("staff." + staff.getUuid() + ".warner1", staff.getWarnedBy1().toString());
        } else {
            config.set("staff." + staff.getUuid() + ".warner1", null);
        }

        config.set("staff." + staff.getUuid() + ".warning2", staff.isWarned2());
        config.set("staff." + staff.getUuid() + ".reason2", staff.getReason2());
        if (staff.getWarnedBy2() != null) {
            config.set("staff." + staff.getUuid() + ".warner2", staff.getWarnedBy2().toString());
        } else {
            config.set("staff." + staff.getUuid() + ".warner2", null);
        }

        config.set("staff." + staff.getUuid() + ".warning3", staff.isWarned3());
        config.set("staff." + staff.getUuid() + ".reason3", staff.getReason3());
        if (staff.getWarnedBy3() != null) {
            config.set("staff." + staff.getUuid() + ".warner3", staff.getWarnedBy3().toString());
        } else {
            config.set("staff." + staff.getUuid() + ".warner3", null);
        }

        saveConfig();
    }

}
