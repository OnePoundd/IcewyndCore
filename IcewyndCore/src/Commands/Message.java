package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Main.Main;

public class Message implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msg")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Player r = Bukkit.getPlayer(args[0]);
				if (args.length == 2) {
					player.sendMessage("§a§lMe" + " §7» " + "§b" + args[0] + "§7:§f " + args[1]);
					r.sendMessage("§b" + player.getName() + " §7» " + "§a§lMe§f" + "§7:§f " + args[1]);
					r.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
				}
			}
		}
		return false;
	}
}