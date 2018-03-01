package Commands;

import org.bukkit.Bukkit;
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


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("seen")) {
			Player player = (Player) sender;
			if (sender instanceof Player) {
				if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if(!(target == null)) {
						if (target.isOnline()) {
							long offline = plugin.getConfig().getLong(target.getUniqueId() + ".Seen");
							//Ignore long time = (System.currentTimeMillis() - offline);
							player.sendMessage("§a" + target.getName() + " has been offline for " + offline);
						}else if (!target.isOnline()) {
							player.sendMessage("§cThat player is not online!");
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
	}
}