package Main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class JackpotEntity {
	private Main plugin = Main.getPlugin(Main.class);
	private int Entries = 0;
	private int Value = 0;
	private List<String> PlayerNames = new ArrayList<String>();
	private List<Integer> Amounts = new ArrayList<Integer>();
	
	public JackpotEntity() {
		try {
			PlayerNames = plugin.getConfig().getStringList("JackpotPlayers");
			Amounts = plugin.getConfig().getIntegerList("JackpotAmounts");
			Entries = plugin.getConfig().getInt("JackpotEntries");
			Value = plugin.getConfig().getInt("JackpotValue");
			BukkitScheduler sheduler = plugin.getServer().getScheduler();
			sheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
				@Override
				public void run() {
					Bukkit.broadcastMessage("§f[§bJackpot§f] §7("+ Entries + "/10) Current Value: §b$" + Value + "§7.");
				}
			}, 0L, 20*120);
		}catch(NullPointerException e) {} // thrown if jackpot has never ran before / been saved to config
	}
	
	public int getEntries() {
		return Entries;
	}
	
	public int getValue() {
		return Value;
	}
	
	public List<String> getPlayers(){
		return PlayerNames;
	}
	
	public void addEntry(Player player, int amount) {
		Entries = Entries + 1;
		Value = Value + amount;
		PlayerNames.add(player.getName());
		Amounts.add(amount);
		Bukkit.broadcastMessage("§f[§bJackpot§f] §7("+ Entries + "/10) Current Value: §b$" + Value + "§7.");
		if(Entries == 10) {
			finishJackpot();
		}
	}
	
	public void disable() {
		plugin.getConfig().set("JackpotPlayers", PlayerNames);
		plugin.getConfig().set("JackpotAmounts", Amounts);
		plugin.getConfig().set("JackpotEntries", Entries);
		plugin.getConfig().set("JackpotValue", Value);
		plugin.saveConfig();
	}
	
	private void finishJackpot() {
		
		List<Double> Chances = new ArrayList<Double>();	

		for(int amount : Amounts) {
			Chances.add(Double.valueOf(amount * 100.0f)/Value);
			System.out.println(Double.valueOf(amount * 100.0f)/Value);
		}
		int index = pickRandom(Chances);
		DecimalFormat df = new DecimalFormat("#.##");
		Bukkit.broadcastMessage("§f[§bJackpot§f] §b§l" + PlayerNames.get(index) + " §f§lwon §b§l$" + Value + " §f§lwith a §b§l" + df.format(Chances.get(index)) + "% §f§lchance!");		
				
		Entries = 0;
		Value = 0;
		PlayerNames = new ArrayList<String>();
		Amounts = new ArrayList<Integer>();
	}

	private static int pickRandom(List<Double> chances) {
		// Returns random index of list 
		Random r = new Random();
		Double randomPercentage = r.nextDouble() * 100;
		double sum = 0;
		int index = 0;
		for(Double d : chances) {
			sum += d;
			if(sum > randomPercentage) {
				return index;
			}
			index += 1;
		}
		return index;
	}
}
