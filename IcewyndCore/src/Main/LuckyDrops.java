package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LuckyDrops implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.STONE)) {
			Player player = event.getPlayer(); 
			player.getWorld().playEffect(event.getBlock().getLocation(), Effect.STEP_SOUND, Material.GOLD_BLOCK);
			player.playSound(event.getPlayer().getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);
			int num = new Random().nextInt(13);
			//Lucky Drop Stats Tracking
			int luckydrops = plugin.getConfig().getInt(player.getUniqueId() + ".LuckyDrops");

			//Witch Lucky Drop
			if (num == 1) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §5Witch Spawner §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
				ItemMeta spawnerMeta = spawner.getItemMeta();
				//spawnerMeta.setDisplayName("§e" + witch.toUpperCase().replaceAll("_", " ") + " §fSpawner");
				spawner.setItemMeta(spawnerMeta);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), spawner);
				} else {
					player.getInventory().addItem(spawner);
				}
			//Blaze Lucky Drop
			} else if (num == 2) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §cBlaze Spawner §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Crepper Lucky Drop
			} else if (num == 3) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §aCreeper Spawner §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Villager Lucky Drop	
			} else if (num == 4) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §6Villager Spawner §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Enderman Lucky Drop
			} else if (num == 5) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §5Enderman Spawner §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//ZombiePigman Lucky Drop
			} else if (num == 6) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §eZombie pigman Spawner §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Beacon Lucky Drop
			} else if (num == 7) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §dBeacon §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack beacon = new ItemStack(Material.BEACON, 1);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), beacon);
				} else {
					player.getInventory().addItem(beacon);
				}
			//Hopper Lucky Drop
			} else if (num == 8) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved §710 Hoppers §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack hopper = new ItemStack(Material.HOPPER, 10);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), hopper);
				} else {
					player.getInventory().addItem(hopper);
				}
			//$250,000 Lucky Drop
			} else if (num == 9) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved §e$250,000 §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ecoadmin give " + event.getPlayer().getName() + " 250000");
				player.sendMessage("§a$250,000 was added to your account.");
			//150 coin Lucky Drop
			} else if (num == 10) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved §e150 Coins §b§lfrom Mining!");
				int coins = plugin.getConfig().getInt(player.getUniqueId() + ".Coins");
				plugin.getConfig().set(player.getUniqueId() + ".Coins", coins + 150);
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				player.sendMessage("§a150 Coins were added to your account.");
			//Exotic Crate Lucky Drop
			} else if (num == 11) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved an §eExotic Crate §b§lfrom Mining!");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "exotic " + event.getPlayer().getName());
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Legendary Crate Lucky Drop
			} else if (num == 12) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §eLegendary Crate §b§lfrom Mining!");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "legendary " + event.getPlayer().getName());
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//TNT Crate Lucky Drop
			} else if (num == 13) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §cCrate of TNT §b§lfrom Mining!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack TNTCrate = new ItemStack(Material.TRAPPED_CHEST, 1);
				ItemMeta TNTCrateMeta = TNTCrate.getItemMeta();
				List<String> lore1 = new ArrayList<String>();
				TNTCrateMeta.setDisplayName("§c§l§nCrate of TNT");
				lore1.add("§7Empty your inventory then right click!");
				TNTCrateMeta.setLore(lore1);
				TNTCrate.setItemMeta(TNTCrateMeta);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), TNTCrate);
				} else {
					player.getInventory().addItem(TNTCrate);
				}
			}
		}
	}

	@EventHandler
	public void onFish(PlayerFishEvent event) {
		if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			Player player = event.getPlayer();
			player.playSound(event.getPlayer().getLocation(), Sound.FIREWORK_BLAST, 1f, 1f);
			//Fish Caught Tracking
			int fishcaught = plugin.getConfig().getInt(player.getUniqueId() + ".FishCaught");
			plugin.getConfig().set(player.getUniqueId() + ".FishCaught", fishcaught + 1);
			//Lucky Drops Tracking
			int luckydrops = plugin.getConfig().getInt(player.getUniqueId() + ".LuckyDrops");

			int num = new Random().nextInt(13);
			//Witch Spawner Lucky Drop
			if (num == 1) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §5Witch Spawner §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
				ItemMeta spawnerMeta = spawner.getItemMeta();
				//spawnerMeta.setDisplayName("§e" + witch.toUpperCase().replaceAll("_", " ") + " §fSpawner");
				spawner.setItemMeta(spawnerMeta);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), spawner);
				} else {
					player.getInventory().addItem(spawner);
				}

			//Blaze Lucky Drop
			} else if (num == 2) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §cBlaze Spawner §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Crepper Lucky Drop
			} else if (num == 3) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §aCreeper Spawner §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Villager Lucky Drop	
			} else if (num == 4) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §6Villager Spawner §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Enderman Lucky Drop
			} else if (num == 5) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §5Enderman Spawner §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//ZombiePigman Lucky Drop
			} else if (num == 6) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §eZombie pigman Spawner §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Beacon Lucky Drop
			} else if (num == 7) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §dBeacon §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack beacon = new ItemStack(Material.BEACON, 1);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), beacon);
				} else {
					player.getInventory().addItem(beacon);
				}
			//Hopper Lucky Drop
			} else if (num == 8) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved §710 Hoppers §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack hopper = new ItemStack(Material.HOPPER, 10);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), hopper);
				} else {
					player.getInventory().addItem(hopper);
				}
			//$250,000 Lucky Drop
			} else if (num == 9) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved §e$250,000 §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ecoadmin give " + event.getPlayer().getName() + " 250000");
				player.sendMessage("§a$250,000 was added to your account.");

			//150 coin Lucky Drop
			} else if (num == 10) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved §e150 Coins §b§lfrom Fishing!");
				int coins = plugin.getConfig().getInt(player.getUniqueId() + ".Coins");
				plugin.getConfig().set(player.getUniqueId() + ".Coins", coins + 150);
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				player.sendMessage("§a150 Coins were added to your account.");
			//Exotic Crate Lucky Drop
			} else if (num == 11) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved an §eExotic Crate §b§lfrom Fishing!");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "exotic " + event.getPlayer().getName());
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//Legendary Crate Lucky Drop
			} else if (num == 12) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §eLegendary Crate §b§lfrom Fishing!");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "legendary " + event.getPlayer().getName());
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
			//TNT Crate Lucky Drop
			} else if (num == 13) {
				Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName() + " §b§lwas lucky and recieved a §cCrate of TNT §b§lfrom Fishing!");
				plugin.getConfig().set(player.getUniqueId() + ".LuckyDrops", luckydrops + 1);
				ItemStack TNTCrate = new ItemStack(Material.TRAPPED_CHEST, 1);
				ItemMeta TNTCrateMeta = TNTCrate.getItemMeta();
				List<String> lore1 = new ArrayList<String>();
				TNTCrateMeta.setDisplayName("§c§l§nCrate of TNT");
				lore1.add("§7Empty your inventory then right click!");
				TNTCrateMeta.setLore(lore1);
				TNTCrate.setItemMeta(TNTCrateMeta);
				if (player.getInventory().firstEmpty() == -1) {
					player.sendMessage("§b§lYour inventory is full, dropping item at your feet!");
					player.getWorld().dropItem(player.getLocation(), TNTCrate);
				} else {
					player.getInventory().addItem(TNTCrate);
				}
			}
		}
	}
}