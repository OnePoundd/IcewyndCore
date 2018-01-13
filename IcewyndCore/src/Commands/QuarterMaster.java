//Item0Meta.addEnchant(Enchantment.ARROW_FIRE, 10, true);
package Commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuarterMaster implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			if (cmd.getLabel().equals("q"))
				;
			if (args[0].equalsIgnoreCase("items")) {
				openItemsInventory(Bukkit.getPlayer(args[1]));
			} else if (args[0].equalsIgnoreCase("kits")) {
				openKitsInventory(Bukkit.getPlayer(args[1]));
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void openItemsInventory(Player player) {
		Inventory qitems = Bukkit.createInventory(null, 9, "§8» §9§lItems QuarterMaster");

		// SLOT0
		ItemStack Item0 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta Item0Meta = Item0.getItemMeta();
		List<String> lore0 = new ArrayList<String>();
		Item0Meta.setDisplayName("§c§lPrevious Page");
		lore0.add("§7You cannot go back!");
		Item0Meta.setLore(lore0);
		Item0.setItemMeta(Item0Meta);
		// SLOT1
		ItemStack Item1 = new ItemStack(Material.TRAPPED_CHEST, 1);
		ItemMeta Item1Meta = Item1.getItemMeta();
		List<String> lore1 = new ArrayList<String>();
		Item1Meta.setDisplayName("§c§l§nCrate of TNT");
		lore1.add("§7Small box full of TNT!");
		lore1.add("§1");
		lore1.add("§cPrice: §f5 Coins");
		Item1Meta.setLore(lore1);
		Item1.setItemMeta(Item1Meta);
		// SLOT2
		ItemStack Item2 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta Item2Meta = Item2.getItemMeta();
		List<String> lore2 = new ArrayList<String>();
		Item2Meta.setDisplayName("§d§l§nExotic Pickaxe");
		lore2.add("§f");
		lore2.add("§a§lCustom Enchantments:");
		lore2.add("§eShockwave");
		lore2.add("§eSilk Feet");
		Item2Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		Item2Meta.addEnchant(Enchantment.DURABILITY, 3, true);
		lore2.add("§1");
		lore2.add("§cPrice: §f5 Coins");
		Item2Meta.setLore(lore2);
		Item2.setItemMeta(Item2Meta);
		// SLOT3
		ItemStack Item3 = new ItemStack(Material.BEDROCK, 8);
		ItemMeta Item3Meta = Item3.getItemMeta();
		List<String> lore3 = new ArrayList<String>();
		Item3Meta.setDisplayName("§8§l§nHardened Bedrock");
		lore3.add("§7Destructible block with 100 block durability!");
		lore3.add("§1");
		lore3.add("§cPrice: §f5 Coins");
		Item3Meta.setLore(lore3);
		Item3.setItemMeta(Item3Meta);
		// SLOT4
		ItemStack Item4 = new ItemStack(Material.MONSTER_EGG, 1);
		ItemMeta Item4Meta = Item4.getItemMeta();
		List<String> lore4 = new ArrayList<String>();
		Item4.setDurability(EntityType.CREEPER.getTypeId());
		Item4Meta.setDisplayName("§a§l§nCharged Creeper Egg");
		Item4Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		lore4.add("§7Spawns a charged creeper!");
		lore4.add("§1");
		lore4.add("§cPrice: §f5 Coins");
		Item4Meta.setLore(lore4);
		Item4.setItemMeta(Item4Meta);
		// SLOT5
		ItemStack Item5 = new ItemStack(Material.MOB_SPAWNER, 1);
		ItemMeta Item5Meta = Item5.getItemMeta();
		List<String> lore5 = new ArrayList<String>();
		Item5Meta.setDisplayName("§d§l§nMystery Spawner");
		lore5.add("§7Rewards the player with 1 random spawner out of 5");
		lore5.add("§1");
		lore5.add("§cPrice: §f5 Coins");
		Item5Meta.setLore(lore5);
		Item5.setItemMeta(Item5Meta);
		// SLOT6
		ItemStack Item6 = new ItemStack(Material.POTION, 1, (byte) 8266);
		ItemMeta Item6Meta = Item6.getItemMeta();
		List<String> lore6 = new ArrayList<String>();
		Item6Meta.setDisplayName("§4§l§nElixir of Fury");
		Item6Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		lore6.add("§eSpeed II (1:30)");
		lore6.add("§eStrength II (1:30)");
		lore6.add("§eFire Resistence (8:00)");
		lore6.add("§1");
		lore6.add("§cPrice: §f5 Coins");
		Item6Meta.setLore(lore6);
		Item6.setItemMeta(Item6Meta);
		// Slot8
		ItemStack Item8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		ItemMeta Item8Meta = Item8.getItemMeta();
		List<String> lore8 = new ArrayList<String>();
		Item8Meta.setDisplayName("§a§lNext Page");
		lore8.add("§7Kits QuarterMaster");
		Item8Meta.setLore(lore8);
		Item8.setItemMeta(Item8Meta);

		qitems.setItem(0, Item0);// Page
		qitems.setItem(1, Item2);// Shockwave
		qitems.setItem(2, Item4);// Charged Creeper
		qitems.setItem(3, Item5);// Mystery Spawner
		qitems.setItem(4, Item6);// PVPPot
		qitems.setItem(5, Item3);// Bedrock
		qitems.setItem(6, Item1);// TNTChest
		qitems.setItem(8, Item8);// Page
		player.openInventory(qitems);
	}

	public static void openKitsInventory(Player player) {
		Inventory qKits = Bukkit.createInventory(null, 9, "§8» §5§lKits QuarterMaster");

		// SLOT0
		ItemStack Item0 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta Item0Meta = Item0.getItemMeta();
		List<String> lore0 = new ArrayList<String>();
		Item0Meta.setDisplayName("§c§lPrevious Page");
		lore0.add("§7You cannot go back!");
		Item0Meta.setLore(lore0);
		Item0.setItemMeta(Item0Meta);
		// SLOT1
		ItemStack Item1 = new ItemStack(Material.TNT, 1);
		ItemMeta Item1Meta = Item1.getItemMeta();
		List<String> lore1 = new ArrayList<String>();
		Item1Meta.setDisplayName("§4§l§nExplosives Expert");
		lore1.add("§e§lInformation:");
		lore1.add("§8§l » §eCost:  §775 Tokens");
		lore1.add("§8§l » §eCooldown:  §724 hours");
		lore1.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore1.add("§7");
		lore1.add("§e§lContained Items:");
		lore1.add("§7§l* §b§l10% Chance of a Creeper Egg!");
		lore1.add("§7§l* §c§l1500 TNT");
		lore1.add("§7§l* §c256 Redstone");
		lore1.add("§7§l* §c128 Dispensers");
		lore1.add("§7§l* §c64 Repeaters");
		lore1.add("§7§l* §c32 Comparators");
		lore1.add("§7§l* §c32 Sponge");
		lore1.add("§7§l* §c32 Stone Slab");
		lore1.add("§7§l* §c16 Redstone Torches");
		lore1.add("§7§l* §c8 Sticky Pistons");
		lore1.add("§7§l* §c8 Cobweb");
		Item1Meta.setLore(lore1);
		Item1.setItemMeta(Item1Meta);
		// SLOT2
		ItemStack Item2 = new ItemStack(Material.POTION, 1);
		ItemMeta Item2Meta = Item2.getItemMeta();
		List<String> lore2 = new ArrayList<String>();
		Item2Meta.setDisplayName("§5§l§nAlchemist");
		lore2.add("§e§lInformation:");
		lore2.add("§8§l » §eCost:  §775 Tokens");
		lore2.add("§8§l » §eCooldown:  §748 hours");
		lore2.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore2.add("§7");
		lore2.add("§e§lContained Items:");
		lore2.add("§7§l* §b§l50% Chance of a Potion Crate!");
		lore2.add("§7§l* §d128 Water Bottles");
		lore2.add("§7§l* §d128 Netherwart");
		lore2.add("§7§l* §d64 Redstone");
		lore2.add("§7§l* §d64 Spider Eyes");
		lore2.add("§7§l* §d64 Sugar");
		lore2.add("§7§l* §d64 Glistering Melon");
		lore2.add("§7§l* §d64 Glowstone Dust");
		lore2.add("§7§l* §d32 Fermented Spider Eyes");
		lore2.add("§7§l* §d32 Blaze Rod");
		lore2.add("§7§l* §d16 Ghast Tears");
		Item2Meta.setLore(lore2);
		Item2.setItemMeta(Item2Meta);
		// SLOT3
		ItemStack Item3 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta Item3Meta = Item3.getItemMeta();
		List<String> lore3 = new ArrayList<String>();
		Item3Meta.setDisplayName("§c§l§nWarrior");
		lore3.add("§e§lInformation:");
		lore3.add("§8§l » §eCost:  §775 Tokens");
		lore3.add("§8§l » §eCooldown:  §748 hours");
		lore3.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore3.add("§7");
		lore3.add("§e§lContained Items:");
		lore3.add("§7§l* §b§l50% Chance of a Potion Crate!");
		lore3.add("§7§l* §dDiamond Helmet - P4 Unb3");
		lore3.add("§7§l* §dDiamond Chestplate - P4 Unb3");
		lore3.add("§7§l* §dDiamond Leggings - P4 Unb3");
		lore3.add("§7§l* §dDiamond Boots - P4 Unb3");
		lore3.add("§7§l* §dDiamond Sword - Sharp 5 Fire 2 Unb3");
		lore3.add("§7§l* §d5 Elixir of Fury");
		lore3.add("§7§l* §d16 Splash Healing II Potions");
		Item3Meta.setLore(lore3);
		Item3.setItemMeta(Item3Meta);
		// SLOT8
		ItemStack Item8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta Item8Meta = Item8.getItemMeta();
		List<String> lore8 = new ArrayList<String>();
		Item8Meta.setDisplayName("§c§lNext Page");
		lore8.add("§7You cannot go forward!");
		Item8Meta.setLore(lore8);
		Item8.setItemMeta(Item8Meta);

		qKits.setItem(0, Item0);// PageBack
		qKits.setItem(1, Item1);// TNT Kit
		qKits.setItem(2, Item2);// Alchemist Kit
		qKits.setItem(3, Item3);// Warrior Kit
		qKits.setItem(8, Item8);// PageForward
		player.openInventory(qKits);
	}
}