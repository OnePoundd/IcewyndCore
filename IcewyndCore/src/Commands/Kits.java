package Commands;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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
			if (sender instanceof Player) {
				openKitInventory(player);
			}else if (args[0].equalsIgnoreCase("Icewynd")) {
				ItemStack Item1 = new ItemStack(Material.PISTON_BASE, 1);
				ItemMeta Item1Meta = Item1.getItemMeta();
				List<String> lore1 = new ArrayList<String>();
				Item1Meta.setDisplayName("§b§l§nIcewynd Kit Crate");
				lore1.add("§ftest");
				Item1Meta.setLore(lore1);
				Item1Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
				Item1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				Item1.setItemMeta(Item1Meta);
				player.getInventory().addItem(Item1);
			}
		}
		return false;
	}
	
	public static void openKitInventory(Player player) {
		Inventory kit = Bukkit.createInventory(null, InventoryType.HOPPER, "§5§lKit Crates");
		
		//Rank 1 - Fighter
		ItemStack Item1 = new ItemStack(Material.PISTON_BASE, 1);
		ItemMeta Item1Meta = Item1.getItemMeta();
		List<String> lore1 = new ArrayList<String>();
		Item1Meta.setDisplayName("§2§l§nFighter Kit Crate");
		lore1.add("§e§lInformation:");
		lore1.add("§8§l » §eCooldown:  §7PUTTIMEREMAININGHERE");
		lore1.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore1.add("§7");
		lore1.add("§e§lContained Items:");
		lore1.add("§b§l* §7P2 Unbreaking 1 Armour Set");
		lore1.add("§b§l* §7Sharp 2 Sword");
		lore1.add("§b§l* §7Power 2 Bow");
		lore1.add("§b§l* §7128 Arrows");
		Item1Meta.setLore(lore1);
		Item1.setItemMeta(Item1Meta);
		//Rank 2 - Warlord
		ItemStack Item2 = new ItemStack(Material.PISTON_BASE, 1);
		ItemMeta Item2Meta = Item2.getItemMeta();
		List<String> lore2 = new ArrayList<String>();
		Item2Meta.setDisplayName("§d§l§nWarlord Kit Crate");
		lore2.add("§e§lInformation:");
		lore2.add("§8§l » §eCooldown:  §7PUTTIMEREMAININGHERE");
		lore2.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore2.add("§7");
		lore2.add("§e§lContained Items:");
		lore2.add("§b§l* §7P3 Unbreaking 1 Armour Set");
		lore2.add("§b§l* §7Sharp 3 Sword");
		lore2.add("§b§l* §7Power 3 Bow");
		lore2.add("§b§l* §7128 Arrows");
		Item2Meta.setLore(lore2);
		Item2.setItemMeta(Item2Meta);
		//Rank 3 - Emporer
		ItemStack Item3 = new ItemStack(Material.PISTON_BASE, 1);
		ItemMeta Item3Meta = Item3.getItemMeta();
		List<String> lore3 = new ArrayList<String>();
		Item3Meta.setDisplayName("§c§l§nEmporer Kit Crate");
		lore3.add("§e§lInformation:");
		lore3.add("§8§l » §eCooldown:  §7PUTTIMEREMAININGHERE");
		lore3.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore3.add("§7");
		lore3.add("§e§lContained Items:");
		lore3.add("§b§l* §7P4 Unbreaking 1 Armour Set");
		lore3.add("§b§l* §7Sharp 4 Sword");
		lore3.add("§b§l* §71 Elixir of Fury");
		Item3Meta.setLore(lore3);
		Item3.setItemMeta(Item3Meta);
		//Rank 4 - God
		ItemStack Item4 = new ItemStack(Material.PISTON_BASE, 1);
		ItemMeta Item4Meta = Item4.getItemMeta();
		List<String> lore4 = new ArrayList<String>();
		Item4Meta.setDisplayName("§9§l§nGod Kit Crate");
		lore4.add("§e§lInformation:");
		lore4.add("§8§l » §eCooldown:  §7PUTTIMEREMAININGHERE");
		lore4.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore4.add("§7");
		lore4.add("§e§lContained Items:");
		lore4.add("§b§l* §7P4 Unbreaking 2 Armour Set");
		lore4.add("§b§l* §7Sharp 5 Fire 1 Sword");
		lore4.add("§b§l* §7Power 5 Flame 1 Bow");
		lore4.add("§b§l* §7128 Arrows");
		lore4.add("§b§l* §72 Elixir of Fury");
		Item4Meta.setLore(lore4);
		Item4Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
		Item4Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		Item4.setItemMeta(Item4Meta);
		//Rank 5 - Icewynd
		ItemStack Item5 = new ItemStack(Material.PISTON_BASE, 1);
		ItemMeta Item5Meta = Item5.getItemMeta();
		List<String> lore5 = new ArrayList<String>();
		Item5Meta.setDisplayName("§b§l§nIcewynd Kit Crate");
		lore5.add("§e§lInformation:");
		lore5.add("§8§l » §eCooldown:  §7PUTTIMEREMAININGHERE");
		lore5.add("§8§l » §eStatus:  §a§lUNLOCKED");
		lore5.add("§7");
		lore5.add("§e§lContained Items:");
		lore5.add("§b§l* §7P4 Unbreaking 3 Armour Set");
		lore5.add("§b§l* §7Sharp 5 Fire 2 Unbreaking 3 Sword");
		lore5.add("§b§l* §7Power 5 Infinity 1 Unbreaking 3 Flame 2 Bow");
		lore5.add("§b§l* §73 Elixir of Fury");
		Item5Meta.setLore(lore5);
		Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
		Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		Item5.setItemMeta(Item5Meta);
		
		kit.setItem(0, Item1);//Icewynd
		kit.setItem(1, Item2);//Icewynd
		kit.setItem(2, Item3);//Icewynd
		kit.setItem(3, Item4);//Icewynd
		kit.setItem(4, Item5);//Icewynd
		player.openInventory(kit);
		}
	

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType().equals(Material.PISTON_BASE)) {
			if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§n")) {
				event.getPlayer().getWorld().playEffect(event.getBlock().getLocation(), Effect.STEP_SOUND, Material.GOLD_BLOCK);
			}
		}
	}
}