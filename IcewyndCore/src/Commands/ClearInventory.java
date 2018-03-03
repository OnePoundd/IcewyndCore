package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.getInventory().clear();
				player.getInventory().setArmorContents(player.getInventory().getArmorContents().clone());
				player.updateInventory();
				player.sendMessage("§eYour inventory has been cleared");
			}
		}
		return false;
	}
}
