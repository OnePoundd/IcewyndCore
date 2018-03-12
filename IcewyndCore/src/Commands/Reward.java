package Commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import Main.Main;

public class Reward implements CommandExecutor, Listener{

	Main plugin = Main.getPlugin(Main.class);
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			if (cmd.getName().equalsIgnoreCase("reward")) {
				if(args.length >= 2) {
					String playerName = args[0];
					String reward = args[1];
					OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
					if(player.isOnline()) {
						if(reward.equals("discordlink")) {
							player.getPlayer().sendMessage("§6§l(!)§7 You have been rewarded $10,000 for linking to discord!");
							plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ecoadmin give " + playerName + " 10000");
						}else if(reward.equals("refer")) {
							String referred = args[2];
							player.getPlayer().sendMessage("§6§l(!)§7 You have been rewarded $15,000 for referring " + referred + "!");
							plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ecoadmin give " + playerName + " 15000");
							OfflinePlayer referredPlayer = Bukkit.getOfflinePlayer(referred);
							plugin.getConfig().set(referredPlayer.getUniqueId() + ".Referee", player.getUniqueId());
						}else if(reward.equals("level3")) {
							if(plugin.getConfig().getString(player.getUniqueId() + ".Referee") != null) {
								OfflinePlayer referee = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".Referee"));
								if(referee.isOnline()) {
									referee.getPlayer().sendMessage("§6§l(!)§7 You have been rewarded $20,000 because " + player.getName() + " reached level 3!");
									plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ecoadmin give " + referee.getName() + " 20000");
								}else {
									try {
										File newFile = Main.CommandStore;
										Writer output = new BufferedWriter(new FileWriter(newFile, true));
										String command = cmd.getName();
										for(String s : args) {
											command = command + " " + s;
										}							
										output.append(command + ":");
										output.close();
										System.out.println("[IcewyndHub] Failed to reward player (not online) so added a command to the store!");
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}else if(reward.equals("level5")) {
							if(plugin.getConfig().getString(player.getUniqueId() + ".Referee") != null) {
								OfflinePlayer referee = Bukkit.getOfflinePlayer(plugin.getConfig().getString(player.getUniqueId() + ".Referee"));
								if(referee.isOnline()) {
									referee.getPlayer().sendMessage("§6§l(!)§7 You have been rewarded a referral crate because " + player.getName() + " reached level 5!");
									plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "crategive " + referee.getName() + " referral");
								}else {
									try {
										File newFile = Main.CommandStore;
										Writer output = new BufferedWriter(new FileWriter(newFile, true));
										String command = cmd.getName();
										for(String s : args) {
											command = command + " " + s;
										}							
										output.append(command + ":");
										output.close();
										System.out.println("[IcewyndHub] Failed to reward player (not online) so added a command to the store!");
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}else {
						try {
							File newFile = Main.CommandStore;
							Writer output = new BufferedWriter(new FileWriter(newFile, true));
							String command = cmd.getName();
							for(String s : args) {
								command = command + " " + s;
							}							
							output.append(command + ":");
							output.close();
							System.out.println("[IcewyndHub] Failed to reward player (not online) so added a command to the store!");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else {
					System.out.println("[IcewyndCore] Error: Incorrect reward command usage.");
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	    try {
		    String content = new String (Files.readAllBytes( Paths.get(Main.CommandStore.getPath())));
		    if(content != null) {
		    	ArrayList<String> commandsToRun = new ArrayList<String>(Arrays.asList(content.split(":")));
		    	Iterator<String> iter = commandsToRun.iterator();
		    	while (iter.hasNext()) {
		    	    String str = iter.next();
		    	    if (str.contains(event.getPlayer().getName())) {
		    	    	plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), str);
		    	        iter.remove();
		    	    }
		    	}
			    String newContent = "";
			    for(String s : commandsToRun) {
			    	newContent = newContent + s + ":";
			    }			    
				File newFile = Main.CommandStore;
				Writer output = new BufferedWriter(new FileWriter(newFile));
				output.write(newContent);
				output.close();
		    }
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
