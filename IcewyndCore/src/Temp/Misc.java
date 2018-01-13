package Temp;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
import com.massivecraft.factions.entity.MPlayer;

public class Misc implements Listener {

	@EventHandler
	// GetBucketPickup
	public void onBucketFill(PlayerBucketFillEvent e) {
		if (e.getPlayer().getWorld().getName().equals("world_nether")) {
			ItemStack GenBucket = new ItemStack(Material.LAVA_BUCKET, 1);
			ItemMeta meta = GenBucket.getItemMeta();
			meta.setDisplayName("§c§lGen Bucket");
			meta.setLore(Arrays.asList("§7Automatically generates cobblestone walls."));
			GenBucket.setItemMeta(meta);
			e.getItemStack().setType(Material.AIR);
			e.getPlayer().getInventory().addItem(GenBucket);
		}
		e.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	// PlayerSkullDropOnDeath
	public void ondeath(PlayerDeathEvent event) {
		if ((event.getEntity().getKiller() instanceof Player)) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setDisplayName("§c§lSkull of §7§l" + event.getEntity().getPlayer().getName());
			sm.setOwner(event.getEntity().getPlayer().getName());
			skull.setItemMeta(sm);
			event.getDrops().add(skull);
			event.getEntity().sendMessage(
					"§4You were killed by " + event.getEntity().getKiller() + " §4and you lost your head!");
		}
	}

	@EventHandler
	// SpongePatch
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.SPONGE)) {
			Player player = event.getPlayer();
			ItemStack Sponge = new ItemStack(Material.SPONGE, 1);
			event.getBlock().setType(Material.AIR);
			player.getInventory().addItem(Sponge);
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws InvocationTargetException {
		Player player = event.getPlayer();
		// MOTD
		player.sendMessage("§f§l§m-----------§b§l§m-----------§f§l§m-----------");
		player.sendMessage("        §f§lCONNECTED TO §b§lICEWYND §b§lFACTIONS");
		player.sendMessage("                         §f(§b1.7.10 §f- §b1.12§f)");
		player.sendMessage("");
		player.sendMessage("§b§lFORUMS: §fIcewynd.net");
		player.sendMessage("§b§lDISCORD: §fIcewynd.net/Discord");
		player.sendMessage("§b§lSTORE: §fIcewynd.net/Store");
		player.sendMessage("§f§l§m-----------§b§l§m-----------§f§l§m-----------");
		// TabList foot/header
		PacketContainer packetContainer = Main.Main.protocolManager
				.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		packetContainer.getChatComponents()
				.write(0,
						WrappedChatComponent
								.fromText(" §8§l§m-§7§l§m-§f§l[§f §lICEWYND §b§lNETWORK§f§l ]§7§l§m-§8§l§m-§r "))
				.write(1, WrappedChatComponent.fromText("§c§lSITE:\n§c§lDISCORD:\nSTORE:"));
		ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);
		// Faction next to player name on Tablist
		MPlayer mplayer = MPlayer.get(player);
		String faction = mplayer.getFactionName();
		event.getPlayer().setPlayerListName("§c" + faction + " §f" + event.getPlayer().getName());
		// New Player Announce
		if (player.hasPlayedBefore()) {
		} else {
			Bukkit.broadcastMessage("§b§lWelcome to IcyWynd, §f§l" + player.getName() + "§b§l!");
		}
	}

	@EventHandler
	public void onServerListPing(ServerListPingEvent s) {
		s.setMotd(
				"         §8§l§m-§7§l§m-§f§l[§f §lICEWYND §b§lNETWORK §f§l- §a1.7 - 1.12 §f§l]§7§l§m-§8§l§m-§r                      §c§lFACTIONS MAP 1 LIVE!§7 / §9§l25% OFF SALE");
		s.setMaxPlayers(0);
	}
}