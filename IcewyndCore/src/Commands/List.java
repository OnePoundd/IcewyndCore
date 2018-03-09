package Commands;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import Main.Main;

public class List implements CommandExecutor, Listener{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("list")) {
			if (sender instanceof Player) {
				int playersonline = plugin.getConfig().getInt(".PlayersOnline");
				sender.sendMessage("§bStaff Online:§7 " + Staff);
				sender.sendMessage("§bPlayers Online:§7 " + playersonline);
			}
		}
		return false;
	}

	ArrayList<Player> Staff = new ArrayList<Player>();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Staff.add(event.getPlayer());
	}
}