package Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.massivecraft.factions.entity.MPlayer;

public class PInfo implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pinfo")) {
			Player p = (Player) sender;
			if (p.hasPermission("icewynd.admin")) {
				if (args.length == 1) {
					OfflinePlayer target = Bukkit.getPlayer(args[0]);
					p.sendMessage("§8§l§m-------§r§8§l[ §a§l" + args[0] + " §r§8§l]§8§l§m-------");
					p.sendMessage("§cFirst Seen:§f " + target.getFirstPlayed());
					p.sendMessage("§cLast Seen:§f " + target.getLastPlayed());
					p.sendMessage("§cFaction:§f " + MPlayer.get(target).getFactionName());
					p.sendMessage("§cServer Level:§f " + MPlayer.get(target).getLevel());
					if(Bukkit.getOnlinePlayers().contains(target)) {
						Player onlineTarget = (Player) target;
						p.sendMessage("§cXP Level:§f " + onlineTarget.getExpToLevel());
						p.sendMessage("§cGamemode:§f " + onlineTarget.getGameMode());
						int targetPing = ((CraftPlayer) onlineTarget).getHandle().ping;
						p.sendMessage("§cPing:§f " + targetPing);
						p.sendMessage("§cIP:§f ");
						//target.getAddress().getHostName()); (DISABLED FOR NOW, dont remove)
					}
				}
			}
		}
		return false;
	}
}
