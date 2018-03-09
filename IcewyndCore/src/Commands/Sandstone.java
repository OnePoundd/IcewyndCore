package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.SandstoneBiome;

public class Sandstone implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("sandstone")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (player.hasPermission("icewynd.admin")) {
				SandstoneBiome.convert(player);
			} else {
				player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
			}
		}
		return false;
	}
	
}
