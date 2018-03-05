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
//hi
	/*
	 * //Inventory space check if (player.getInventory().firstEmpty() == -1) {
	 * player.sendMessage("ßcYou do not have the required inventory space."); }else
	 * {
	 */

	@SuppressWarnings("deprecation")
	// Quartermaster Items
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player player = event.getPlayer();
			if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßcßlPlagued Skeleton")) {
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0),EntityType.fromId(5));
					Block block = event.getClickedBlock();
					Location locB = block.getLocation().getBlock().getLocation();
					MPlayer mplayer = MPlayer.get(player);
					String faction = mplayer.getFactionName();

					String nut = "ßdßlBOSS EGGSß8ßl ª ß7ßlA ßcPlagued Skeleton ß7ßlhas been summoned ß7ßlto Warzone by ßeßl" + StringUtils.capitalize(faction) + "ß7ßl! ßfCoords: [" + locB.getBlockX() + ", " + locB.getBlockY() + ", " + locB.getBlockZ() + "]";
					TextComponent text = new TextComponent(nut);
					text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("ßcßlPlagued Skeleton \n \nßcßlRare ß7ßlboss egg that can be summoned in the Warzone. \nß7ßlSlay this boss to be rewarded with a prize! \n \nßcßlDifficulty: ß75 Players").create()));
					for (Player player11 : Bukkit.getOnlinePlayers()) {
						player11.sendMessage(text);
						block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
					}

					// Charged Creeper
				} else if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
					if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßaßlßnCharged Creeper Egg")) {
						player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
						player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0),EntityType.CREEPER);
						Block block = event.getClickedBlock();
						Location locB = block.getLocation().getBlock().getLocation();
						block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
					}
				} else if (player.getItemInHand().getType().equals(Material.TRAPPED_CHEST)) {
					if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßcßlßnCrate of TNT")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("ßcYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						} else {
							player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							player.getInventory().addItem(new ItemStack(Material.TNT, 2304));
							event.setCancelled(true);
						}

						// Mystery Spawner
					} else if (player.getItemInHand().getType().equals(Material.MOB_SPAWNER)) {
						if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßdßlßnMystery Spawner")) {
							if (player.getInventory().firstEmpty() == -1) {
								player.sendMessage("ßcYou do not have the required inventory space.");
								event.setCancelled(true);
								player.closeInventory();
							} else {
								Random rand = new Random();
								int index5 = rand.nextInt(5) + 1;
								if (index5 == 1) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spawnergive " + player.getName() + " villager");
									Bukkit.broadcastMessage(" " + player.getName() + " was lucky and recieved a ßdßlVillager spawnerßbßl from a Mystery Spawner!" + " ßdßl≈ì¶");
								} else if (index5 == 2) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " creeper");
								} else if (index5 == 3) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " enderman");
								} else if (index5 == 4) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " blaze");
								} else if (index5 == 5) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " witch");
								}
								player.getInventory().getItemInHand()
										.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							}
						} else if (player.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
							if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßdßlRandom TP")) {
								event.setCancelled(true);
								ArrayList<Player> players = new ArrayList<Player>();
								for (Player e : Bukkit.getOnlinePlayers())
									players.add(e);
								Player randomPlayer = players.get(new Random().nextInt(players.size()));
								player.teleport(randomPlayer.getLocation());
							}
						} else if (player.getItemInHand().getType().equals(Material.CHEST)) {
							if (player.getItemInHand().getItemMeta().getDisplayName() == ("ß2ßlßnFighter Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("ßcYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								} else {
									player.getInventory().getItemInHand()
											.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									// Fighter Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("ß2Fighter Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item1.setItemMeta(Item1Meta);

									// Fighter Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("ß2Fighter Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item2.setItemMeta(Item2Meta);

									// Fighter Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("ß2Fighter Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item3.setItemMeta(Item3Meta);

									// Fighter Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("ß2Fighter Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item4.setItemMeta(Item4Meta);

									// Fighter Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("ß2Fighter Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
									Item5.setItemMeta(Item5Meta);

									// Fighter Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("ß2Fighter Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
									Item6.setItemMeta(Item6Meta);

									// Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
								}
							} else if (player.getItemInHand().getItemMeta().getDisplayName() == ("ßdßlßnWarlord Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("ßcYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								} else {
									player.getInventory().getItemInHand()
											.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									// Warlord Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("ßdWarlord Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item1.setItemMeta(Item1Meta);

									// Warlord Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("ßdWarlord Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item2.setItemMeta(Item2Meta);

									// Warlord Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("ßdWarlord Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item3.setItemMeta(Item3Meta);

									// Warlord Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("ßdWarlord Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item4.setItemMeta(Item4Meta);

									// Warlord Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("ßdWarlord Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
									Item5.setItemMeta(Item5Meta);

									// Warlord Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("ßdWarlord Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
									Item6.setItemMeta(Item6Meta);

									// Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									player.getInventory().addItem(Item1);
									player.getInventory().addItem(Item2);
									player.getInventory().addItem(Item3);
									player.getInventory().addItem(Item4);
									player.getInventory().addItem(Item5);
									player.getInventory().addItem(Item6);
									player.getInventory().addItem(Item7);
								}
							} else if (player.getItemInHand().getItemMeta().getDisplayName() == ("ßcßlßnEmporer Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("ßcYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								} else {
									player.getInventory().getItemInHand()
											.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									// Emporer Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("ßcEmporer Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item1.setItemMeta(Item1Meta);

									// Emporer Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("ßcEmporer Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item2.setItemMeta(Item2Meta);

									// Emporer Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("ßcEmporer Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item3.setItemMeta(Item3Meta);

									// Emporer Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("ßcEmporer Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
									Item4.setItemMeta(Item4Meta);

									// Emporer Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("ßcEmporer Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
									Item5.setItemMeta(Item5Meta);

									// Emporer Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("ßcEmporer Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
									Item6.setItemMeta(Item6Meta);

									// Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									// Elixir of Fury
									ItemStack Item8 = new ItemStack(Material.POTION, 1, (byte) 8266);
									ItemMeta Item8Meta = Item6.getItemMeta();
									List<String> lore8 = new ArrayList<String>();
									Item8Meta.setDisplayName("ß4ßlßnElixir of Fury");
									Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
									lore8.add("ßeSpeed II (1:30)");
									lore8.add("ßeStrength II (1:30)");
									lore8.add("ßeFire Resistence (8:00)");
									lore8.add("ß1");
									lore8.add("ßcPrice: ßf5 Coins");
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
							} else if (player.getItemInHand().getItemMeta().getDisplayName() == ("ß9ßlßnGod Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("ßcYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								} else {
									player.getInventory().getItemInHand()
											.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									// God Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("ß9ßlGod Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item1.setItemMeta(Item1Meta);

									// God Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("ß9ßlGod Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item2.setItemMeta(Item2Meta);

									// God Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("ß9ßlGod Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item3.setItemMeta(Item3Meta);

									// God Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("ß9ßlGod Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 2, true);
									Item4.setItemMeta(Item4Meta);

									// God Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("ß9ßlGod Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
									Item5Meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
									Item5.setItemMeta(Item5Meta);

									// God Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("ß9ßlGod Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
									Item6Meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
									Item6.setItemMeta(Item6Meta);

									// Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 128);

									// Elixir of Fury
									ItemStack Item8 = new ItemStack(Material.POTION, 1, (byte) 8266);
									ItemMeta Item8Meta = Item6.getItemMeta();
									List<String> lore8 = new ArrayList<String>();
									Item8Meta.setDisplayName("ß4ßlßnElixir of Fury");
									Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
									lore8.add("ßeSpeed II (1:30)");
									lore8.add("ßeStrength II (1:30)");
									lore8.add("ßeFire Resistence (8:00)");
									lore8.add("ß1");
									lore8.add("ßcPrice: ßf5 Coins");
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
							} else if (player.getItemInHand().getItemMeta().getDisplayName()
									.equals("ßbßlßnIcewynd Kit Crate")) {
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("ßcYou do not have the required inventory space.");
									event.setCancelled(true);
									player.closeInventory();
								} else {
									player.getInventory().getItemInHand()
											.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
									// Icewynd Helmet
									ItemStack Item1 = new ItemStack(Material.DIAMOND_HELMET, 1);
									ItemMeta Item1Meta = Item1.getItemMeta();
									Item1Meta.setDisplayName("ßbßlIcewynd Helmet");
									Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item1Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item1.setItemMeta(Item1Meta);

									// Icewynd Chestplate
									ItemStack Item2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
									ItemMeta Item2Meta = Item2.getItemMeta();
									Item2Meta.setDisplayName("ßbßlIcewynd Chestplate");
									Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item2Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item2.setItemMeta(Item2Meta);

									// Icewynd Leggings
									ItemStack Item3 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
									ItemMeta Item3Meta = Item3.getItemMeta();
									Item3Meta.setDisplayName("ßbßlIcewynd Leggings");
									Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item3Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item3.setItemMeta(Item3Meta);

									// Icewynd Boots
									ItemStack Item4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
									ItemMeta Item4Meta = Item4.getItemMeta();
									Item4Meta.setDisplayName("ßbßlIcewynd Boots");
									Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
									Item4Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item4.setItemMeta(Item4Meta);

									// Icewynd Sword
									ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta Item5Meta = Item5.getItemMeta();
									Item5Meta.setDisplayName("ßbßlIcewynd Sword");
									Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
									Item5Meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
									Item5Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item5.setItemMeta(Item5Meta);

									// Icewynd Bow
									ItemStack Item6 = new ItemStack(Material.BOW, 1);
									ItemMeta Item6Meta = Item6.getItemMeta();
									Item6Meta.setDisplayName("ßbßlIcewynd Bow");
									Item6Meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
									Item6Meta.addEnchant(Enchantment.ARROW_FIRE, 2, true);
									Item6Meta.addEnchant(Enchantment.DURABILITY, 3, true);
									Item6Meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
									Item6.setItemMeta(Item6Meta);

									// Arrows
									ItemStack Item7 = new ItemStack(Material.ARROW, 1);

									// Elixir of Fury
									ItemStack Item8 = new ItemStack(Material.POTION, 1, (byte) 8266);
									ItemMeta Item8Meta = Item6.getItemMeta();
									List<String> lore8 = new ArrayList<String>();
									Item8Meta.setDisplayName("ß4ßlßnElixir of Fury");
									Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
									lore8.add("ßeSpeed II (1:30)");
									lore8.add("ßeStrength II (1:30)");
									lore8.add("ßeFire Resistence (8:00)");
									lore8.add("ß1");
									lore8.add("ßcPrice: ßf5 Coins");
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

	// Quartermaster Item - PVP Potion
	@EventHandler
	public void onDrink(PlayerItemConsumeEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType().equals(Material.POTION)) {
			if (player.getItemInHand().getItemMeta().getDisplayName().equals("ß4ßlßnElixir of Fury")) {
				;
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 1));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1));
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9570, 1));
				player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
			}
		}
	}

	@EventHandler
	public void onClick(PlayerInteractEntityEvent event) {
		// Overwatch Tools
		String IPlayer = event.getRightClicked().getName();
		Player player = event.getPlayer();
		if (player.hasPermission("server.admin")) {
			if (player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
				if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
					event.getPlayer().chat("/invsee " + IPlayer);
				}
			} else if (event.getPlayer().getInventory().getItemInHand().getType() == Material.BOOK) {
				if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
					event.getPlayer().chat("/pinfo " + IPlayer);
				}
			}
		}
	}
}

