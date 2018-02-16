package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Main.Main;

public class Suicide implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("suicide")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.setHealth(0);
				player.sendMessage("§7You ended it all...");
			}
		}
		return false;
	}
}