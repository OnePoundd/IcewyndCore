package Commands;

import java.util.Arrays;

import org.bukkit.Location;
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
				Player player = (Player) sender;
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("on")) {
						if (player.hasPermission("server.admin")) {
							ItemStack RandomTP = new ItemStack(Material.EYE_OF_ENDER, 1);
							ItemMeta meta = RandomTP.getItemMeta();
							meta.setDisplayName("§d§lRandom TP");
							meta.setLore(Arrays.asList("§eTeleport to a random player."));
							RandomTP.setItemMeta(meta);
							player.getInventory().setItem(0, RandomTP);
							player.sendMessage("§a§lOverwatch Enabled!");
					}else if (args[0].equalsIgnoreCase("off"));{
						if (player.hasPermission("server.admin")) {
							player.getInventory().clear();
							Location spawn = player.getWorld().getSpawnLocation();
							player.teleport(spawn);
							player.sendMessage("§4§lOverwatch Disabled!");
						
							
							}
						}
					}
				}
			}
	}
		return false;
}
}