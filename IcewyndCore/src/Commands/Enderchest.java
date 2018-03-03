package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enderchest implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("enderchest")) {
			Player player = (Player) sender;
			player.openInventory(player.getEnderChest());
			player.sendMessage("§7Opening Enderchest...");
		}
		return false;
	}
}
