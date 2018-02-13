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

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatFormat implements Listener {
Main plugin = Main.getPlugin(Main.class);



	@EventHandler
	public void onChat(AsyncPlayerChatEvent c) { //adds rank prefixes to chat
		if (!c.isCancelled()) {
			String ChatMessage = c.getMessage();
			Player player = c.getPlayer();
			
			int timeplayed = plugin.getConfig().getInt(player.getUniqueId() + ".TimePlayed");
			int blocksplaced = plugin.getConfig().getInt(player.getUniqueId() + ".BlocksPlaced");
			int playerskilled = plugin.getConfig().getInt(player.getUniqueId() + ".PlayersKilled");
			int mobskilled = plugin.getConfig().getInt(player.getUniqueId() + ".MobsKilled");
			int moneymade = plugin.getConfig().getInt(player.getUniqueId() + ".MoneyMade");
			int fishcaught = plugin.getConfig().getInt(player.getUniqueId() + ".FishCaught");
			int genbuckets = plugin.getConfig().getInt(player.getUniqueId() + ".GenBuckets");
			int sugarcanemined = plugin.getConfig().getInt(player.getUniqueId() + ".SugarcaneMined");
			int blocksmined = plugin.getConfig().getInt(player.getUniqueId() + ".BlocksMined");
			int luckydrops = plugin.getConfig().getInt(player.getUniqueId() + ".LuckyDrops");
			int supplydropscaptured = plugin.getConfig().getInt(player.getUniqueId() + ".SupplyDropsCaptured");
			int castlecaptures = plugin.getConfig().getInt(player.getUniqueId() + ".CastleCaptures");
			int booksenchanted = plugin.getConfig().getInt(player.getUniqueId() + ".BooksEnchanted");
			int challengescompleted = plugin.getConfig().getInt(player.getUniqueId() + ".ChallengesCompleted");
			int mcmmolevelsgained = plugin.getConfig().getInt(player.getUniqueId() + ".MCMMOLevelsGained");
			
			c.setCancelled(true);
			if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned") == true) {
				c.setCancelled(true);
			}else {
				if (player.hasPermission("server.admin")) {
					TextComponent text = new TextComponent("Test");
					text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7             §a§lGeneral          \n      §a§l§m------------§a   \n§d§lTime Played:§e " + timeplayed + "\n§d§lBlocks Broken:§e " + blocksmined + "\n§d§lSugarcane Farmed:§e " + sugarcanemined + "\n§d§lBlocks Placed:§e " + blocksplaced + "\n§d§lFish Caught:§e " + fishcaught + "\n§d§lPlayers Killed:§e " + playerskilled + "\n§d§lMonsters Killed:§e " + mobskilled + "\n              §a§lOther          \n      §a§l§m------------§a" + "\n§d§lMoney Made:§e " + moneymade + "\n§d§lGenbuckets Placed:§e " + genbuckets + "\n§d§lLucky Drops Found:§e " + luckydrops + "\n§d§lSupply Drops Captured:§e " + supplydropscaptured + "\n§d§lCastle Captures:§e " + castlecaptures + "\n§d§lBooks Enchanted:§e " + booksenchanted + "\n§d§lChallenges Completed:§e " + challengescompleted + "\n§d§lMCMMO Levels Gained:§e " + mcmmolevelsgained).create()));
					for(Player player1 : Bukkit.getOnlinePlayers()) {
						player1.sendMessage(text + "§7§l[§c§lADMIN§7§l]§b " + c.getPlayer().getName() + " §a» §f" + ChatMessage);
					}
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