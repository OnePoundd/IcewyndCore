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

public class Tpa implements CommandExecutor{
	
	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpa")) {
			if(args.length == 1) {
				try {					
					MPlayer playerTo = MPlayer.get(Bukkit.getPlayer(args[0]));
					MPlayer playerFrom = MPlayer.get(Bukkit.getPlayer(sender.getName()));
					
					if(plugin.getConfig().getBoolean(playerTo.getPlayer().getUniqueId() + ".BlockingTeleport")) {
						Rel rel = playerTo.getRelationTo(playerFrom);
						if(!rel.equals(Rel.ALLY) || !rel.equals(Rel.TRUCE) || !playerTo.getFaction().equals(playerFrom.getFaction())) {
							sender.sendMessage("§c§l(!)§7 That player is not accepting teleport requests!");
							return false;					
						}
					}
					new FancyMessage().text("§6§l(!) " + playerTo.getColorTo(playerFrom) + sender.getName()
					+ " §7has requested to teleport to you. You have 20 seconds to click this message or type /tpyes!")
					.color(ChatColor.GRAY).command("/tpaccept " + playerFrom.getName())
					.send(playerTo.getPlayer());
					plugin.getConfig().set(playerTo.getUuid() + ".LatestRequest", playerFrom.getPlayer().getUniqueId());
					plugin.getConfig().set(playerTo.getUuid() + ".LatestRequestType", "tpa");
					plugin.getConfig().set(playerFrom.getUuid()+ ".LatestRequestSent", System.currentTimeMillis());
					plugin.getConfig().set(playerFrom.getUuid() + ".LatestRequestSentTo", playerTo.getUuid());
					plugin.saveConfig();
				}catch(Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 You must specify a player! Usage: /tpa <player>");
			}
		}
		return false;
	}
}
