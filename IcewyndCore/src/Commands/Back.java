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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

import Main.Main;

public class Back implements CommandExecutor, Listener{
	
	static Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("back")) {
			Player player = (Player) sender;
			if(plugin.getConfig().get(player.getUniqueId() + ".BackLocation") != null) {
				tryTeleport(player, (Location) plugin.getConfig().get(player.getUniqueId() + ".BackLocation"));
			}else {
				player.sendMessage("§c§l(!)§7 You have no location to go back to!");
			}
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if(event.getCause().equals(TeleportCause.COMMAND)) {
			plugin.getConfig().set(event.getPlayer().getUniqueId() + ".BackLocation", event.getFrom());
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.getConfig().set(event.getPlayer().getUniqueId() + ".BackLocation", null);
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
