package Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;

import Main.Main;

public class Freecam implements CommandExecutor, Listener{
	
	static Main plugin = Main.getPlugin(Main.class);
	static Map<Player, Location> playersInFreecam = new HashMap<Player, Location>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("freecam")) {
			Player player = (Player) sender;
			if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Freecam") == false) {
				plugin.getConfig().set(player.getUniqueId() + ".Freecam", true);
				plugin.getConfig().set(player.getUniqueId() + ".FreecamStartLocation", player.getLocation());
				plugin.saveConfig();
				playersInFreecam.put(player, player.getLocation());
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage("§e§l(!)§7 Freecam has been enabled!");
			}else if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Freecam") == true) {
				plugin.getConfig().set(player.getUniqueId() + ".Freecam", false);
				plugin.saveConfig();
				player.teleport((Location) plugin.getConfig().get(player.getUniqueId() + ".FreecamStartLocation"));
				playersInFreecam.remove(player);
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("§e§l(!)§7 Freecam has been disabled!");
			}
		}
		return false;
	}
	
	public static void triggerFreecamCheck() {
		BukkitScheduler scheduler = plugin.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for(Player player : playersInFreecam.keySet()) {
					if(player.getLocation().distance(playersInFreecam.get(player)) >= 100) {
						plugin.getConfig().set(player.getUniqueId() + ".Freecam", false);
						plugin.saveConfig();
						player.teleport((Location) plugin.getConfig().get(player.getUniqueId() + ".FreecamStartLocation"));
						playersInFreecam.remove(player);
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage("§e§l(!)§7 Freecam has been disabled - you past the 100 block limit!");
					}
				}
				plugin.saveConfig();
			}
		}, 0L, 20*5L);
	}
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Freecam") == true) {
			player.teleport((Location) plugin.getConfig().get(player.getUniqueId() + ".FreecamStartLocation"));
			player.setGameMode(GameMode.SURVIVAL);
			plugin.getConfig().set(player.getUniqueId() + ".Freecam", false);
			playersInFreecam.remove(player);
			plugin.getConfig().set(player.getUniqueId() + ".Seen", System.currentTimeMillis());
			int playersonline = plugin.getConfig().getInt(".PlayersOnline");
			plugin.getConfig().set(".PlayersOnline", playersonline - 1);
			plugin.saveConfig();
		}
	}
}
