package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Experience implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("xp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			player.sendMessage("§b§l(!)§7 You have §8" + player.getTotalExperience() + " §7experience!");
		} else if (label.equalsIgnoreCase("xpgive")) {
			if (sender instanceof Player) {
				Player player = Bukkit.getPlayer(sender.getName());
				if (player.hasPermission("icewynd.admin")) {
					if (args.length == 2) {
						try {
							Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
							player.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
						} catch (Exception e) {
							player.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
						}
					} else {
						player.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
					}
				} else {
					player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
				}
			} else {
				if (args.length == 2) {
					try {
						Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
						sender.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
				}
			}
		}
		return true;
	}

}
