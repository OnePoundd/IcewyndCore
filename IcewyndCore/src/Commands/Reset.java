package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Main.Main;

public class Reset implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reset")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				plugin.getConfig().set(".WitherSkeletons", 0);
				plugin.getConfig().set(".WitherInvin", false);
				plugin.getConfig().set(".WitherPhase", 0);
				player.sendMessage("§eReset Successful");
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lagg killmobs");
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sm removeall");
			}
		}
		return false;
	}
}
