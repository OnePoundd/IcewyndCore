package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import Main.Main;

public class ClearInventory implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack[] armorContents = player.getInventory().getArmorContents().clone();
				player.getInventory().clear();
				player.getInventory().setArmorContents(armorContents);
				player.updateInventory();
				player.sendMessage("§eYour inventory has been cleared");
			}
		}
		return false;
	}
}
