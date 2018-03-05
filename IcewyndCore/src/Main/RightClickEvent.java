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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.massivecraft.factions.entity.MPlayer;
import Commands.Kits;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class RightClickEvent implements Listener {

		@SuppressWarnings("deprecation")
		// Quartermaster Items
		@EventHandler
		public void onPlayerUse(PlayerInteractEvent event) {
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Player player = event.getPlayer();
				if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
					if (player.getItemInHand().getItemMeta().getDisplayName().equals("�c�lPlagued Skeleton")) {
						player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
						player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0),EntityType.fromId(5));
						Block block = event.getClickedBlock();
						Location locB = block.getLocation().getBlock().getLocation();
						MPlayer mplayer = MPlayer.get(player);
						String faction = mplayer.getFactionName();

						String nut = "�d�lBOSS EGGS�8�l � �7�lA �cPlagued Skeleton �7�lhas been summoned �7�lto Warzone by �e�l" + StringUtils.capitalize(faction) + "�7�l! �fCoords: [" + locB.getBlockX() + ", " + locB.getBlockY() + ", " + locB.getBlockZ() + "]";
						TextComponent text = new TextComponent(nut);
						text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("�c�lPlagued Skeleton \n \n�c�lRare �7�lboss egg that can be summoned in the Warzone. \n�7�lSlay this boss to be rewarded with a prize! \n \n�c�lDifficulty: �75 Players").create()));
						for (Player player11 : Bukkit.getOnlinePlayers()) {
							player11.sendMessage(text);
							block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
						}

						// Charged Creeper
					} else if (player.getItemInHand().getType().equals(Material.MONSTER_EGG)) {
						if (player.getItemInHand().getItemMeta().getDisplayName().equals("�a�l�nCharged Creeper Egg")) {
							player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0),EntityType.CREEPER);
							Block block = event.getClickedBlock();
							Location locB = block.getLocation().getBlock().getLocation();
							block.getWorld().spawnEntity(locB, EntityType.LIGHTNING);
						}
					} else if (player.getItemInHand().getType().equals(Material.TRAPPED_CHEST)) {
						if (player.getItemInHand().getItemMeta().getDisplayName().equals("�c�l�nCrate of TNT")) {
							if (player.getInventory().firstEmpty() == -1) {
								player.sendMessage("�cYou do not have the required inventory space.");
								event.setCancelled(true);
								player.closeInventory();
							} else {
								player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
								player.getInventory().addItem(new ItemStack(Material.TNT, 2304));
								event.setCancelled(true);
							}
						}

						// Mystery Spawner
					} else if (player.getItemInHand().getType().equals(Material.MOB_SPAWNER)) {
						if (player.getItemInHand().getItemMeta().getDisplayName().equals("�d�l�nMystery Spawner")) {
							if (player.getInventory().firstEmpty() == -1) {
								player.sendMessage("�cYou do not have the required inventory space.");
								event.setCancelled(true);
								player.closeInventory();
							} else {
								Random rand = new Random();
								int index5 = rand.nextInt(5) + 1;
								if (index5 == 1) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "spawnergive " + player.getName() + " villager");
									Bukkit.broadcastMessage(" " + player.getName() + " was lucky and recieved a �d�lVillager spawner�b�l from a Mystery Spawner!" + " �d�lœ�");
								} else if (index5 == 2) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " creeper");
								} else if (index5 == 3) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " enderman");
								} else if (index5 == 4) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " blaze");
								} else if (index5 == 5) {
									Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"spawnergive " + player.getName() + " witch");
								}
								player.getInventory().getItemInHand()
								.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							}
						}
					} else if (player.getItemInHand().getType().equals(Material.EYE_OF_ENDER)) {
						if (player.getItemInHand().getItemMeta().getDisplayName().equals("�d�lRandom TP")) {
							event.setCancelled(true);
							ArrayList<Player> players = new ArrayList<Player>();
							for (Player e : Bukkit.getOnlinePlayers())
								players.add(e);
							Player randomPlayer = players.get(new Random().nextInt(players.size()));
							player.teleport(randomPlayer.getLocation());
						}
					}
				} else if (player.getItemInHand().getType().equals(Material.CHEST)) {
					if (player.getItemInHand().getItemMeta().getDisplayName() == ("�2�l�nFighter Kit Crate")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("�cYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						} else {
							player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							Kits.giveKitFighter(player);
						}
					} else if (player.getItemInHand().getItemMeta().getDisplayName() == ("�d�l�nWarlord Kit Crate")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("�cYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						} else {
							player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							Kits.giveKitWarlord(player);
						}
					} else if (player.getItemInHand().getItemMeta().getDisplayName() == ("�c�l�nEmporer Kit Crate")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("�cYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						} else {
							player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							Kits.giveKitEmporer(player);
						}
					} else if (player.getItemInHand().getItemMeta().getDisplayName() == ("�9�l�nGod Kit Crate")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("�cYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						} else {
							player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							Kits.giveKitGod(player);
						}
					} else if (player.getItemInHand().getItemMeta().getDisplayName()
							.equals("�b�l�nIcewynd Kit Crate")) {
						if (player.getInventory().firstEmpty() == -1) {
							player.sendMessage("�cYou do not have the required inventory space.");
							event.setCancelled(true);
							player.closeInventory();
						} else {
							player.getInventory().getItemInHand()
							.setAmount(player.getInventory().getItemInHand().getAmount() - 1);
							Kits.giveKitIcewynd(player);
						}
					}
				}
			}
		}




		// Quartermaster Item - PVP Potion
		@EventHandler
		public void onDrink(PlayerItemConsumeEvent e) {
			Player player = e.getPlayer();
			if (player.getItemInHand().getType().equals(Material.POTION)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("�4�l�nElixir of Fury")) {
					;
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 1));
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 1));
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9570, 1));
					player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
				}
			}
		}

		@EventHandler
		public void onClick(PlayerInteractEntityEvent event) {
			// Overwatch Tools
			String IPlayer = event.getRightClicked().getName();
			Player player = event.getPlayer();
			if (player.hasPermission("server.admin")) {
				if (player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
					if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
						event.getPlayer().chat("/invsee " + IPlayer);
					}
				} else if (event.getPlayer().getInventory().getItemInHand().getType() == Material.BOOK) {
					if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasDisplayName()) {
						event.getPlayer().chat("/pinfo " + IPlayer);
					}
				}
			}
		}
	}

