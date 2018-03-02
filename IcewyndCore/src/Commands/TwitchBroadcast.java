package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TwitchBroadcast implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("twitch")) {
			Player player = (Player) sender;
			if (player.hasPermission("server.twitch")) {
				String link = args[0];
				if (link.contains("twitch.tv")) {
					TextComponent message = new TextComponent( "§5§lTWITCH§8§l » §b§l" + player.getName() + " has started streaming! Click here §b§lto check it out." );
					message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "" + link ) );
					Bukkit.broadcast( message );
				}else {
					player.sendMessage("§cYour message must contain a Twitch link!");
				}
			}
		}
		return false;
	}
}