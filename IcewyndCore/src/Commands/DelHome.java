package Commands;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class DelHome implements CommandExecutor{

	Main plugin = Main.getPlugin(Main.class);
	
	@SuppressWarnings("unchecked")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("delhome") || cmd.getName().equalsIgnoreCase("deletehome")) {
			if(args.length == 1) {
				Player player = (Player) sender;
				List<String> homeNames = plugin.getConfig().getStringList(player.getUniqueId() + ".HomeNames");
				if(homeNames == null || !homeNames.contains(args[0])) {
					sender.sendMessage("§c§l(!)§7 You do not have a home with that name!");
					return false;
				}
				int index = homeNames.indexOf(args[0]);
				List<Location> homeLocations = (List<Location>) plugin.getConfig().getList(player.getUniqueId() + ".HomeLocations");
				homeLocations.remove(index);
				homeNames.remove(args[0]);
				plugin.getConfig().set(player.getUniqueId() + ".HomeNames", homeNames);
				plugin.getConfig().set(player.getUniqueId() + ".HomeLocations", homeLocations);
				plugin.getConfig().set(player.getUniqueId() + ".Homes", plugin.getConfig().getInt(player.getUniqueId() + ".Homes") - 1);
				plugin.saveConfig();
				player.sendMessage("§a§l(!)§7 The home has been removed!");
			}else {
				sender.sendMessage("§c§l(!)§7 Incorrect usage, try /delhome <name>!");
			}
		}	
		return false;
	}
}
