package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Disposal implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("disposal")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Inventory Disposal = Bukkit.createInventory(null, 27, "§cDeposit items here to be deleted..");
				player.openInventory(Disposal);
			}
		}
		return false;
	}
}
