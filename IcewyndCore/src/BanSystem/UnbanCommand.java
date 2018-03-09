package BanSystem;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;

import Main.Main;

public class UnbanCommand implements CommandExecutor {
	static Main plugin = Main.getPlugin(Main.class);

	//Banning a player
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("unban")) {
			if(args.length == 1){
				if(Bukkit.getPlayer(args[0]) != null) {
					Player BannedPlayer = Bukkit.getPlayer(args[0]);
					if (args.length == 1) {
						Bukkit.broadcastMessage("§c§l[Ban] §a" + args[0] + " has been unbanned! ");
						plugin.getConfig().set(BannedPlayer.getUniqueId() + ".Banned", false);
						BannedPlayer.teleport(Bukkit.getWorld("world").getSpawnLocation());
						BannedPlayer.getInventory().setContents(((Inventory) plugin.getConfig().get(BannedPlayer.getUniqueId() + ".InventoryWhenBanned")).getContents());
						plugin.saveConfig();
						Main.econ.withdrawPlayer(BannedPlayer, Main.econ.getBalance(BannedPlayer));
						Main.econ.depositPlayer(BannedPlayer, plugin.getConfig().getDouble(BannedPlayer.getUniqueId() + ".BalanceWhenBanned"));
					}
				}else {
					sender.sendMessage("§c§l(!)§7 The player cannot be found!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 Usage: /unban <player>");
			}
		}
		return false;
	}
	
	public static void triggerUnbanScheduler() {
		BukkitScheduler scheduler = plugin.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned")) {
						if(System.currentTimeMillis() >= plugin.getConfig().getLong(player.getUniqueId() + ".MillisWhenUnbanned")) {
							Bukkit.broadcastMessage("§c§l[Ban] §a" + player.getName() + " has been unbanned! ");
							plugin.getConfig().set(player.getUniqueId() + ".Banned", false);
							player.teleport(Bukkit.getWorld("world").getSpawnLocation());
							player.getInventory().setContents(((Inventory) plugin.getConfig().get(player.getUniqueId() + ".InventoryWhenBanned")).getContents());
							Main.econ.withdrawPlayer(player, Main.econ.getBalance(player));
							Main.econ.depositPlayer(player, plugin.getConfig().getDouble(player.getUniqueId() + ".BalanceWhenBanned"));
						}
					}
				}
				plugin.saveConfig();
			}
		}, 0L, 20*60*10L);
	}
	
}