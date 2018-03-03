package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Help implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("help")) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage("§f§l§m--------------------------------------");
				player.sendMessage("§b§l                         Icewynd Help");
				player.sendMessage("§f§l§m--------------------------------------");
				player.sendMessage(
						"§7 Use this menu to familiarize yourself will the custom commands on this server. Select each category by clicking on them.");
				TextComponent factions = new TextComponent("§f§l> §eFactions");
				factions.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help factions"));
				player.spigot().sendMessage(factions);
				TextComponent Misc = new TextComponent("§f§l> §eMiscellaneous");
				Misc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help misc"));
				player.spigot().sendMessage(Misc);
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("factions")) {
					player.sendMessage("factions");
				} else if (args[0].equalsIgnoreCase("misc")) {
					player.sendMessage("§f§l§m--------------------------------------");
					player.sendMessage("§b§l                         Icewynd Help");
					player.sendMessage("§f§l§m--------------------------------------");
					player.sendMessage("   §b/ping §f- §7Check your ping. This works on others.");
					player.sendMessage("   §b/newplayerbook §f- §7Get the new player book of information.");
					player.sendMessage("   §b/disposal §f- §7Dispose of no longer needed items.");
					player.sendMessage("   §b/ci §f- §7Clear your inventory. This does not clear armour.");
					player.sendMessage("   §b/ec §f& §b/enderchest §f- §7Open your enderchest.");
					player.sendMessage("   §b/feed §f- §7Replenish your hunger.");
					player.sendMessage("   §b/freecam §f- §7Freely look at your surroundings.");
					TextComponent Page2 = new TextComponent("§f§l> §ePAGE 2");
					Page2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help misc 2"));
					player.spigot().sendMessage(Page2);
				} else if (args.length == 1) {
					if (args[1].equalsIgnoreCase("2")) {
						player.sendMessage("§f§l§m--------------------------------------");
						player.sendMessage("§b§l                         Icewynd Help");
						player.sendMessage("§f§l§m--------------------------------------");
						player.sendMessage("   §b/kit §f- §7Select and spawn in kit crates.");
						player.sendMessage("   §b/nv §f& §b/nightvision §f- §7Makes it easier to see.");
						player.sendMessage("   §b/msgtoggle §f& §b/tptoggle §f- §7Toggles messages or teleporting.");
						player.sendMessage("   §b/patchnotes §f- §7Catch up on the latest weekly update.");
						player.sendMessage("   §b/rules §f- §7List of server rules");
						player.sendMessage("   §b/sell §f& §b/sell all §f- §7Opens an inventory to deposit items.");
						player.sendMessage("   §b/shop §f- §7Opens the server shop GUI.");
						player.sendMessage("   §b/stats §f- §7Check your server stats, this works on other players.");
					}
				}
			}
		}
		return false;
	}
}
						player.sendMessage("   §b/kit §f- §7Select and spawn in kit crates.");
						player.sendMessage("   §b/nv §f& §b/nightvision §f- §7Makes it easier to see.");
						player.sendMessage("   §b/msgtoggle §f& §b/tptoggle §f- §7Toggles messages or teleporting.");
						player.sendMessage("   §b/patchnotes §f- §7Catch up on the latest weekly update.");
						player.sendMessage("   §b/rules §f- §7List of server rules");
						player.sendMessage("   §b/sell §f& §b/sell all §f- §7Opens an inventory to deposit items.");
						player.sendMessage("   §b/shop §f- §7Opens the server shop GUI.");
						player.sendMessage("   §b/stats §f- §7Check your server stats, this works on other players.");
					}
				}
			}
		}
		return false;
	}
}
