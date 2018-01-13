package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Main.Main;

public class Rules implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("rules")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("§8§l§m--------§8§l[§a§l Rules §8§l]§8§l§m--------");
				player.sendMessage("§eClick here for more indepth rules.");
				player.sendMessage("§a[1]§f Do not spam chat excessively.");
				player.sendMessage("§a[2]§f Do not be excessively racist in chat.");
				player.sendMessage("§a[3]§f Do not run scripts, macros or clients to change gameplay.");
				player.sendMessage("§a[4]§f Do not discuss DDoSing or Doxing.");
				int coinbalance = plugin.getConfig().getInt(player.getUniqueId() + ".Coins");
				plugin.getConfig().set(player.getUniqueId() + ".Coins", coinbalance + 1);
				plugin.saveConfig();
			}
		}
		return false;
	}
}