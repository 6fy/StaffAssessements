package me.bouwen.staffassessments.staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Staff {

    private final UUID uuid;
    private String rank;
    private String note;

    private boolean warn1;
    private boolean warn2;
    private boolean warn3;

    private UUID warnedBy1;
    private UUID warnedBy2;
    private UUID warnedBy3;

    private String reason1;
    private String reason2;
    private String reason3;

    public Staff(UUID uuid, String rank) {
        this.uuid = uuid;
        this.rank = rank;
        this.note = "";
        this.warn1 = false;
        this.warn2 = false;
        this.warn3 = false;
        this.warnedBy1 = null;
        this.warnedBy2 = null;
        this.warnedBy3 = null;
        this.reason1 = "";
        this.reason2 = "";
        this.reason3 = "";
    }

    public Staff(UUID uuid, String rank, String note, boolean warn1, boolean warn2, boolean warn3, UUID warnedBy1, UUID warnedBy2, UUID warnedBy3,
                 String reason1, String reason2, String reason3) {
        this.uuid = uuid;
        this.rank = rank;
        this.note = note;
        this.warn1 = warn1;
        this.warn2 = warn2;
        this.warn3 = warn3;
        this.warnedBy1 = warnedBy1;
        this.warnedBy2 = warnedBy2;
        this.warnedBy3 = warnedBy3;
        this.reason1 = reason1;
        this.reason2 = reason2;
        this.reason3 = reason3;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return Bukkit.getOfflinePlayer(getUuid()).getName();
    }

    public int getWarnCount() {
        int count = 0;
        if (warn1) {
            count++;
        }
        if (warn2) {
            count++;
        }
        if (warn3) {
            count++;
        }

        return count;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean removeWarn(Player player, int id) {
        switch (id) {
            case 1: {
                warn1 = false;
                warnedBy1 = null;
                reason1 = "";
                return true;
            }
            case 2: {
                warn2 = false;
                warnedBy2 = null;
                reason2 = "";
                return true;
            }
            case 3: {
                warn3 = false;
                warnedBy3 = null;
                reason3 = "";
                return true;
            }
            default: {
                player.sendMessage(ChatColor.RED + "Invalid warn ID.");
                return false;
            }
        }
    }

    public void setWarn1(Player player, String reason) {
        warn1 = true;
        warnedBy1 = player.getUniqueId();
        reason1 = reason;
    }

    public void setWarn2(Player player, String reason) {
        warn2 = true;
        warnedBy2 = player.getUniqueId();
        reason2 = reason;
    }

    public void setWarn3(Player player, String reason) {
        warn3 = true;
        warnedBy3 = player.getUniqueId();
        reason3 = reason;
    }

    public void addWarn(Player player, String reason, int id) {
        if (id == 1) {
            setWarn1(player, reason);
            return;
        } else if (id == 2) {
            setWarn2(player, reason);
            return;
        } else if (id == 3) {
            setWarn3(player, reason);
            return;
        }

        if (getWarnCount() == 0) {
            setWarn1(player, reason);
        } else if (getWarnCount() == 1) {
            setWarn2(player, reason);
        } else if (getWarnCount() == 2) {
            setWarn3(player, reason);
        } else {
            player.sendMessage(ChatColor.RED + getName() + " has already been warned 3 times.");
        }
    }

    public String getWarnReason(int id) {
        switch (id) {
            case 1: {
                return reason1;
            }
            case 2: {
                return reason2;
            }
            case 3: {
                return reason3;
            }
            default: {
                return "No reason provided.";
            }
        }
    }

    public String getRank() {
        return rank;
    }

    public boolean isWarned1() {
        return warn1;
    }
    public boolean isWarned2() {
        return warn2;
    }
    public boolean isWarned3() {
        return warn3;
    }

    public String getReason1() {
        return reason1;
    }
    public String getReason2() {
        return reason2;
    }
    public String getReason3() {
        return reason3;
    }

    public UUID getWarnedBy1() {
        return warnedBy1;
    }
    public UUID getWarnedBy2() {
        return warnedBy2;
    }
    public UUID getWarnedBy3() {
        return warnedBy3;
    }
}
