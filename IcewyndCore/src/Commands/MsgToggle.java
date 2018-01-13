package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class MsgToggle implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ow")) {
			Player player = (Player) sender;
			if (plugin.getConfig().getString(player.getUniqueId() + ".Coins") == null);
				Bukkit.broadcastMessage("none");
			}else {
				Bukkit.broadcastMessage("found");
			}
		return false;
		}
}
