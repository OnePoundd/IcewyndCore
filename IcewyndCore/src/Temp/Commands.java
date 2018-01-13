package Temp;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.massivecraft.factions.entity.MPlayer;

import Main.Main;

public class Commands implements Listener, CommandExecutor {
	Main plugin = Main.getPlugin(Main.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// CI COMMAND
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack[] armorContents = player.getInventory().getArmorContents().clone();
				player.getInventory().clear();
				player.getInventory().setArmorContents(armorContents);
				player.updateInventory();
				player.sendMessage("§eYour inventory has been cleared");
				plugin.getConfig().set(player.getUniqueId() + ".Name", player.getName());
				plugin.getConfig().set(player.getUniqueId() + ".Coins", 0);
				plugin.saveConfig();
			}
		}
		// RULES COMMAND
		else if (cmd.getName().equalsIgnoreCase("rules")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("§8§l§m--------§8§l[§a§l Rules §8§l]§8§l§m--------");
				player.sendMessage("§eClick here for more indepth rules.");
				player.sendMessage("§a[1]§f Do not spam chat excessively.");
				player.sendMessage("§a[2]§f Do not be excessively racist in chat.");
				player.sendMessage("§a[3]§f Do not run scripts, macros or clients to change gameplay.");
				player.sendMessage("§a[4]§f Do not discuss DDoSing or Doxing.");
				int coinbalance = plugin.getConfig().getInt(player.getUniqueId() + ".Coins");
				plugin.getConfig().set(player.getUniqueId() + ".Coins", coinbalance + 1);
				plugin.saveConfig();
			}
		}
		// PATCHNOTES COMMAND
		else if (cmd.getName().equalsIgnoreCase("patchnotes")) {
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
		// PING COMMAND
		else if (cmd.getName().equalsIgnoreCase("ping")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				int ping = ((CraftPlayer) p).getHandle().ping;
				p.sendMessage("§aPing §7»§f " + ping);
			}
		}
		// PINFO COMMAND
		else if (cmd.getName().equalsIgnoreCase("pinfo")) {
			if (sender instanceof Player) {
				if (args.length == 2) {
					Player p = (Player) sender;
					MPlayer mplayer = MPlayer.get(p);
					String faction = mplayer.getFactionName();
					int ping = ((CraftPlayer) p).getHandle().ping;
					p.sendMessage("§8§l§m-------§r§8§l[ §a§l" + args[0] + " §r§8§l]§8§l§m-------");
					p.sendMessage("§cIP:§f " + p.getAddress().getHostName());
					p.sendMessage("§cPing:§f " + ping);
					p.sendMessage("§cFirst Seen:§f " + p.getFirstPlayed());
					p.sendMessage("§cLast Seen:§f " + p.getLastPlayed());
					p.sendMessage("§cTimes Seen:§f " + "");
					p.sendMessage("§cFaction:§f " + faction);
					p.sendMessage("§cXP Level:§f " + p.getExpToLevel());
					p.sendMessage("§cGamemode:§f " + p.getGameMode());
				}
			}
		}
		// OVERWATCH
		else if (cmd.getName().equalsIgnoreCase("ow")) {
			if (sender instanceof Player) {
				if (args.length == 1) {
					Player player = (Player) sender;
					if (player.hasPermission("server.admin")) {
						ItemStack RandomTP = new ItemStack(Material.EYE_OF_ENDER, 1);
						ItemMeta meta = RandomTP.getItemMeta();
						meta.setDisplayName("§d§lRandom TP");
						meta.setLore(Arrays.asList("§eTeleport to a random player."));
						RandomTP.setItemMeta(meta);
						player.getInventory().addItem(RandomTP);
						player.sendMessage("§a§lOverwatch Enabled!");
					} else {
						player.sendMessage("§cYou do not have permission.");
					}
				}
			}
		}
		// CLEARCHAT COMMAND
		else if (cmd.getName().equalsIgnoreCase("clearchat")) {
			for (int i = 0; i < 150; i++) {
				Bukkit.broadcastMessage("§1");
			}
			Bukkit.broadcastMessage("§f§l[§b§lIcyWynd§f§l]§a§l Chat has been cleared!");
		}
		// MSG COMMAND
		else if (cmd.getName().equalsIgnoreCase("msg")) {
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