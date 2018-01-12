package net.OnePound.Additions;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.OnePound.Crates.CrateEventListener;
import net.OnePound.Crates.EventCrate;
import net.OnePound.Crates.ExoticCrate;
import net.OnePound.Crates.LegendaryCrate;
import net.OnePound.CustomEnchants.Enchanter;
import net.OnePound.CustomEnchants.Librarian;
import net.OnePound.CustomEnchants.Enchantments;
import net.OnePound.WebConnection.Uploader;

public class Main extends JavaPlugin implements Listener{
	
	public static Main instance;
	//public static List<NPC> npcs;
	
	public void onEnable(){
		instance = this;
		PluginManager manager = Bukkit.getServer().getPluginManager();
		manager.registerEvents(this, this);
		manager.registerEvents(new CrateEventListener(), this);
		manager.registerEvents(new Enchantments(), this); //OTHER ENCHANTS
		manager.registerEvents(new Librarian(), this);
		manager.registerEvents(new Enchanter(), this);
		manager.registerEvents(new SilkSpawners(), this);
		manager.registerEvents(new Other(), this);
		
		manager.addPermission(new Permission("spawner.give"));
		manager.addPermission(new Permission("crate.give"));
		manager.addPermission(new Permission("npc.use"));
		
		ExoticCrate.load();
		LegendaryCrate.load();
		EventCrate.load();
		
		//npcs = new ArrayList<NPC>();
		
		//Uploader.triggerDatabaseAutoUpdate(); //Triggers the auto-updater for the factions web-database. Every 5 mins player and faction data will be updated.
	}

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("crategive")){
			if(!sender.hasPermission("crate.give")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if(args.length == 2){
				try{
					Player player = Bukkit.getPlayer(args[0]);
					if(args[1].equalsIgnoreCase("legendary")){
						LegendaryCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					}else if(args[1].equalsIgnoreCase("exotic")){
						ExoticCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					}else if(args[1].equalsIgnoreCase("event")){
						EventCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					}else{
						sender.sendMessage("§c§l(!)§7 Incorrect usage. Try /crate <playername> <legendary/exotic/event>");
					}
				}catch(Exception e){
					e.printStackTrace();
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			}else{
				sender.sendMessage("§c§l(!)§7 Usage: /crategive <playername> <legendary/exotic>");
			}
		}else if(label.equalsIgnoreCase("librarian")) {
			if(sender instanceof ConsoleCommandSender) {
				if(args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Librarian.openInventory(player);
					}catch(Exception e) {
						sender.sendMessage("§c§l(!)§7 That player cannot be found!");
					}
				}else {
					sender.sendMessage("§c§l(!)§7 Usage: /librarian <playername>!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
			}
		}else if(label.equalsIgnoreCase("enchanter")) {
			if(sender instanceof ConsoleCommandSender) {
				if(args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Enchanter.openInventory(player);
					}catch(Exception e) {
						sender.sendMessage("§c§l(!)§7 That player cannot be found!");
					}
				}else {
					sender.sendMessage("§c§l(!)§7 Usage: /enchanter <playername>!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
			}
		}else if(label.equalsIgnoreCase("spawnergive")) {
			if(!sender.hasPermission("spawner.give")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if(args.length == 2) {
				try{
					Player player = Bukkit.getPlayer(args[0]);
					System.out.println(player.getName());
					ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta spawnerMeta = spawner.getItemMeta();
					spawnerMeta.setDisplayName("§e" + args[1].toUpperCase().replaceAll("_"," ") + " §fSpawner");
					spawner.setItemMeta(spawnerMeta);
					if(player.getInventory().firstEmpty() == -1){
						player.sendMessage("§b§l(!)§7 Your inventory is full, dropping spawner at your feet!");
						player.getWorld().dropItem(player.getLocation(), spawner);
					}else{
						player.sendMessage("§b§l(!)§7 A spawner has been added to your inventory!");
						player.getInventory().addItem(spawner);
					}
					sender.sendMessage("§a§l(!)§7 Successfully added the spawner to the player's inventory!");				
				}catch(Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			}else {
				sender.sendMessage("§c§l(!)§7 Usage: /spawnergive <playername> <type>");
			}	
		}else if(label.equalsIgnoreCase("sandstone")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if(player.hasPermission("server.admin")) {
				SandstoneBiome.convert(player);
			}else {
				player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
			}
		}else if(label.equalsIgnoreCase("xp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			player.sendMessage("§b§l(!)§7 You have §8" + player.getExp() + " §7experience!");
		}else if(label.equalsIgnoreCase("xpgive")) {
			if(sender instanceof Player) {
				Player player = Bukkit.getPlayer(sender.getName());
				if(player.hasPermission("server.admin")) {
					if(args.length == 2) {
						try {
							Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
							player.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
						}catch(Exception e) {
							player.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
						}
					}else {
						player.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
					}
				}else {
					player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
				}
			}else {
				if(args.length == 2) {
					try {
						Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
						sender.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
					}catch(Exception e) {
						sender.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
					}
				}else {
					sender.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
				}
			}
		}
			
			
			
			
			
//		}else if(label.equalsIgnoreCase("npc")) {
//			Player player = Bukkit.getPlayer(sender.getName());
//			if(player.hasPermission("npc.use")) {
//				if(args.length == 0) {
//					player.sendMessage("§8§l§m---------------§7§l[ §dNPC Help§7§l ]§8§l§m-----------------");
//					sender.sendMessage("§b/npc create <name> §7- creates a new npc.");
//					sender.sendMessage("§b/npc delete <name> §7- deletes an existing npc.");
//					sender.sendMessage("§b/npc player <name> §7- makes an npc act as a player.");
//					sender.sendMessage("§b/npc tphere <name> §7- teleports an npc to you.");
//					sender.sendMessage("§b/npc tp <name> §7- teleports you to an npc.");
//					sender.sendMessage("§b/npc list §7- lists all existing npcs.");
//				}else {
//					if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
//						String message = "§b§l(!)§7 NPC Names: ";
//						for(NPC npc : npcs) {
//							message = message + npc.get().displayName + ", ";
//						}
//						if(!message.equals("§b§l(!)§7 NPC Names: ")) {
//							player.sendMessage(message.substring(0, message.length() - 2) + ".");
//						}else {
//							player.sendMessage("§c§l(!)§7 There are no npcs!");
//						}
//					}else if(args.length == 2) {
//						if(args[0].equalsIgnoreCase("create")) {
//							for(NPC npc : npcs) {
//								if(npc.get().getName().equals(args[1])) {
//									sender.sendMessage("§c§l(!)§7 There is already an NPC with that name!");
//									return true;
//								}
//							}							
//							NPC npc = new NPC(args[1], player);
//							Bukkit.getPluginManager().registerEvents(npc, this);
//							npcs.add(npc);
//							sender.sendMessage("§a§l(!)§7 An npc has been created at your location!");
//						}else if(args[0].equalsIgnoreCase("delete")) {
//							for(NPC npc : npcs) {
//								if(npc.get().getName().equals(args[1])) {
//									npc.delete();
//									npcs.remove(npc);
//									npc = null;
//									sender.sendMessage("§a§l(!)§7 Successfully deleted the npc!");
//									return true;
//								}
//							}
//							sender.sendMessage("§c§l(!)§7 There was no npc with that name!");
//						}else if(args[0].equals("tphere")) {
//							for(NPC npc : npcs) {
//								if(npc.get().getName().equals(args[1])) {
//									npc.get().teleportTo(player.getLocation(), false);
//									sender.sendMessage("§a§l(!)§7 Successfully teleported the npc!");
//									return true;
//								}
//							}
//							sender.sendMessage("§c§l(!)§7 There was no npc with that name!");
//						}else if(args[0].equals("tp")) {
//							for(NPC npc : npcs) {
//								if(npc.get().getName().equals(args[1])) {
//									player.teleport(npc.get().getBukkitEntity().getLocation());
//									sender.sendMessage("§a§l(!)§7 Successfully teleported to the npc!");
//									return true;
//								}
//							}
//							sender.sendMessage("§c§l(!)§7 There was no npc with that name!");
//						}
//					}else {
//						player.sendMessage("§8§l§m---------------§7§l[ §NPC Help§7§l ]§8§l§m-----------------");
//						sender.sendMessage("§b/npc create <name> §7- creates a new npc.");
//						sender.sendMessage("§b/npc delete <name> §7- deletes an existing npc.");
//						sender.sendMessage("§b/npc player <name> §7- makes an npc act as a player.");
//						sender.sendMessage("§b/npc tphere <name> §7- teleports an npc to you.");
//						sender.sendMessage("§b/npc tp <name> §7- teleports you to an npc.");
//						sender.sendMessage("§b/npc list §7- lists all existing npcs.");
//					}
//				}
//			}
		return true;
	}
}
