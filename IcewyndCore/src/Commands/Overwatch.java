package Commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import Main.Main;

public class Overwatch implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ow")) {
			if (sender instanceof Player) {
				if (args.length == 1) {
					Player player = (Player) sender;
					if (player.hasPermission("server.admin")) {
						ItemStack RandomTP = new ItemStack(Material.EYE_OF_ENDER, 1);
						ItemMeta meta = RandomTP.getItemMeta();
						meta.setDisplayName("§d§lRandom TP");
						meta.setLore(Arrays.asList("§eTeleport to a random player."));
						RandomTP.setItemMeta(meta);
						player.getInventory().addItem(RandomTP);
						player.sendMessage("§a§lOverwatch Enabled!");
					} else {
						player.sendMessage("§cYou do not have permission.");
					}
				}
			}
		}
		return false;
	}
}