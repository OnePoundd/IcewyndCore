package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("invsee")) {
			Player player = (Player) sender;
			if(player.hasPermission("icewynd.admin")) {
				if (args.length == 1) {
					Player tplayer = player.getServer().getPlayer(args[0]);
					Inventory tinv = tplayer.getInventory();
					player.openInventory(tinv);
				} else if (args.length == 2) {
					if (args[1].equalsIgnoreCase("enderchest"));
					Player tplayer = player.getServer().getPlayer(args[0]);
					Inventory tinv = tplayer.getEnderChest();
					player.openInventory(tinv);
				}
			}
		}
		return false;
	}
}
