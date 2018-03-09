package BanSystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.massivecraft.factions.entity.MConf;

import Main.Main;

public class BanCommand implements CommandExecutor, Listener{
	Main plugin = Main.getPlugin(Main.class);

	//Banning a player
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if(args.length == 3){
				if(Bukkit.getPlayer(args[0]) != null) {
					Player BannedPlayer = Bukkit.getPlayer(args[0]);
					Bukkit.broadcastMessage("§c§l[Ban] §7" + args[0] + " has been banned for " + args[1] + ".");
					plugin.getConfig().set(BannedPlayer.getUniqueId() + ".Banned", true);
					plugin.getConfig().set(BannedPlayer.getUniqueId() + ".InventoryWhenBanned", BannedPlayer.getInventory());
					plugin.getConfig().set(BannedPlayer.getUniqueId() + ".BalanceWhenBanned", Main.econ.getBalance(BannedPlayer));
					plugin.getConfig().set(BannedPlayer.getUniqueId() + ".MillisWhenUnbanned", (System.currentTimeMillis() + (Double.valueOf(args[2]) * 86400000)));
					plugin.saveConfig();
					BannedPlayer.sendMessage("§c§lATTENTION! §eYou've been banned for " + args[2] + ". §eBans are handled differently here. Please read the holograms for more information.");
					BannedPlayer.getInventory().clear();
					Main.econ.withdrawPlayer(BannedPlayer, Main.econ.getBalance(BannedPlayer));
					if(MConf.get().getWarp("jail") != null) {
						BannedPlayer.teleport(MConf.get().getWarp("jail"));
					}else {
						System.out.println("[Factions] ERROR: You must set /warp jail.");
					}
				}else {
					sender.sendMessage("§c§l(!)§7 The player cannot be found!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 Usage: /ban <player> <reason> <duration in days>");
			}
		}
		return false;
	}
	
	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		if(plugin.getConfig().getBoolean(event.getPlayer().getUniqueId() + ".Banned")){
			if(!event.getMessage().startsWith("/sell") || event.getMessage().equals("/escape") || event.getMessage().equals("/bal")) {
				event.getPlayer().sendMessage("§c§l(!)§7 The only command you can used whilst banned is /sell!");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if(plugin.getConfig().getBoolean(event.getPlayer().getUniqueId() + ".Banned")){
			event.getPlayer().sendMessage("§c§l(!)§7 You cannot use chat whilst banned!");
		}
	}
}
