package Main;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();

		if (event.getInventory().getName() == "�8� �9�lItems QuarterMaster") {
			if (item.getType() == Material.TRAPPED_CHEST) {
				ItemStack Item1 = new ItemStack(Material.TRAPPED_CHEST, 1);
				ItemMeta Item1Meta = Item1.getItemMeta();
				List<String> lore1 = new ArrayList<String>();
				Item1Meta.setDisplayName("�c�l�nCrate of TNT");
				lore1.add("�7Empty your inventory then right click!");
				Item1Meta.setLore(lore1);
				Item1.setItemMeta(Item1Meta);
				player.sendMessage("�aPurchased 1 Crate of TNT!");
				player.getInventory().addItem(new ItemStack(Item1));
			} else if (item.getType() == Material.DIAMOND_PICKAXE) {
				ItemStack Item2 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
				ItemMeta Item2Meta = Item2.getItemMeta();
				List<String> lore2 = new ArrayList<String>();
				Item2Meta.setDisplayName("�d�l�nExotic Pickaxe");
				lore2.add("�f");
				lore2.add("�a�lCustom Enchantments:");
				lore2.add("�eShockwave");
				lore2.add("�eSilk Feet");
				Item2Meta.setLore(lore2);
				Item2Meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
				Item2Meta.addEnchant(Enchantment.DURABILITY, 3, true);
				Item2.setItemMeta(Item2Meta);
				player.sendMessage("�aPurchased 1 Exotic Pickaxe!");
				player.getInventory().addItem(new ItemStack(Item2));
			} else if (item.getType() == Material.BEDROCK) {
				ItemStack Item3 = new ItemStack(Material.BEDROCK, 8);
				ItemMeta Item3Meta = Item3.getItemMeta();
				List<String> lore3 = new ArrayList<String>();
				Item3Meta.setDisplayName("�8�l�nHardened Bedrock");
				lore3.add("�7Destructible block with 100 block durability!");
				Item3Meta.setLore(lore3);
				Item3.setItemMeta(Item3Meta);
				player.sendMessage("�aPurchased 8 Bedrock!");
				player.getInventory().addItem(new ItemStack(Item3));
			} else if (item.getType() == Material.MONSTER_EGG) {
				ItemStack Item4 = new ItemStack(Material.MONSTER_EGG, 1, (short)50);
				ItemMeta Item4Meta = Item4.getItemMeta();
				List<String> lore4 = new ArrayList<String>();
				Item4Meta.setDisplayName("�a�l�nCharged Creeper Egg");
				lore4.add("�7Spawns a charged creeper!");
				Item4Meta.setLore(lore4);
				Item4.setItemMeta(Item4Meta);
				player.sendMessage("�aPurchased 1 Charged Creeper Egg!");
				player.getInventory().addItem(new ItemStack(Item4));
			} else if (item.getType() == Material.MOB_SPAWNER) {
				ItemStack Item5 = new ItemStack(Material.MOB_SPAWNER, 1);
				ItemMeta Item5Meta = Item5.getItemMeta();
				List<String> lore5 = new ArrayList<String>();
				Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
				Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				Item5Meta.setDisplayName("�d�l�nMystery Spawner");
				lore5.add("�7Rewards the player with 1 random spawner out of 5");
				lore5.add("�1");
				lore5.add("�cPrice: �f5 Coins");
				Item5Meta.setLore(lore5);
				Item5.setItemMeta(Item5Meta);
				player.sendMessage("�aPurchased 1 Mystery Spawner");
				player.getInventory().addItem(new ItemStack(Item5));
			} else if (item.getType() == Material.POTION) {
				ItemStack Item6 = new ItemStack(Material.POTION, 1);
				ItemMeta Item6Meta = Item6.getItemMeta();
				List<String> lore6 = new ArrayList<String>();
				Item6Meta.setDisplayName("�4�l�nElixir of Fury");
				lore6.add("�eSpeed II (1:30)");
				lore6.add("�eStrength II (1:30)");
				lore6.add("�eFire Resistence (8:00)");
				Item6Meta.setLore(lore6);
				Item6.setItemMeta(Item6Meta);
				player.sendMessage("�aPurchased 1 Elixir of Fury");
				player.getInventory().addItem(new ItemStack(Item6));
			}
		}
		
	}
}