package BanSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class UnbanCommand implements CommandExecutor {
	Main plugin = Main.getPlugin(Main.class);

	//Banning a player
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player BannedPlayer = args[0];
		if (cmd.getName().equalsIgnoreCase("unban")) {
			if (args.length == 1) {
				Bukkit.broadcastMessage("§c§l[Ban] §a" + args[0] + " has been unbanned! ");
				plugin.getConfig().set(BannedPlayer.getUniqueId() + ".Banned", false);
			}
			
		}
		return false;
	}
}