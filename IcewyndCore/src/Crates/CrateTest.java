package Crates;

import java.util.ArrayList;
import java.util.List;
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
import Main.Main;

public class CrateTest implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME) {
				Player player = event.getPlayer();
				CrateTest.openCrateInventory(player);
			}
		}
	}
	@EventHandler
	public void inventory(InventoryClickEvent event) {
		if (event.getInventory().getName() == "§lCrates") {
			//Location WitherSpawn = (Location)(plugin.getConfig()).get(".WitherSpawn");
			Bukkit.broadcastMessage("hi");
		}
		
	}

	public static void openCrateInventory(Player player) {

		Inventory ItemsInv = Bukkit.createInventory(null, 45, "§lCrates");
		// SLOT0
		ItemStack Item0 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta Item0Meta = Item0.getItemMeta();
		Item0Meta.setDisplayName("§f");
		Item0.setItemMeta(Item0Meta);
		
		// SLOT0
		ItemStack Item10 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
		ItemMeta Item10Meta = Item10.getItemMeta();
		Item10Meta.setDisplayName("§f");
		Item10.setItemMeta(Item10Meta);

		//CRATE 1
		ItemStack Item1 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item1Meta = Item1.getItemMeta();
		List<String> lore1 = new ArrayList<String>();
		Item1Meta.setDisplayName("§c§k §c§n Ancient Gladiators Crate §c§k ");
		lore1.add("§7High tier armor, weapons and other valuable items."); 
		lore1.add("§7Purchase crates at store.Icewynd.net");
		lore1.add("");
		lore1.add("§8§m----------------------");
		lore1.add("§e§n§lCOMMON ITEMS:");
		lore1.add("§7- §eExperience Bombs");
		lore1.add("§7- §eLegendary Enchantment Books");
		lore1.add("§7- §ePVP Potions");
		lore1.add("§7- §ePotion Crates");
		lore1.add("§b§n§lRARE ITEMS:");
		lore1.add("§7- §bMCMMO Credits");
		lore1.add("§7- §bShockwave Pickaxe / Shovel");
		lore1.add("§7- §bExperience 1 Sharpness 5 Unbreaking 5 Sword");
		lore1.add("§7- §bCreeper Eggs");
		lore1.add("§d§n§lEPIC ITEMS:");
		lore1.add("§7- §dSharpness 6 Unbreaking 3 Sword");
		lore1.add("§7- §dEnderman Spawner");
		lore1.add("§7- §dCreeper Spawner");
		lore1.add("§7- §dBlaze Disguise");
		lore1.add("§7- §dWarriors Kit");
		lore1.add("§8§m----------------------");
		lore1.add("§aKeys: §f0");
		Item1Meta.setLore(lore1);
		Item1.setItemMeta(Item1Meta);

		//Crate 2
		ItemStack Item2 = new ItemStack(Material.CHEST, 1);
		ItemMeta Item2Meta = Item2.getItemMeta();
		List<String> lore2 = new ArrayList<String>();
		Item2Meta.setDisplayName("§c§k §c§n CRATE 2 §c§k ");
		lore2.add("§7Very valuable crate that contains many");
		lore2.add("§7exlusive items only obtainable in crates.");
		lore2.add("§7Purchase crates at store.Icewynd.net");
		lore2.add("");
		lore2.add("§8§m----------------------");
		lore2.add("§e§n§lCOMMON ITEMS:");
		lore2.add("§7- §e");
		lore2.add("§7- §e");
		lore2.add("§7- §e");
		lore2.add("§7- §e");
		lore2.add("§7- §e");
		lore2.add("§7- §eTrench TNT");
		lore2.add("§b§n§lRARE ITEMS:");
		lore2.add("§7- §b");
		lore2.add("§7- §b");
		lore2.add("§7- §b");
		lore2.add("§d§n§lEPIC ITEMS:");
		lore2.add("§7- §dBuilders Kit");
		lore2.add("§7- §dBlaze Disguise");
		lore2.add("§7- §d");
		lore2.add("§7- §d");
		lore2.add("§8§m----------------------");
		lore2.add("§aKeys: §f0");
		Item2Meta.setLore(lore2);
		Item2.setItemMeta(Item2Meta);

		//CRATE 3
		ItemStack Item3 = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta Item3Meta = Item3.getItemMeta();
		List<String> lore3 = new ArrayList<String>();
		Item3Meta.setDisplayName("§c§k §c§n CRATE 3 §c§k ");
		lore3.add("§7Collection of cosmetic & miscellaneous");
		lore3.add("§7items of various value and rarity.");
		lore3.add("§7Purchase crates at store.Icewynd.net");
		lore3.add("");
		lore3.add("§8§m----------------------");
		lore3.add("§e§n§lCOMMON ITEMS:");
		lore3.add("§7- §eTrench TNT");
		lore3.add("§7- §e4 Mystery Spawners");
		lore3.add("§7- §eSuper Golden Apples");
		lore3.add("§7- §eMCMMO Credits");
		lore3.add("§7- §e2 TNT Chests");
		lore3.add("§b§n§lRARE ITEMS:");
		lore3.add("§7- §bBedrock/Lava Particle");
		lore3.add("§7- §bEmporer Rank");
		lore3.add("§7- §bPortal Particle");
		lore3.add("§d§n§lEPIC ITEMS:");
		lore3.add("§7- §dAlchemist Kit");
		lore3.add("§7- §dCharged Creeper Egg");
		lore3.add("§7- §dSilverfish Disguise");
		lore3.add("§7- §dVillager Spawner");
		lore3.add("§8§m----------------------");
		lore3.add("§aKeys: §f0");
		Item3Meta.setLore(lore3);
		Item3.setItemMeta(Item3Meta);

		//CRATE 4
		ItemStack Item4 = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta Item4Meta = Item4.getItemMeta();
		List<String> lore4 = new ArrayList<String>();
		Item4Meta.setDisplayName("§c§k §b§n Forgotten Loot Crate §c§k ");
		lore4.add("§7Very valuable crate that contains many");
		lore4.add("§7exlusive items only obtainable in crates.");
		lore4.add("§7Purchase crates at store.Icewynd.net");
		lore4.add("");
		lore4.add("§8§m----------------------");
		lore4.add("§e§n§lCOMMON ITEMS:");
		lore4.add("§7- §eCreeper Egg");
		lore4.add("§7- §eEnderman Spawner");
		lore4.add("§7- §eCreeper Spawner");
		lore4.add("§7- §eSell Wand");
		lore4.add("§7- §e250 mcMMO Credits");
		lore4.add("§7- §eBeacon");
		lore4.add("§b§n§lRARE ITEMS:");
		lore4.add("§7- §bVillager Spawner");
		lore4.add("§7- §bMagma Cube Disguise");
		lore4.add("§7- §bEnderman Disguise");
		lore4.add("§7- §b32 Bedrock");
		lore4.add("§d§n§lEPIC ITEMS:");
		lore4.add("§7- §dIcewynd Rank Ticket");
		lore4.add("§7- §d9 Enderchest Slots");
		lore4.add("§7- §dCharged Creeper Egg");
		lore4.add("§7- §dChunkLoader (Spawners & Crops)");
		lore4.add("§7- §d$750,000 Ingame Cash");
		lore4.add("§8§m----------------------");
		lore4.add("§aKeys: §f0");
		Item4Meta.setLore(lore4);
		Item4.setItemMeta(Item4Meta);

		ItemsInv.setItem(0, Item0);
		ItemsInv.setItem(1, Item0);
		ItemsInv.setItem(2, Item0);
		ItemsInv.setItem(3, Item0);
		ItemsInv.setItem(4, Item0);
		ItemsInv.setItem(5, Item0);
		ItemsInv.setItem(6, Item0);
		ItemsInv.setItem(7, Item0);
		ItemsInv.setItem(8, Item0);
		ItemsInv.setItem(9, Item10);
		ItemsInv.setItem(10, Item10);
		ItemsInv.setItem(11, Item10);
		ItemsInv.setItem(12, Item10);
		ItemsInv.setItem(13, Item10);
		ItemsInv.setItem(14, Item10);
		ItemsInv.setItem(15, Item10);
		ItemsInv.setItem(16, Item10);
		ItemsInv.setItem(17, Item10);
		ItemsInv.setItem(18, Item10);
		ItemsInv.setItem(19, Item1);
		ItemsInv.setItem(20, Item10);
		ItemsInv.setItem(21, Item2);
		ItemsInv.setItem(22, Item10);
		ItemsInv.setItem(23, Item3);
		ItemsInv.setItem(24, Item10);
		ItemsInv.setItem(25, Item4);
		ItemsInv.setItem(26, Item10);
		ItemsInv.setItem(27, Item10);
		ItemsInv.setItem(28, Item10);
		ItemsInv.setItem(29, Item10);
		ItemsInv.setItem(30, Item10);
		ItemsInv.setItem(31, Item10);
		ItemsInv.setItem(32, Item10);
		ItemsInv.setItem(33, Item10);
		ItemsInv.setItem(34, Item10);
		ItemsInv.setItem(35, Item10);
		ItemsInv.setItem(36, Item0);
		ItemsInv.setItem(37, Item0);
		ItemsInv.setItem(38, Item0);
		ItemsInv.setItem(39, Item0);
		ItemsInv.setItem(40, Item0);
		ItemsInv.setItem(41, Item0);
		ItemsInv.setItem(42, Item0);
		ItemsInv.setItem(43, Item0);
		ItemsInv.setItem(44, Item0);
		player.openInventory(ItemsInv);
	}
}