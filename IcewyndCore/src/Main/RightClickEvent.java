package Main;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class RightClickEvent implements Listener {

	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player player = event.getPlayer();
			//Skeleton Boss Egg
			if(player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßcßlPlagued Skeleton")) {
					if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						event.setCancelled(true);
						if(BoardColl.get().getFactionAt(PS.valueOf(event.getClickedBlock())).getName().equalsIgnoreCase("Warzone")) {
							player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							Skeleton skeleton = (Skeleton) player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0,1,0), EntityType.SKELETON);
							skeleton.setSkeletonType(SkeletonType.WITHER);
							skeleton.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 3)); //strength 4
							skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 2)); //speed 3
							skeleton.setHealth(300.0);
							skeleton.setCustomName("ßcßlPlagued Skeleton");
							Block block = event.getClickedBlock();
							Location locB = block.getLocation().getBlock().getLocation();
							MPlayer mplayer = MPlayer.get(player);
							String faction = mplayer.getFactionName();
							String nut = "ßdßlBOSS EGGSß8ßl ª ß7A ßcPlagued Skeleton ß7has been summoned in Warzone by the faction ße" + StringUtils.capitalize(faction) + "ß7! Coords: [" + locB.getBlockX() + ", " + locB.getBlockY() + ", " + locB.getBlockZ() + "]";
							Bukkit.broadcastMessage(nut);
						}else {
							player.sendMessage("ßcBoss eggs can only be used in WarZone!");
						}
					}
				//Charged Creeper Item
				}else if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßaßlßnCharged Creeper Egg")) {
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0),EntityType.CREEPER);
					Block block = event.getClickedBlock();
					Location locB = block.getLocation().getBlock().getLocation();
					block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
				//TNT Crate Item
				} else if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßcßlßnCrate of TNT")) {
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("ßcYou do not have the required inventory space.");
						event.setCancelled(true);
						player.closeInventory();
					} else {
						player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
						player.getInventory().addItem(new ItemStack(Material.TNT, 2304));
						event.setCancelled(true);
					}

				//Mystery Spawner Item
				} else if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßdßlßnMystery Spawner")) {
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("ßcYou do not have the required inventory space.");
						event.setCancelled(true);
						player.closeInventory();
					} else {
						Random rand = new Random();
						int index5 = rand.nextInt(5) + 1;
						if (index5 == 1) {
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spawnergive " + player.getName() + " villager");
							Bukkit.broadcastMessage(" " + player.getName() + " was lucky and recieved a ßdßlVillager spawnerßbßl from a Mystery Spawner!" + " ßdßl≈ì¶");
						} else if (index5 == 2) {
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " creeper");
						} else if (index5 == 3) {
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " enderman");
						} else if (index5 == 4) {
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " blaze");
						} else if (index5 == 5) {
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " witch");
						}
						player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
					}

				//Overwatch Random Teleport Item
				} else if (player.getItemInHand().getItemMeta().getDisplayName().equals("ßdßlRandom TP")) {
					event.setCancelled(true);
					ArrayList<Player> players = new ArrayList<Player>();
					for (Player e : Bukkit.getOnlinePlayers())
						players.add(e);
					Player randomPlayer = players.get(new Random().nextInt(players.size()));
					player.teleport(randomPlayer.getLocation());
				}
			}
		}
	}
}