package Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import Main.Main;

public class Seen implements CommandExecutor, Listener{
	Main plugin = Main.getPlugin(Main.class);


	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("seen")) {
			Player player = (Player) sender;
			if (sender instanceof Player) {
				if (args.length == 1) {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					if(!(target == null)) {
						if (target.isOnline()) {
							player.sendMessage("That player is online");
						}else if (!target.isOnline()) {
							long offline = plugin.getConfig().getLong(target.getUniqueId() + ".Seen");
							long time = (System.currentTimeMillis() - offline);
							int days = (int) time / (1000*60*60*24);
							int hours = (int) (time - (days*1000*60*60*24)) / (1000*60*60);
							int minutes = (int) (time - (days*1000*60*60*24) - (hours*1000*60*60)) / (1000*60);
							int seconds = (int) (time - (days*1000*60*60*24) - (hours*1000*60*60) - (minutes*1000*60)) / 1000;

							player.sendMessage("§b" + target.getName() + "§e has been offline for §b" + days + "§e days §b" + minutes + "§e minutes and §b" + seconds + "§e seconds");
						}
					}else {
						player.sendMessage("§cThat player cannot be found.");
					}
				}
			}
		}
		return false;
	}
}
		plugin.saveConfig();
	}
}
