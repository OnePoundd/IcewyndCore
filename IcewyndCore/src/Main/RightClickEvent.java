package Main;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RightClickEvent implements Listener {

	//Quartermaster Items
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player player = event.getPlayer();
			if (player.getItemInHand().getType().equals(Material.TRAPPED_CHEST)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("�c�l�nCrate of TNT"))
					player.getPlayer().getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
				player.getInventory().addItem(new ItemStack(Material.TNT, 2304));
				event.setCancelled(true);

				// Charged Creeper
			} else if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("�a�l�nCharged Creeper Egg"))
					player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
				player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0), EntityType.CREEPER);
				Block block = event.getClickedBlock();
				Location locB = block.getLocation().getBlock().getLocation();
				block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);

				// Mystery Spawner
			} else if (player.getItemInHand().getType().equals(Material.MOB_SPAWNER)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("�d�l�nMystery Spawner")) {
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					Random rand = new Random();
					int index5 = rand.nextInt(5) + 1;
					if (index5 == 1) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " villager");
						Bukkit.broadcastMessage("�d�l���b�l " + player.getName() + " was lucky and recieved a �d�lVillager spawner�b�l from a Mystery Spawner!" + " �d�l��");
					} else if (index5 == 2) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " creeper");
					} else if (index5 == 3) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " enderman");
					} else if (index5 == 4) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " blaze");
					} else if (index5 == 5) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " witch");
				}
			} else if (player.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("�d�lRandom TP"));
					event.setCancelled(true);
				    ArrayList<Player> players = new ArrayList<Player>();
				    for (Player e : Bukkit.getOnlinePlayers()) players.add(e);
				    Player randomPlayer = players.get(new Random().nextInt(players.size()));
				    player.teleport(randomPlayer.getLocation());
				}
			}
		}
	}
	
	//Quartermaster Item
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDrink(PlayerItemConsumeEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType().equals(Material.POTION)) {
			if (player.getItemInHand().getItemMeta().getDisplayName().equals("�4�l�nElixir of Fury"))
				;
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 1));
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1));
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9570, 1));
			player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
		}
	}
	@EventHandler
	public void onClick(PlayerInteractEntityEvent event) {
		String IPlayer = event.getRightClicked().getName();
		if (event.getPlayer().hasPermission("server.admin")) {
			if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) 
				event.getPlayer().chat("/invsee " + IPlayer);
			if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOOK) 
				event.getPlayer().chat("/pinfo " + IPlayer);
		}
	}
}