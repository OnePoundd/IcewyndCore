package Commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import Main.Main;

public class Kits implements CommandExecutor, Listener{
	
	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kit")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				openKitInventory(player);
			}else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("fighter") && player.hasPermission("kits.fighter")) {
					giveKitFighter(player);
				}else if (args[0].equalsIgnoreCase("warlord") && player.hasPermission("kits.warlord")) {
					giveKitWarlord(player);
				}else if (args[0].equalsIgnoreCase("emporer") && player.hasPermission("kits.emporer")) {
					giveKitEmporer(player);
				}else if (args[0].equalsIgnoreCase("god")) {
					giveKitGod(player);
				}else if (args[0].equalsIgnoreCase("Icewynd")) {
					giveKitIcewynd(player);
				}else {
					player.sendMessage("§cYou do not have permission to use that kit!");
				}
			}
		}
		return false;
	}

	public void openKitInventory(Player player) {
		Inventory kit = Bukkit.createInventory(null, InventoryType.HOPPER, "§5§lKits");
		
		//Rank 1 - Fighter
		long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Fighter");
		String cooldown;
		if (millisWhenKitIsAvailable == 0) {
			cooldown = "0hours, 0minutes, 0seconds.";
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			cooldown = hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.";
		}
		ItemStack Item1 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item1Meta = Item1.getItemMeta();
		List<String> lore1 = new ArrayList<String>();
		Item1Meta.setDisplayName("§2§l§nFighter Kit");
		lore1.add("§e§lInformation:");
		lore1.add("§8§l » §eCooldown:  §7" + cooldown);
		lore1.add("§7");
		lore1.add("§e§lContents:");
		lore1.add("§8§l » §7Protection 2 Unbreaking 1 Armour Set");
		lore1.add("§8§l » §7Sharpness 2 Sword");
		lore1.add("§8§l » §7Power 2 Bow");
		lore1.add("§8§l » §7128 Arrows");
		Item1Meta.setLore(lore1);
		Item1.setItemMeta(Item1Meta);
		
		//Rank 2 - Warlord
		millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Warlord");
		if (millisWhenKitIsAvailable == 0) {
			cooldown = "0hours, 0minutes, 0seconds.";
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			cooldown = hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.";
		}
		ItemStack Item2 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item2Meta = Item2.getItemMeta();
		List<String> lore2 = new ArrayList<String>();
		Item2Meta.setDisplayName("§d§l§nWarlord Kit");
		lore2.add("§e§lInformation:");
		lore2.add("§8§l » §eCooldown:  §7" + cooldown);
		lore2.add("§7");
		lore2.add("§e§lContents:");
		lore2.add("§8§l » §7Protection 3 Unbreaking 1 Armour Set");
		lore2.add("§8§l » §7Sharpness 3 Sword");
		lore2.add("§8§l » §7Power 3 Bow");
		lore2.add("§8§l » §7128 Arrows");
		Item2Meta.setLore(lore2);
		Item2.setItemMeta(Item2Meta);		
		
		//Rank 3 - Emporer
		millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Emporer");
		if (millisWhenKitIsAvailable == 0) {
			cooldown = "0hours, 0minutes, 0seconds.";
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			cooldown = hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.";
		}
		ItemStack Item3 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item3Meta = Item3.getItemMeta();
		List<String> lore3 = new ArrayList<String>();
		Item3Meta.setDisplayName("§c§l§nEmporer Kit");
		lore3.add("§e§lInformation:");
		lore3.add("§8§l » §eCooldown:  §7" + cooldown);
		lore3.add("§7");
		lore3.add("§e§lContents:");
		lore3.add("§8§l » §7Protection 4 Unbreaking 1 Armour Set");
		lore3.add("§8§l » §7Sharpness 4 Sword");
		lore3.add("§8§l » §7Power 4 Bow");
		lore3.add("§8§l » §7128 Arrows");
		lore3.add("§8§l » §71 Elixir of Fury");
		Item3Meta.setLore(lore3);
		Item3.setItemMeta(Item3Meta);
		
		//Rank 4 - God
		millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.God");
		if (millisWhenKitIsAvailable == 0) {
			cooldown = "0hours, 0minutes, 0seconds.";
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			cooldown = hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.";
		}
		ItemStack Item4 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item4Meta = Item4.getItemMeta();
		List<String> lore4 = new ArrayList<String>();
		Item4Meta.setDisplayName("§9§l§nGod Kit");
		lore4.add("§e§lInformation:");
		lore4.add("§8§l » §eCooldown:  §7" + cooldown);
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
		
		//Rank 5 - Icewynd
		millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Icewynd");
		if (millisWhenKitIsAvailable == 0) {
			cooldown = "0hours, 0minutes, 0seconds.";
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			cooldown = hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.";
		}
		ItemStack Item5 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item5Meta = Item5.getItemMeta();
		List<String> lore5 = new ArrayList<String>();
		Item5Meta.setDisplayName("§b§l§nIcewynd Kit");
		lore5.add("§e§lInformation:");
		lore5.add("§8§l » §eCooldown:  §7" + cooldown);
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

		kit.setItem(0, Item1);
		kit.setItem(1, Item2);
		kit.setItem(2, Item3);
		kit.setItem(3, Item4);
		kit.setItem(4, Item5);
		player.openInventory(kit);
	}
	
	public void giveKitFighter(Player player) {
		long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Fighter");
		if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
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

			// Cooldown
			plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Fighter", (System.currentTimeMillis() + 86400000));
			plugin.saveConfig();
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
		}
	}
	
	public void giveKitWarlord(Player player) {
		long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Warlord");
		if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
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

			// Cooldown
			plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Warlord", (System.currentTimeMillis() + 86400000));
			plugin.saveConfig();
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
		}
	}
	
	public void giveKitEmporer(Player player) {
		long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Emporer");
		if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
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

			// Cooldown
			plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Emporer", (System.currentTimeMillis() + 86400000));
			plugin.saveConfig();
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
		}
	}
	
	public void giveKitGod(Player player) {
		long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.God");
		if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
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
			ItemStack Item8 = new ItemStack(Material.POTION, 2, (byte) 8266);
			ItemMeta Item8Meta = Item6.getItemMeta();
			List<String> lore8 = new ArrayList<String>();
			Item8Meta.setDisplayName("§4§l§nElixir of Fury");
			Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
			lore8.add("§eSpeed II (1:30)");
			lore8.add("§eStrength II (1:30)");
			lore8.add("§eFire Resistence (8:00)");
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
			// Cooldown
			plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.God", (System.currentTimeMillis() + 86400000));
			plugin.saveConfig();
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
		}
	}
	
	public void giveKitIcewynd(Player player) {
		long millisWhenKitIsAvailable = plugin.getConfig().getLong(player.getUniqueId() + ".KitCooldowns.Icewynd");
		if ((millisWhenKitIsAvailable <= System.currentTimeMillis()) || millisWhenKitIsAvailable == 0) {
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
			ItemStack Item8 = new ItemStack(Material.POTION, 3, (byte) 8266);
			ItemMeta Item8Meta = Item6.getItemMeta();
			List<String> lore8 = new ArrayList<String>();
			Item8Meta.setDisplayName("§4§l§nElixir of Fury");
			Item8Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
			lore8.add("§eSpeed II (1:30)");
			lore8.add("§eStrength II (1:30)");
			lore8.add("§eFire Resistence (8:00)");
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
			
			// Cooldown
			plugin.getConfig().set(player.getUniqueId() + ".KitCooldowns.Icewynd", (System.currentTimeMillis() + 86400000));
			plugin.saveConfig();
		}else {
			long millisTillKit = millisWhenKitIsAvailable - System.currentTimeMillis();
			long secondsTillKit = millisTillKit / 1000;
			long hoursTillKit = secondsTillKit / (60*60);
			long minutesTillKit = (secondsTillKit - (hoursTillKit*60*60)) / 60;
			secondsTillKit = secondsTillKit - (hoursTillKit*60*60) - (minutesTillKit*60);
			player.sendMessage("§cYou can use that again in " + hoursTillKit + "hours, " + minutesTillKit + "minutes, " + secondsTillKit + "seconds.");
		}
	}
	
}
	