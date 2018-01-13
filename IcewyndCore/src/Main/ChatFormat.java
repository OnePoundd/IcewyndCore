package Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent c) {
		if (!c.isCancelled()) {
			String ChatMessage = c.getMessage();
			Player player = c.getPlayer();
			c.setCancelled(true);
			if (player.hasPermission("server.admin")) {
				Bukkit.broadcastMessage("§7§l[§c§lADMIN§7§l]§b " + player.getName() + " §a» §f" + ChatMessage);
			} else if (player.hasPermission("server.chatmod")) {
				Bukkit.broadcastMessage("§f§l[§bChatMod§f§l]§a " + player.getName() + " §7» §f" + ChatMessage);
			} else {
				Bukkit.broadcastMessage("§8[§fMember§8]§7 " + player.getName() + " » §f" + ChatMessage);
			}
		}
	}
}