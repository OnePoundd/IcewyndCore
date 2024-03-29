package Main;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.massivecraft.factions.entity.MConf;

public class Misc implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	/*
	 * //Scoreboard
	 * 
	 * @EventHandler public void onScoreboardUpdate(ScoreboardEvent event){ Player
	 * player = event.getPlayer(); event.setScoreboardName("�b�lIcewynd.net");
	 * event.setHeader("�7�l�m------------"); event.setFooter("�7�l�m------------");
	 * MPlayer mplayer = MPlayer.get(player); String faction =
	 * mplayer.getFactionName(); event.writeLine("�a�lFaction: ");
	 * event.writeLine("�7" + faction); event.writeLine(""); int ping =
	 * ((CraftPlayer) player).getHandle().ping; event.writeLine("�d�lPing:");
	 * event.writeLine("�7" + ping); event.writeLine("");
	 * event.writeLine("�a�lBalance:"); event.writeLine("�7" +
	 * Main.econ.getBalance(player)); }
	 */

	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws InvocationTargetException {
		Player player = event.getPlayer();
		// MOTD
		player.sendMessage("�f�l�m-----------�b�l�m-----------�f�l�m-----------");
		player.sendMessage("        �f�lCONNECTED TO �b�lICEWYND �b�lFACTIONS");
		player.sendMessage("                         �f(�b1.7.10 �f- �b1.12�f)");
		player.sendMessage("");
		player.sendMessage("�b�lFORUMS: �fforums.icewynd.net");
		player.sendMessage("�b�lDISCORD: �fdiscord.icewynd.net");
		player.sendMessage("�b�lSTORE: �fstore.icewynd.net");
		player.sendMessage("�f�l�m-----------�b�l�m-----------�f�l�m-----------");

		if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned") == true) {
			player.teleport(MConf.get().getWarp("jail"));
		}
		//TabList foot/header
		PacketContainer packetContainer = Main.protocolManager
				.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		packetContainer.getChatComponents()
				.write(0,
						WrappedChatComponent
								.fromText(" �8�l�m-�7�l�m-�f�l[�f ICEWYND �bNETWORK�f�l ]�7�l�m-�8�l�m-�r "))
				.write(1, WrappedChatComponent.fromText("�dStore, forums and more at Icewynd.net"));
		ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);
		//New Player Announce
		if (!player.hasPlayedBefore()) {
			Bukkit.broadcastMessage("�b�lWelcome to Icewynd, �f�l" + player.getName() + "�b�l!");
			plugin.getConfig().set(player.getUniqueId() + ".Name", player.getName());
			plugin.getConfig().set(player.getUniqueId() + ".Coins", 0);
			plugin.getConfig().set(player.getUniqueId() + ".MsgToggle", false);
			plugin.getConfig().set(player.getUniqueId() + ".Freecam", false);
			plugin.getConfig().set(player.getUniqueId() + ".Banned", false);
			plugin.getConfig().set(player.getUniqueId() + ".BlocksMined", 0);
			plugin.getConfig().set(player.getUniqueId() + ".SugarcaneMined", 0);
			plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", 0);
			plugin.getConfig().set(player.getUniqueId() + ".BlocksPlaced", 0);
			plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", 0);
			plugin.getConfig().set(player.getUniqueId() + ".MCMMOLevelsGained", 0);
			plugin.getConfig().set(player.getUniqueId() + ".SkillsObtained", 0);
			plugin.getConfig().set(player.getUniqueId() + ".LuckyDropsFound", 0);
			plugin.getConfig().set(player.getUniqueId() + ".ChallengesCompleted", 0);
			plugin.getConfig().set(player.getUniqueId() + ".BooksEnchanted", 0);
			plugin.getConfig().set(player.getUniqueId() + ".CastleCaptures", 0);
			plugin.getConfig().set(player.getUniqueId() + ".SupplyDropsCaptured", 0);
			plugin.saveConfig();
		}
	}

	@EventHandler
	//GenBucket Pickup
	public void onBucketFill(PlayerBucketFillEvent event) {
		if (event.getPlayer().getWorld().getName().equals("world")) {
			ItemStack GenBucket = new ItemStack(Material.LAVA_BUCKET, 1);
			ItemMeta meta = GenBucket.getItemMeta();
			meta.setDisplayName("�c�lGen Bucket");
			meta.setLore(Arrays.asList("�7Automatically generates cobblestone walls."));
			GenBucket.setItemMeta(meta);
			event.getPlayer().getInventory().getItemInHand()
					.setAmount(event.getPlayer().getInventory().getItemInHand().getAmount() - 1);
			event.getPlayer().getInventory().addItem(GenBucket);
		}
		event.setCancelled(true);
	}

	@EventHandler
	//Player Skull Drop On Death
	public void ondeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned") == true) {
			player.teleport(MConf.get().getWarp("jail"));
		} else if ((event.getEntity().getKiller() instanceof Player)) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setDisplayName("�c�lSkull of �7�l" + event.getEntity().getPlayer().getName());
			sm.setOwner(event.getEntity().getPlayer().getName());
			skull.setItemMeta(sm);
			event.getDrops().add(skull);
			int playerskilled = plugin.getConfig().getInt(event.getEntity().getKiller().getUniqueId() + ".PlayersKilled");
			plugin.getConfig().set(event.getEntity().getKiller().getUniqueId() + ".PlayersKilled", playerskilled + 1);
			plugin.saveConfig();
		}
	}

	//Sponge Patch
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		int blocksmined = plugin.getConfig().getInt(player.getUniqueId() + ".BlocksMined");
		plugin.getConfig().set(player.getUniqueId() + ".BlocksMined", blocksmined + 1);
		plugin.saveConfig();
		if (event.getBlock().getType().equals(Material.SUGAR_CANE_BLOCK)) {
			int sugarcanemined = plugin.getConfig().getInt(player.getUniqueId() + ".SugarcaneMined");
			plugin.getConfig().set(player.getUniqueId() + ".SugarcaneMined", sugarcanemined + 1);
			plugin.saveConfig();
		} else if (event.getBlock().getType().equals(Material.SPONGE)) {
			ItemStack Sponge = new ItemStack(Material.SPONGE, 1);
			event.getBlock().setType(Material.AIR);
			player.getInventory().addItem(Sponge);
		}
	}

	//MOTD
	@EventHandler
	public void onServerListPing(ServerListPingEvent s) {
		s.setMotd(
				"         �8�l�m-�7�l�m-�f�l[�f �lICEWYND �b�lNETWORK �f�l- �a1.7 - 1.12 �f�l]�7�l�m-�8�l�m-�r                      �c�lFACTIONS MAP 1 LIVE!�7 / �9�l25% OFF SALE");
		s.setMaxPlayers(0);
	}

	//Prevents hoppers picking up spawners
	@EventHandler
	public void onHopperPickupItemEvent(InventoryPickupItemEvent event) {
		if ((event.getInventory().getType().equals(InventoryType.HOPPER))
				&& (event.getItem().getItemStack().getType().equals(Material.MOB_SPAWNER))) {
			event.setCancelled(true);
		}
	}

	//Prevents players brewing invisibility potions
	@EventHandler
	public void onBrewEvent(BrewEvent event) {
		if (event.getContents().contains(Material.FERMENTED_SPIDER_EYE)) {
			event.setCancelled(true);
		}
	}

	//Bedrock exploit patch
	@EventHandler
	public void onExtend(BlockPistonExtendEvent event) {
		if (event.getBlock().getLocation().getBlockY() <= 12) {
			for (Block blocks : event.getBlocks()) {
				if (blocks.getType().equals(Material.PISTON_BASE)) {
					event.setCancelled(true);
				} else if (blocks.getType().equals(Material.PISTON_STICKY_BASE)) {
					event.setCancelled(true);
				}
			}
		}
	}

	//Prevents players crafting hoppers
	@EventHandler
	public void onCraftEvent(PrepareItemCraftEvent event) {
		try {
			Material toCraft = event.getRecipe().getResult().getType();
			if (toCraft.equals(Material.HOPPER)) {
				event.getInventory().setResult(new ItemStack(Material.AIR));
			}
		} catch (NullPointerException localNullPointerException) {
		}
	}

	//Sign exploit fix
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onSignChange(SignChangeEvent e) {
		for (int i = 0; i < 4; i++) {
			if (e.getLine(i).matches("^[a-zA-Z0-9_]*$")) {
				if (e.getLine(i).length() > 20) {
					e.setCancelled(true);
					e.getPlayer().sendMessage("Invalid amount of characters");
				}
			} else if (e.getLine(i).length() > 50) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("Invalid amount of characters");
			}
		}
	}

	//Keep XP on Death
	@EventHandler
	public void XPKeep(PlayerDeathEvent event) {
		if (event.getEntity().getPlayer().hasPermission("server.keepxp")) {
			event.setKeepLevel(true);
		}
	}

	//Doubles arrow damage
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow) {
			double damage = event.getDamage() * 2;
			event.setDamage(damage);
		}
	}
}
