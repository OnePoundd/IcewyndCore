package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Main.Main;

public class PatchNotes implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("patchnotes")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("§8§l§m--------§8§l[§e§l Patch Notes §8§l]§8§l§m--------");
				player.sendMessage("§7>> §c§lDecember 1st - December 7th:");
				player.sendMessage("§a[+]§f Text1");
				player.sendMessage("§a[+]§f Text1");
				player.sendMessage("§c[-]§f Text1");
				player.sendMessage("§c[-]§f Text1");
			}
		}
		return false;
	}
}