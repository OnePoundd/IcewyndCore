package WebConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;

import Main.Main;

public class Uploader {

	/**
	 * Every 5 Minutes, new data will be uploaded to the database.
	 * This data will include the updated information for factions and players.
	 */
	
	static Main plugin = Main.getPlugin(Main.class);
	
	public static void triggerDatabaseAutoUpdate() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
            	System.out.println("[Additions] Starting the database update procedure.");
                DatabaseManager manager = new DatabaseManager();
                for(Faction faction : FactionColl.get().getAll()) {
                    ResultSet result = manager.getResultFromQuery("SELECT * FROM factions WHERE Name = '" + faction.getName() + "'");
                    
					int recruiting = 0;
					if(faction.getRecruiting()) {recruiting = 1;}
					String leader = ""; String officers = null; String members = null; String recruits = null;
					for(MPlayer player : faction.getMPlayers()) {
						if(player.getRole().equals(Rel.LEADER)) {
							leader = player.getName();
						}else if(player.getRole() == Rel.OFFICER) {
							officers = officers + player.getName() + ":";
						}else if(player.getRole().equals(Rel.MEMBER)) {
							members = members + player.getName() + ":";
						}else {
							recruits = recruits + player.getName() + ":";
						}
					}
					if(officers != null) {
						officers.substring(0, officers.length() - 2);
					}
					if(members != null) {
						members.substring(0, officers.length() - 2);
					}
					if(recruits != null) {
						recruits.substring(0, officers.length() - 2);
					}
                    
                    try {
						if(result.next()) {
							String query = "UPDATE factions SET Name = '" + faction.getName() + 
									"', Position = '" + faction.getPower() + 
									"', Points = '" + faction.getVictoryPoints() + 
									"', Wealth = '" + faction.getWealth() + 
									"', Recruiting = '" + recruiting + 
									"', PlayerCount = '" + faction.getMPlayers().size() +
									"', Leader = '" + leader + 
									"', Officers = '" + officers + 
									"', Members = '" + members +
									"', Recruits = '" + recruits +
									"' WHERE Name = '" + faction.getName() + "'";
							manager.sendQuery(query);
						}else {
							String query = "INSERT INTO factions (Name, Position, Points, Wealth, Recruiting, PlayerCount, Leader, Officers, Members, Recruits) VALUES ('" + 
									faction.getName() +
									"', '" + faction.getPower() + 
									"', '" + faction.getVictoryPoints() + 
									"', '" + faction.getWealth() + 
									"', '" + recruiting + 
									"', '" + faction.getMPlayers().size() +
									"', '" + leader + 
									"', '" + officers + 
									"', '" + members +
									"', '" + recruits + "');";
							manager.sendQuery(query);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
                }
                System.out.println("[Additions] Successfully completed the database update procedure.");
            }
        }, 0L, (20*60*5L)); //20*60*5 for 5 mins
	}
	
}
