package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import Main.Main;

public class ClearChat implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {
			for (int i = 0; i < 150; i++) {
				Bukkit.broadcastMessage("§1");
			}
			Bukkit.broadcastMessage("§f§l[§b§lICEWYND§f§l]§a§l Chat has been cleared!");
			}
		return false;
	}
}