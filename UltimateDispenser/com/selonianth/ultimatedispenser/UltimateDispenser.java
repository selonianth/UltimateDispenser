package com.selonianth.ultimatedispenser;

import java.io.File;
import java.util.HashSet;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
	public CommandSender sender;
	// Permissions interface
	public PermissionHandler permissionHandler;
	public static final String PERMISSION_ROOT_NAME = "UltimateDispenser";

	@Override
	public void onDisable() {
		// Console message when plugin is disabled.
		System.out.println("[UltimateDispenser] Disabled");
	}

	private void writeConfig() {
		// Save config
		if (!this.hdConfig.write(this)) {
			LOG.warning(LOG_PREFIX
					+ "Failed to save configuration file (version "
					+ this.getDescription().getVersion()
					+ "); continuing anyway...");
		}
	}

	// Command name
	public static final String COMMAND_NAME = "UltimateDispenser";

	@Override
	public void onEnable() {
		// Console Message when plugin is enabled.
		System.out.println("[UltimateDispenser] Enabled");

		// Initialize Permissions system
		this.setupPermissions();

		LOG.info(LOG_PREFIX + "Version " + this.getDescription().getVersion()
				+ " enabled");
	}

	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager()
				.getPlugin("Permissions");

		if (this.permissionHandler == null) {
			if (permissionsPlugin != null) {
				this.permissionHandler = ((Permissions) permissionsPlugin)
						.getHandler();
				LOG.info(LOG_PREFIX + "Hooked into Permissions version "
						+ permissionsPlugin.getDescription().getVersion());
			} else {
				LOG.info(LOG_PREFIX
						+ "Permissions system not detected; allowing all commands");
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

	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
        // What command did we get? Notice use of .equals() for String comparison
        if(commandLabel.equals("udfill")) {
            // Call another method to check the block
            this.lookAtBlock(sender);
            
            // Return true to say we handled this command
            return true;
        } else {
            // Not our command. Log it and continue
            LOG.info(LOG_PREFIX + "Not the /udfill command - ignoring...");
            
            // Return false to say some other plugin can handle this command
            return false;
        }
	}
    /**
* If sender is a Player, check the block they are looking at.
*
* @param sender Sender of the /demo command
*/
    private void lookAtBlock(CommandSender sender) {
        if(sender instanceof Player) {
            LOG.info(LOG_PREFIX + "Player");
            
            // Cast the sender to a Player type, so you can use Player methods
            Player player = (Player)sender;
            
            // Get the block the player is looking at
            // First argument is null because we want only air to be "transparent"
            // Second argument is maximum look distance - blocks over 100 blocks away are discarded
            Block block = player.getTargetBlock(null, 100);
            
            // Check the material of the block
            if(block.getType() == Material.DISPENSER) {
                // This block is a dispenser! Tell the player!
                player.sendMessage(ChatColor.AQUA + "This is a dispenser!");
            } else {
                // This block is not a dispenser.
                player.sendMessage(ChatColor.YELLOW + "This is not a dispenser.");
            }
        } else {
            LOG.info(LOG_PREFIX + "I am not a player.");
        }
    }

	public boolean checkPermission(CommandSender sender1, String permission) {
		if (sender1 instanceof Player) {
			if (this.permissionHandler == null) {
				// No permissions; allow
				return true;
			} else {
				return this.permissionHandler.has((Player) sender1, permission);
			}
		} else {
			// Running from console; always allow
			return true;
		}
	}
}
