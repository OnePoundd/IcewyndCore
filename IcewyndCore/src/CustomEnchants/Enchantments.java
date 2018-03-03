package CustomEnchants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import com.google.common.collect.Lists;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

import Main.Main;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.events.DisguiseEvent;
import me.libraryaddict.disguise.events.UndisguiseEvent;

public class Enchantments implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler //TO PREVENT DISGUISE BUFF DUPLICATION
	public void onPlayerJoin(PlayerJoinEvent event) {
		updateEnchantBuffs(event.getPlayer());
	}


	// Shockwave, Extractor
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		try {
			Player player = event.getPlayer();
			if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta()) {
				ItemMeta meta = player.getItemInHand().getItemMeta();
				if (meta.hasLore()) {
					for (String s : meta.getLore()) {
						if (s.equals("§eShockwave")) {
							if (!player.isSneaking()) {
								Material m = player.getItemInHand().getType();
								if (m.equals(Material.DIAMOND_PICKAXE)) {
									Shockwave.minePickaxe(player, event.getBlock());
								} else if (m.equals(Material.DIAMOND_SPADE)) {
									Shockwave.mineShovel(player, event.getBlock());
								}
							}
						} else if (s.equals("§5Extractor")) {
							Material m = event.getBlock().getType();
							if (m.equals(Material.COAL_ORE) || m.equals(Material.DIAMOND_ORE)
									|| m.equals(Material.EMERALD_ORE) || m.equals(Material.GLOWING_REDSTONE_ORE)
									|| m.equals(Material.GOLD_ORE) || m.equals(Material.IRON_ORE)
									|| m.equals(Material.LAPIS_ORE) || m.equals(Material.QUARTZ_ORE)
									|| m.equals(Material.REDSTONE_ORE)) {
								ArrayList<Block> ListOfBlocks = new ArrayList<Block>();
								checkBlocks(ListOfBlocks, event.getBlock());
								for (Block block : ListOfBlocks) {
									block.breakNaturally();
								}
							}
						} else if (s.equals("§5Chopper")) {
							Material m = event.getBlock().getType();
							if (m.equals(Material.LOG) || m.equals(Material.LOG_2)) {
								ArrayList<Block> ListOfBlocks = new ArrayList<Block>();
								checkBlocks(ListOfBlocks, event.getBlock());
								for (Block block : ListOfBlocks) {
									block.breakNaturally();
								}
							}
						} else if (s.equals("§5Harvester")) {
							Material m = event.getBlock().getType();
							if (m.equals(Material.SUGAR_CANE_BLOCK)) {
								Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
									public void run() {
										for (Entity entity : event.getPlayer().getNearbyEntities(6, 2, 6)) {
											if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
												Item item = (Item) entity;
												if (item.getItemStack().getType().equals(Material.SUGAR_CANE)) {
													if (player.getInventory().firstEmpty() != -1) {
														player.getInventory().addItem(item.getItemStack());
														item.remove();
													}
												}
											}
										}
									}
								}, 1);
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {
			// not player destroying block
		}
	}

	// Fireborne, Regenerator
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntityType().equals(EntityType.PLAYER)) {
			if (event.getCause().equals(DamageCause.FIRE_TICK)) {
				Player player = (Player) event.getEntity();
				if (player.getInventory().getChestplate() != null) {
					ItemStack chestplate = player.getInventory().getChestplate();
					if (chestplate.hasItemMeta() && chestplate.getItemMeta().hasLore()) {
						List<String> lore = chestplate.getItemMeta().getLore();
						for (String line : lore) {
							if (line.equals("§5Fireborne")) {
								Random rand = new Random();
								int num = rand.nextInt(2) + 1;
								if (num == 2) {
									if (player.getHealth() + event.getDamage() <= 20) {
										player.setHealth(event.getDamage() + player.getHealth());
									}
								}
								return;
							}
						}
					}
				}
			}
		}
	}

	// Override, Shadowstep, Cannibal, Reflection, Firefly
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player damaged = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			if (damager.getItemInHand() != null && damager.getItemInHand().hasItemMeta()
					&& damager.getItemInHand().getItemMeta().hasLore()) {
				ItemMeta meta = damager.getItemInHand().getItemMeta();
				List<String> lore = meta.getLore();
				for (String line : lore) {
					if (line.equals("§eOverride")) {
						Random rand = new Random();
						int index = rand.nextInt(20) + 1;
						if (index == 20) {
							if (damaged.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)) {
								for(PotionEffect potionEffect : damaged.getActivePotionEffects()) {
									if(potionEffect.getType().equals(PotionEffectType.FIRE_RESISTANCE)) {
										if(potionEffect.getDuration() < 99999) {
											damaged.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
											break;
										}
									}
								}
							}
						}
					} else if (line.equals("§eShadowstep")) {
						String factionName = BoardColl.get().getFactionAt(PS.valueOf(damager.getLocation())).getName();
						if (factionName.equalsIgnoreCase("Warzone") || factionName.equalsIgnoreCase("Castle")) {
							Random rand = new Random();
							int index = rand.nextInt(5) + 1;
							if (index == 5) {
								Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
									public void run() {
										Location damagedloc = damaged.getLocation();
										Vector direction = damagedloc.getDirection().normalize();
										direction.multiply(-5); // now the vector should have a length of 2 blocks
										// pointing behind the player
										damagedloc.add(direction);
										Vector dir = damagedloc.clone().subtract(damaged.getEyeLocation()).toVector();
										Location loc = damagedloc.setDirection(dir);
										damager.teleport(loc);
									}
								}, 1);
							}
						}
					} else if (line.equals("§5Cannibal")) {
						Random rand = new Random();
						int index = rand.nextInt(15) + 1;
						if (index == 15) {
							if ((damaged.getFoodLevel() - 2) > 0 && (damager.getFoodLevel() + 2) <= 20) {
								damaged.setFoodLevel(damaged.getFoodLevel() - 2);
								damager.setFoodLevel(damager.getFoodLevel() + 2);
							}
						}
					}
				}
			}
			ItemStack chestplate = damaged.getInventory().getChestplate();
			if (chestplate != null && chestplate.hasItemMeta() && chestplate.getItemMeta().hasLore()) {
				ItemMeta meta = chestplate.getItemMeta();
				List<String> lore = meta.getLore();
				for (String line : lore) {
					if (line.equals("§eReflection")) {
						Random rand = new Random();
						int num = rand.nextInt(25) + 1;
						if (num == 25) {
							damager.damage(event.getDamage());
							event.setDamage(0);
						}
					} else if (line.equals("§5Regenerator")) {
						Random rand = new Random();
						int num = rand.nextInt(25) + 1;
						if (num == 25) {
							damaged.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, 1));
							Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
								public void run() {
									updateEnchantBuffs(damaged);
								}
							}, (20 * 5) + 1);
						}
					}
				}
			}
		} else if (event.getEntity() instanceof Player && event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			Player damaged = (Player) event.getEntity();
			if (arrow.getShooter() instanceof Player) {
				Player damager = (Player) arrow.getShooter();
				if (damager.getItemInHand() != null && damager.getItemInHand().hasItemMeta()
						&& damager.getItemInHand().getItemMeta().hasLore()) {
					ItemMeta meta = damager.getItemInHand().getItemMeta();
					List<String> lore = meta.getLore();
					for (String line : lore) {
						if (line.equals("§eFirefly")) {
							Random rand = new Random();
							int index = rand.nextInt(5) + 1;
							if (index == 5) {
								Location loc = damaged.getLocation();
								damaged.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5, false, false);
							}
						} else if (line.equals("§5Medic")) {
							if (damaged.getHealth() + event.getDamage() <= 20.0) {
								damaged.setHealth(damaged.getHealth() + event.getDamage());
								event.setDamage(0);
							}
						}
					}
				}
			}
		}
	}

	// Thor
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			Player killer = event.getEntity().getKiller();
			if (killer.getItemInHand() != null && killer.getItemInHand().hasItemMeta()
					&& killer.getItemInHand().getItemMeta().hasLore()) {
				ItemMeta meta = killer.getItemInHand().getItemMeta();
				List<String> lore = meta.getLore();
				for (String line : lore) {
					if (line.equals("§5Thor")) {
						for (Entity entity : killer.getNearbyEntities(10, 20, 10)) {
							if (entity instanceof Player) {
								entity.setVelocity(entity.getLocation().getDirection().setY(1.333));
							}
						}
						return;
					}
				}
			}
		}
	}

	// Runner, Jumper, Inferno, Vision, ARMOR BUFFS, DISGUISE BUFFS
	public void updateEnchantBuffs(Player player) {
		// removes unlimited potion effects
		Collection<PotionEffect> potions = player.getActivePotionEffects();
		for (PotionEffect potion : potions) {
			if (potion.getDuration() >= 10000) {
				player.removePotionEffect(potion.getType());
			}
		}
		// check armor slots and apply appropriate buffs...
		for (ItemStack item : player.getInventory().getArmorContents()) {
			if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
				for (String line : item.getItemMeta().getLore()) {
					if (line.equals("§eRunner")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
					} else if (line.equals("§5Jumper")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 0));
					} else if (line.equals("§eInferno")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 0));
					} else if (line.equals("§5Vision")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999999, 0));
					}
				}
			}
		}

		// apply class buffs
		ItemStack[] armor = player.getInventory().getArmorContents();
		try {
			if (armor[0].getType().equals(Material.IRON_BOOTS) && armor[1].getType().equals(Material.IRON_LEGGINGS)
					&& armor[2].getType().equals(Material.IRON_CHESTPLATE)
					&& armor[3].getType().equals(Material.IRON_HELMET) && MPlayer.get(player).getSkillMiner()) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 9999999, 1));
			} else if (armor[0].getType().equals(Material.LEATHER_BOOTS)
					&& armor[1].getType().equals(Material.LEATHER_LEGGINGS)
					&& armor[2].getType().equals(Material.LEATHER_CHESTPLATE)
					&& armor[3].getType().equals(Material.LEATHER_HELMET) && MPlayer.get(player).getSkillArcher()) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 0));
			} else if (armor[0].getType().equals(Material.GOLD_BOOTS)
					&& armor[1].getType().equals(Material.GOLD_LEGGINGS)
					&& armor[2].getType().equals(Material.GOLD_CHESTPLATE)
					&& armor[3].getType().equals(Material.GOLD_HELMET) && MPlayer.get(player).getSkillBard()) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9999999, 1));
			}
		} catch (NullPointerException e) {}

		// apply disguise unlimited potion effects
		if(DisguiseAPI.isDisguised(player)) {
			Disguise disguise = DisguiseAPI.getDisguise(player);
			if(disguise.getType().equals(DisguiseType.ENDERMAN)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
			}else if(disguise.getType().equals(DisguiseType.HORSE)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 1));
			}else if(disguise.getType().equals(DisguiseType.CHICKEN)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 1));
			}else if(disguise.getType().equals(DisguiseType.BLAZE)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 0));
			}else if(disguise.getType().equals(DisguiseType.MAGMA_CUBE)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 2));
			}else if(disguise.getType().equals(DisguiseType.SLIME)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 2));
			}else if(disguise.getType().equals(DisguiseType.SQUID)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9999999, 0));
			}
		}
	}

	@EventHandler
	public void onPlayerDisguise(DisguiseEvent event) {
		if(event.getEntity() instanceof Player) {
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					updateEnchantBuffs((Player)event.getEntity());
				}
			}, 3);
		}
	}

	@EventHandler
	public void onPlayerDisguise(UndisguiseEvent event) {
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				updateEnchantBuffs((Player)event.getEntity());
			}
		}, 3);
	}


	/**
	 * Necceccities for unlimited potion effects
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inventory = event.getClickedInventory();
		if (inventory != null && inventory.getType().equals(InventoryType.PLAYER)) {
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					updateEnchantBuffs(Bukkit.getPlayer(event.getWhoClicked().getName()));
				}
			}, 2);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInterract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getItemInHand() != null) {
			Material m = event.getPlayer().getItemInHand().getType();
			if (m.equals(Material.IRON_BOOTS) || m.equals(Material.IRON_CHESTPLATE) || m.equals(Material.IRON_LEGGINGS)
					|| m.equals(Material.IRON_HELMET) || m.equals(Material.LEATHER_BOOTS)
					|| m.equals(Material.LEATHER_CHESTPLATE) || m.equals(Material.LEATHER_LEGGINGS)
					|| m.equals(Material.LEATHER_HELMET) || m.equals(Material.GOLD_BOOTS)
					|| m.equals(Material.GOLD_CHESTPLATE) || m.equals(Material.GOLD_LEGGINGS)
					|| m.equals(Material.GOLD_HELMET) || m.equals(Material.DIAMOND_BOOTS)
					|| m.equals(Material.DIAMOND_CHESTPLATE) || m.equals(Material.DIAMOND_LEGGINGS)
					|| m.equals(Material.DIAMOND_HELMET)) {
				// one tick later because it won't detect new armor piece
				Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
					public void run() {
						updateEnchantBuffs(event.getPlayer());
					}
				}, 1);
			}
		}
	}

	/**
	 * Neccecities for extractor
	 */
	public void checkBlocks(List<Block> ListOfBlocks, Block block) {
		for (BlockFace face : BlockFace.values()) {
			Block touching = block.getRelative(face);
			if (touching.getType().equals(block.getType())) {
				if (!ListOfBlocks.contains(touching)) {
					ListOfBlocks.add(touching);
					checkBlocks(ListOfBlocks, touching);
				}
			}
		}
	}



	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : Lists.reverse(list)) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public List<Player> getNearbyEnemies(Player player, int x, int y, int z) {
		List<Player> players = null;
		MPlayer mPlayer = MPlayer.get(player);
		List<Entity> entityList = player.getNearbyEntities(x, y, z);
		for (Entity entity : entityList) {
			if (entity.getType().equals(EntityType.PLAYER)) {
				try {
					MPlayer mPlayerEntity = MPlayer.get(entity);
					if (mPlayer.getRelationTo(mPlayerEntity).equals(Rel.ENEMY)
							|| mPlayer.getRelationTo(mPlayerEntity).equals(Rel.NEUTRAL)) {
						players.add(mPlayer.getPlayer());
					}
				} catch (Exception e) {
					// citizen
				}
			}
		}
		return players;
	}

}
