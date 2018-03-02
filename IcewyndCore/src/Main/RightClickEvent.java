package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.massivecraft.factions.entity.MPlayer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class RightClickEvent implements Listener {


	/*
	 //Inventory space check
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§cYou do not have the required inventory space.");
				}else {
	 */

	//Quartermaster Items
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player player = event.getPlayer();
			if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("§c§lPlagued Skeleton")) {
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0), EntityType.WITHER_SKELETON);
					Block block = event.getClickedBlock();
					Location locB = block.getLocation().getBlock().getLocation();
					MPlayer mplayer = MPlayer.get(player);
					String faction = mplayer.getFactionName();

					String nut = "§d§lBOSS EGGS§8§l » §7§lA §cPlagued Skeleton §7§lhas been summoned §7§lto Warzone by §e§l" + StringUtils.capitalize(faction) + "§7§l! §fCoords: [" + locB.getBlockX() + ", " +  locB.getBlockY() + ", " + locB.getBlockZ() + "]";
					TextComponent text = new TextComponent(nut);
					text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§lPlagued Skeleton \n \n§c§lRare §7§lboss egg that can be summoned in the Warzone. \n§7§lSlay this boss to be rewarded with a prize! \n \n§c§lDifficulty: §75 Players").create()));
					for(Player player11 : Bukkit.getOnlinePlayers()) {
						player11.sendMessage(text);
						block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
					}

					// Charged Creeper
				} else if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
					if (player.getItemInHand().getItemMeta().getDisplayName().equals("§a§l§nCharged Creeper Egg")) {
						player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
						player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0), EntityType.CREEPER);
						Block block = event.getClickedBlock();
						Location locB = block.getLocation().getBlock().getLocation();
						block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
					}
				}else if (player.getItemInHand().getType().equals(Material.TRAPPED_CHEST)) {
					if (player.getItemInHand().getItemMeta().getDisplayName().equals("§c§l§nCrate of TNT")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("§cYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						}else {
							player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							player.getInventory().addItem(new ItemStack(Material.TNT, 2304));
							event.setCancelled(true);
						}

						// Mystery Spawner
					} else if (player.getItemInHand().getType().equals(Material.MOB_SPAWNER)) {
						if (player.getItemInHand().getItemMeta().getDisplayName().equals("§d§l§nMystery Spawner")) {
							if (player.getInventory().firstEmpty() == -1) {
								player.sendMessage("§cYou do not have the required inventory space.");
								event.setCancelled(true);
								player.closeInventory();
							}else {
								Random rand = new Random();
								int index5 = rand.nextInt(5) + 1;
								if (index5 == 1) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " villager");
									Bukkit.broadcastMessage(" " + player.getName() + " was lucky and recieved a §d§lVillager spawner§b§l from a Mystery Spawner!" + " §d§lœ¦");
								} else if (index5 == 2) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " creeper");
								} else if (index5 == 3) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " enderman");
								} else if (index5 == 4) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " blaze");
								} else if (index5 == 5) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " witch");
								}
								player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							}
						} else if (player.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
							if (player.getItemInHand().getItemMeta().getDisplayName().equals("§d§lRandom TP")) {
								event.setCancelled(true);
								ArrayList<Player> players = new ArrayList<Player>();
								for (Player e : Bukkit.getOnlinePlayers()) players.add(e);
								Player randomPlayer = players.get(new Random().nextInt(players.size()));
								player.teleport(randomPlayer.getLocation());
							}
						} else if (player.getItemInHand().getType().equals(Material.CHEST)) {
							if (player.getItemInHand().getItemMeta().getDisplayName().equals("§2§l§nFighter Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§cYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								}else {
									player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									//Fighter Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("§2Fighter Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item1.setItemMeta(Item1Meta);

									//Fighter Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("§2Fighter Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item2.setItemMeta(Item2Meta);

									//Fighter Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("§2Fighter Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item3.setItemMeta(Item3Meta);

									//Fighter Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("§2Fighter Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item4.setItemMeta(Item4Meta);

									//Fighter Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("§2Fighter Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
									Item5.setItemMeta(Item5Meta);

									//Fighter Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("§2Fighter Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
									Item6.setItemMeta(Item6Meta);

									//Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
								}
							}else if (player.getItemInHand().getItemMeta().getDisplayName().equals("§d§l§nWarlord Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§cYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								}else {
									player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									//Warlord Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("§dWarlord Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item1.setItemMeta(Item1Meta);

									//Warlord Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("§dWarlord Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item2.setItemMeta(Item2Meta);

									//Warlord Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("§dWarlord Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item3.setItemMeta(Item3Meta);

									//Warlord Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("§dWarlord Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item4.setItemMeta(Item4Meta);

									//Warlord Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("§dWarlord Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
									Item5.setItemMeta(Item5Meta);

									//Warlord Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("§dWarlord Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
									Item6.setItemMeta(Item6Meta);

									//Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
								}
							}
							else if (player.getItemInHand().getItemMeta().getDisplayName().equals("§c§l§nEmporer Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§cYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								}else {
									player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									//Emporer Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("§cEmporer Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item1.setItemMeta(Item1Meta);

									//Emporer Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("§cEmporer Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item2.setItemMeta(Item2Meta);

									//Emporer Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("§cEmporer Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item3.setItemMeta(Item3Meta);

									//Emporer Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("§cEmporer Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item4.setItemMeta(Item4Meta);

									//Emporer Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("§cEmporer Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
									Item5.setItemMeta(Item5Meta);

									//Emporer Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("§cEmporer Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
									Item6.setItemMeta(Item6Meta);

									//Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									//Elixir of Fury
									ItemStack Item8 = new ItemStack(Material.POTION, 1, (byte) 8266);
									ItemMeta Item8Meta = Item6.getItemMeta();
									List<String> lore8 = new ArrayList<String>();
									Item8Meta.setDisplayName("§4§l§nElixir of Fury");
									Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
									lore8.add("§eSpeed II (1:30)");
									lore8.add("§eStrength II (1:30)");
									lore8.add("§eFire Resistence (8:00)");
									lore8.add("§1");
									lore8.add("§cPrice: §f5 Coins");
									Item8Meta.setLore(lore8);
									Item8.setItemMeta(Item8Meta);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
									player.getInventory().addItem(Item8);
								}
							}
							else if (player.getItemInHand().getItemMeta().getDisplayName().equals("§9§l§nGod Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§cYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								}else {
									player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									//God Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("§9§lGod Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item1.setItemMeta(Item1Meta);

									//God Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("§9§lGod Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item2.setItemMeta(Item2Meta);

									//God Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("§9§lGod Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item3.setItemMeta(Item3Meta);

									//God Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("§9§lGod Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item4.setItemMeta(Item4Meta);

									//God Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("§9§lGod Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
									Item5Meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
									Item5.setItemMeta(Item5Meta);

									//God Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("§9§lGod Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
									Item6Meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
									Item6.setItemMeta(Item6Meta);

									//Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									//Elixir of Fury
									ItemStack Item8 = new ItemStack(Material.POTION, 1, (byte) 8266);
									ItemMeta Item8Meta = Item6.getItemMeta();
									List<String> lore8 = new ArrayList<String>();
									Item8Meta.setDisplayName("§4§l§nElixir of Fury");
									Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
									lore8.add("§eSpeed II (1:30)");
									lore8.add("§eStrength II (1:30)");
									lore8.add("§eFire Resistence (8:00)");
									lore8.add("§1");
									lore8.add("§cPrice: §f5 Coins");
									Item8Meta.setLore(lore8);
									Item8.setItemMeta(Item8Meta);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
									player.getInventory().addItem(Item8);
									player.getInventory().addItem(Item8);
								}
							}
							else if (player.getItemInHand().getItemMeta().getDisplayName().equals("§b§l§nIcewynd Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§cYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								}else {
									player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									//Icewynd Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("§b§lIcewynd Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item1.setItemMeta(Item1Meta);

									//Icewynd Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("§b§lIcewynd Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item2.setItemMeta(Item2Meta);

									//Icewynd Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("§b§lIcewynd Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item3.setItemMeta(Item3Meta);

									//Icewynd Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("§b§lIcewynd Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item4.setItemMeta(Item4Meta);

									//Icewynd Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("§b§lIcewynd Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
									Item5Meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
									Item5Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item5.setItemMeta(Item5Meta);

									//Icewynd Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("§b§lIcewynd Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
									Item6Meta.addEnchant(Enchantment.ARROW_FIRE, 2, true);
									Item6Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item6Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
									Item6.setItemMeta(Item6Meta);

									//Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 1);

									//Elixir of Fury
									ItemStack Item8 = new ItemStack(Material.POTION, 1, (byte) 8266);
									ItemMeta Item8Meta = Item6.getItemMeta();
									List<String> lore8 = new ArrayList<String>();
									Item8Meta.setDisplayName("§4§l§nElixir of Fury");
									Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
									lore8.add("§eSpeed II (1:30)");
									lore8.add("§eStrength II (1:30)");
									lore8.add("§eFire Resistence (8:00)");
									lore8.add("§1");
									lore8.add("§cPrice: §f5 Coins");
									Item8Meta.setLore(lore8);
									Item8.setItemMeta(Item8Meta);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
									player.getInventory().addItem(Item8);
									player.getInventory().addItem(Item8);
									player.getInventory().addItem(Item8);
								}
							}
						}
					}
				}
			}
		}
	}



	//Quartermaster Item
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDrink(PlayerItemConsumeEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType().equals(Material.POTION)) {
			if (player.getItemInHand().getItemMeta().getDisplayName().equals("§4§l§nElixir of Fury")) {
				;
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 1));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9570, 1));
				player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(PlayerInteractEntityEvent event) {
		String IPlayer = event.getRightClicked().getName();
		Player player = event.getPlayer();
		if (player.hasPermission("server.admin")) {
			if (player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
				if(player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
					// AND HAS NAME (NAME = WHATEVER IS IN OVERWATCH)
					event.getPlayer().chat("/invsee " + IPlayer);
				}
			}else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOOK) {
				if(player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
					// AND HAS NAME (NAME = WHATEVER IS IN OVERWATCH)
					event.getPlayer().chat("/pinfo " + IPlayer);
				}
			}
		}
	}
}