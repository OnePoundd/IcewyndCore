package Main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.factions.entity.MPlayer;

import BanSystem.BanCommand;
import BanSystem.UnbanCommand;
import Commands.Book;
import Commands.ClearChat;
import Commands.ClearInventory;
import Commands.Disposal;
import Commands.Enderchest;
import Commands.Event;
import Commands.Feed;
import Commands.Freecam;
import Commands.Help;
import Commands.Invsee;
import Commands.Kits;
import Commands.List;
import Commands.Message;
import Commands.MsgToggle;
import Commands.NightVision;
import Commands.Overwatch;
import Commands.PInfo;
import Commands.PatchNotes;
import Commands.Ping;
import Commands.QuarterMaster;
import Commands.Reset;
import Commands.Rules;
import Commands.Seen;
import Commands.Sell;
import Commands.Shop;
import Commands.Stats;
import Commands.Suicide;
import Commands.TwitchBroadcast;
import Commands.WitherSpawn;
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
import eu.haelexuis.utils.xoreboard.XoreBoard;
import eu.haelexuis.utils.xoreboard.XoreBoardGlobalSidebar;
import eu.haelexuis.utils.xoreboard.XoreBoardUtil;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener {
	public static ProtocolManager protocolManager;
	public static FileConfiguration pricesConfig;
	public static Economy econ;


	public void onEnable() {
		saveDefaultConfig();
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
		manager.registerEvents(new Sell(), this);
		manager.registerEvents(new Shop(), this);
		manager.registerEvents(new DisguiseBuffs(), this);
		manager.registerEvents(new BossEggs(), this);
		manager.registerEvents(new Seen(), this);
		manager.registerEvents(new WitherSpawn(), this);


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
		getCommand("unban").setExecutor(new UnbanCommand());
		getCommand("disposal").setExecutor(new Disposal());
		getCommand("youtube").setExecutor(new YoutubeBroadcast());
		getCommand("twitch").setExecutor(new TwitchBroadcast());
		getCommand("fstats").setExecutor(new Stats());
		getCommand("freecam").setExecutor(new Freecam());
		getCommand("kit").setExecutor(new Kits());
		getCommand("event").setExecutor(new Event());
		getCommand("sell").setExecutor(new Sell());
		getCommand("shop").setExecutor(new Shop());
		getCommand("feed").setExecutor(new Feed());
		getCommand("help").setExecutor(new Help());
		getCommand("list").setExecutor(new List());
		getCommand("suicide").setExecutor(new Suicide());
		getCommand("seen").setExecutor(new Seen());
		getCommand("witherspawn").setExecutor(new WitherSpawn());
		getCommand("reset").setExecutor(new Reset());

		ExoticCrate.load();
		LegendaryCrate.load();
		EventCrate.load();

		//Uploader.triggerDatabaseAutoUpdate(); //Triggers the auto-updater for the factions web-database. Every 5 mins player and faction data will be updated.

		BukkitScheduler Broadcasts = getServer().getScheduler();
		Broadcasts.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Random rand = new Random();
				int index = rand.nextInt(19) + 1;
				if (index == 1) {
					Bukkit.broadcastMessage("�b�lINFO �7� Join our community Discord to chat with others and get suppport by clicking this message");
				} else if (index == 2) {
					Bukkit.broadcastMessage("�b�lINFO �7� There are many ways to gamble but Jackpot is the most fun! Participate using �b/jackpot�7.");
				} else if (index == 3) {
					Bukkit.broadcastMessage("�b�lINFO �7� Need a faction? Type �b/f find�7 to see if any factions are recruiting.");
				} else if (index == 4) {
					Bukkit.broadcastMessage("�b�lINFO �7� Need support? Send in a support ticket on the forums or join Discord for help.");
				} else if (index == 5) {
					Bukkit.broadcastMessage("�b�lINFO �7� Upgrade your faction using �b/f upgrade�7 to gain perks and progress faster!");
				} else if (index == 6) {
					Bukkit.broadcastMessage("�b�lINFO �7� Secure your account using �b/auth�7 and prevent hackers from accessing your account.");
				} else if (index == 7) {
					Bukkit.broadcastMessage("�b�lINFO �7� Fishing is fun and fast! McMMO is doubled in SafeZone and you may catch something valuable!");
				} else if (index == 8) {
					Bukkit.broadcastMessage("�b�lINFO �7� Find more players for your faction by marking your faction as recruiting with the command �b/f recruiting�7!");
				} else if (index == 9) {
					Bukkit.broadcastMessage("�b�lINFO �7� Complete achievements to gain rewards and show off using �b/achievements�7.");
				} else if (index == 10) {
					Bukkit.broadcastMessage("�b�lINFO �7� Quickly mine out areas for your base with the shockwave enchantment aquired from the Enchanter NPC or the store!");
				} else if (index == 11) {
					Bukkit.broadcastMessage("�b�lINFO �7� Spend coins on a large variety of prizes by visiting the Quartermaster found in Safezone! ");
				} else if (index == 12) {
					Bukkit.broadcastMessage("�b�lINFO �7� Found a bug? Submit it on our forums for a potential reward.");
				} else if (index == 13) {
					Bukkit.broadcastMessage("�b�lINFO �7� Boss eggs are dropped from killing mobs, summon them in Warzone for a battle & prize!");
				} else if (index == 14) {
					Bukkit.broadcastMessage("�b�lINFO �7� Protect your faction from betrayal by using faction regions and trusted permissions.");
				} else if (index == 15) {
					Bukkit.broadcastMessage("�b�lINFO �7� Reach the top of the mcmmo leaderboard in any skill for a unique prefix in chat and a unique kit every day!");
				} else if (index == 16) {
					Bukkit.broadcastMessage("�b�lINFO �7� Capture Castle for victory points, double spawner rates, an extra 10% on /sell, and cash every 10 minutes!");
				} else if (index == 17) {
					Bukkit.broadcastMessage("�b�lINFO �7� Test out your cannons and build freely using our cannon server! Connect by going to the hub.");
				} else if (index == 18) {
					Bukkit.broadcastMessage("�b�lINFO �7� Gunpowder can be converted to TNT using furnaces! Increase the speed using �b/f upgrade�7.");
				} else if (index == 19) {
					Bukkit.broadcastMessage("�b�lINFO �7� Fill cannons quickly and store TNT using �b/f tnt�7.");
				} else if (index == 20) {
					Bukkit.broadcastMessage("�b�lINFO �7� Want to sell items easier? Sell wands sell entire chests at a time. Obtain them from the Quartermaster.");
				} else if (index == 21) {
					Bukkit.broadcastMessage("�b�lINFO �7� Support the server by purchasing items on the Store. Visit at �bIcewynd.net/store�7.");
				} else if (index == 22) {
					Bukkit.broadcastMessage("�b�lINFO �7� Gain rewards from completing daily bounties, you can find these at Xavier in Safezone.");
				} else if (index == 23) {
					Bukkit.broadcastMessage("�b�lINFO �7� Find our more information about the server including all commands by using �b/info�7!");
				} else if (index == 24) {
					Bukkit.broadcastMessage("�b�lINFO �7� Check your ping by using �b/ping�b7. This command also works on other players.");
				} else if (index == 25) {
					Bukkit.broadcastMessage("�b�lINFO �7� Access your enderchest by using �b/echest�7. This only works for ranked players.");
				} else if (index == 26) {
					Bukkit.broadcastMessage("�b�lINFO �7� Check yours and other players statistics by using �b/statistics�7.");
				} else if (index == 27) {
					Bukkit.broadcastMessage("�b�lINFO �7� Get assistance from your faction members in PVP by using Faction Banners!");
				} else if (index == 28) {
					Bukkit.broadcastMessage("�b�lINFO �7� Do you stream on our server? Add your stream to our streamers tab on our website �bIcewynd.net�7!");
				} else if (index == 29) {
					Bukkit.broadcastMessage("�b�lINFO �7� Is someone being annoying? Toggle messages and teleports by using �b/msgtoggle �7and �b/tptoggle�7.");
				} else if (index == 30) {
					Bukkit.broadcastMessage("�b�lINFO �7� Broadcast a message to all online faction members by using �b/f announce�7.");
				} else if (index == 31) {
					Bukkit.broadcastMessage("�b�lINFO �7� Everything you do on our server levels you up. You can spend level up tokens on rewards in �b/skills");
				} else if (index == 32) {
					Bukkit.broadcastMessage("�b�lINFO �7� Purchase mob spawners and anything else you need in the �b/shop�7.");

				}
			}
		}, 0L, 3000L);

		// Wither Boss Event
		BukkitScheduler WitherBossEvent = getServer().getScheduler();
		WitherBossEvent.scheduleSyncRepeatingTask(this, new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Bukkit.broadcastMessage("Spawn Message");
				Location WitherSpawn = (Location)(getConfig()).get(".WitherSpawn");
				Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn, EntityType.WITHER);
				mob.setCustomName("�4�l�nWither King");
				mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
				mob.setMaxHealth(100);
				mob.setHealth(100);
			}
		}, 0L, 3000L);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws InvocationTargetException {
		Player player = event.getPlayer();
		// MOTD
		player.sendMessage("�f�l�m-----------�b�l�m-----------�f�l�m-----------");
		player.sendMessage("        �f�lCONNECTED TO �b�lICEWYND �b�lFACTIONS");
		player.sendMessage("                         �f(�b1.7.10 �f- �b1.12�f)");
		player.sendMessage("");
		player.sendMessage("�b�lFORUMS: �fIcewynd.net");
		player.sendMessage("�b�lDISCORD: �fIcewynd.net/Discord");
		player.sendMessage("�b�lSTORE: �fIcewynd.net/Store");
		player.sendMessage("�f�l�m-----------�b�l�m-----------�f�l�m-----------");
		//Scoreboard
		XoreBoard xoreBoard = XoreBoardUtil.getNextXoreBoard();
		xoreBoard.addPlayer(event.getPlayer());
		XoreBoardGlobalSidebar sidebar = xoreBoard.getSidebar();
		sidebar.setDisplayName("�b�lIcewynd.net");
		sidebar.showSidebar();
		sidebar.putLine("�l�7�m------------", 9);
		sidebar.putLine("�a�lFaction:", 8);
		MPlayer mplayer = MPlayer.get(player);
		String faction = mplayer.getFactionName();
		sidebar.putLine("�7��f " + StringUtils.capitalize(faction), 7);
		sidebar.putLine("�b", 6);
		sidebar.putLine("�d�lPing:", 5);
		int ping = ((CraftPlayer) player).getHandle().ping;
		sidebar.putLine("�7��f " + ping, 4);
		sidebar.putLine("�f", 3);
		sidebar.putLine("�a�lBalance:", 2);
		sidebar.putLine("�7��f $" + econ.getBalance(player), 1);
		sidebar.putLine("�7�l�m------------", 0);

		if (getConfig().getBoolean(player.getUniqueId() + ".Banned") == true) {
			player.teleport(MConf.get().getWarp("jail"));
		}
		// TabList foot/header
		PacketContainer packetContainer = Main.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		packetContainer.getChatComponents().write(0, WrappedChatComponent.fromText(
				" �8�l�m-�7�l�m-�f�l[�f ICEWYND �bNETWORK�f�l ]�7�l�m-�8�l�m-�r "))
		.write(1, WrappedChatComponent.fromText("�dStore, forums and more at Icewynd.net"));
		ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);

		// New Player Announce
		if (!player.hasPlayedBefore()) {
			Bukkit.broadcastMessage("�b�lWelcome to Icewynd, �f�l" + player.getName() + "�b�l!");
			getConfig().set(player.getUniqueId() + ".Name", player.getName());
			getConfig().set(player.getUniqueId() + ".Coins", 0);
			getConfig().set(player.getUniqueId() + ".MsgToggle", false);
			getConfig().set(player.getUniqueId() + ".Freecam", false);
			getConfig().set(player.getUniqueId() + ".Banned", false);
			getConfig().set(player.getUniqueId() + ".BlocksMined", 0);
			getConfig().set(player.getUniqueId() + ".SugarcaneMined", 0);
			getConfig().set(player.getUniqueId() + ".LuckyDrops", 0);
			getConfig().set(player.getUniqueId() + ".BlocksPlaced", 0);
			getConfig().set(player.getUniqueId() + ".LuckyDrops", 0);
			getConfig().set(player.getUniqueId() + ".MCMMOLevelsGained", 0);
			getConfig().set(player.getUniqueId() + ".SkillsObtained", 0);
			getConfig().set(player.getUniqueId() + ".LuckyDropsFound", 0);
			getConfig().set(player.getUniqueId() + ".ChallengesCompleted", 0);
			getConfig().set(player.getUniqueId() + ".BooksEnchanted", 0);
			getConfig().set(player.getUniqueId() + ".CastleCaptures", 0);
			getConfig().set(player.getUniqueId() + ".SupplyDropsCaptured", 0);
			saveConfig();
		}
		// Scoreboard Update
		BukkitScheduler WitherBossEvent = getServer().getScheduler();
		WitherBossEvent.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("update");
				sidebar.setDisplayName("�b�lIcewynd.net");
				sidebar.showSidebar();
				sidebar.putLine("�l�7�m------------", 9);
				sidebar.putLine("�a�lFaction:", 8);
				MPlayer mplayer = MPlayer.get(player);
				String faction = mplayer.getFactionName();
				sidebar.putLine("�7��f " + StringUtils.capitalize(faction), 7);
				sidebar.putLine("�b", 6);
				sidebar.putLine("�d�lPing:", 5);
				int ping = ((CraftPlayer) player).getHandle().ping;
				sidebar.putLine("�7��f " + ping, 4);
				sidebar.putLine("�f", 3);
				sidebar.putLine("�a�lBalance:", 2);
				sidebar.putLine("�7��f $" + econ.getBalance(player), 1);
				sidebar.putLine("�7�l�m------------", 0);
			}
		}, 0L, 50L);


		// Creates default prices.yml file if one doesn't already exist
		File customYml = new File(getDataFolder()+"/prices.yml");
		pricesConfig = YamlConfiguration.loadConfiguration(customYml);	
		saveResource("prices.yml", false);

		// Gets the economy
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp != null) {
			econ = rsp.getProvider();
		}
	}
	@EventHandler
	public void ondeath(EntityDeathEvent event) {
		if (event.getEntityType().equals(EntityType.WITHER_SKELETON)) {
			if (event.getEntity().getCustomName().equals("�4�l�nWither Minion")) {
				Bukkit.broadcastMessage("hi");
			}
		} else if (event.getEntityType().equals(EntityType.WITHER)) {
			if (event.getEntity().getCustomName().equals("�4�l�nWither King")) {
				getConfig().set(".WitherPhase", 0);
				saveConfig();
			}
		}
	}


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("crategive")) {
			if (!sender.hasPermission("crate.give")) {
				sender.sendMessage("�c�l(!)�7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					if (args[1].equalsIgnoreCase("legendary")) {
						LegendaryCrate.give(player);
						sender.sendMessage("�b�l(!)�7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("exotic")) {
						ExoticCrate.give(player);
						sender.sendMessage("�b�l(!)�7 A crate has been added to the players inventory!");
					} else if (args[1].equalsIgnoreCase("event")) {
						EventCrate.give(player);
						sender.sendMessage("�b�l(!)�7 A crate has been added to the players inventory!");
					} else {
						sender.sendMessage(
								"�c�l(!)�7 Incorrect usage. Try /crate <playername> <legendary/exotic/event>");
					}
				} catch (Exception e) {
					e.printStackTrace();
					sender.sendMessage("�c�l(!)�7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("�c�l(!)�7 Usage: /crategive <playername> <legendary/exotic>");
			}
		} else if (label.equalsIgnoreCase("librarian")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Librarian.openInventory(player);
					} catch (Exception e) {
						sender.sendMessage("�c�l(!)�7 That player cannot be found!");
					}
				} else {
					sender.sendMessage("�c�l(!)�7 Usage: /librarian <playername>!");
				}
			} else {
				sender.sendMessage("�c�l(!)�7 You do not have permission to do that!");
			}
		} else if (label.equalsIgnoreCase("enchanter")) {
			if (sender instanceof ConsoleCommandSender) {
				if (args.length == 1) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						Enchanter.openInventory(player);
					} catch (Exception e) {
						sender.sendMessage("�c�l(!)�7 That player cannot be found!");
					}
				} else {
					sender.sendMessage("�c�l(!)�7 Usage: /enchanter <playername>!");
				}
			} else {
				sender.sendMessage("�c�l(!)�7 You do not have permission to do that!");
			}
		} else if (label.equalsIgnoreCase("spawnergive")) {
			if (!sender.hasPermission("spawner.give")) {
				sender.sendMessage("�c�l(!)�7 You do not have permission to do that!");
				return true;
			}
			if (args.length == 2) {
				try {
					Player player = Bukkit.getPlayer(args[0]);
					System.out.println(player.getName());
					ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
					ItemMeta spawnerMeta = spawner.getItemMeta();
					spawnerMeta.setDisplayName("�e" + args[1].toUpperCase().replaceAll("_", " ") + " �fSpawner");
					spawner.setItemMeta(spawnerMeta);
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("�b�l(!)�7 Your inventory is full, dropping spawner at your feet!");
						player.getWorld().dropItem(player.getLocation(), spawner);
					} else {
						player.sendMessage("�b�l(!)�7 A spawner has been added to your inventory!");
						player.getInventory().addItem(spawner);
					}
					sender.sendMessage("�a�l(!)�7 Successfully added the spawner to the player's inventory!");
				} catch (Exception e) {
					sender.sendMessage("�c�l(!)�7 That player cannot be found!");
				}
			} else {
				sender.sendMessage("�c�l(!)�7 Usage: /spawnergive <playername> <type>");
			}
		} else if (label.equalsIgnoreCase("sandstone")) {
			Player player = Bukkit.getPlayer(sender.getName());
			if (player.hasPermission("server.admin")) {
				SandstoneBiome.convert(player);
			} else {
				player.sendMessage("�c�l(!)�7 You don't have permission to use that command!");
			}
		} else if (label.equalsIgnoreCase("xp")) {
			Player player = Bukkit.getPlayer(sender.getName());
			player.sendMessage("�b�l(!)�7 You have �8" + player.getExp() + " �7experience!");
		} else if (label.equalsIgnoreCase("xpgive")) {
			if (sender instanceof Player) {
				Player player = Bukkit.getPlayer(sender.getName());
				if (player.hasPermission("server.admin")) {
					if (args.length == 2) {
						try {
							Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
							player.sendMessage("�a�l(!)�7 Successfully added the xp to the specified player.");
						} catch (Exception e) {
							player.sendMessage("�c�l(!)�7 Player offline or incorrect amount.");
						}
					} else {
						player.sendMessage("�c�l(!)�7 Usage: /xpgive <player> <amount>");
					}
				} else {
					player.sendMessage("�c�l(!)�7 You don't have permission to use that command!");
				}
			} else {
				if (args.length == 2) {
					try {
						Bukkit.getPlayer(args[0]).giveExp(Integer.parseInt(args[1]));
						sender.sendMessage("�a�l(!)�7 Successfully added the xp to the specified player.");
					} catch (Exception e) {
						sender.sendMessage("�c�l(!)�7 Player offline or incorrect amount.");
					}
				} else {
					sender.sendMessage("�c�l(!)�7 Usage: /xpgive <player> <amount>");
				}
			}
		}
		return true;
	}
}
