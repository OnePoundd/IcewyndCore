package Commands;

import java.util.List;

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

public class Tpaccept implements CommandExecutor{

	static Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpaccept") || cmd.getName().equalsIgnoreCase("tpyes")) {
			if(args.length == 0) {
				try {
					MPlayer accepter = MPlayer.get(Bukkit.getPlayer(sender.getName()));
					String uuidTo = plugin.getConfig().getString(accepter.getPlayer().getUniqueId() + ".LatestRequest");
					MPlayer requester = MPlayer.get(Bukkit.getPlayer(uuidTo));
					String type = plugin.getConfig().getString(accepter.getPlayer().getUniqueId() + ".LatestRequestType");
					if((System.currentTimeMillis() - plugin.getConfig().getLong(requester.getUuid() + ".LastRequestSent")) <= 20000) {
						if(plugin.getConfig().getString(requester.getUuid() + ".LastRequestSentTo").equals(accepter.getUuid().toString())) {
							if(type.equals("tpa")) {
								tryTeleport(requester.getPlayer(), accepter.getPlayer().getLocation());
								accepter.message("§6§l(!)§7 " + requester.getName() + " has accepted your teleport request!");
							}else if(type.equals("tpahere")) {
								tryTeleport(accepter.getPlayer(), requester.getPlayer().getLocation());
								accepter.message("§6§l(!)§7 " + requester.getName() + " has accepted your teleport request!");
							}
						}else {
							sender.sendMessage("§c§l(!)§7 That request is no longer valid!");
						}
					}else {
						sender.sendMessage("§c§l(!)§7 That teleport request has expired!");
					}
				}catch(Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			}else if(args.length == 1){
				
			}else {
				sender.sendMessage("§c§l(!)§7 Incorrect usage, try /tpyes or /tpyes <player>!");
			}
		}
		return false;
	}

	public static void tryTeleport(Player player, Location location) {
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
