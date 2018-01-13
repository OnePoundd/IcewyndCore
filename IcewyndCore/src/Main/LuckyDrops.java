package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LuckyDrops implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.getBlock().getType().equals(Material.STONE))
			;
		Random rand = new Random();
		int index = rand.nextInt(1) + 1;
		// Player gets lucky:
		Random rand2 = new Random();
		int index2 = rand2.nextInt(12) + 1;
		Player player = event.getPlayer();
		if (index2 == 1) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §5Witch Spawner §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"spawnergive " + event.getPlayer().getName() + " witch");
		} else if (index2 == 2) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §cBlaze Spawner §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"spawnergive " + event.getPlayer().getName() + " Blaze");
		} else if (index2 == 3) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §aCreeper Spawner §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"spawnergive " + event.getPlayer().getName() + " Creeper");
		} else if (index2 == 4) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §6Villager Spawner §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"spawnergive " + event.getPlayer().getName() + " Villager");
		} else if (index2 == 5) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §5Enderman Spawner §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"spawnergive " + event.getPlayer().getName() + " enderman");
		} else if (index2 == 6) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §eZombie pigman Spawner §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"spawnergive " + event.getPlayer().getName() + " zombie_pigman");
		} else if (index2 == 7) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §dBeacon §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"give " + event.getPlayer().getName() + " beacon 1");
		} else if (index2 == 8) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved §710 Hoppers §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"give " + event.getPlayer().getName() + " hopper 10");
		} else if (index2 == 9) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved §e$250,000 §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"ecoadmin give " + event.getPlayer().getName() + " 250000");
		} else if (index2 == 10) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved §e150 Coins §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
					"coins " + event.getPlayer().getName() + " 2500");
		} else if (index2 == 11) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved an  §eExotic Crate §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "exotic " + event.getPlayer().getName());
		} else if (index2 == 12) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §eLegendary Crate §b§lfrom Mining!");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "legendary " + event.getPlayer().getName());
		} else if (index2 == 13) {
			Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
					+ " §b§lwas lucky and recieved a §cCrate of TNT §b§lfrom Mining!");
			ItemStack Item1 = new ItemStack(Material.TRAPPED_CHEST, 1);
			ItemMeta Item1Meta = Item1.getItemMeta();
			List<String> lore1 = new ArrayList<String>();
			Item1Meta.setDisplayName("§c§l§nCrate of TNT");
			lore1.add("§7Empty your inventory then right click!");
			Item1Meta.setLore(lore1);
			Item1.setItemMeta(Item1Meta);
			player.getInventory().addItem(new ItemStack(Item1));
			// Player not lucky
		} else if (index < 1000) {
		}
	}

	@EventHandler
	public void onFish(PlayerFishEvent event) {
		if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			Random rand = new Random();
			int index = rand.nextInt(1) + 1;
			Player player = event.getPlayer();
			if (index == 1) {
				// Player gets lucky:
				Random rand2 = new Random();
				int index2 = rand2.nextInt(12) + 1;
				if (index2 == 1) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §5Witch Spawner §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"spawnergive " + event.getPlayer().getName() + " witch");
				} else if (index2 == 2) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §cBlaze Spawner §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"spawnergive " + event.getPlayer().getName() + " Blaze");
				} else if (index2 == 3) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §aCreeper Spawner §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"spawnergive " + event.getPlayer().getName() + " Creeper");
				} else if (index2 == 4) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §6Villager Spawner §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"spawnergive " + event.getPlayer().getName() + " Villager");
				} else if (index2 == 5) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §5Enderman Spawner §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"spawnergive " + event.getPlayer().getName() + " enderman");
				} else if (index2 == 6) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §eZombie pigman Spawner §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"spawnergive " + event.getPlayer().getName() + " zombie_pigman");
				} else if (index2 == 7) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §dBeacon §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"give " + event.getPlayer().getName() + " beacon 1");
				} else if (index2 == 8) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved §710 Hoppers §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"give " + event.getPlayer().getName() + " hopper 10");
				} else if (index2 == 9) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved §e$250,000 §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"ecoadmin give " + event.getPlayer().getName() + " 250000");
				} else if (index2 == 10) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved §e2500 Coins §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"coins " + event.getPlayer().getName() + " 2500");
				} else if (index2 == 11) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §eExotic Crate §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"exotic " + event.getPlayer().getName());
				} else if (index2 == 12) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §eLegendary Crate §b§lfrom Fishing!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
							"legendary " + event.getPlayer().getName());
				} else if (index2 == 13) {
					Bukkit.broadcastMessage("§e§lLUCKYDROPS§8§l » §a§l" + event.getPlayer().getName()
							+ " §b§lwas lucky and recieved a §cCrate of TNT §b§lfrom Mining!");
					ItemStack Item1 = new ItemStack(Material.TRAPPED_CHEST, 1);
					ItemMeta Item1Meta = Item1.getItemMeta();
					List<String> lore1 = new ArrayList<String>();
					Item1Meta.setDisplayName("§c§l§nCrate of TNT");
					lore1.add("§7Empty your inventory then right click!");
					Item1Meta.setLore(lore1);
					Item1.setItemMeta(Item1Meta);
					player.getInventory().addItem(new ItemStack(Item1));
					// Player not lucky
				} else if (index < 1000) {
				}
			}
		}
	}
}
