package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerGive implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("spawnergive")) {
			if (!sender.hasPermission("icewynd.admin")) {
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
		}
		return false;
	}
	
}
