package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import Crates.EventCrate;
import Crates.ExoticCrate;
import Crates.LegendaryCrate;
import CustomEnchants.Enchanter;
import CustomEnchants.Librarian;
import Main.SandstoneBiome;

public class Crates implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("crategive")) {
			if (!sender.hasPermission("crate.give")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					if (args[1].equalsIgnoreCase("legendary")) {
						LegendaryCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("exotic")) {
						ExoticCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("event")) {
						EventCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else {
						sender.sendMessage(
								"§c§l(!)§7 Incorrect usage. Try /crate <playername> <legendary/exotic/event>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 Usage: /crategive <playername> <legendary/exotic>");
			}
		} else if (label.equalsIgnoreCase("librarian")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Librarian.openInventory(player);
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 That player cannot be found!");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /librarian <playername>!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
			}
		} else if (label.equalsIgnoreCase("enchanter")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Enchanter.openInventory(player);
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 That player cannot be found!");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /enchanter <playername>!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
			}
		} else if (label.equalsIgnoreCase("spawnergive")) {
			if (!sender.hasPermission("spawner.give")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					System.out.println(player.getName());
					ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta spawnerMeta = spawner.getItemMeta();
					spawnerMeta.setDisplayName("§e" + args[1].toUpperCase().replaceAll("_", " ") + " §fSpawner");
					spawner.setItemMeta(spawnerMeta);
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("§b§l(!)§7 Your inventory is full, dropping spawner at your feet!");
						player.getWorld().dropItem(player.getLocation(), spawner);
					} else {
						player.sendMessage("§b§l(!)§7 A spawner has been added to your inventory!");
						player.getInventory().addItem(spawner);
					}
					sender.sendMessage("§a§l(!)§7 Successfully added the spawner to the player's inventory!");
				} catch (Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 Usage: /spawnergive <playername> <type>");
			}
		} else if (label.equalsIgnoreCase("sandstone")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (player.hasPermission("server.admin")) {
				SandstoneBiome.convert(player);
			} else {
				player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
			}
		} else if (label.equalsIgnoreCase("xp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			player.sendMessage("§b§l(!)§7 You have §8" + player.getExp() + " §7experience!");
		} else if (label.equalsIgnoreCase("xpgive")) {
			if (sender instanceof Player) {
				Player player = Bukkit.getPlayer(sender.getName());
				if (player.hasPermission("server.admin")) {
					if (args.length == 2) {
						try {
							Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
							player.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
						} catch (Exception e) {
							player.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
						}
					} else {
						player.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
					}
				} else {
					player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
				}
			} else {
				if (args.length == 2) {
					try {
						Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
						sender.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
				}
			}
		}
		return true;
	}
}