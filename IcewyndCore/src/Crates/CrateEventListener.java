package Crates;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import Main.Main;

public class CrateEventListener implements Listener {
	Main plugin = Main.getPlugin(Main.class);
	// CRATE OPENING
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			ItemStack item = player.getItemInHand();
			if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
				String name = item.getItemMeta().getDisplayName();
				if (item.getType().equals(Material.CHEST)) {
					if (name.equals("§eExotic Crate")) {
						event.setCancelled(true);
						ExoticCrate.open(player);
					} else if (name.equals("§5Legendary Crate")) {
						event.setCancelled(true);
						LegendaryCrate.open(player);
					} else if (name.equals("§3Event Crate")) {
						event.setCancelled(true);
						EventCrate.open(player);
					}
				} else if (item.getType().equals(Material.PAPER)) {
					if (name.equals("§6$250,000 Cash")) {
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 $250,000 has been added to your account!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								"ecoadmin give " + player.getName() + " 250000");
					} else if (name.equals("§6$150,000 Cash")) {
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 $150,000 has been added to your account!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								"ecoadmin give " + player.getName() + " 150000");
					} else if (name.equals("§6$100,000 Cash")) {
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 $100,000 has been added to your account!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								"ecoadmin give " + player.getName() + " 100000");
					} else if (name.equals("§6150 McMMO Credits")) {
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 150 McMMO credits have been added to your account!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								"credits add " + player.getName() + " 150");
					} else if (name.equals("§6100 McMMO Credits")) {
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 100 McMMO credits have been added to your account!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								"credits add " + player.getName() + " 100");
					} else if (name.equals("§675 McMMO Credits")) {
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 75 McMMO credits have been added to your account!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
								"credits add " + player.getName() + " 75");
					}
				} else if (item.getType().equals(Material.MONSTER_EGG)) {
					if (name.equals("§6Villager Disguise")) {
						event.setCancelled(true);
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 You have been granted access to the villager disguise!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName()
						+ " permission set libsdisguises.disguise.villager true global");
					} else if (name.equals("§6Creeper Disguise")) {
						event.setCancelled(true);
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 You have been granted access to the creeper disguise!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName()
						+ " permission set libsdisguises.disguise.creeper true global");
					} else if (name.equals("§6Zombie Disguise")) {
						event.setCancelled(true);
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 You have been granted access to the zombie disguise!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName()
						+ " permission set libsdisguises.disguise.zombie true global");
					} else if (name.equals("§6Skeleton Disguise")) {
						event.setCancelled(true);
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.sendMessage("§b§l(!)§7 You have been granted access to the skeleton disguise!");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName()
						+ " permission set libsdisguises.disguise.skeleton true global");
					}
				} else if (item.getType().equals(Material.EXP_BOTTLE)) {
					Faction faction = MPlayer.get(player).getFaction();
					if (name.equals("§63 Hour Faction McMMO Booster (50%)")) {
						faction.setMcmmoBoost(0.5);
						faction.setBoostEndMillis(System.currentTimeMillis() + (3 * 3600000));
						faction.msg("§b§l(!)§7 A 3 hour, 50% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§63 Hour Faction McMMO Booster (100%)")) {
						faction.setMcmmoBoost(1.0);
						faction.setBoostEndMillis(System.currentTimeMillis() + (3 * 3600000));
						faction.msg("§b§l(!)§7 A 3 hour, 100% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§66 Hour Faction McMMO Booster (50%)")) {
						faction.setMcmmoBoost(0.5);
						faction.setBoostEndMillis(System.currentTimeMillis() + (6 * 3600000));
						faction.msg("§b§l(!)§7 A 6 hour, 50% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§66 Hour Faction McMMO Booster (100%)")) {
						faction.setMcmmoBoost(1.0);
						faction.setBoostEndMillis(System.currentTimeMillis() + (6 * 3600000));
						faction.msg("§b§l(!)§7 A 6 hour, 100% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§612 Hour Faction McMMO Booster (50%)")) {
						faction.setMcmmoBoost(0.5);
						faction.setBoostEndMillis(System.currentTimeMillis() + (12 * 3600000));
						faction.msg("§b§l(!)§7 A 12 hour, 50% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§612 Hour Faction McMMO Booster (100%)")) {
						faction.setMcmmoBoost(1.0);
						faction.setBoostEndMillis(System.currentTimeMillis() + (12 * 3600000));
						faction.msg("§b§l(!)§7 A 12 hour, 100% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§624 Hour Faction McMMO Booster (50%)")) {
						faction.setMcmmoBoost(0.5);
						faction.setBoostEndMillis(System.currentTimeMillis() + (24 * 3600000));
						faction.msg("§b§l(!)§7 A 24 hour, 50% mcmmo booster has been applied to your faction!");
					} else if (name.equals("§624 Hour Faction McMMO Booster (100%)")) {
						faction.setMcmmoBoost(1.0);
						faction.setBoostEndMillis(System.currentTimeMillis() + (24 * 3600000));
						faction.msg("§b§l(!)§7 A 24 hour, 100% mcmmo booster has been applied to your faction!");
					}
				}
			}
		}
	}

	// PREVENT STEALING ITEMS
	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent event) {
		if (event.getClickedInventory() != null && event.getClickedInventory().getName() != null) {
			String name = event.getClickedInventory().getName();
			if (name.equals("§eChoose 3") || name.equals("§5Choose 3") || name.equals("§3Choose 3")) {
				event.setCancelled(true);
				ItemStack item = event.getCurrentItem();
				if (item != null && item.getType().equals(Material.WOOL)) {
					int alreadyGot = 0;
					for (ItemStack currentItem : event.getInventory().getContents()) {
						if (!currentItem.getType().equals(Material.WOOL)) {
							alreadyGot = alreadyGot + 1;
						}
					}
					if (alreadyGot <= 1) {
						ItemStack rewardedItem = ExoticCrate.getRandomItem();
						event.getInventory().setItem(event.getSlot(), rewardedItem);
						event.getWhoClicked().getInventory().addItem(rewardedItem);
					}
					if (alreadyGot == 2) {
						ItemStack rewardedItem = ExoticCrate.getRandomItem();
						event.getInventory().setItem(event.getSlot(), rewardedItem);
						event.getWhoClicked().getInventory().addItem(rewardedItem);
						Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
							public void run() {
								event.getWhoClicked().closeInventory();
							}
						}, 20);
					}
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		String name = event.getInventory().getName();
		if (name.equals("§eChoose 3")) {
			int alreadyGot = 0;
			for (ItemStack currentItem : event.getInventory().getContents()) {
				if (!currentItem.getType().equals(Material.WOOL)) {
					alreadyGot = alreadyGot + 1;
				}
			}
			if (alreadyGot == 0) {
				event.getPlayer().getInventory().addItem(ExoticCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(ExoticCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(ExoticCrate.getRandomItem());
			} else if (alreadyGot == 1) {
				event.getPlayer().getInventory().addItem(ExoticCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(ExoticCrate.getRandomItem());
			} else if (alreadyGot == 2) {
				event.getPlayer().getInventory().addItem(ExoticCrate.getRandomItem());
			}
		} else if (name.equals("§5Choose 3")) {
			int alreadyGot = 0;
			for (ItemStack currentItem : event.getInventory().getContents()) {
				if (!currentItem.getType().equals(Material.WOOL)) {
					alreadyGot = alreadyGot + 1;
				}
			}
			if (alreadyGot == 0) {
				event.getPlayer().getInventory().addItem(LegendaryCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(LegendaryCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(LegendaryCrate.getRandomItem());
			} else if (alreadyGot == 1) {
				event.getPlayer().getInventory().addItem(LegendaryCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(LegendaryCrate.getRandomItem());
			} else if (alreadyGot == 2) {
				event.getPlayer().getInventory().addItem(LegendaryCrate.getRandomItem());
			}
		} else if (name.equals("§3Choose 3")) {
			int alreadyGot = 0;
			for (ItemStack currentItem : event.getInventory().getContents()) {
				if (!currentItem.getType().equals(Material.WOOL)) {
					alreadyGot = alreadyGot + 1;
				}
			}
			if (alreadyGot == 0) {
				event.getPlayer().getInventory().addItem(EventCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(EventCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(EventCrate.getRandomItem());
			} else if (alreadyGot == 1) {
				event.getPlayer().getInventory().addItem(EventCrate.getRandomItem());
				event.getPlayer().getInventory().addItem(EventCrate.getRandomItem());
			} else if (alreadyGot == 2) {
				event.getPlayer().getInventory().addItem(EventCrate.getRandomItem());
			}
		}
	}
}