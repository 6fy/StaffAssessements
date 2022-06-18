package me.bouwen.staffassessments.staff;

import java.util.ArrayList;
import java.util.UUID;

public class StaffTeam {

    private static final ArrayList<Staff> staff = new ArrayList<>();

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
