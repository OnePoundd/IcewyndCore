package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class YoutubeBroadcast implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("youtube")) {
			Player player = (Player) sender;
			if (player.hasPermission("icewynd.youtube")) {
				String link = args[0];
				if (link.contains("youtube.com/")) {
					TextComponent message = new TextComponent( "�f�lYOU�c�lTUBE�8�l � �b�l" + player.getName() + " has uploaded a new video! Click �b�lhere to check it out." );
					message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" + link ) );
					Bukkit.broadcast( message );
				}else {
					player.sendMessage("�cYour message must contain a Youtube link!");
				}
			}else {
				player.sendMessage("�cYou must have the youtube rank to do that!");
			}
		}
		return false;
	}
}