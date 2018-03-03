package Crates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Main.Main;

public class LegendaryCrate {
	static Main plugin = Main.getPlugin(Main.class);

	static ItemStack LegendaryCrateItem;
	static List<ItemStack> items = new ArrayList<ItemStack>();

	public static void give(Player player) {
		if (player.getInventory().firstEmpty() == -1) {
			player.sendMessage("§b§l(!)§7 Your inventory is full, dropping crate at your feet!");
			player.getWorld().dropItem(player.getLocation(), LegendaryCrateItem);
		} else {
			player.sendMessage("§b§l(!)§7 A crate has been added to your inventory!");
			player.getInventory().addItem(LegendaryCrateItem);
		}
	}

	public static void open(Player player) {
		// CANCELS EVENT IF PLAYER DOES NOT HAVE 3 OPEN INVENTORY SLOTS
		int count = 0;
		for (ItemStack currentItem : player.getInventory().getContents()) {
			if (currentItem == null) {
				count = count + 1;
			}
		}
		if (count >= 3) {
			// REMOVES ONE FROM THE ITEMSTACK
			ItemStack clicked = player.getItemInHand();
			clicked.setAmount(clicked.getAmount() - 1);
			// OPENS AN INVENTORY SHOWING ALL POSSIBLE ITEMS
			player.openInventory(newInventory(false));
			// AFTER 3 SECONDS, THE INVENTORY WILL "SHUFFLE"
			Inventory LegendaryCrateShuffle = Bukkit.createInventory(null, 27, "§5Shuffling");
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					for (int i = 0; i < 27; i++) {
						LegendaryCrateShuffle.setItem(i, getRandomGlass());
					}
					player.openInventory(LegendaryCrateShuffle);
					Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
						public void run() {
							for (int i = 0; i < 27; i++) {
								LegendaryCrateShuffle.setItem(i, getRandomGlass());
							}
							player.openInventory(LegendaryCrateShuffle);
							Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
								public void run() {
									for (int i = 0; i < 27; i++) {
										LegendaryCrateShuffle.setItem(i, getRandomGlass());
									}
									player.openInventory(LegendaryCrateShuffle);
									Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
										public void run() {
											for (int i = 0; i < 27; i++) {
												LegendaryCrateShuffle.setItem(i, getRandomGlass());
											}
											player.openInventory(LegendaryCrateShuffle);
											Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
												public void run() {
													for (int i = 0; i < 27; i++) {
														LegendaryCrateShuffle.setItem(i, getRandomGlass());
													}
													player.openInventory(LegendaryCrateShuffle);
													Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
														public void run() {
															for (int i = 0; i < 27; i++) {
																LegendaryCrateShuffle.setItem(i, getRandomGlass());
															}
															player.openInventory(LegendaryCrateShuffle);
															Bukkit.getScheduler().runTaskLater(plugin,
																	new Runnable() {
																public void run() {
																	for (int i = 0; i < 27; i++) {
																		LegendaryCrateShuffle.setItem(i,
																				getRandomGlass());
																	}
																	player.openInventory(LegendaryCrateShuffle);
																	Bukkit.getScheduler().runTaskLater(
																			plugin, new Runnable() {
																				public void run() {
																					player.openInventory(
																							newInventory(true));
																				}
																			}, 4);
																}
															}, 4);
														}
													}, 4);
												}
											}, 4);
										}
									}, 4);
								}
							}, 4);
						}
					}, 4);
				}
			}, 3 * 20);
		} else {
			player.sendMessage("§c§l(!)§7 You must have 3 open inventory slots to open that!");
		}
	}

	private static Inventory newInventory(boolean end) {
		if (end) {
			Inventory LegendaryCrate = Bukkit.createInventory(null, 27, "§5Choose 3");
			Collections.shuffle(items);
			for (int i = 0; i < 27; i++) {
				LegendaryCrate.setItem(i, getRandomGlass());
			}
			return LegendaryCrate;
		} else {
			Inventory LegendaryCrate = Bukkit.createInventory(null, 27, "§5Potential");
			Collections.shuffle(items);
			for (int i = 0; i < 27; i++) {
				LegendaryCrate.setItem(i, items.get(i));
			}
			return LegendaryCrate;
		}
	}

	private static ItemStack getRandomGlass() {
		Random rand = new Random();
		// (max index) + minindex
		int index = rand.nextInt(8) + 1;
		if (index == 1) {
			return new ItemStack(Material.WOOL, 1, (byte) 1);
		} else if (index == 2) {
			return new ItemStack(Material.WOOL, 1, (byte) 2);
		} else if (index == 3) {
			return new ItemStack(Material.WOOL, 1, (byte) 3);
		} else if (index == 4) {
			return new ItemStack(Material.WOOL, 1, (byte) 4);
		} else if (index == 5) {
			return new ItemStack(Material.WOOL, 1, (byte) 5);
		} else if (index == 6) {
			return new ItemStack(Material.WOOL, 1, (byte) 6);
		} else if (index == 7) {
			return new ItemStack(Material.WOOL, 1, (byte) 7);
		} else if (index == 8) {
			return new ItemStack(Material.WOOL, 1, (byte) 8);
		} else {
			return new ItemStack(Material.AIR);
		}
	}

	public static ItemStack getRandomItem() {
		Random rand = new Random();
		int index = rand.nextInt(26);
		return items.get(index);
	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public static void load() {
		// THIS IS JUST THE CRATE, NOT THE CONTENTS OF THE CRATE
		LegendaryCrateItem = new ItemStack(Material.CHEST);
		ItemMeta LegendaryCrateMeta = LegendaryCrateItem.getItemMeta();
		LegendaryCrateMeta.setDisplayName("§5Legendary Crate");
		ArrayList<String> LegendaryCrateLore = new ArrayList<String>();
		LegendaryCrateLore.add("§7Right click to open!");
		LegendaryCrateMeta.setLore(LegendaryCrateLore);
		LegendaryCrateItem.setItemMeta(LegendaryCrateMeta);

		// 4 x Cash Prizes
		ItemStack Item1 = new ItemStack(Material.PAPER);
		ItemMeta Item1Meta = Item1.getItemMeta();
		Item1Meta.setDisplayName("§6$150,000 Cash");
		ArrayList<String> Item1Lore = new ArrayList<String>();
		Item1Lore.add("§7Right click this ticket for $150,000!");
		Item1Meta.setLore(Item1Lore);
		Item1.setItemMeta(Item1Meta);
		items.add(Item1);

		ItemStack Item2 = new ItemStack(Material.PAPER);
		ItemMeta Item2Meta = Item2.getItemMeta();
		Item2Meta.setDisplayName("§6$150,000 Cash");
		ArrayList<String> Item2Lore = new ArrayList<String>();
		Item2Lore.add("§7Right click this ticket for $150,000!");
		Item2Meta.setLore(Item2Lore);
		Item2.setItemMeta(Item2Meta);
		items.add(Item2);

		ItemStack Item3 = new ItemStack(Material.PAPER);
		ItemMeta Item3Meta = Item3.getItemMeta();
		Item3Meta.setDisplayName("§6$100,000 Cash");
		ArrayList<String> Item3Lore = new ArrayList<String>();
		Item3Lore.add("§7Right click this ticket for $100,000!");
		Item3Meta.setLore(Item3Lore);
		Item3.setItemMeta(Item3Meta);
		items.add(Item3);

		ItemStack Item4 = new ItemStack(Material.PAPER);
		ItemMeta Item4Meta = Item4.getItemMeta();
		Item4Meta.setDisplayName("§6$100,000 Cash");
		ArrayList<String> Item4Lore = new ArrayList<String>();
		Item4Lore.add("§7Right click this ticket for $100,000!");
		Item4Meta.setLore(Item4Lore);
		Item4.setItemMeta(Item4Meta);
		items.add(Item4);

		// 3 x McMMO Prizes
		ItemStack Item5 = new ItemStack(Material.PAPER);
		ItemMeta Item5Meta = Item5.getItemMeta();
		Item5Meta.setDisplayName("§6100 McMMO Credits");
		ArrayList<String> Item5Lore = new ArrayList<String>();
		Item5Lore.add("§5Right click this ticket for 100 mcmmo credits!");
		Item5Meta.setLore(Item5Lore);
		Item5.setItemMeta(Item5Meta);
		items.add(Item5);

		ItemStack Item6 = new ItemStack(Material.PAPER);
		ItemMeta Item6Meta = Item6.getItemMeta();
		Item6Meta.setDisplayName("§675 McMMO Credits");
		ArrayList<String> Item6Lore = new ArrayList<String>();
		Item6Lore.add("§7Right click this ticket for 75 mcmmo credits!");
		Item6Meta.setLore(Item6Lore);
		Item6.setItemMeta(Item6Meta);
		items.add(Item6);

		ItemStack Item7 = new ItemStack(Material.PAPER);
		ItemMeta Item7Meta = Item7.getItemMeta();
		Item7Meta.setDisplayName("§675 McMMO Credits");
		ArrayList<String> Item7Lore = new ArrayList<String>();
		Item7Lore.add("§7Right click this ticket for 75 mcmmo credits!");
		Item7Meta.setLore(Item7Lore);
		Item7.setItemMeta(Item7Meta);
		items.add(Item7);

		// 1 x Sell Wand
		ItemStack Item8 = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta Item8Meta = Item8.getItemMeta();
		Item8Meta.setDisplayName("§c§lSell Wand");
		ArrayList<String> Item8Lore = new ArrayList<String>();
		Item8Lore.add("§7When you right click a chest in your land,");
		Item8Lore.add("§7all items from within it will be sold!");
		Item8Meta.setLore(Item8Lore);
		Item8.setItemMeta(Item8Meta);
		items.add(Item8);

		// 1 x Harvester Hoe
		ItemStack Item9 = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta Item9Meta = Item9.getItemMeta();
		ArrayList<String> Item9Lore = new ArrayList<String>();
		Item9Lore.add("§5Harvester");
		Item9Meta.setLore(Item9Lore);
		Item9.setItemMeta(Item9Meta);
		items.add(Item9);

		// 6 x Low Tier Spawners
		ItemStack Item10 = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta Item10Meta = Item10.getItemMeta();
		Item10Meta.setDisplayName("§eSQUID §fSpawner");
		Item10.setItemMeta(Item10Meta);
		items.add(Item10);

		ItemStack Item11 = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta Item11Meta = Item11.getItemMeta();
		Item11Meta.setDisplayName("§eSQUID §fSpawner");
		Item11.setItemMeta(Item11Meta);
		items.add(Item11);

		ItemStack Item12 = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta Item12Meta = Item12.getItemMeta();
		Item12Meta.setDisplayName("§eCOW §fSpawner");
		Item12.setItemMeta(Item12Meta);
		items.add(Item12);

		ItemStack Item13 = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta Item13Meta = Item13.getItemMeta();
		Item13Meta.setDisplayName("§ePIG §fSpawner");
		Item13.setItemMeta(Item13Meta);
		items.add(Item13);

		ItemStack Item14 = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta Item14Meta = Item14.getItemMeta();
		Item14Meta.setDisplayName("§eZOMBIE §fSpawner");
		Item14.setItemMeta(Item14Meta);
		items.add(Item14);

		ItemStack Item15 = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta Item15Meta = Item15.getItemMeta();
		Item15Meta.setDisplayName("§eSKELETON §fSpawner");
		Item15.setItemMeta(Item15Meta);
		items.add(Item15);

		// 2 x Disguises
		ItemStack Item16 = new ItemStack(Material.MONSTER_EGG);
		ItemMeta Item16Meta = Item16.getItemMeta();
		Item16.setDurability(EntityType.ZOMBIE.getTypeId());
		Item16Meta.setDisplayName("§6Zombie Disguise");
		ArrayList<String> Item16Lore = new ArrayList();
		Item16Lore.add("§7Right click this to gain access to the Zombie Disguise!");
		Item16Meta.setLore(Item16Lore);
		Item16.setItemMeta(Item16Meta);
		items.add(Item16);

		ItemStack Item17 = new ItemStack(Material.MONSTER_EGG);
		ItemMeta Item17Meta = Item17.getItemMeta();
		Item17.setDurability(EntityType.SKELETON.getTypeId());
		Item17Meta.setDisplayName("§6Skeleton Disguise");
		ArrayList<String> Item17Lore = new ArrayList();
		Item17Lore.add("§7Right click this to gain access to the Skeleton Disguise!");
		Item17Meta.setLore(Item17Lore);
		Item17.setItemMeta(Item17Meta);
		items.add(Item17);

		// 4 x Book Stacks
		ItemStack Item18 = new ItemStack(Material.BOOK, 3);
		ItemMeta Item18Meta = Item18.getItemMeta();
		Item18Meta.setDisplayName("§5Legendary Book");
		ArrayList<String> Item18Lore = new ArrayList();
		Item18Lore.add("§7Right click to open the book!");
		Item18Meta.setLore(Item18Lore);
		Item18.setItemMeta(Item18Meta);
		items.add(Item18);

		ItemStack Item19 = new ItemStack(Material.BOOK, 2);
		ItemMeta Item19Meta = Item19.getItemMeta();
		Item19Meta.setDisplayName("§5Legendary Book");
		ArrayList<String> Item19Lore = new ArrayList();
		Item19Lore.add("§7Right click to open the book!");
		Item19Meta.setLore(Item19Lore);
		Item19.setItemMeta(Item19Meta);
		items.add(Item19);

		ItemStack Item20 = new ItemStack(Material.BOOK, 2);
		ItemMeta Item20Meta = Item20.getItemMeta();
		Item20Meta.setDisplayName("§5Legendary Book");
		ArrayList<String> Item20Lore = new ArrayList();
		Item20Lore.add("§7Right click to open the book!");
		Item20Meta.setLore(Item20Lore);
		Item20.setItemMeta(Item20Meta);
		items.add(Item20);

		ItemStack Item21 = new ItemStack(Material.BOOK, 1);
		ItemMeta Item21Meta = Item21.getItemMeta();
		Item21Meta.setDisplayName("§5Legendary Book");
		ArrayList<String> Item21Lore = new ArrayList();
		Item21Lore.add("§7Right click to open the book!");
		Item21Meta.setLore(Item21Lore);
		Item21.setItemMeta(Item21Meta);
		items.add(Item21);

		// 1 Set
		ItemStack Item22 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta Item22Meta = Item22.getItemMeta();
		Item22Meta.setDisplayName("§5Legendary Sword");
		ArrayList<String> Item22Lore = new ArrayList();
		Item22Lore.add("§5Cannibal");
		Item22Lore.add("§5Thor");
		Item22Meta.setLore(Item22Lore);
		Item22.setItemMeta(Item22Meta);
		Item22.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		Item22.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		Item22.addEnchantment(Enchantment.DURABILITY, 3);
		items.add(Item22);

		ItemStack Item23 = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta Item23Meta = Item23.getItemMeta();
		Item23Meta.setDisplayName("§5Legendary Helmet");
		ArrayList<String> Item23Lore = new ArrayList();
		Item23Lore.add("§5Vision");
		Item23Meta.setLore(Item23Lore);
		Item23.setItemMeta(Item23Meta);
		Item23.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		Item23.addEnchantment(Enchantment.DURABILITY, 3);
		items.add(Item23);

		ItemStack Item24 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta Item24Meta = Item24.getItemMeta();
		Item24Meta.setDisplayName("§5Legendary Chestplate");
		ArrayList<String> Item24Lore = new ArrayList();
		Item24Lore.add("§5Regenerator");
		Item24Meta.setLore(Item24Lore);
		Item24.setItemMeta(Item24Meta);
		Item24.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		Item24.addEnchantment(Enchantment.DURABILITY, 3);
		items.add(Item24);

		ItemStack Item25 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta Item25Meta = Item25.getItemMeta();
		Item25Meta.setDisplayName("§5Legendary Leggings");
		ArrayList<String> Item25Lore = new ArrayList();
		Item25Lore.add("§5Fireborne");
		Item25Meta.setLore(Item25Lore);
		Item25.setItemMeta(Item25Meta);
		Item25.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		Item25.addEnchantment(Enchantment.DURABILITY, 3);
		items.add(Item25);

		ItemStack Item26 = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta Item26Meta = Item26.getItemMeta();
		Item26Meta.setDisplayName("§5Legendary Boots");
		ArrayList<String> Item26Lore = new ArrayList();
		Item26Lore.add("§5Jumper");
		Item26Meta.setLore(Item26Lore);
		Item26.setItemMeta(Item26Meta);
		Item26.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		Item26.addEnchantment(Enchantment.DURABILITY, 3);
		Item26.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
		items.add(Item26);

		ItemStack Item27 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta Item27Meta = Item27.getItemMeta();
		Item27Meta.setDisplayName("§5Legendary Pickaxe");
		ArrayList<String> Item27Lore = new ArrayList();
		Item27Lore.add("§5Extractor");
		Item27Meta.setLore(Item27Lore);
		Item27.setItemMeta(Item27Meta);
		Item27.addUnsafeEnchantment(Enchantment.DIG_SPEED, 6);
		Item27.addEnchantment(Enchantment.DURABILITY, 3);
		items.add(Item27);

	}

}