package Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class Freecam implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (cmd.getName().equalsIgnoreCase("freecam")) {
		Player player = (Player) sender;
		if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Freecam") == true) {
			plugin.getConfig().set(player.getUniqueId() + ".Freecam", false);
			player.sendMessage("§e§l(!)§7 Freecam has been disabled!");
			player.setGameMode(GameMode.SURVIVAL);
		}else {
			plugin.getConfig().set(player.getUniqueId() + ".Freecam", true);
			player.sendMessage("§e§l(!)§7 Freecam has been enabled!");
			player.setGameMode(GameMode.SPECTATOR);
		}
	}
	return false;
}
}
