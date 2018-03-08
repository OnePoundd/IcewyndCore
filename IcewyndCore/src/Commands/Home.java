package Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

import Main.Main;

public class Home implements CommandExecutor{
	
	static Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("home")) {
			Player player = (Player) sender;
			if (args.length == 1) {
				List<String> homeNames = plugin.getConfig().getStringList(player.getUniqueId() + ".HomeNames");
				if(homeNames.contains(args[0])) {
					tryTeleport(player, (Location) plugin.getConfig().getList(player.getUniqueId() + ".HomeLocations").get(homeNames.indexOf(args[0])));
				}else {
					player.sendMessage("§c§l(!)§7 You have no home with that name!");
				}
			} else {
				int current = plugin.getConfig().getInt(player.getUniqueId() + ".Homes");
				if (current == 0) {
					sender.sendMessage("§c§l(!)§7 You have no homes, use: /sethome <name> to set one!");
				} else {
					List<String> homeNames = (List<String>) plugin.getConfig().getList(player.getUniqueId() + ".HomeNames");
					
					int max = 0;
					if (player.hasPermission("icewynd.homes.15")) {
						max = 15;
					} else if (player.hasPermission("icewynd.homes.10")) {
						max = 10;
					} else if (player.hasPermission("icewynd.homes.7")) {
						max = 7;
					} else if (player.hasPermission("icewynd.homes.5")) {
						max = 5;
					} else if (player.hasPermission("icewynd.homes.3")) {
						max = 3;
					} else if (player.hasPermission("icewynd.homes.1")) {
						max = 1;
					}
					
					String homeList = "  ";
					for (String s : homeNames) {
						homeList = homeList + s + ", ";
					}
					
					sender.sendMessage("§6§l(!)§7 Homes [" + current + "/" + max + "]: " + homeList.substring(0, homeList.length() - 2).trim() + ".");
				}
			}
		}
		return false;
	}
	
	public static void tryTeleport(Player player, Location location) {
		Faction factionTo = BoardColl.get().getFactionAt(PS.valueOf(location));
		Rel relation = MPlayer.get(player).getRelationTo(factionTo);
		if(!(factionTo.getName().equalsIgnoreCase("Wilderness") || relation.equals(Rel.ALLY) || factionTo.equals(MPlayer.get(player).getFaction()))) {
			player.sendMessage("§c§l(!)§7 Your home is claimed by another faction! You can only teleport to homes in wilderness, your factions, or an allied factions territory.");
			return;
		}
		try {
			Faction factionAtCurrent = BoardColl.get().getFactionAt(PS.valueOf(player.getLocation()));
			MPlayer mplayer = MPlayer.get(player);
			if ((factionAtCurrent.getName().equalsIgnoreCase("Castle")) || (factionAtCurrent.getName().equalsIgnoreCase("Warzone")) || isEnemyNearby(60, 256, 60, mplayer)) {
				mplayer.message("§6§l(!)§7 The teleport will commence in 5 seconds as there are some enemies nearby or you are in a warzone. Do not move!");
				Location locBefore = player.getLocation();
				Bukkit.getScheduler().runTaskLater(Factions.get(), new Runnable() {
					public void run() {
						Location locNow = mplayer.getPlayer().getLocation();
						if(locBefore.getBlock().equals(locNow.getBlock())){
							player.teleport(location.subtract(0, getNumToSubtract(location, 0), 0), PlayerTeleportEvent.TeleportCause.COMMAND);
							player.sendMessage("§6§l(!)§7 Teleporting...");
						}else {
							player.sendMessage("§c§l(!)§7 The teleport was cancelled because you moved!");
						}
					}
				}, 100L);
			}else {
				player.teleport(location.subtract(0, getNumToSubtract(location, 0), 0), PlayerTeleportEvent.TeleportCause.COMMAND);
				player.sendMessage("§6§l(!)§7 Teleporting...");
			}
		} catch (NullPointerException localNullPointerException) {}
	}
	
	public static boolean isEnemyNearby(int x, int y, int z, MPlayer mPlayer) {
		List<Entity> entityList = mPlayer.getPlayer().getNearbyEntities(x, y, z);
		for (Entity entity : entityList) {
			if (entity.getType().equals(EntityType.PLAYER)) {
				MPlayer mPlayerEntity = MPlayer.get(entity);
				if ((mPlayer.getRelationTo(mPlayerEntity).equals(Rel.ENEMY)) || (mPlayer.getRelationTo(mPlayerEntity).equals(Rel.NEUTRAL))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static int getNumToSubtract(Location loc, int numToSubtract) {
		Location locBelow = loc.subtract(0.0, 1.0, 0.0);
		if (locBelow.getBlock().getType().equals(Material.AIR)) {
			getNumToSubtract(locBelow, numToSubtract + 1);
		}
		return numToSubtract - 1;
	}
	
}
