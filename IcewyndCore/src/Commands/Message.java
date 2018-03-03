package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.MPlayer;

import Main.Main;

public class Message implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msg")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Player r = Bukkit.getPlayer(args[0]);
				if(r != null) {
					if (args.length >= 2) {
						if (plugin.getConfig().getBoolean(r.getUniqueId() + ".MsgToggle") == true) { // if the player has messages turned off
							if(sender.hasPermission("server.admin")) { // admins should bypass msgtoggle
								String messageContents = "";
								int i = 1;
								for(String s : args) {
									if(i > 1) {
										messageContents = messageContents + s + " ";
									}
									i++;
								}
								player.sendMessage("§a§lMe" + " §7» " + "§b" + args[0] + "§7:§f " + messageContents);
								r.sendMessage("§b" + player.getName() + " §7» " + "§a§lMe§f" + "§7:§f " + messageContents);
								r.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
							}else if(!MPlayer.get(r).getFaction().equals(MPlayer.get(sender).getFaction())) { // faction members should bypass msgtoggle
								player.sendMessage("§a§lMe" + " §7» " + "§b" + args[0] + "§7:§f " + args[1]);
								r.sendMessage("§b" + player.getName() + " §7» " + "§a§lMe§f" + "§7:§f " + args[1]);
								r.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
							}
						}else { // if the player is allowing messages to be received
							String messageContents = "";
							int i = 1;
							for(String s : args) {
								if(i > 1) {
									messageContents = messageContents + s + " ";
								}
								i++;
							}
							player.sendMessage("§a§lMe" + " §7» " + "§b" + args[0] + "§7:§f " + messageContents);
							r.sendMessage("§b" + player.getName() + " §7» " + "§a§lMe§f" + "§7:§f " + messageContents);
							r.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
						}
					}else {
						player.sendMessage("§c§l(!)§7 Usage: /msg <player> <message>!");
					}
				}else {
					player.sendMessage("§c§l(!)§7 That player is not online!");
				}
			}
		}
		return false;
	}
}