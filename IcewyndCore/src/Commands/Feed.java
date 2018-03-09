package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("feed")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("icewynd.feed")) {
					player.setFoodLevel(20);
					player.sendMessage("§7Your hunger has been replenished.");
				}
			}
		}
		return false;
	}
}
