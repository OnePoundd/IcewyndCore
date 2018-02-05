package Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import com.massivecraft.factions.entity.MPlayer;

public class ChatFormat implements Listener {
Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onChat(AsyncPlayerChatEvent c) { //adds rank prefixes to chat
		if (!c.isCancelled()) {
			String ChatMessage = c.getMessage();
			Player player = c.getPlayer();
			c.setCancelled(true);
			if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned") == true) {
				c.setCancelled(true);
			}else {
				if (player.hasPermission("server.admin")) {
					Bukkit.broadcastMessage("§7§l[§c§lADMIN§7§l]§b " + player.getName() + " §a» §f" + ChatMessage);
				} else if (player.hasPermission("server.chatmod")) {
					Bukkit.broadcastMessage("§f§l[§bChatMod§f§l]§a " + player.getName() + " §7» §f" + ChatMessage);
				} else if (player.hasPermission("server.rank1")) {
					Bukkit.broadcastMessage("§f[§2Fighter§f]§a " + player.getName() + " §7» §f" + ChatMessage);
				} else if (player.hasPermission("server.rank2")) {
					Bukkit.broadcastMessage("§f[§dWarlord§f]§a " + player.getName() + " §7» §f" + ChatMessage);
				} else if (player.hasPermission("server.rank3")) {
					Bukkit.broadcastMessage("§f[§cEmporer§f]§a " + player.getName() + " §7» §f" + ChatMessage);
				} else if (player.hasPermission("server.rank4")) {
					Bukkit.broadcastMessage("§f[§9§lGod§f]§a " + player.getName() + " §7» §f" + ChatMessage);
				} else if (player.hasPermission("server.rank5")) {
					Bukkit.broadcastMessage("§f[§b§lIcewynd§f]§a " + player.getName() + " §7» §f" + ChatMessage);
				} else {
					Bukkit.broadcastMessage("§8[§fMember§8]§7 " + player.getName() + " » §f" + ChatMessage);
				}
			}
		}
	}
	
	@EventHandler
	public void onQuitEvent(PlayerQuitEvent event) { //changes the message when a player logs out and only shows to faction members
		event.setQuitMessage("");
		MPlayer player = MPlayer.get(event.getPlayer());
		if (!player.getFaction().getName().equals("Wilderness")) {
			player.getFaction().msg("§c§l(!)§7 " + player.getName() + " is now offline!");
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) { // player death messages with faction relation color formatting
		event.setDeathMessage("");
		if ((event.getEntity().getKiller() != null)
				&& (event.getEntity().getPlayer().getKiller().getType().equals(EntityType.PLAYER))) {
			Player killed = event.getEntity();
			Player killer = killed.getKiller();
			ItemStack item = killer.getItemInHand();
			if ((item.hasItemMeta()) && (item.getItemMeta().hasDisplayName())
					&& (item.getItemMeta().getDisplayName().contains(""))) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					MPlayer mplayer = MPlayer.get(player);
					player.sendMessage("§8§l>> " + mplayer.getColorTo(MPlayer.get(killed)) + killed.getName()
							+ "§8 was killed by " + mplayer.getColorTo(MPlayer.get(killer)) + killer.getName()
							+ "§8 with a " + item.getItemMeta().getDisplayName() + "§7!");
				}
			} else {
				for (Player player : Bukkit.getOnlinePlayers()) {
					MPlayer mplayer = MPlayer.get(player);
					player.sendMessage("§8§l>> " + mplayer.getColorTo(MPlayer.get(killed)) + killed.getName()
							+ "§8 was killed by " + mplayer.getColorTo(MPlayer.get(killer)) + killer.getName()
							+ "§8!");
				}
			}
		}
	}
	
	
	
}