package Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class SetHome implements CommandExecutor{
	
	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sethome")) {
			if(args.length == 1) {
				Player player = Bukkit.getPlayer(sender.getName());
				int max = 0;
				if (player.hasPermission("icewynd.homes.15")) {
					max = 15;
				} else if (player.hasPermission("icewynd.homes.10")) {
					max = 10;
				} else if (player.hasPermission("icewynd.homes.7")) {
					max = 7;
				} else if (player.hasPermission("icewynd.homes.5")) {
					max = 5;
				} else if (player.hasPermission("icewynd.homes.3")) {
					max = 3;
				} else if (player.hasPermission("icewynd.homes.1")) {
					max = 1;
				}
				int current = plugin.getConfig().getInt(player.getUniqueId() + ".Homes");
				if(current + 1 > max) {
					sender.sendMessage("§c§l(!)§7 You have already set " + max + " homes, get a higher rank or delete old homes to set more!");
					return false;
				}
				
				List<String> homeNames = plugin.getConfig().getStringList(player.getUniqueId() + ".HomeNames");
				if(homeNames != null && homeNames.contains(args[0])) {
					sender.sendMessage("§c§l(!)§7 You already have a home with that name!");
					return false;
				}
				
				if(homeNames == null) {
					homeNames = new ArrayList<String>();
				}
				
				List<Location> homeLocations = (List<Location>) plugin.getConfig().getList(player.getUniqueId() + ".HomeLocations");
				if(homeLocations == null) {
					homeLocations = new ArrayList<Location>();
				}
				homeLocations.add(player.getLocation());
				homeNames.add(args[0]);
				plugin.getConfig().set(player.getUniqueId() + ".HomeLocations", homeLocations);
				plugin.getConfig().set(player.getUniqueId() + ".HomeNames", homeNames);
				plugin.getConfig().set(player.getUniqueId() + ".Homes", current + 1);
				plugin.saveConfig();
				player.sendMessage("§a§l(!)§7 The home has been set successfully!");
			}else {
				sender.sendMessage("§c§l(!)§7 You must specify a name! Usage: /sethome <name>");
			}
		}
		return false;
	}

}
