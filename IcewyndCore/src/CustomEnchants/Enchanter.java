package CustomEnchants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchanter implements Listener {

	public static void openInventory(Player player) {
		Inventory anvil = Bukkit.createInventory(null, 27, "§c§l>> §8Enchanter §c§l<<");

		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName(" ");
		empty.setItemMeta(emptyMeta);
		@SuppressWarnings("unused")
		ItemStack slot = new ItemStack(Material.AIR);

		ItemStack legendary = new ItemStack(Material.BOOK);
		ItemMeta legendaryMeta = legendary.getItemMeta();
		legendaryMeta.setDisplayName("§f§l>> §5Legendary Book §f§l<<");
		ArrayList<String> legendaryLore = new ArrayList<String>();
		legendaryLore.add("§7Left click to purchase a legendary book!");
		legendaryLore.add("§7Right click to view all legendary enchantments!");
		legendaryLore.add("§3Cost: §7800XP!");
		legendaryMeta.setLore(legendaryLore);
		legendary.setItemMeta(legendaryMeta);

		ItemStack exotic = new ItemStack(Material.BOOK);
		ItemMeta exoticMeta = exotic.getItemMeta();
		exoticMeta.setDisplayName("§f§l>> §eExotic Book §f§l<<");
		ArrayList<String> exoticLore = new ArrayList<String>();
		exoticLore.add("§7Left click to purchase an exotic book!");
		exoticLore.add("§7Right click to view all exotic enchantments!");
		exoticLore.add("§3Cost: §71000XP!");
		exoticMeta.setLore(exoticLore);
		exotic.setItemMeta(exoticMeta);

		ItemStack[] items = { empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty,
				legendary, empty, exotic, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty, empty,
				empty };
		anvil.setContents(items);
		player.openInventory(anvil);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getClickedInventory() != null) {
			if (event.getClickedInventory().getName().equals("§c§l>> §8Enchanter §c§l<<")) {
				event.setCancelled(true);
				ItemStack item = event.getCurrentItem();
				if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
					String name = item.getItemMeta().getDisplayName();
					if (name == "§f§l>> §eExotic Book §f§l<<") {
						if (event.isRightClick()) {
							openExoticInventory((Player) event.getWhoClicked());
						} else {
							Player player = (Player) event.getWhoClicked();
							if (player.getExp() - 1000 >= 0) {
								player.setExp(player.getExp() - 1000);
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§b§l(!)§7 Your inventory is full, dropping item at your feet!");
									player.getWorld().dropItem(player.getLocation(), ExoticBook());
								} else {
									player.sendMessage("§b§l(!)§7 Your new item has been added to your inventory!");
									player.getInventory().addItem(ExoticBook());
								}
							} else {
								player.sendMessage("§c§l(!)§7 You cannot afford to buy that!");
							}
						}
					} else if (name == "§f§l>> §5Legendary Book §f§l<<") {
						if (event.isRightClick()) {
							openLegendaryInventory((Player) event.getWhoClicked());
						} else {
							Player player = (Player) event.getWhoClicked();
							if (player.getExp() - 800 >= 0) {
								player.setExp(player.getExp() - 800);
								if (player.getInventory().firstEmpty() == -1) {
									player.sendMessage("§b§l(!)§7 Your inventory is full, dropping item at your feet!");
									player.getWorld().dropItem(player.getLocation(), LegendaryBook());
								} else {
									player.sendMessage("§b§l(!)§7 Your new item has been added to your inventory!");
									player.getInventory().addItem(LegendaryBook());
								}
							} else {
								player.sendMessage("§c§l(!)§7 You cannot afford to buy that!");
							}
						}
					}
				}
			} else if (event.getClickedInventory().getName().equals("§f§l>> §5Legendary §f§l<<")) {
				event.setCancelled(true);
				if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()
						&& event.getCurrentItem().getItemMeta().getDisplayName().equals("§c§l>> §fBack §c§l<<")) {
					openInventory((Player) event.getWhoClicked());
				}
			} else if (event.getClickedInventory().getName().equals("§f§l>> §eExotic §f§l<<")) {
				event.setCancelled(true);
				if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()
						&& event.getCurrentItem().getItemMeta().getDisplayName().equals("§c§l>> §fBack §c§l<<")) {
					openInventory((Player) event.getWhoClicked());
				}
			}
		}
	}

	public ItemStack ExoticBook() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§eExotic Book");
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add("§7Right click to open the book!");
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack LegendaryBook() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§5Legendary Book");
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add("§7Right click to open the book!");
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler
	public void onPlayerInterract(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			ItemStack item = event.getPlayer().getItemInHand();
			if (item.getType().equals(Material.BOOK) && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
				if (item.getItemMeta().getDisplayName().equals("§5Legendary Book")) {
					Player player = event.getPlayer();
					if (player.getInventory().firstEmpty() == -1) {
						player.getWorld().dropItem(player.getLocation(), getRandomEnchant("legendary"));
					} else {
						player.getInventory().addItem(getRandomEnchant("legendary"));
					}
					player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
				} else if (item.getItemMeta().getDisplayName().equals("§eExotic Book")) {
					Player player = event.getPlayer();
					if (player.getInventory().firstEmpty() == -1) {
						player.getWorld().dropItem(player.getLocation(), getRandomEnchant("exotic"));
					} else {
						player.getInventory().addItem(getRandomEnchant("exotic"));
					}
					player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
				}
			}
		}
	}

	public ItemStack getRandomEnchant(String type) {
		if (type == "exotic") {
			List<ItemStack> items = new ArrayList<ItemStack>();

			ItemStack Item1 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item1Meta = Item1.getItemMeta();
			ArrayList<String> Item1Lore = new ArrayList<String>();
			Item1Lore.add("§eInsight");
			Item1Lore.add("§3Helmet Enchant");
			Item1Lore.add("§7Allows you to see particle effects around");
			Item1Lore.add("§7the weakest enemy in your facinity!");
			Item1Meta.setLore(Item1Lore);
			Item1.setItemMeta(Item1Meta);
			items.add(Item1);

			ItemStack Item2 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item2Meta = Item2.getItemMeta();
			ArrayList<String> Item2Lore = new ArrayList<String>();
			Item2Lore.add("§eReflection");
			Item2Lore.add("§3Chestplate Enchant");
			Item2Lore.add("§7Gives a chance to reflect a percentage of");
			Item2Lore.add("§7damage received back towards the enemy!");
			Item2Meta.setLore(Item2Lore);
			Item2.setItemMeta(Item2Meta);
			items.add(Item2);

			ItemStack Item3 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item3Meta = Item3.getItemMeta();
			ArrayList<String> Item3Lore = new ArrayList<String>();
			Item3Lore.add("§eInferno");
			Item3Lore.add("§3Leggings Enchant");
			Item3Lore.add("§7Applies an unlimited fire resistance effect!");
			Item3Meta.setLore(Item3Lore);
			Item3.setItemMeta(Item3Meta);
			items.add(Item3);

			ItemStack Item4 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item4Meta = Item4.getItemMeta();
			ArrayList<String> Item4Lore = new ArrayList<String>();
			Item4Lore.add("§eRunner");
			Item4Lore.add("§3Boot Enchant");
			Item4Lore.add("§7Applies an unlimited speed 2 effect!");
			Item4Meta.setLore(Item4Lore);
			Item4.setItemMeta(Item4Meta);
			items.add(Item4);

			ItemStack Item5 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item5Meta = Item5.getItemMeta();
			ArrayList<String> Item5Lore = new ArrayList<String>();
			Item5Lore.add("§eShockwave");
			Item5Lore.add("§3Pickaxe Enchant");
			Item5Lore.add("§7Mines all blocks in a 3x3 area!");
			Item5Meta.setLore(Item5Lore);
			Item5.setItemMeta(Item5Meta);
			items.add(Item5);

			ItemStack Item6 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item6Meta = Item6.getItemMeta();
			ArrayList<String> Item6Lore = new ArrayList<String>();
			Item6Lore.add("§eShadowstep");
			Item6Lore.add("§3Sword Enchant");
			Item6Lore.add("§7Chance of teleporting a small distance");
			Item6Lore.add("§7when hitting an enemy player in warzone!");
			Item6Meta.setLore(Item6Lore);
			Item6.setItemMeta(Item6Meta);
			items.add(Item6);

			ItemStack Item7 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item7Meta = Item7.getItemMeta();
			ArrayList<String> Item7Lore = new ArrayList<String>();
			Item7Lore.add("§eOverride");
			Item7Lore.add("§3Sword Enchant");
			Item7Lore.add("§7Chance to remove fire resistance from your enemy!");
			Item7Meta.setLore(Item7Lore);
			Item7.setItemMeta(Item7Meta);
			items.add(Item7);

			ItemStack Item8 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item8Meta = Item8.getItemMeta();
			ArrayList<String> Item8Lore = new ArrayList<String>();
			Item8Lore.add("§eSilk Feet");
			Item8Lore.add("§3Pickaxe Enchant");
			Item8Lore.add("§7Drops any mined spawners at your feet instead");
			Item8Lore.add("§7of the block location, no silktouch required!");
			Item8Meta.setLore(Item8Lore);
			Item8.setItemMeta(Item8Meta);
			items.add(Item8);

			ItemStack Item9 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item9Meta = Item9.getItemMeta();
			ArrayList<String> Item9Lore = new ArrayList<String>();
			Item9Lore.add("§eFirefly");
			Item9Lore.add("§3Bow Enchant");
			Item9Lore.add("§7Chance for arrows shot to become explosive!");
			Item9Meta.setLore(Item9Lore);
			Item9.setItemMeta(Item9Meta);
			items.add(Item9);

			Random rand = new Random();
			int index = rand.nextInt(9) + 0;
			return items.get(index);

		} else if (type == "legendary") {
			List<ItemStack> items = new ArrayList<ItemStack>();

			ItemStack Item1 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item1Meta = Item1.getItemMeta();
			ArrayList<String> Item1Lore = new ArrayList<String>();
			Item1Lore.add("§5Jumper");
			Item1Lore.add("§3Boot Enchant");
			Item1Lore.add("§7Applies an unlimited jump boost effect!");
			Item1Meta.setLore(Item1Lore);
			Item1.setItemMeta(Item1Meta);
			items.add(Item1);

			ItemStack Item2 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item2Meta = Item2.getItemMeta();
			ArrayList<String> Item2Lore = new ArrayList<String>();
			Item2Lore.add("§5Fireborne");
			Item2Lore.add("§3Leggings Enchant");
			Item2Lore.add("§7Gives a chance to convert fire damage to extra health!");
			Item2Meta.setLore(Item2Lore);
			Item2.setItemMeta(Item2Meta);
			items.add(Item2);

			ItemStack Item3 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item3Meta = Item3.getItemMeta();
			ArrayList<String> Item3Lore = new ArrayList<String>();
			Item3Lore.add("§5Regenerator");
			Item3Lore.add("§3Chestplate Enchant");
			Item3Lore.add("§7Gives a chance to recieve a short regeneration");
			Item3Lore.add("§72 effect when you are damaged by an enemy!");
			Item3Meta.setLore(Item3Lore);
			Item3.setItemMeta(Item3Meta);
			items.add(Item3);

			ItemStack Item4 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item4Meta = Item4.getItemMeta();
			ArrayList<String> Item4Lore = new ArrayList<String>();
			Item4Lore.add("§5Vision");
			Item4Lore.add("§3Helmet Enchant");
			Item4Lore.add("§7Applies an unlimited night vision effect!");
			Item4Meta.setLore(Item4Lore);
			Item4.setItemMeta(Item4Meta);
			items.add(Item4);

			ItemStack Item5 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item5Meta = Item5.getItemMeta();
			ArrayList<String> Item5Lore = new ArrayList<String>();
			Item5Lore.add("§5Extractor");
			Item5Lore.add("§3Pickaxe Enchant");
			Item5Lore.add("§7Extracts all connected ores to the ore you mine!");
			Item5Meta.setLore(Item5Lore);
			Item5.setItemMeta(Item5Meta);
			items.add(Item5);

			ItemStack Item6 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item6Meta = Item6.getItemMeta();
			ArrayList<String> Item6Lore = new ArrayList<String>();
			Item6Lore.add("§5Cannibal");
			Item6Lore.add("§3Sword Enchant");
			Item6Lore.add("§7Gives a chance to steal hunger from your enemy!");
			Item6Meta.setLore(Item6Lore);
			Item6.setItemMeta(Item6Meta);
			items.add(Item6);

			ItemStack Item7 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item7Meta = Item7.getItemMeta();
			ArrayList<String> Item7Lore = new ArrayList<String>();
			Item7Lore.add("§5Thor");
			Item7Lore.add("§3Sword Enchant");
			Item7Lore.add("§7Shoots all nearby players in the");
			Item7Lore.add("§7air whenever you get a kill!");
			Item7Meta.setLore(Item7Lore);
			Item7.setItemMeta(Item7Meta);
			items.add(Item7);

			ItemStack Item8 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item8Meta = Item8.getItemMeta();
			ArrayList<String> Item8Lore = new ArrayList<String>();
			Item8Lore.add("§5Medic");
			Item8Lore.add("§3Bow Enchant");
			Item8Lore.add("§7Arrows shot heal the player it lands on!");
			Item8Meta.setLore(Item8Lore);
			Item8.setItemMeta(Item8Meta);
			items.add(Item8);

			ItemStack Item9 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item9Meta = Item9.getItemMeta();
			ArrayList<String> Item9Lore = new ArrayList<String>();
			Item9Lore.add("§5Chopper");
			Item9Lore.add("§3Axe Enchant");
			Item9Lore.add("§7All wood connected to the block you");
			Item9Lore.add("§7destroy will also be demolished!");
			Item9Meta.setLore(Item9Lore);
			Item9.setItemMeta(Item9Meta);
			items.add(Item9);

			ItemStack Item10 = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta Item10Meta = Item10.getItemMeta();
			ArrayList<String> Item10Lore = new ArrayList<String>();
			Item10Lore.add("§5Harvester");
			Item10Lore.add("§3Hoe Enchant");
			Item10Lore.add("§7All sugarcane blocks destroyed will");
			Item10Lore.add("§7go straight to your inventory!");
			Item10Meta.setLore(Item10Lore);
			Item10.setItemMeta(Item10Meta);
			items.add(Item10);

			Random rand = new Random();
			int index = rand.nextInt(10) + 0;
			return items.get(index);
		} else {
			return new ItemStack(Material.AIR);
		}
	}

	public void openLegendaryInventory(Player player) {

		Inventory inv = Bukkit.createInventory(null, 18, "§f§l>> §5Legendary §f§l<<");

		ItemStack Item1 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item1Meta = Item1.getItemMeta();
		ArrayList<String> Item1Lore = new ArrayList<String>();
		Item1Lore.add("§5Jumper");
		Item1Lore.add("§3Boot Enchant");
		Item1Lore.add("§7Applies an unlimited jump boost effect!");
		Item1Meta.setLore(Item1Lore);
		Item1.setItemMeta(Item1Meta);
		inv.setItem(0, Item1);

		ItemStack Item2 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item2Meta = Item2.getItemMeta();
		ArrayList<String> Item2Lore = new ArrayList<String>();
		Item2Lore.add("§5Fireborne");
		Item2Lore.add("§3Leggings Enchant");
		Item2Lore.add("§7Gives a chance to convert fire damage to extra health!");
		Item2Meta.setLore(Item2Lore);
		Item2.setItemMeta(Item2Meta);
		inv.setItem(1, Item2);

		ItemStack Item3 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item3Meta = Item3.getItemMeta();
		ArrayList<String> Item3Lore = new ArrayList<String>();
		Item3Lore.add("§5Regenerator");
		Item3Lore.add("§3Chestplate Enchant");
		Item3Lore.add("§7Gives a chance to recieve a short regeneration");
		Item3Lore.add("§72 effect when you are damaged by an enemy!");
		Item3Meta.setLore(Item3Lore);
		Item3.setItemMeta(Item3Meta);
		inv.setItem(2, Item3);

		ItemStack Item4 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item4Meta = Item4.getItemMeta();
		ArrayList<String> Item4Lore = new ArrayList<String>();
		Item4Lore.add("§5Vision");
		Item4Lore.add("§3Helmet Enchant");
		Item4Lore.add("§7Applies an unlimited night vision effect!");
		Item4Meta.setLore(Item4Lore);
		Item4.setItemMeta(Item4Meta);
		inv.setItem(3, Item4);

		ItemStack Item5 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item5Meta = Item5.getItemMeta();
		ArrayList<String> Item5Lore = new ArrayList<String>();
		Item5Lore.add("§5Extractor");
		Item5Lore.add("§3Pickaxe Enchant");
		Item5Lore.add("§7Extracts all connected ores to the ore you mine!");
		Item5Meta.setLore(Item5Lore);
		Item5.setItemMeta(Item5Meta);
		inv.setItem(4, Item5);

		ItemStack Item6 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item6Meta = Item6.getItemMeta();
		ArrayList<String> Item6Lore = new ArrayList<String>();
		Item6Lore.add("§5Cannibal");
		Item6Lore.add("§3Sword Enchant");
		Item6Lore.add("§7Gives a chance to steal hunger from your enemy!");
		Item6Meta.setLore(Item6Lore);
		Item6.setItemMeta(Item6Meta);
		inv.setItem(5, Item6);

		ItemStack Item7 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item7Meta = Item7.getItemMeta();
		ArrayList<String> Item7Lore = new ArrayList<String>();
		Item7Lore.add("§5Thor");
		Item7Lore.add("§3Sword Enchant");
		Item7Lore.add("§7Shoots all nearby players in the");
		Item7Lore.add("§7air whenever you get a kill!");
		Item7Meta.setLore(Item7Lore);
		Item7.setItemMeta(Item7Meta);
		inv.setItem(6, Item7);

		ItemStack Item8 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item8Meta = Item8.getItemMeta();
		ArrayList<String> Item8Lore = new ArrayList<String>();
		Item8Lore.add("§5Medic");
		Item8Lore.add("§3Bow Enchant");
		Item8Lore.add("§7Arrows shot heal the player it lands on!");
		Item8Meta.setLore(Item8Lore);
		Item8.setItemMeta(Item8Meta);
		inv.setItem(7, Item8);

		ItemStack Item9 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item9Meta = Item9.getItemMeta();
		ArrayList<String> Item9Lore = new ArrayList<String>();
		Item9Lore.add("§5Chopper");
		Item9Lore.add("§3Axe Enchant");
		Item9Lore.add("§7All wood connected to the block you");
		Item9Lore.add("§7destroy will also be demolished!");
		Item9Meta.setLore(Item9Lore);
		Item9.setItemMeta(Item9Meta);
		inv.setItem(8, Item9);

		ItemStack Item10 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item10Meta = Item10.getItemMeta();
		ArrayList<String> Item10Lore = new ArrayList<String>();
		Item10Lore.add("§5Harvester");
		Item10Lore.add("§3Hoe Enchant");
		Item10Lore.add("§7All sugarcane blocks destroyed will");
		Item10Lore.add("§7go straight to your inventory!");
		Item10Meta.setLore(Item10Lore);
		Item10.setItemMeta(Item10Meta);
		inv.setItem(9, Item10);

		ItemStack Back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName("§c§l>> §fBack §c§l<<");
		ArrayList<String> BackLore = new ArrayList<String>();
		BackLore.add("§c§l> §7Click to go back to the previous menu!");
		BackMeta.setLore(BackLore);
		Back.setItemMeta(BackMeta);
		inv.setItem(17, Back);

		player.openInventory(inv);
	}

	public void openExoticInventory(Player player) {

		Inventory inv = Bukkit.createInventory(null, 18, "§f§l>> §eExotic §f§l<<");

		ItemStack Item1 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item1Meta = Item1.getItemMeta();
		ArrayList<String> Item1Lore = new ArrayList<String>();
		Item1Lore.add("§eInsight");
		Item1Lore.add("§3Helmet Enchant");
		Item1Lore.add("§7Allows you to see particle effects around");
		Item1Lore.add("§7the weakest enemy in your facinity!");
		Item1Meta.setLore(Item1Lore);
		Item1.setItemMeta(Item1Meta);
		inv.setItem(0, Item1);

		ItemStack Item2 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item2Meta = Item2.getItemMeta();
		ArrayList<String> Item2Lore = new ArrayList<String>();
		Item2Lore.add("§eReflection");
		Item2Lore.add("§3Chestplate Enchant");
		Item2Lore.add("§7Gives a chance to reflect a percentage of");
		Item2Lore.add("§7damage received back towards the enemy!");
		Item2Meta.setLore(Item2Lore);
		Item2.setItemMeta(Item2Meta);
		inv.setItem(1, Item2);

		ItemStack Item3 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item3Meta = Item3.getItemMeta();
		ArrayList<String> Item3Lore = new ArrayList<String>();
		Item3Lore.add("§eInferno");
		Item3Lore.add("§3Leggings Enchant");
		Item3Lore.add("§7Applies an unlimited fire resistance effect!");
		Item3Meta.setLore(Item3Lore);
		Item3.setItemMeta(Item3Meta);
		inv.setItem(2, Item3);

		ItemStack Item4 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item4Meta = Item4.getItemMeta();
		ArrayList<String> Item4Lore = new ArrayList<String>();
		Item4Lore.add("§eRunner");
		Item4Lore.add("§3Boot Enchant");
		Item4Lore.add("§7Applies an unlimited speed 2 effect!");
		Item4Meta.setLore(Item4Lore);
		Item4.setItemMeta(Item4Meta);
		inv.setItem(3, Item4);

		ItemStack Item5 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item5Meta = Item5.getItemMeta();
		ArrayList<String> Item5Lore = new ArrayList<String>();
		Item5Lore.add("§eShockwave");
		Item5Lore.add("§3Pickaxe Enchant");
		Item5Lore.add("§7Mines all blocks in a 3x3 area!");
		Item5Meta.setLore(Item5Lore);
		Item5.setItemMeta(Item5Meta);
		inv.setItem(4, Item5);

		ItemStack Item6 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item6Meta = Item6.getItemMeta();
		ArrayList<String> Item6Lore = new ArrayList<String>();
		Item6Lore.add("§eShadowstep");
		Item6Lore.add("§3Sword Enchant");
		Item6Lore.add("§7Chance of teleporting a small distance");
		Item6Lore.add("§7when hitting an enemy player in warzone!");
		Item6Meta.setLore(Item6Lore);
		Item6.setItemMeta(Item6Meta);
		inv.setItem(5, Item6);

		ItemStack Item7 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item7Meta = Item7.getItemMeta();
		ArrayList<String> Item7Lore = new ArrayList<String>();
		Item7Lore.add("§eOverride");
		Item7Lore.add("§3Sword Enchant");
		Item7Lore.add("§7Chance to remove fire resistance from your enemy!");
		Item7Meta.setLore(Item7Lore);
		Item7.setItemMeta(Item7Meta);
		inv.setItem(6, Item7);

		ItemStack Item8 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item8Meta = Item8.getItemMeta();
		ArrayList<String> Item8Lore = new ArrayList<String>();
		Item8Lore.add("§eSilk Feet");
		Item8Lore.add("§3Pickaxe Enchant");
		Item8Lore.add("§7Drops any mined spawners at your feet instead");
		Item8Lore.add("§7of the block location, no silktouch required!");
		Item8Meta.setLore(Item8Lore);
		Item8.setItemMeta(Item8Meta);
		inv.setItem(7, Item8);

		ItemStack Item9 = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta Item9Meta = Item9.getItemMeta();
		ArrayList<String> Item9Lore = new ArrayList<String>();
		Item9Lore.add("§eFirefly");
		Item9Lore.add("§3Bow Enchant");
		Item9Lore.add("§7Chance for arrows shot to become explosive!");
		Item9Meta.setLore(Item9Lore);
		Item9.setItemMeta(Item9Meta);
		inv.setItem(8, Item9);

		ItemStack Back = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta BackMeta = Back.getItemMeta();
		BackMeta.setDisplayName("§c§l>> §fBack §c§l<<");
		ArrayList<String> BackLore = new ArrayList<String>();
		BackLore.add("§c§l> §7Click to go back to the previous menu!");
		BackMeta.setLore(BackLore);
		Back.setItemMeta(BackMeta);
		inv.setItem(17, Back);

		player.openInventory(inv);
	}

}