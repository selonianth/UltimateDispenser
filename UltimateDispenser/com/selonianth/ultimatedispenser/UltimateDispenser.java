package com.selonianth.ultimatedispenser;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class UltimateDispenser extends JavaPlugin {

    public HDConfiguration hdConfig;
    public static final Logger LOG = Logger.getLogger("Minecraft");
    public static final String LOG_PREFIX = "[UltimateDispenser]";
 // Permissions interface
    public PermissionHandler permissionHandler;
    public static final String PERMISSION_ROOT_NAME = "UltimateDispenser";
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

 // Command name
    public static final String COMMAND_NAME = "scratch";
    
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
    
 
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Dispenser Set to Autofill");
        // Parse out the command
        String subcommand = args[0];
        String[] subargs = new String[args.length - 1];
        for(int i = 0; i < subargs.length; i++) {
        subargs[i] = args[i + 1];
          return false;      
          
       }
        
     // Check permission
        if(!this.checkPermission(sender, PERMISSION_ROOT_NAME + "." + subcommand)) {
        sender.sendMessage(ChatColor.RED + "You do not have the necessary permission to run /" + COMMAND_NAME + " " + subcommand);}
        }

	public boolean checkPermission(CommandSender sender1, String permission) {
        	if(sender1 instanceof Player) {
        	if(this.permissionHandler == null) {
        	// No permissions; allow
        	return true;
        	} else {
        	return this.permissionHandler.has((Player)sender1, permission);
        	}
        	} else {
        	// Running from console; always allow
        	return true;
        	}
        	}
	}