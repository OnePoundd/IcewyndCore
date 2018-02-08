package Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Main.Main;

public class Sell implements CommandExecutor{

	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sell")) {
			Player player = (Player) sender;
			ItemStack[] itemsToSell = player.getInventory().getContents();
			for(ItemStack item : itemsToSell) {
				if(Main.pricesConfig.getDouble(String.valueOf(item.getType().getId())+ ".Sell") != 0) {
					System.out.println("sellable");
				}else {
					System.out.println("unsellable");
				}
			}
			
		}
		return false;
	}
	
}
