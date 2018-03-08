package Commands;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Overwatch implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ow")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 1) {
					if (player.hasPermission("server.admin")) {
						if (args[0].equalsIgnoreCase("on")) {
							ItemStack RandomTP = new ItemStack(Material.EYE_OF_ENDER, 1);
							ItemMeta meta = RandomTP.getItemMeta();
							meta.setDisplayName("§d§lRandom TP");
							meta.setLore(Arrays.asList("§eTeleport to a random player."));
							RandomTP.setItemMeta(meta);

							ItemStack OpenInv = new ItemStack(Material.BLAZE_ROD, 1);
							ItemMeta meta1 = OpenInv.getItemMeta();
							meta1.setDisplayName("§9§lOpen Inventory");
							meta1.setLore(Arrays.asList("§eOpen target players inventory."));
							OpenInv.setItemMeta(meta1);

							ItemStack PInfo = new ItemStack(Material.BOOK, 1);
							ItemMeta meta2 = PInfo.getItemMeta();
							meta2.setDisplayName("§b§lPlayer Info");
							meta2.setLore(Arrays.asList("§eView targeted players information"));
							PInfo.setItemMeta(meta2);

							player.getInventory().setItem(0, RandomTP);
							player.getInventory().setItem(2, OpenInv);
							player.getInventory().setItem(4, PInfo);
							player.sendMessage("§d§l(!) §a§lOverwatch Enabled!");
						} else if (args[0].equalsIgnoreCase("off")) {
							if (player.hasPermission("server.admin")) {
								player.getInventory().clear();
								Location spawn = player.getWorld().getSpawnLocation();
								player.teleport(spawn);
								player.sendMessage("§d§l(!) §c§lOverwatch Disabled!");
							}
						}
					}
				}
			}
		}
		return false;
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