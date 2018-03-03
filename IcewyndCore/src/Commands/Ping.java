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
			if (sender instanceof Player) {
				if (args.length == 0) {
					int ping = ((CraftPlayer) player).getHandle().ping;
					player.sendMessage("§aPing §7»§f " + ping);
				}
			} else if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(!(target == null)) {
					if (target.isOnline()) {
						int targetPing = ((CraftPlayer) target).getHandle().ping;
						player.sendMessage("§a" + target.getName() + "'s §aPing §7»§f " + targetPing);
					}else if (!target.isOnline()) {
						player.sendMessage("§cThat player is not online!");
					}
				}else {
					player.sendMessage("§cThat player cannot be found or is offline.");
				}
			}
		}
		return false;
	}
}
