package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import com.massivecraft.factions.entity.MPlayer;
import Main.Main;

public class PInfo implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pinfo")) {
			if (sender instanceof Player) {
				if (args.length == 2) {
					Player p = (Player) sender;
					MPlayer mplayer = MPlayer.get(p);
					String faction = mplayer.getFactionName();
					int ping = ((CraftPlayer) p).getHandle().ping;
					p.sendMessage("§8§l§m-------§r§8§l[ §a§l" + args[0] + " §r§8§l]§8§l§m-------");
					p.sendMessage("§cIP:§f " + p.getAddress().getHostName());
					p.sendMessage("§cPing:§f " + ping);
					p.sendMessage("§cFirst Seen:§f " + p.getFirstPlayed());
					p.sendMessage("§cLast Seen:§f " + p.getLastPlayed());
					p.sendMessage("§cTimes Seen:§f " + "");
					p.sendMessage("§cFaction:§f " + faction);
					p.sendMessage("§cXP Level:§f " + p.getExpToLevel());
					p.sendMessage("§cGamemode:§f " + p.getGameMode());
				}
			}
		}
		return false;
	}
}
