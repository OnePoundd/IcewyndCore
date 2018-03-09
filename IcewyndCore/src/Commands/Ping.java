package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class Ping implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				int ping = ((CraftPlayer) player).getHandle().ping;
				player.sendMessage("§e§l(!)§7 " + player.getName() + "'s §aPing §7»§f " + ping);
			}else if (args.length == 1) {
				try {
					Player target = Bukkit.getPlayer(args[0]);
					int targetPing = ((CraftPlayer) target).getHandle().ping;
					player.sendMessage("§e§l(!)§7 " + target.getName() + "'s §aPing §7»§f " + targetPing);
				}catch(Exception e) {
					player.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			}else {
				player.sendMessage("§c§l(!)§7 Usage: /ping or /ping <player>!");
			}
		}
		return false;
	}
}
