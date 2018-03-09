package BanSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import Main.Main;

public class EscapeCommand implements Listener, CommandExecutor{
	
	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("escape")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if(plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned")) {
				if(Main.econ.getBalance(player) >= 2000000) {
					Bukkit.broadcastMessage("§c§l[Ban] §a" + args[0] + " has grinded 2 million dollars worth of sugarcane and unbanned themselves! ");
					plugin.getConfig().set(player.getUniqueId() + ".Banned", false);
					player.teleport(Bukkit.getWorld("world").getSpawnLocation());
					player.getInventory().setContents(((Inventory) plugin.getConfig().get(player.getUniqueId() + ".InventoryWhenBanned")).getContents());
					plugin.saveConfig();
					Main.econ.withdrawPlayer(player, Main.econ.getBalance(player));
					Main.econ.depositPlayer(player, plugin.getConfig().getDouble(player.getUniqueId() + ".BalanceWhenBanned"));
				}else {
					sender.sendMessage("§c§l(!)§7 You must have $2,000,000 to perform this command!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 You must be banned in order to use that command!");
			}
		}
		return false;
	}
}
