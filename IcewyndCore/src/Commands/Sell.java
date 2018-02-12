package Commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.massivecraft.factions.entity.MPlayer;
import Main.Main;

public class Sell implements CommandExecutor, Listener{

	Main plugin = Main.getPlugin(Main.class);

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sell")) {
			Player player = (Player) sender;
			if(args.length == 0) {
				openSellInventory(player);
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("all")) {
					double totalValue = 0;
					for(ItemStack item : player.getInventory().getContents()) {
						if(item != null) {
							double value = Main.pricesConfig.getDouble(String.valueOf(item.getTypeId())+ ".Sell") * item.getAmount();
							if(value > 0) {
								item.setAmount(0);
								totalValue = totalValue + value;
							}
						}
					}
					if(MPlayer.get(player).getFaction().getOwnsCastle()) {
						totalValue = totalValue * 1.1;
					}
					Main.econ.depositPlayer(player, totalValue);
					player.sendMessage("§a§l(!)§7 You sold all your sellable items for §a$" + totalValue);
				}else if(args[0].equalsIgnoreCase("hand")) {
					ItemStack item = player.getItemInHand();
					double value = Main.pricesConfig.getDouble(String.valueOf(item.getTypeId())+ ".Sell") * item.getAmount();
					if(value > 0) {
						item.setAmount(0);
					}
					if(MPlayer.get(player).getFaction().getOwnsCastle()) {
						value = value * 1.1;
					}
					Main.econ.depositPlayer(player, value);
					player.sendMessage("§a§l(!)§7 You sold the items in your hand for §a$" + value);
				}else {
					player.sendMessage("§c§l(!)§7 Usage: /sell, /sell all, or /sell hand");
				}
			}else {
				player.sendMessage("§c§l(!)§7 Usage: /sell, /sell all, or /sell hand");
			}
		}
		return false;
	}
	
	public void openSellInventory(Player player) {
		Inventory sellInv = Bukkit.createInventory(null, 54, "§c§l>> §8Sell §c§l<<");

		ItemStack sell = new ItemStack(Material.EMERALD);
		ItemMeta sellMeta = sell.getItemMeta();
		sellMeta.setDisplayName("§f§l>> §aSell Items §f§l<<");
		ArrayList<String> sellLore = new ArrayList<String>();
		sellLore.add("§7Click this to sell all the items that");
		sellLore.add("§7you have added to this inventory!");
		sellMeta.setLore(sellLore);
		sell.setItemMeta(sellMeta);

		sellInv.setItem(53, sell);
		player.openInventory(sellInv);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getClickedInventory() != null) {
			if(event.getClickedInventory().getName().equals("§c§l>> §8Sell §c§l<<")) {
				if(event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && 
				event.getCurrentItem().getItemMeta().hasDisplayName() && event.getCurrentItem().getItemMeta().getDisplayName().equals("§f§l>> §aSell Items §f§l<<")) {
					event.setCancelled(true);
					double totalValue = 0;
					for(ItemStack item : event.getClickedInventory().getContents()) {
						if(item != null) {
							if(!(item.getType().equals(Material.EMERALD) && item.hasItemMeta() && item.getItemMeta().hasDisplayName())) {
								double value = Main.pricesConfig.getDouble(String.valueOf(item.getTypeId())+ ".Sell") * item.getAmount();
								if(value > 0) {
									item.setAmount(0);
									totalValue = totalValue + value;
								}else {
									player.getLocation().getWorld().dropItem(player.getLocation(), item);
									item.setAmount(0);
								}
							}
						}
					}
					if(MPlayer.get(player).getFaction().getOwnsCastle()) {
						totalValue = totalValue * 1.1;
					}
					Main.econ.depositPlayer(player, totalValue);
					player.sendMessage("§a§l(!)§7 You sold all your sellable items for §a$" + totalValue);
					player.closeInventory();
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if(event.getInventory().getName().equals("§c§l>> §8Sell §c§l<<")) {
			for(ItemStack item : event.getInventory().getContents()) {
				if(item != null) {
					if(!(item.getType().equals(Material.EMERALD) && item.hasItemMeta() && item.getItemMeta().hasDisplayName())) {
						event.getPlayer().getLocation().getWorld().dropItem(event.getPlayer().getLocation(), item);
					}
				}
			}
		}
	}
	
}
