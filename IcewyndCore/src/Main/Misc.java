package Main;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import com.massivecraft.factions.entity.MConf;

public class Misc implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@SuppressWarnings("deprecation")
	@EventHandler
	// GetBucketPickup
	public void onBucketFill(PlayerBucketFillEvent e) {
		if (e.getPlayer().getWorld().getName().equals("world")) {
			ItemStack GenBucket = new ItemStack(Material.LAVA_BUCKET, 1);
			ItemMeta meta = GenBucket.getItemMeta();
			meta.setDisplayName("§c§lGen Bucket");
			meta.setLore(Arrays.asList("§7Automatically generates cobblestone walls."));
			GenBucket.setItemMeta(meta);
			e.getPlayer().getInventory().getItemInHand()
			.setAmount(e.getPlayer().getInventory().getItemInHand().getAmount() - 1);
			e.getPlayer().getInventory().addItem(GenBucket);
		}
		e.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	// PlayerSkullDropOnDeath
	public void ondeath(PlayerDeathEvent event) {
		Player player = event.getEntity().getPlayer();
		if (plugin.getConfig().getBoolean(player.getUniqueId() + ".Banned") == true) {
			player.teleport(MConf.get().getWarp("jail"));
		} else if ((event.getEntity().getKiller() instanceof Player)) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setDisplayName("§c§lSkull of §7§l" + event.getEntity().getPlayer().getName());
			sm.setOwner(event.getEntity().getPlayer().getName());
			skull.setItemMeta(sm);
			event.getDrops().add(skull);
			int playerskilled = plugin.getConfig().getInt(event.getEntity().getKiller().getUniqueId() + ".PlayersKilled");
			plugin.getConfig().set(event.getEntity().getKiller().getUniqueId() + ".PlayersKilled", playerskilled + 1);
			plugin.saveConfig();
		}
	}

	@EventHandler
	// SpongePatch
	// Also tracks blocks mined for stats
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

	@EventHandler
	public void onServerListPing(ServerListPingEvent s) {
		s.setMotd(
				"         §8§l§m-§7§l§m-§f§l[§f §lICEWYND §b§lNETWORK §f§l- §a1.7 - 1.12 §f§l]§7§l§m-§8§l§m-§r                      §c§lFACTIONS MAP 1 LIVE!§7 / §9§l25% OFF SALE");
		s.setMaxPlayers(0);
	}

	// Prevents hoppers picking up spawners
	@EventHandler
	public void onHopperPickupItemEvent(InventoryPickupItemEvent event) {
		if ((event.getInventory().getType().equals(InventoryType.HOPPER))
				&& (event.getItem().getItemStack().getType().equals(Material.MOB_SPAWNER))) {
			event.setCancelled(true);
		}
	}

	// Prevents players brewing invisibility potions
	@EventHandler
	public void onBrewEvent(BrewEvent event) {
		if (event.getContents().contains(Material.FERMENTED_SPIDER_EYE)) {
			event.setCancelled(true);
		}
	}

	// Bedrock exploit patch
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

	// Prevents players crafting hoppers
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

	// Sign exploit fix
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

	// prevents explosive block damage caused by fireballs
	@EventHandler
	public void onFireball(EntityExplodeEvent event) {
		if(event.getEntity() instanceof Fireball) {
			event.blockList().clear();
		}
	}
	// Keep XP on Death
	@EventHandler
	public void XPKeep(PlayerDeathEvent event) {
		if (event.getEntity().getPlayer().hasPermission("server.keepxp")) {
			event.setKeepLevel(true);
		}
	}

}
