package Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;

import Main.Main;
import mkremins.fanciful.FancyMessage;

public class Tpahere implements CommandExecutor{

	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpahere")) {
			if(args.length == 1) {
				try {					
					MPlayer requested = MPlayer.get(Bukkit.getPlayer(args[0]));
					MPlayer requester = MPlayer.get(Bukkit.getPlayer(sender.getName()));
					
					if(plugin.getConfig().getBoolean(requested.getPlayer().getUniqueId() + ".BlockingTeleport")) {
						Rel rel = requested.getRelationTo(requester);
						if(!rel.equals(Rel.ALLY) || !rel.equals(Rel.TRUCE) || !requested.getFaction().equals(requester.getFaction())) {
							sender.sendMessage("§c§l(!)§7 That player is not accepting teleport requests!");
							return false;					
						}
					}
					new FancyMessage().text("§6§l(!) " + requested.getColorTo(requester) + sender.getName()
					+ " §7has requested to teleport to you to them. You have 20 seconds to click this message or type /tpyes!")
					.color(ChatColor.GRAY).command("/tpaccept " + requester.getName())
					.send(requested.getPlayer());
					plugin.getConfig().set(requested.getUuid() + ".LatestRequest", requester.getPlayer().getUniqueId());
					plugin.getConfig().set(requested.getUuid() + ".LatestRequestType", "tpahere");
					plugin.getConfig().set(requester.getUuid() + ".LatestRequestSent", System.currentTimeMillis());
					plugin.getConfig().set(requester.getUuid() + ".LatestRequestSentTo", requested.getUuid());
					plugin.saveConfig();
				}catch(Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 You must specify a player! Usage: /tpahere <player>");
			}
		}
		return false;
	}
	
}
