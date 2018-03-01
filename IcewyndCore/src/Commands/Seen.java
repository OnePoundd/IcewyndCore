package Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
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
							long seconds = time / 1000;
							long minutes = seconds / 60;
							long hours = minutes / 60;
							long days = hours / 24; 
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

	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		plugin.getConfig().set(player.getUniqueId() + ".Seen", System.currentTimeMillis());
		plugin.saveConfig();
	}
}