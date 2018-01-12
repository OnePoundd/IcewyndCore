package net.OnePound.CustomEnchants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.OnePound.Additions.Main;

public class Librarian implements Listener{
	
	public static void openInventory(Player player) { 
		Inventory anvil = Bukkit.createInventory(null, 27, "§c§l>> §8Librarian §c§l<<");
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName(" ");
		empty.setItemMeta(emptyMeta);
		ItemStack slot = new ItemStack(Material.AIR);
		
		ItemStack repair = new ItemStack(Material.PAPER);
		ItemMeta repairMeta = repair.getItemMeta();
		repairMeta.setDisplayName("§c§l>> §fItem One §c§l<<");
		ArrayList<String> repairLore = new ArrayList<String>();
		repairLore.add("§7Place the item you would like to add");
		repairLore.add("§7an enchantment to here! This must");
		repairLore.add("§7be an item rather than a book!");
		repairMeta.setLore(repairLore);
		repair.setItemMeta(repairMeta);
		
		ItemStack add = new ItemStack(Material.PAPER);
		ItemMeta addMeta = add.getItemMeta();
		addMeta.setDisplayName("§c§l>> §fItem Two §c§l<<");
		ArrayList<String> addLore = new ArrayList<String>();
		addLore.add("§7Place the item you would like to enchant");
		addLore.add("§7or fix your first item with here!");
		addMeta.setLore(addLore);
		add.setItemMeta(addMeta);
		
		ItemStack level = new ItemStack(Material.PAPER);
		ItemMeta levelMeta = add.getItemMeta();
		levelMeta.setDisplayName("§c§l>> §fCost §c§l<<");
		ArrayList<String> levelLore = new ArrayList<String>();
		levelLore.add("§7This is how many levels it will cost to apply!");
		levelMeta.setLore(levelLore);
		level.setItemMeta(levelMeta);
		
		ItemStack Return = new ItemStack(Material.PAPER);
		ItemMeta ReturnMeta = add.getItemMeta();
		ReturnMeta.setDisplayName("§c§l>> §fItem Three §c§l<<");
		ArrayList<String> ReturnLore = new ArrayList<String>();
		ReturnLore.add("§7This is the item you will recieve!");
		ReturnMeta.setLore(ReturnLore);
		Return.setItemMeta(ReturnMeta);
		

		
		ItemStack[] items = {
		empty, repair, empty, add, empty, level, empty, Return, empty,
		empty, slot, empty, slot, empty, new ItemStack(Material.EXP_BOTTLE, 15), empty, slot, empty,
		empty, repair, empty, add, empty, level, empty, Return, empty};
		anvil.setContents(items);
		player.openInventory(anvil);
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getClickedInventory() != null && event.getClickedInventory().getName().equals("§c§l>> §8Librarian §c§l<<")) {
			ItemStack item = event.getCurrentItem();
			// Prevents stealing xp bottles and paper
			if(item.getType().equals(Material.PAPER) || item.getType().equals(Material.EXP_BOTTLE) || item.getType().equals(Material.WOOL)) {
				event.setCancelled(true);
				return;
			}else if(event.getSlot() == 16) {
				event.setCancelled(true);
				if(event.getClickedInventory().getItem(16) != null && !event.getClickedInventory().getItem(16).getType().equals(Material.AIR)) {
					Player player = (Player) event.getWhoClicked();
					if(player.getLevel() >= 15) {
						player.setLevel(player.getLevel() - 15);
						player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 10, 1);
						if(player.getInventory().firstEmpty() == -1){
							player.sendMessage("§b§l(!)§7 Your inventory is full, dropping item at your feet!");
							player.getWorld().dropItem(player.getLocation(), event.getClickedInventory().getItem(16));
						}else{
							player.sendMessage("§b§l(!)§7 Your new item has been added to your inventory!");
							player.getInventory().addItem(event.getClickedInventory().getItem(16));
						}
						event.getClickedInventory().setItem(10, new ItemStack(Material.AIR));
						event.getClickedInventory().setItem(12, new ItemStack(Material.AIR));
						player.closeInventory();
					}else {
						player.sendMessage("§c§l(!)§7 You cannot afford to do that!");
						player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 10, 1);
					}
				}
				return;
			}
			// Prevents placing items in open slots
			if(!(event.getSlot() == 10 || event.getSlot() == 12)) {
				event.setCancelled(true);
			}else {
				// Waits one tick so that it can get the new item in the slot 10/12 if there is one
				Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
					public void run() {
						ItemStack itemToRepair = event.getInventory().getContents()[10];
						ItemStack itemToAdd = event.getInventory().getContents()[12];
						ItemStack itemToReturn;
						// Makes sure that both slots are full before running the following code to determine the third slot item
						if(itemToAdd != null && !itemToAdd.getType().equals(Material.AIR) && itemToRepair != null && !itemToRepair.getType().equals(Material.AIR)) {
							// Prevents players from adding enchantments to a book.
							if(itemToRepair.getType().equals(Material.ENCHANTED_BOOK)) {
								event.setCancelled(true);
								return;
							}else {
								if(itemToRepair != null && !itemToRepair.getType().equals(Material.AIR)) {
									itemToReturn = itemToRepair.clone();
								}else {
									itemToReturn = new ItemStack(Material.AIR);
								}
								
								// We set the item in the final slot to be air because the following code updates it
								event.getInventory().setItem(16, new ItemStack(Material.AIR));
								
								List<String> lore = new ArrayList<String>(); 
								
								// We will soon alter the lore of the new item, first we check if the item has a lore and copy it otherwise create a new one
								if(itemToReturn.hasItemMeta() && itemToReturn.getItemMeta().hasLore()) {
									lore = itemToReturn.getItemMeta().getLore();
								}
								
								// We will now check if the item to add is the same type and if so we will add the enchantment to the lore removing duplicates
								// Otherwise we check if its an enchanted book and then we add the enchantment to the lore if it fits the item, if not we return air
								if(itemToRepair.getType().equals(itemToAdd.getType())) {
									Iterator<String> itemToAddIterator = itemToAdd.getItemMeta().getLore().iterator();
									while(itemToAddIterator.hasNext()) {
										String currentEnchant = itemToAddIterator.next();
										if(!lore.contains(currentEnchant)) {
											lore.add(currentEnchant);
										}
									}									
								}else if(itemToAdd.getType().equals(Material.ENCHANTED_BOOK)) {
									// Checks if the enchantment can be applied to the item, whilst also removing the information lines
									Iterator<String> iter = itemToAdd.getItemMeta().getLore().iterator();
									while(iter.hasNext()) {
										String currentLine = iter.next();
										if(currentLine.startsWith("§7")) {
											iter.remove();
										}else if(currentLine.startsWith("§3")) {
											if(currentLine.contains("Pickaxe")) {
												Material m = itemToRepair.getType();
												if(m.equals(Material.DIAMOND_PICKAXE)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Axe")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_AXE)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Sword")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_SWORD)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Bow")){
												Material m = itemToReturn.getType();
												if(m.equals(Material.BOW)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Helmet")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_HELMET)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Chestplate")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_CHESTPLATE)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
												}
											}else if(currentLine.contains("Leggings")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_LEGGINGS)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Boot")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_BOOTS)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}else if(currentLine.contains("Hoe")) {
												Material m = itemToReturn.getType();
												if(m.equals(Material.DIAMOND_HOE)) {
													iter.remove();
												}else {
													itemToReturn = new ItemStack(Material.AIR);
													return;
												}
											}
										}else if(currentLine.startsWith("§e") || currentLine.startsWith("§5")) {
											if(!lore.contains(currentLine)) {
												lore.add(currentLine);
											}
										}
									}
									// So now the lore will contain only the enchantments possible for the item. If there are any faults the item returned is set to air 
									// We now finalize the returned item itemstack and add it to the inventory.
									ItemMeta itemMeta = itemToReturn.getItemMeta();
									if(lore != null) {itemMeta.setLore(lore);}
									itemToReturn.setItemMeta(itemMeta); //could break move below diamond if so
								}else if(itemToAdd.getType().equals(Material.DIAMOND)) {
									if(itemToRepair.getDurability() > 0) {
										if(itemToRepair != null && !itemToRepair.getType().equals(Material.AIR)) {
											itemToReturn = itemToRepair.clone();
										}else {
											itemToReturn = new ItemStack(Material.AIR);
										}
										int damage = itemToReturn.getDurability();
										int undamage = itemToAdd.getAmount() * 200;
										if(damage - undamage < 0) {
											itemToReturn.setDurability((short) 0); 
										}else {
											itemToReturn.setDurability(((short)  (damage - undamage)));
										}
									}else {
										itemToReturn = new ItemStack(Material.AIR);
									}
								}else {
									itemToReturn = new ItemStack(Material.AIR);
									event.getInventory().setItem(16, itemToReturn);
									return;
								}
								event.getInventory().setItem(16, itemToReturn);
							}
						//This is just a check to remove the item from the third slot if the user removes one of the two previous items
						}else if(itemToRepair == null || itemToRepair.getType().equals(Material.AIR) || itemToAdd == null || itemToAdd.getType().equals(Material.AIR)) {
							event.getInventory().setItem(16, new ItemStack(Material.AIR));
						}
					}
				}, 2);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if(event.getInventory().getName().equals("              §c§l>> §8Librarian §c§l<<")) {
			if(event.getInventory().getItem(10) != null && !event.getInventory().getItem(10).getType().equals(Material.AIR)) {
				event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getInventory().getItem(10));
			}
			if(event.getInventory().getItem(12) != null && !event.getInventory().getItem(12).getType().equals(Material.AIR)) {
				event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getInventory().getItem(12));
			}
		}
	}
	
	@EventHandler
	public void onAnvilPrepare(PrepareAnvilEvent event) {
		Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
			public void run() {
				AnvilInventory anvil = event.getInventory();
				for(ItemStack item : anvil.getContents()) {
					if(item != null && item.hasItemMeta() && (item.getItemMeta().hasLore() || item.getItemMeta().hasDisplayName())) {
						anvil.setRepairCost(9999);
					}
				}
			}
		}, 1);
	}
}
