package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import Main.Main;

public class TpToggle implements CommandExecutor{

	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("tptoggle") || cmd.getLabel().equalsIgnoreCase("toggletp")) {
			Player player = (Player) sender;
			if(plugin.getConfig().getBoolean(player.getUniqueId() + ".BlockingTeleport")) {
				plugin.getConfig().set(player.getUniqueId() + ".BlockingTeleport", false);
				plugin.saveConfig();
				sender.sendMessage("§a§l(!)§7 You will now receive teleport requests!");
			}else {
				plugin.getConfig().set(player.getUniqueId() + ".BlockingTeleport", true);
				plugin.saveConfig();
				sender.sendMessage("§a§l(!)§7 You will no longer receive teleport requests!");
			}
		}
		return false;
	}
	
}
