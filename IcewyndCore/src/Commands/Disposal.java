package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import Main.Main;

public class Disposal implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("disposal")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Inventory Disposal = Bukkit.createInventory(null, 27, "Drop items here to be deleted.");
				player.openInventory(Disposal);
			}
		}
		return false;
	}
}