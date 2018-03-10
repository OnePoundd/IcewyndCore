package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class Jackpot implements CommandExecutor{

	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("jackpot")) {
			if(args.length == 1) {
				try {
					if(!Main.jackpot.getPlayers().contains(sender.getName())) {
						int amount = Integer.parseInt(args[0]);
						if(amount > 4999 && amount < 1000001) {
							Main.jackpot.addEntry((Player) sender, amount);
						}else {
							sender.sendMessage("§f[§bJackpot§f] §cEnter a number between 5000 and 1000000!");
						}
					}else {
						sender.sendMessage("§f[§bJackpot§f] §cYou are already participating in this pot!");
					}
				}catch(NumberFormatException e) {
					sender.sendMessage("§f[§bJackpot§f] §cEnter a number between 5000 and 1000000!");
				}
			}else {
				sender.sendMessage("§f[§bJackpot§f] §7Type §b/jackpot <amount> §7to join the jackpot!");
			}
		}	
		return false;
	}
	
}