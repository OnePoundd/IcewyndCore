package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.massivecraft.factions.entity.MPlayer;

import Main.Main;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

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
	//MCMMO Level obtain tracking
	@EventHandler
	public void onPlayerLevelUp(McMMOPlayerLevelUpEvent event) {
		Player player = event.getPlayer();
		int mcmmolevelsgained = plugin.getConfig().getInt(player.getUniqueId() + ".MCMMOLevelsGained");
		plugin.getConfig().set(player.getUniqueId() + ".MCMMOLevelsGained", mcmmolevelsgained + 1);
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
				int mcmmolevelsgained = plugin.getConfig().getInt(target.getUniqueId() + ".MCMMOLevelsGained");
				
				MPlayer mplayer = MPlayer.get(target);
				String faction = mplayer.getFactionName();
				TextComponent text = new TextComponent("§a§lHover here to see " + target.getName() + "'s stats");
				text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dUsername: §e" + target.getName() + "\n§dFaction:§e " + faction + "\n" + "\n§7             §a§lGeneral          \n      §a§l§m------------§a   \n§d§lTime Played:§e " + timeplayed + "\n§d§lBlocks Broken:§e " + blocksmined + "\n§d§lSugarcane Farmed:§e " + sugarcanemined + "\n§d§lBlocks Placed:§e " + blocksplaced + "\n§d§lFish Caught:§e " + fishcaught + "\n§d§lPlayers Killed:§e " + playerskilled + "\n§d§lMonsters Killed:§e " + mobskilled + "\n               §a§lOther          \n      §a§l§m------------§a" + "\n§d§lMoney Made:§e " + moneymade + "\n§d§lGenbuckets Placed:§e " + genbuckets + "\n§d§lLucky Drops Found:§e " + luckydrops + "\n§d§lSupply Drops Captured:§e " + supplydropscaptured + "\n§d§lCastle Captures:§e " + castlecaptures + "\n§d§lBooks Enchanted:§e " + booksenchanted + "\n§d§lChallenges Completed:§e " + challengescompleted + "\n§d§lMCMMO Levels Gained:§e " + mcmmolevelsgained).create()));
				p.sendMessage(text);
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
				int mcmmolevelsgained = plugin.getConfig().getInt(p.getUniqueId() + ".MCMMOLevelsGained");
				
				MPlayer mplayer = MPlayer.get(p);
				String faction = mplayer.getFactionName();
				TextComponent text = new TextComponent("§a§lHover here to see your stats!");
				text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dUsername: §e" + p.getName() + "\n§dFaction:§e " + faction + "\n" + "\n§7             §a§lGeneral          \n      §a§l§m------------§a   \n§d§lTime Played:§e " + timeplayed + "\n§d§lBlocks Broken:§e " + blocksmined + "\n§d§lSugarcane Farmed:§e " + sugarcanemined + "\n§d§lBlocks Placed:§e " + blocksplaced + "\n§d§lFish Caught:§e " + fishcaught + "\n§d§lPlayers Killed:§e " + playerskilled + "\n§d§lMonsters Killed:§e " + mobskilled + "\n               §a§lOther          \n      §a§l§m------------§a" + "\n§d§lMoney Made:§e " + moneymade + "\n§d§lGenbuckets Placed:§e " + genbuckets + "\n§d§lLucky Drops Found:§e " + luckydrops + "\n§d§lSupply Drops Captured:§e " + supplydropscaptured + "\n§d§lCastle Captures:§e " + castlecaptures + "\n§d§lBooks Enchanted:§e " + booksenchanted + "\n§d§lChallenges Completed:§e " + challengescompleted + "\n§d§lMCMMO Levels Gained:§e " + mcmmolevelsgained).create()));
				p.sendMessage(text);
			}
		}
		return false;
	}
}