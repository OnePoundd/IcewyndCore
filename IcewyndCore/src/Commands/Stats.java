package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import Main.Main;

public class Stats implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	//Genbucket Tracking
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§c§lGen Bucket")) {
			event.getPlayer().sendMessage("hi");
			
	}
}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fstats")) {
			Player p = (Player) sender;
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				int timeplayed = plugin.getConfig().getInt(target.getUniqueId() + ".TimePlayed");
				int blocksplaced = plugin.getConfig().getInt(target.getUniqueId() + ".BlocksPlaced");
				int playerskilled = plugin.getConfig().getInt(target.getUniqueId() + ".PlayersKilled");
				int mobskilled = plugin.getConfig().getInt(target.getUniqueId() + ".MobsKilled");
				int moneymade = plugin.getConfig().getInt(target.getUniqueId() + ".MoneyMade");
				int fishcaught = plugin.getConfig().getInt(target.getUniqueId() + ".FishCaught");
				int genbuckets = plugin.getConfig().getInt(target.getUniqueId() + ".GenBuckets");
				int sugarcanemined = plugin.getConfig().getInt(target.getUniqueId() + ".SugarcaneMined");
				int blocksmined = plugin.getConfig().getInt(target.getUniqueId() + ".BlocksMined");
				int luckydrops = plugin.getConfig().getInt(target.getUniqueId() + ".LuckyDrops");
				int supplydropscaptured = plugin.getConfig().getInt(target.getUniqueId() + ".SupplyDropsCaptured");
				int castlecaptures = plugin.getConfig().getInt(target.getUniqueId() + ".CastleCaptures");
				int booksenchanted = plugin.getConfig().getInt(target.getUniqueId() + ".BooksEnchanted");
				int challengescompleted = plugin.getConfig().getInt(target.getUniqueId() + ".ChallengesCompleted");
				int skillsobtained = plugin.getConfig().getInt(target.getUniqueId() + ".SkillsBbtained");
				int mcmmolevelsgained = plugin.getConfig().getInt(target.getUniqueId() + ".MCMMOLevelsGained");
				//Blocks mined is tracked in Sponge Patch, Misc class
				//Blocks placed is tracked in Silkspawners
				
				p.sendMessage("§8§l§m-------§r§8§l[ §a§l" + args[0] + "'s" + " Stats" + " §r§8§l]§8§l§m-------");
				p.sendMessage("§cFirst Seen:§f " + target.getFirstPlayed());
				p.sendMessage("§cTime Played:§f " + timeplayed);
				p.sendMessage("§cBlocks Placed:§f " + blocksplaced);
				p.sendMessage("§cBlocks Broken:§f " + blocksmined);
				p.sendMessage("§cSugarcane Farmed:§f " + sugarcanemined);
				p.sendMessage("§cPlayers Killed:§f " + playerskilled);
				p.sendMessage("§cMobs Killed:§f " + mobskilled);
				p.sendMessage("§cMoney Made:§f " + moneymade);
				p.sendMessage("§cMoney Spent:§f " + luckydrops);
				p.sendMessage("§cFish Caught:§f " + fishcaught);
				p.sendMessage("§cGenbuckets Placed:§f " + genbuckets);
				p.sendMessage("§cLucky Drops Found:§f " + luckydrops);
				p.sendMessage("§cSupply Drops Captured:§f " + supplydropscaptured);
				p.sendMessage("§cCastle Captures:§f " + castlecaptures);
				p.sendMessage("§cBooks Enchanted:§f " + booksenchanted);
				p.sendMessage("§cChallenges Completed:§f " + challengescompleted);
				p.sendMessage("§cSkills Obtained:§f " + skillsobtained);
				p.sendMessage("§cMCMMO Levels Gained:§f " + mcmmolevelsgained);
			}else if (args.length == 0){
				int timeplayed = plugin.getConfig().getInt(p.getUniqueId() + ".TimePlayed");
				int blocksplaced = plugin.getConfig().getInt(p.getUniqueId() + ".BlocksPlaced");
				int playerskilled = plugin.getConfig().getInt(p.getUniqueId() + ".PlayersKilled");
				int mobskilled = plugin.getConfig().getInt(p.getUniqueId() + ".MobsKilled");
				int moneymade = plugin.getConfig().getInt(p.getUniqueId() + ".MoneyMade");
				int fishcaught = plugin.getConfig().getInt(p.getUniqueId() + ".FishCaught");
				int genbuckets = plugin.getConfig().getInt(p.getUniqueId() + ".GenBuckets");
				int sugarcanemined = plugin.getConfig().getInt(p.getUniqueId() + ".SugarcaneMined");
				int blocksmined = plugin.getConfig().getInt(p.getUniqueId() + ".BlocksMined");
				int luckydrops = plugin.getConfig().getInt(p.getUniqueId() + ".LuckyDrops");
				int supplydropscaptured = plugin.getConfig().getInt(p.getUniqueId() + ".SupplyDropsCaptured");
				int castlecaptures = plugin.getConfig().getInt(p.getUniqueId() + ".CastleCaptures");
				int booksenchanted = plugin.getConfig().getInt(p.getUniqueId() + ".BooksEnchanted");
				int challengescompleted = plugin.getConfig().getInt(p.getUniqueId() + ".ChallengesCompleted");
				int skillsobtained = plugin.getConfig().getInt(p.getUniqueId() + ".SkillsObtained");
				int mcmmolevelsgained = plugin.getConfig().getInt(p.getUniqueId() + ".MCMMOLevelsGained");
				
				p.sendMessage("§8§l§m-------§r§8§l[ §a§l Your Stats" + " §r§8§l]§8§l§m-------");
				p.sendMessage("§cFirst Seen:§f " + p.getFirstPlayed());
				p.sendMessage("§cTime Played:§f " + timeplayed);
				p.sendMessage("§cBlocks Placed:§f " + blocksplaced);
				p.sendMessage("§cBlocks Broken:§f " + blocksmined);
				p.sendMessage("§cSugarcane Farmed:§f " + sugarcanemined);
				p.sendMessage("§cPlayers Killed:§f " + playerskilled);
				p.sendMessage("§cMobs Killed:§f " + mobskilled);
				p.sendMessage("§cMoney Made:§f " + moneymade);
				p.sendMessage("§cMoney Spent:§f " + luckydrops);
				p.sendMessage("§cFish Caught:§f " + fishcaught);
				p.sendMessage("§cGenbuckets Placed:§f " + genbuckets);
				p.sendMessage("§cLucky Drops Found:§f " + luckydrops);
				p.sendMessage("§cSupply Drops Captured:§f " + supplydropscaptured);
				p.sendMessage("§cCastle Captures:§f " + castlecaptures);
				p.sendMessage("§cBooks Enchanted:§f " + booksenchanted);
				p.sendMessage("§cChallenges Completed:§f " + challengescompleted);
				p.sendMessage("§cSkills Obtained:§f " + skillsobtained);
				p.sendMessage("§cMCMMO Levels Gained:§f " + mcmmolevelsgained);
			}
		}
		return false;
	}
}