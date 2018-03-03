package BanSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.massivecraft.factions.entity.MConf;
import Main.Main;

public class BanCommand implements CommandExecutor {
	Main plugin = Main.getPlugin(Main.class);

	//Banning a player
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if(args.length == 3){
				if(Bukkit.getPlayer(args[0]) != null) {
					Player BannedPlayer = Bukkit.getPlayer(args[0]);
					if (cmd.getName().equalsIgnoreCase("ban")) {
						if (args.length == 3) {
							Bukkit.broadcastMessage("§c§l[Ban] §7" + args[0] + " has been banned for " + args[1] );
							plugin.getConfig().set(BannedPlayer.getUniqueId() + ".Banned", true);
							BannedPlayer.sendMessage("§c§lATTENTION! §eYou've been banned for " + args[2] + ". §eBans are handled differently here. Please read the holograms for more information.");
							BannedPlayer.getInventory().clear();
							BannedPlayer.setLevel(0);
							if(MConf.get().getWarp("jail") != null) {
								BannedPlayer.teleport(MConf.get().getWarp("jail"));
							}else {
								System.out.println("[Factions] ERROR: You must set /warp jail.");
							}
						}

					}
				}else {
					sender.sendMessage("§c§l(!)§7 The player cannot be found!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 Usage: /ban <player> <reason> <duration>");
			}
		}
		return false;
	}
}
