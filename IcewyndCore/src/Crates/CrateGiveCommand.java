package Crates;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateGiveCommand implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("crategive")) {
			if (!sender.hasPermission("icewynd.admin")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					if (args[1].equalsIgnoreCase("legendary")) {
						LegendaryCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("exotic")) {
						ExoticCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("event")) {
						EventCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else {
						sender.sendMessage(
								"§c§l(!)§7 Incorrect usage. Try /crate <playername> <legendary/exotic/event>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 Usage: /crategive <playername> <legendary/exotic>");
			}
		}
		return false;
	}

}
