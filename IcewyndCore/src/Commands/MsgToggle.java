package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.massivecraft.factions.entity.MPlayer;

import Main.Main;

public class MsgToggle implements CommandExecutor, Listener{
	
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msgtoggle")) {
			Player player = (Player) sender;
			if (plugin.getConfig().getBoolean(player.getUniqueId() + ".MsgToggle") == true) {
				plugin.getConfig().set(player.getUniqueId() + ".MsgToggle", false);
				player.sendMessage("§c§l(!)§7 You will now receive messages from other players!");
			}else {
				plugin.getConfig().set(player.getUniqueId() + ".MsgToggle", true);
				player.sendMessage("§c§l(!)§7 You will no longer receive messages from other players!");
			}
		}
		return false;
	}
}
