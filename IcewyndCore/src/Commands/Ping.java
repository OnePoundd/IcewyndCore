package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import Main.Main;

public class Ping implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				int ping = ((CraftPlayer) p).getHandle().ping;
				p.sendMessage("§aPing §7»§f " + ping);
			}
		}
		return false;
	}
}