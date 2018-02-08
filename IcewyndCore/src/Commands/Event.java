package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import Main.Main;

public class Event implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("event")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("server.admin")) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("set")) {
							player.sendMessage("Location has been set");
							plugin.getConfig().set(".TimeTraveler", player.getLocation());
						}
					}
				}
			}
		}
		return false;
	}
}