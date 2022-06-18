package me.bouwen.staffassessments.staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class StaffTeam {

    private static final ArrayList<Staff> staff = new ArrayList<>();

    private static final HashMap<UUID, String> reasonBuilder = new HashMap<>();

    public static boolean isBuildingReason(UUID uuid) {
        return reasonBuilder.containsKey(uuid);
    }

    public static String getReasonBuilder(UUID uuid) {
        return reasonBuilder.get(uuid);
    }

    public static void setReasonBuilder(UUID uuid, String reason) {
        reasonBuilder.put(uuid, reason);
    }

    public static void removeReasonBuilder(UUID uuid) {
        reasonBuilder.remove(uuid);
    }

    public static ArrayList<Staff> getStaff() {
        return staff;
    }

    public static void addStaffMember(Staff staff) {
        getStaff().add(staff);
    }

    public static Staff getStaffMember(UUID uuid) {
        for (Staff staff : getStaff()) {
            if (staff.getUuid().equals(uuid)) {
                return staff;
            }
        }
        return null;
    }

    public static Staff getStaffMember(String name) {
        for (Staff staff : getStaff()) {
            if (staff.getName().equals(name)) {
                return staff;
            }
        }
        return null;
    }

    public static void removeStaff(Staff staff) {
        getStaff().remove(staff);
    }

    public boolean isStaff(UUID uuid) {
        for (Staff staff : getStaff()) {
            if (staff.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

}
