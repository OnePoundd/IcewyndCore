package Commands;

import org.bukkit.Bukkit;
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
			Player p = (Player) sender;
			if (p.hasPermission("server.admin"));
			if (args.length == 1) {
				MPlayer mplayer = MPlayer.get(p);
				String faction = mplayer.getFactionName();
				Player target = Bukkit.getPlayer(args[0]);
				int targetPing = ((CraftPlayer) target).getHandle().ping;
				p.sendMessage("§8§l§m-------§r§8§l[ §a§l" + args[0] + " §r§8§l]§8§l§m-------");
				p.sendMessage("§cIP:§f ");
				//target.getAddress().getHostName()); (DISABLED FOR NOW, dont remove)
				p.sendMessage("§cPing:§f " + targetPing);
				p.sendMessage("§cFirst Seen:§f " + target.getFirstPlayed());
				p.sendMessage("§cLast Seen:§f " + target.getLastPlayed());
				p.sendMessage("§cFaction:§f " + faction);
				p.sendMessage("§cXP Level:§f " + target.getExpToLevel());
				p.sendMessage("§cServer Level:§f " + MPlayer.get(target).getLevel());
				p.sendMessage("§cGamemode:§f " + target.getGameMode());
			}
		}
		return false;
	}
}
