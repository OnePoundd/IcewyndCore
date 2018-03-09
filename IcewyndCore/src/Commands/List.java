package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import Main.Main;

public class List implements CommandExecutor, Listener{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("list")) {
			if (sender instanceof Player) {
				String admins = "";
				String chatmods = "";
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(player.hasPermission("icewynd.admin")) {
						admins = admins + player.getName() + ", ";
					}else if(player.hasPermission("icewynd.chatmod")) {
						chatmods = chatmods + player.getName() + ", ";
					}
				}
				
				sender.sendMessage("§f§l§m--------------------------------------");
				if(admins != "") {
					sender.sendMessage("§bOnline Admins: §f" + admins.substring(0, admins.length()-2) + ".");
				}
				if(chatmods != "") {
					sender.sendMessage("§bOnline Chat Mods: §f" + chatmods.substring(0, chatmods.length()-2) + ".");
				}
				sender.sendMessage("§bPlayers Online: §f" + plugin.getConfig().getInt(".PlayersOnline"));
				sender.sendMessage("§f§l§m--------------------------------------");
			}
		}
		return false;
	}
}