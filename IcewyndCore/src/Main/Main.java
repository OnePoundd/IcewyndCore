package Main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import BanSystem.BanCommand;
import Commands.Book;
import Commands.ClearChat;
import Commands.ClearInventory;
import Commands.Disposal;
import Commands.Enderchest;
import Commands.Freecam;
import Commands.Invsee;
import Commands.Kits;
import Commands.Message;
import Commands.MsgToggle;
import Commands.NightVision;
import Commands.Overwatch;
import Commands.PInfo;
import Commands.PatchNotes;
import Commands.Ping;
import Commands.QuarterMaster;
import Commands.Rules;
import Commands.Stats;
import Commands.TwitchBroadcast;
import Commands.YoutubeBroadcast;
import Crates.CrateEventListener;
import Crates.EventCrate;
import Crates.ExoticCrate;
import Crates.LegendaryCrate;
import CustomEnchants.Enchanter;
import CustomEnchants.Enchantments;
import CustomEnchants.Librarian;
import McMMO.Milestones;
import McMMO.Repair;

public class Main extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	public Main plugin;
	
	public static Main getPlugin() {
		return Main.getPlugin(Main.class);
	}

	public static Plugin instance;
	public static ProtocolManager protocolManager;
	
	// public static List<NPC> npcs;

	public void onEnable() {
		saveDefaultConfig();
		instance = this;
		PluginManager manager = Bukkit.getServer().getPluginManager();
		Main.protocolManager = ProtocolLibrary.getProtocolManager();
		manager.registerEvents(this, this);
		manager.registerEvents(new CrateEventListener(), this);
		manager.registerEvents(new Enchantments(), this); // OTHER ENCHANTS
		manager.registerEvents(new Librarian(), this);
		manager.registerEvents(new Enchanter(), this);
		manager.registerEvents(new SilkSpawners(), this);
		manager.registerEvents(new CreeperCountdown(), this);
		manager.registerEvents(new Cancels(), this);
		manager.registerEvents(new ChatFormat(), this);
		manager.registerEvents(new CustomMobDrops(), this);
		manager.registerEvents(new FastPots(), this);
		manager.registerEvents(new Misc(), this);
		manager.registerEvents(new InventoryClick(), this);
		manager.registerEvents(new RightClickEvent(), this);
		manager.registerEvents(new LuckyDrops(), this);
		manager.registerEvents(new SellWands(), this);
		manager.registerEvents(new Repair(), this);
		manager.registerEvents(new Milestones(), this);
		manager.registerEvents(new Kits(), this);
		manager.registerEvents(new Freecam(), this);
		
		getCommand("rules").setExecutor(new Rules());
		getCommand("q").setExecutor(new QuarterMaster());
		getCommand("ping").setExecutor(new Ping());
		getCommand("pinfo").setExecutor(new PInfo());
		getCommand("patchnotes").setExecutor(new PatchNotes());
		getCommand("ow").setExecutor(new Overwatch());
		getCommand("msg").setExecutor(new Message());
		getCommand("ci").setExecutor(new ClearInventory());
		getCommand("clearchat").setExecutor(new ClearChat());
		getCommand("msgtoggle").setExecutor(new MsgToggle());
		getCommand("nightvision").setExecutor(new NightVision());
		getCommand("invsee").setExecutor(new Invsee());
		getCommand("enderchest").setExecutor(new Enderchest());
		getCommand("book").setExecutor(new Book());
		getCommand("ban").setExecutor(new BanCommand());
		getCommand("disposal").setExecutor(new Disposal());
		getCommand("youtube").setExecutor(new YoutubeBroadcast());
		getCommand("twitch").setExecutor(new TwitchBroadcast());
		getCommand("fstats").setExecutor(new Stats());
		getCommand("freecam").setExecutor(new Freecam());
		getCommand("kit").setExecutor(new Kits());

		//manager.addPermission(new Permission("spawner.give"));
		//manager.addPermission(new Permission("crate.give"));
		//manager.addPermission(new Permission("npc.use"));

		ExoticCrate.load();
		LegendaryCrate.load();
		EventCrate.load();

		// npcs = new ArrayList<NPC>();

		// Uploader.triggerDatabaseAutoUpdate(); //Triggers the auto-updater for the
		// factions web-database. Every 5 mins player and faction data will be updated.
	
	BukkitScheduler scheduler = getServer().getScheduler();
	scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
		@Override
		public void run() {
			Random rand = new Random();
			int index = rand.nextInt(19) + 1;
			if (index == 1) {
				Bukkit.broadcastMessage("§a§lINFO §7» Join our community Discord to chat with others and get suppport by clicking this message");
			} else if (index == 2) {
				Bukkit.broadcastMessage("§a§lINFO §7» There are many ways to gamble but Jackpot is the most fun! Participate using §a/jackpot§7.");
			} else if (index == 3) {
				Bukkit.broadcastMessage("§a§lINFO §7» Need a faction? Type §a/f list§7 to see if any factions are recruiting.");
			} else if (index == 4) {
				Bukkit.broadcastMessage("§a§lINFO §7» Need support? Send in a support ticket on the forums or join Discord for help.");
			} else if (index == 5) {
				Bukkit.broadcastMessage("§a§lINFO §7» Upgrade your faction using §a/f upgrade§7 to gain perks and progress faster!");
			} else if (index == 6) {
				Bukkit.broadcastMessage("§a§lINFO §7» Secure your account using §a/auth§7 and prevent hackers from accessing your account.");
			} else if (index == 7) {
				Bukkit.broadcastMessage("§a§lINFO §7» Fishing is fun and fast! mcMMO is doubled in SafeZone and you may catch something valuable!");
			} else if (index == 8) {
				Bukkit.broadcastMessage("§a§lINFO §7» Practice and war other factions and players by visiting the duel NPCs at spawn.");
			} else if (index == 9) {
				Bukkit.broadcastMessage("§a§lINFO §7» Complete achievements to gain rewards and show off using §a/achievements§7.");
			} else if (index == 10) {
				Bukkit.broadcastMessage("§a§lINFO §7» Quickly mine out your base using Shockwave pickaxes & Mining Drones!");
			} else if (index == 11) {
				Bukkit.broadcastMessage("§a§lINFO §7» Spend coins at the Quartermaster in Safezone and buy kits, disguises, ranks and other goodies!");
			} else if (index == 12) {
				Bukkit.broadcastMessage("§a§lINFO §7» Found a bug? Submit it on our forums for a potential reward.");
			} else if (index == 13) {
				Bukkit.broadcastMessage("§a§lINFO §7» Summon bosses using Boss Eggs, battle them and be rewarded!");
			} else if (index == 14) {
				Bukkit.broadcastMessage("§a§lINFO §7» Protect your faction from betrayal using faction regions and trusted permissions.");
			} else if (index == 15) {
				Bukkit.broadcastMessage("§a§lINFO §7» Prestige your mcMMO once reaching 1000 in all skills. Use &a/prestige&7 for more info.");
			} else if (index == 16) {
				Bukkit.broadcastMessage("§a§lINFO §7» Capture Castle for increased spawner rates, better sell prices and other rewards!");
			} else if (index == 17) {
				Bukkit.broadcastMessage("§a§lINFO §7» Looking to test your cannons or need a place to build? Checkout our cannon server in the hub.");
			} else if (index == 18) {
				Bukkit.broadcastMessage("§a§lINFO §7» Turn gunpowder into TNT using furnaces! Speed up this process using §a/f upgrade§7.");
			} else if (index == 19) {
				Bukkit.broadcastMessage("§a§lINFO §7» Fill cannons quickly and store virtual TNT using §a/f tnt§7.");
			}
		}
	}, 0L, 2000L);
}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("crategive")) {
			if (!sender.hasPermission("crate.give")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					if (args[1].equalsIgnoreCase("legendary")) {
						LegendaryCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("exotic")) {
						ExoticCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("event")) {
						EventCrate.give(player);
						sender.sendMessage("§b§l(!)§7 A crate has been added to the players inventory!");
					} else {
						sender.sendMessage(
								"§c§l(!)§7 Incorrect usage. Try /crate <playername> <legendary/exotic/event>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 Usage: /crategive <playername> <legendary/exotic>");
			}
		} else if (label.equalsIgnoreCase("librarian")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Librarian.openInventory(player);
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 That player cannot be found!");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /librarian <playername>!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
			}
		} else if (label.equalsIgnoreCase("enchanter")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Enchanter.openInventory(player);
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 That player cannot be found!");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /enchanter <playername>!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
			}
		} else if (label.equalsIgnoreCase("spawnergive")) {
			if (!sender.hasPermission("spawner.give")) {
				sender.sendMessage("§c§l(!)§7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					System.out.println(player.getName());
					ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta spawnerMeta = spawner.getItemMeta();
					spawnerMeta.setDisplayName("§e" + args[1].toUpperCase().replaceAll("_", " ") + " §fSpawner");
					spawner.setItemMeta(spawnerMeta);
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("§b§l(!)§7 Your inventory is full, dropping spawner at your feet!");
						player.getWorld().dropItem(player.getLocation(), spawner);
					} else {
						player.sendMessage("§b§l(!)§7 A spawner has been added to your inventory!");
						player.getInventory().addItem(spawner);
					}
					sender.sendMessage("§a§l(!)§7 Successfully added the spawner to the player's inventory!");
				} catch (Exception e) {
					sender.sendMessage("§c§l(!)§7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("§c§l(!)§7 Usage: /spawnergive <playername> <type>");
			}
		} else if (label.equalsIgnoreCase("sandstone")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (player.hasPermission("server.admin")) {
				SandstoneBiome.convert(player);
			} else {
				player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
			}
		} else if (label.equalsIgnoreCase("xp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			player.sendMessage("§b§l(!)§7 You have §8" + player.getExp() + " §7experience!");
		} else if (label.equalsIgnoreCase("xpgive")) {
			if (sender instanceof Player) {
				Player player = Bukkit.getPlayer(sender.getName());
				if (player.hasPermission("server.admin")) {
					if (args.length == 2) {
						try {
							Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
							player.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
						} catch (Exception e) {
							player.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
						}
					} else {
						player.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
					}
				} else {
					player.sendMessage("§c§l(!)§7 You don't have permission to use that command!");
				}
			} else {
				if (args.length == 2) {
					try {
						Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
						sender.sendMessage("§a§l(!)§7 Successfully added the xp to the specified player.");
					} catch (Exception e) {
						sender.sendMessage("§c§l(!)§7 Player offline or incorrect amount.");
					}
				} else {
					sender.sendMessage("§c§l(!)§7 Usage: /xpgive <player> <amount>");
				}
			}
		}

		// }else if(label.equalsIgnoreCase("npc")) {
		// Player player = Bukkit.getPlayer(sender.getName());
		// if(player.hasPermission("npc.use")) {
		// if(args.length == 0) {
		// player.sendMessage("§8§l§m---------------§7§l[ §dNPC Help§7§l
		// ]§8§l§m-----------------");
		// sender.sendMessage("§b/npc create <name> §7- creates a new npc.");
		// sender.sendMessage("§b/npc delete <name> §7- deletes an existing npc.");
		// sender.sendMessage("§b/npc player <name> §7- makes an npc act as a player.");
		// sender.sendMessage("§b/npc tphere <name> §7- teleports an npc to you.");
		// sender.sendMessage("§b/npc tp <name> §7- teleports you to an npc.");
		// sender.sendMessage("§b/npc list §7- lists all existing npcs.");
		// }else {
		// if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
		// String message = "§b§l(!)§7 NPC Names: ";
		// for(NPC npc : npcs) {
		// message = message + npc.get().displayName + ", ";
		// }
		// if(!message.equals("§b§l(!)§7 NPC Names: ")) {
		// player.sendMessage(message.substring(0, message.length() - 2) + ".");
		// }else {
		// player.sendMessage("§c§l(!)§7 There are no npcs!");
		// }
		// }else if(args.length == 2) {
		// if(args[0].equalsIgnoreCase("create")) {
		// for(NPC npc : npcs) {
		// if(npc.get().getName().equals(args[1])) {
		// sender.sendMessage("§c§l(!)§7 There is already an NPC with that name!");
		// return true;
		// }
		// }
		// NPC npc = new NPC(args[1], player);
		// Bukkit.getPluginManager().registerEvents(npc, this);
		// npcs.add(npc);
		// sender.sendMessage("§a§l(!)§7 An npc has been created at your location!");
		// }else if(args[0].equalsIgnoreCase("delete")) {
		// for(NPC npc : npcs) {
		// if(npc.get().getName().equals(args[1])) {
		// npc.delete();
		// npcs.remove(npc);
		// npc = null;
		// sender.sendMessage("§a§l(!)§7 Successfully deleted the npc!");
		// return true;
		// }
		// }
		// sender.sendMessage("§c§l(!)§7 There was no npc with that name!");
		// }else if(args[0].equals("tphere")) {
		// for(NPC npc : npcs) {
		// if(npc.get().getName().equals(args[1])) {
		// npc.get().teleportTo(player.getLocation(), false);
		// sender.sendMessage("§a§l(!)§7 Successfully teleported the npc!");
		// return true;
		// }
		// }
		// sender.sendMessage("§c§l(!)§7 There was no npc with that name!");
		// }else if(args[0].equals("tp")) {
		// for(NPC npc : npcs) {
		// if(npc.get().getName().equals(args[1])) {
		// player.teleport(npc.get().getBukkitEntity().getLocation());
		// sender.sendMessage("§a§l(!)§7 Successfully teleported to the npc!");
		// return true;
		// }
		// }
		// sender.sendMessage("§c§l(!)§7 There was no npc with that name!");
		// }
		// }else {
		// player.sendMessage("§8§l§m---------------§7§l[ §NPC Help§7§l
		// ]§8§l§m-----------------");
		// sender.sendMessage("§b/npc create <name> §7- creates a new npc.");
		// sender.sendMessage("§b/npc delete <name> §7- deletes an existing npc.");
		// sender.sendMessage("§b/npc player <name> §7- makes an npc act as a player.");
		// sender.sendMessage("§b/npc tphere <name> §7- teleports an npc to you.");
		// sender.sendMessage("§b/npc tp <name> §7- teleports you to an npc.");
		// sender.sendMessage("§b/npc list §7- lists all existing npcs.");
		// }
		// }
		// }
		return true;
	}
}
