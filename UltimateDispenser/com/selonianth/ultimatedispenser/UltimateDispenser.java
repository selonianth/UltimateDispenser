package com.selonianth.ultimatedispenser;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class UltimateDispenser extends JavaPlugin {

    public HDConfiguration hdConfig;
    public static final Logger LOG = Logger.getLogger("Minecraft");
    public static final String LOG_PREFIX = "[UltimateDispenser]";
    private PermissionHandler permissionHandler;

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
        System.out.println("UltimateDispenser Disabled");
    }

    private void writeConfig() {
        // Save config
        if (!this.hdConfig.write(this)) {
            LOG.warning(LOG_PREFIX + "Failed to save configuration file (version " + this.getDescription().getVersion() + "); continuing anyway...");
        }
    }

    @Override
    public void onEnable() {
        // TODO Auto-generated method stub
        System.out.println("UltimateDispenser Enabled");

        // Initialize Permissions system
        this.setupPermissions();

        LOG.info(LOG_PREFIX + "Version " + this.getDescription().getVersion() + " enabled");
    }

    private void setupPermissions() {
        Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

        if (this.permissionHandler == null) {
            if (permissionsPlugin != null) {
                this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
                LOG.info(LOG_PREFIX + "Hooked into Permissions version " + permissionsPlugin.getDescription().getVersion());
            } else {
                LOG.info(LOG_PREFIX + "Permissions system not detected; allowing all commands");
            }
        }

        // Get a configuration
        this.readConfig();
    }

    private void readConfig() {
        // Set up configuration folder
        this.getDataFolder().mkdirs();
        
        // Read configuration file
        File configFile = new File(this.getDataFolder(), "config.yml");
        this.hdConfig = new HDConfiguration(configFile);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        // TODO Auto-generated method stub
        return false;
    }
}