package me.bouwen.staffassessments;

import me.bouwen.staffassessments.commands.StaffAssessments;
import me.bouwen.staffassessments.events.GUIEvents;
import me.bouwen.staffassessments.yaml.StaffData;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Staff Assessments enabled!");

        instance = this;
        StaffData staffData = new StaffData();
        staffData.getStaff();

        // Register commands
        this.getCommand("staffassessments").setExecutor(new StaffAssessments(this.getConfig()));

        // Register events
        getServer().getPluginManager().registerEvents(new GUIEvents(), this);
    }
}
