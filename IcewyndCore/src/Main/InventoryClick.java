package Main;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {
	Main plugin = Main.getPlugin(Main.class);
//hi
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();

		// Inventory space check
		if (player.getInventory().firstEmpty() == -1) {
			player.sendMessage("§cYou do not have the required inventory space.");
			event.setCancelled(true);
			player.closeInventory();
		}else if (inv.getName() == "§8» §9§lItems QuarterMaster") {
			if (item.getType() == Material.TRAPPED_CHEST) {
				ItemStack Item1 = new ItemStack(Material.TRAPPED_CHEST, 1);
				ItemMeta Item1Meta = Item1.getItemMeta();
				List<String> lore1 = new ArrayList<String>();
				Item1Meta.setDisplayName("§c§l§nCrate of TNT");
				lore1.add("§7Empty your inventory then right click!");
				Item1Meta.setLore(lore1);
				Item1.setItemMeta(Item1Meta);
				player.sendMessage("§aPurchased 1 Crate of TNT!");
				player.getInventory().addItem(new ItemStack(Item1));
			} else if (item.getType() == Material.DIAMOND_PICKAXE) {
				ItemStack Item2 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
				ItemMeta Item2Meta = Item2.getItemMeta();
				List<String> lore2 = new ArrayList<String>();
				Item2Meta.setDisplayName("§d§l§nExotic Pickaxe");
				lore2.add("§f");
				lore2.add("§a§lCustom Enchantments:");
				lore2.add("§eShockwave");
				lore2.add("§eSilk Feet");
				Item2Meta.setLore(lore2);
				Item2Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
				Item2Meta.addEnchant(Enchantment.DURABILITY, 3, true);
				Item2.setItemMeta(Item2Meta);
				player.sendMessage("§aPurchased 1 Exotic Pickaxe!");
				player.getInventory().addItem(new ItemStack(Item2));
			} else if (item.getType() == Material.BEDROCK) {
				ItemStack Item3 = new ItemStack(Material.BEDROCK, 8);
				ItemMeta Item3Meta = Item3.getItemMeta();
				List<String> lore3 = new ArrayList<String>();
				Item3Meta.setDisplayName("§8§l§nHardened Bedrock");
				lore3.add("§7Destructible block with 100 block durability!");
				Item3Meta.setLore(lore3);
				Item3.setItemMeta(Item3Meta);
				player.sendMessage("§aPurchased 8 Bedrock!");
				player.getInventory().addItem(new ItemStack(Item3));
			} else if (item.getType() == Material.MONSTER_EGG) {
				ItemStack Item4 = new ItemStack(Material.MONSTER_EGG, 1);
				Item4.setDurability(EntityType.CREEPER.getTypeId());
				ItemMeta Item4Meta = Item4.getItemMeta();
				List<String> lore4 = new ArrayList<String>();
				Item4Meta.setDisplayName("§a§l§nCharged Creeper Egg");
				lore4.add("§7Spawns a charged creeper!");
				Item4Meta.setLore(lore4);
				Item4.setItemMeta(Item4Meta);
				player.sendMessage("§aPurchased 1 Charged Creeper Egg!");
				player.getInventory().addItem(new ItemStack(Item4));
			} else if (item.getType() == Material.MOB_SPAWNER) {
				ItemStack Item5 = new ItemStack(Material.MOB_SPAWNER, 1);
				ItemMeta Item5Meta = Item5.getItemMeta();
				List<String> lore5 = new ArrayList<String>();
				Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
				Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				Item5Meta.setDisplayName("§d§l§nMystery Spawner");
				lore5.add("§7Rewards the player with 1 random spawner out of 5");
				lore5.add("§1");
				lore5.add("§cPrice: §f5 Coins");
				Item5Meta.setLore(lore5);
				Item5.setItemMeta(Item5Meta);
				player.sendMessage("§aPurchased 1 Mystery Spawner");
				player.getInventory().addItem(new ItemStack(Item5));
			} else if (item.getType() == Material.POTION) {
				ItemStack Item6 = new ItemStack(Material.POTION, 1);
				ItemMeta Item6Meta = Item6.getItemMeta();
				List<String> lore6 = new ArrayList<String>();
				Item6Meta.setDisplayName("§4§l§nElixir of Fury");
				lore6.add("§eSpeed II (1:30)");
				lore6.add("§eStrength II (1:30)");
				lore6.add("§eFire Resistence (8:00)");
				Item6Meta.setLore(lore6);
				Item6.setItemMeta(Item6Meta);
				player.sendMessage("§aPurchased 1 Elixir of Fury");
				player.getInventory().addItem(new ItemStack(Item6));
			}
		}else if (inv.getName() == "§5§lKits") {
			if (item.getType() == Material.CHEST) {
				if (event.getCurrentItem().getItemMeta().getDisplayName() == "§d§l§nWarlord Kit") {
					long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Warlord");
					if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
						ItemStack Item2 = new ItemStack(Material.CHEST, 1);
						ItemMeta Item2Meta = Item2.getItemMeta();
						List<String> lore2 = new ArrayList<String>();
						Item2Meta.setDisplayName("§d§l§nWarlord Kit");
						lore2.add("§e§lInformation:");
						lore2.add("§8§l » §eCooldown:  §7");
						lore2.add("§7");
						lore2.add("§e§lContents:");
						lore2.add("§8§l » §7Protection 3 Unbreaking 1 Armour Set");
						lore2.add("§8§l » §7Sharpness 3 Sword");
						lore2.add("§8§l » §7Power 3 Bow");
						lore2.add("§8§l » §7128 Arrows");
						Item2Meta.setLore(lore2);
						Item2.setItemMeta(Item2Meta);
						player.getInventory().addItem(Item2);
						plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Warlord", (System.currentTimeMillis() + 86400000));
						plugin.saveConfig();
					}
					else {
						long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
						long secondsTillKit = millisTillKit / 1000;
						long hoursTillKit = secondsTillKit / (60*60);
						long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
						secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
						player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
					}
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§c§l§nEmporer Kit") {
					long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Emporer");
					if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
						ItemStack Item3 = new ItemStack(Material.CHEST, 1);
						ItemMeta Item3Meta = Item3.getItemMeta();
						List<String> lore3 = new ArrayList<String>();
						Item3Meta.setDisplayName("§c§l§nEmporer Kit");
						lore3.add("§e§lInformation:");
						lore3.add("§8§l » §eCooldown:  §7");
						lore3.add("§7");
						lore3.add("§e§lContents:");
						lore3.add("§8§l » §7Protection 4 Unbreaking 1 Armour Set");
						lore3.add("§8§l » §7Sharpness 4 Sword");
						lore3.add("§8§l » §7Power 4 Bow");
						lore3.add("§8§l » §7128 Arrows");
						lore3.add("§8§l » §71 Elixir of Fury");
						Item3Meta.setLore(lore3);
						Item3.setItemMeta(Item3Meta);
						player.getInventory().addItem(Item3);
						plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Emporer", (System.currentTimeMillis() + 86400000));
						plugin.saveConfig();
					}
					else {
						long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
						long secondsTillKit = millisTillKit / 1000;
						long hoursTillKit = secondsTillKit / (60*60);
						long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
						secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
						player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
					}
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§9§l§nGod Kit") {
					long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.God");
					if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
						ItemStack Item4 = new ItemStack(Material.CHEST, 1);
						ItemMeta Item4Meta = Item4.getItemMeta();
						List<String> lore4 = new ArrayList<String>();
						Item4Meta.setDisplayName("§9§l§nGod Kit");
						lore4.add("§e§lInformation:");
						lore4.add("§8§l » §eCooldown:  §7");
						lore4.add("§7");
						lore4.add("§e§lContents:");
						lore4.add("§8§l » §7Protection 4 Unbreaking 2 Armour Set");
						lore4.add("§8§l » §7Sharpness 5 Fire 1 Sword");
						lore4.add("§8§l » §7Power 5 Flame 1 Bow");
						lore4.add("§8§l » §7128 Arrows");
						lore4.add("§8§l » §72 Elixir of Fury");
						Item4Meta.setLore(lore4);
						Item4Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
						Item4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						Item4.setItemMeta(Item4Meta);
						player.getInventory().addItem(Item4);
						plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.God", (System.currentTimeMillis() + 86400000));
						plugin.saveConfig();
					}
					else {
						long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
						long secondsTillKit = millisTillKit / 1000;
						long hoursTillKit = secondsTillKit / (60*60);
						long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
						secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
						player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
					}
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§b§l§nIcewynd Kit") {
					long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Icewynd");
					if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
						ItemStack Item5 = new ItemStack(Material.CHEST, 1);
						ItemMeta Item5Meta = Item5.getItemMeta();
						List<String> lore5 = new ArrayList<String>();
						Item5Meta.setDisplayName("§b§l§nIcewynd Kit");
						lore5.add("§e§lInformation:");
						lore5.add("§8§l » §eCooldown:  §7");
						lore5.add("§7");
						lore5.add("§e§lContents:");
						lore5.add("§8§l » §7Protection 4 Unbreaking 3 Armour Set");
						lore5.add("§8§l » §7Sharpness 5 Fire 2 Unbreaking 3 Sword");
						lore5.add("§8§l » §7Power 5 Infinity 1 Unbreaking 3 Flame 2 Bow");
						lore5.add("§8§l » §71 Arrow");
						lore5.add("§8§l » §73 Elixir of Fury");
						Item5Meta.setLore(lore5);
						Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
						Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						Item5.setItemMeta(Item5Meta);
						player.getInventory().addItem(Item5);
						plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Icewynd", (System.currentTimeMillis() + 86400000));
						plugin.saveConfig();
					}
					else {
						long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
						long secondsTillKit = millisTillKit / 1000;
						long hoursTillKit = secondsTillKit / (60*60);
						long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
						secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
						player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
					}
				}
				else if (event.getCurrentItem().getItemMeta().getDisplayName() == "§2§l§nFighter Kit") {
					long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Fighter");
					if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
						ItemStack Item1 = new ItemStack(Material.CHEST, 1);
						ItemMeta Item1Meta = Item1.getItemMeta();
						List<String> lore1 = new ArrayList<String>();
						Item1Meta.setDisplayName("§2§l§nFighter Kit");
						lore1.add("§e§lInformation:");
						lore1.add("§8§l » §eCooldown:  §7");
						lore1.add("§7");
						lore1.add("§e§lContents:");
						lore1.add("§8§l » §7Protection 2 Unbreaking 1 Armour Set");
						lore1.add("§8§l » §7Sharpness 2 Sword");
						lore1.add("§8§l » §7Power 2 Bow");
						lore1.add("§8§l » §7128 Arrows");
						Item1Meta.setLore(lore1);
						Item1.setItemMeta(Item1Meta);
						player.getInventory().addItem(Item1);
						plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Fighter", (System.currentTimeMillis() + 86400000));
						plugin.saveConfig();
					}
					else {
						long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
						long secondsTillKit = millisTillKit / 1000;
						long hoursTillKit = secondsTillKit / (60*60);
						long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
						secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
						player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
					}
				}
			}
			event.setCancelled(true);
			player.closeInventory();
		}
	}
}


