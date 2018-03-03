package Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

public class SilkSpawners implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		//Tracks blocks placed for stats command
		int blocksplaced = plugin.getConfig().getInt(player.getUniqueId() + ".BlocksPlaced");
		plugin.getConfig().set(player.getUniqueId() + ".BlocksPlaced", blocksplaced + 1);
		plugin.saveConfig();
		if (!event.isCancelled()) {
			if (event.getBlock().getType().equals(Material.MOB_SPAWNER)) {
				ItemStack item = event.getItemInHand();
				if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
					String name = item.getItemMeta().getDisplayName();
					Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
						public void run() {
							if (!event.isCancelled()) {
								BlockState blockState = event.getBlock().getState();
								if (blockState != null) {
									CreatureSpawner spawner = (CreatureSpawner) blockState;
									if (name.equalsIgnoreCase("§eCreeper §fSpawner")) {
										spawner.setSpawnedType(EntityType.CREEPER);
									} else if (name.equalsIgnoreCase("§eZombie Pigman §fSpawner")) {
										spawner.setSpawnedType(EntityType.PIG_ZOMBIE);
									} else if (name.equalsIgnoreCase("§eWitch §fSpawner")) {
										spawner.setSpawnedType(EntityType.WITCH);
									} else if (name.equalsIgnoreCase("§eEnderman §fSpawner")) {
										spawner.setSpawnedType(EntityType.ENDERMAN);
									} else if (name.equalsIgnoreCase("§eBlaze §fSpawner")) {
										spawner.setSpawnedType(EntityType.BLAZE);
									} else if (name.equalsIgnoreCase("§eVillager §fSpawner")) {
										spawner.setSpawnedType(EntityType.VILLAGER);
									} else if (name.equalsIgnoreCase("§eSquid §fSpawner")) {
										spawner.setSpawnedType(EntityType.SQUID);
									} else if (name.equalsIgnoreCase("§eIron Golem §fSpawner")) {
										spawner.setSpawnedType(EntityType.IRON_GOLEM);
									} else if (name.equalsIgnoreCase("§ePig §fSpawner")) {
										spawner.setSpawnedType(EntityType.PIG);
									} else if (name.equalsIgnoreCase("§eCow §fSpawner")) {
										spawner.setSpawnedType(EntityType.COW);
									} else if (name.equalsIgnoreCase("§eChicken §fSpawner")) {
										spawner.setSpawnedType(EntityType.CHICKEN);
									} else if (name.equalsIgnoreCase("§eCave Spider §fSpawner")) {
										spawner.setSpawnedType(EntityType.CAVE_SPIDER);
									} else if (name.equalsIgnoreCase("§eSpider §fSpawner")) {
										spawner.setSpawnedType(EntityType.SPIDER);
									} else if (name.equalsIgnoreCase("§eZombie §fSpawner")) {
										spawner.setSpawnedType(EntityType.ZOMBIE);
									} else if (name.equalsIgnoreCase("§eSkeleton §fSpawner")) {
										spawner.setSpawnedType(EntityType.SKELETON);
									} else if (name.equalsIgnoreCase("§eMagma Cube §fSpawner")) {
										spawner.setSpawnedType(EntityType.MAGMA_CUBE);
									} else if (name.equalsIgnoreCase("§eSlime §fSpawner")) {
										spawner.setSpawnedType(EntityType.SLIME);
									} else if (name.equalsIgnoreCase("§eSheep §fSpawner")) {
										spawner.setSpawnedType(EntityType.SHEEP);
									} else if (name.equalsIgnoreCase("§eWolf §fSpawner")) {
										spawner.setSpawnedType(EntityType.WOLF);
									}
									blockState.update();
									event.getPlayer().sendMessage("§b§l(!)§7 You have placed a "
											+ ChatColor.stripColor(name).toLowerCase() + "!");
								}
							}
						}
					}, 1L);
				}
			}
		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.MOB_SPAWNER)) {
			event.setExpToDrop(0);
			Player player = event.getPlayer();
			ItemStack item = player.getItemInHand();
			if (item != null && item.getType().equals(Material.DIAMOND_PICKAXE)) {
				item.setDurability((short) (item.getDurability() + 200));
				CreatureSpawner spawner = (CreatureSpawner) event.getBlock().getState();
				ItemStack spawnerItem = new ItemStack(Material.MOB_SPAWNER);
				ItemMeta spawnerMeta = spawnerItem.getItemMeta();
				spawnerMeta.setDisplayName(
						"§e" + (spawner.getCreatureTypeName().replace("_", " ")).toUpperCase() + " §fSpawner");
				spawnerItem.setItemMeta(spawnerMeta);
				if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
					for (String s : item.getItemMeta().getLore()) {
						if (s.equals("§eSilk Feet")) {
							player.getWorld().dropItem(player.getLocation(), spawnerItem);
							event.getPlayer()
							.sendMessage("§b§l(!)§7 You have destroyed a "
									+ ChatColor.stripColor(
											spawner.getCreatureTypeName().replace("_", " ").toLowerCase())
									+ " spawner!");
							return;
						}
					}
				}
				if (item.containsEnchantment(Enchantment.SILK_TOUCH)) {
					player.getWorld().dropItem(event.getBlock().getLocation(), spawnerItem);
					event.getPlayer()
					.sendMessage("§b§l(!)§7 You have destroyed a "
							+ ChatColor
							.stripColor(spawner.getCreatureTypeName().replace("_", " ").toLowerCase())
							+ " spawner!");
				}
			}
		}
	}

}

								