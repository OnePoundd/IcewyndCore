package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class Stats implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fstats")) {
			Player p = (Player) sender;
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				int timesplayed = plugin.getConfig().getInt(target.getUniqueId() + ".TimesPlayed");
				int blocksplaced = plugin.getConfig().getInt(target.getUniqueId() + ".BlocksPlaced");
				int playerskilled = plugin.getConfig().getInt(target.getUniqueId() + ".PlayersKilled");
				int mobskilled = plugin.getConfig().getInt(target.getUniqueId() + ".MobsKilled");
				int moneymade = plugin.getConfig().getInt(target.getUniqueId() + ".MoneyMade");
				int fishcaught = plugin.getConfig().getInt(target.getUniqueId() + ".FishCaught");
				int genbuckets = plugin.getConfig().getInt(target.getUniqueId() + ".GenBuckets");
				int sugarcanemined = plugin.getConfig().getInt(target.getUniqueId() + ".SugarcaneMined");
				int blocksmined = plugin.getConfig().getInt(target.getUniqueId() + ".BlocksMined");
				int luckydrops = plugin.getConfig().getInt(target.getUniqueId() + ".LuckyDrops");
				//Blocks placed is tracked in Sponge Patch, Misc class
				
				p.sendMessage("§8§l§m-------§r§8§l[ §a§l" + args[0] + "'s" + " Stats" + " §r§8§l]§8§l§m-------");
				p.sendMessage("§cFirst Seen:§f " + target.getFirstPlayed());
				p.sendMessage("§cTime Played:§f " + timesplayed);
				p.sendMessage("§cBlocks Placed:§f " + blocksplaced);
				p.sendMessage("§cBlocks Broken:§f " + blocksmined);
				p.sendMessage("§cSugarcane Farmed:§f " + sugarcanemined);
				p.sendMessage("§cPlayers Killed:§f " + playerskilled);
				p.sendMessage("§cMobs Killed:§f " + mobskilled);
				p.sendMessage("§cMoney Made:§f " + moneymade);
				p.sendMessage("§cFish Caught:§f " + fishcaught);
				p.sendMessage("§cGenbuckets Placed:§f " + genbuckets);
				p.sendMessage("§cLucky Drops Found:§f " + luckydrops);
			}else if (args.length == 0){
				int sugarcanemined = plugin.getConfig().getInt(p.getUniqueId() + ".SugarcaneMined");
				int blocksmined = plugin.getConfig().getInt(p.getUniqueId() + ".BlocksMined");
				int luckydrops = plugin.getConfig().getInt(p.getUniqueId() + ".LuckyDrops");
				p.sendMessage("§8§l§m-------§r§8§l[ §a§l Your Stats" + " §r§8§l]§8§l§m-------");
				p.sendMessage("§cFirst Seen:§f " + p.getFirstPlayed());
				p.sendMessage("§cTime Played:§f ");
				p.sendMessage("§cBlocks Placed:§f ");
				p.sendMessage("§cBlocks Broken:§f " + blocksmined);
				p.sendMessage("§cSugarcane Farmed:§f " + sugarcanemined);
				p.sendMessage("§cPlayers Killed:§f ");
				p.sendMessage("§cMobs Killed:§f ");
				p.sendMessage("§cMoney Made:§f ");
				p.sendMessage("§cFish Caught:§f ");
				p.sendMessage("§cGenbuckets Placed:§f ");
				p.sendMessage("§cLucky Drops Found:§f " + luckydrops);
			}
		}
		return false;
	}
}