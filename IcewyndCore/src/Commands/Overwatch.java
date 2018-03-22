package Commands;

import java.util.Arrays;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Main.Main;


public class Overwatch implements CommandExecutor, Listener{

	static Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ow") || cmd.getName().equals("overwatch")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("icewynd.admin")) {
					if(!plugin.getConfig().getBoolean(player.getUniqueId() + ".Overwatch")) {
						plugin.getConfig().set(player.getUniqueId() + ".Overwatch", true);
						plugin.getConfig().set(player.getUniqueId() + ".OverwatchStartLocation", player.getLocation());
						plugin.getConfig().set(player.getUniqueId() + ".OverwatchStartInventory", player.getInventory());
						plugin.saveConfig();

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
					}else if(plugin.getConfig().getBoolean(player.getUniqueId() + ".Overwatch")) {
						player.getInventory().setContents(((Inventory) plugin.getConfig().get(player.getUniqueId() + ".OverwatchStartInventory")).getContents());
						player.teleport((Location) plugin.getConfig().get(player.getUniqueId() + ".OverwatchStartLocation"));
						plugin.getConfig().set(player.getUniqueId() + ".Overwatch", false);
						plugin.saveConfig();
						player.sendMessage("§d§l(!) §c§lOverwatch Disabled!");
					}
				}else {
					player.sendMessage("§c§l(!) §7You must be an admin to enable overwatch!");
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
		if (player.hasPermission("icewynd.admin")) {
			if (player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("§9§lOpen Inventory")) {
					event.getPlayer().chat("/invsee " + IPlayer);
				}
			} else if (event.getPlayer().getInventory().getItemInHand().getType() == Material.BOOK) {
				if (player.getItemInHand().getItemMeta().getDisplayName().equals("§b§lPlayer Info")) {
					event.getPlayer().chat("/pinfo " + IPlayer);
				}
			}
		}
	}
}