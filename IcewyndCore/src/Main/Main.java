package Main;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.Chest;
import org.bukkit.block.Hopper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wither;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import BanSystem.BanCommand;
import BanSystem.EscapeCommand;
import BanSystem.UnbanCommand;
import Commands.Back;
import Commands.Book;
import Commands.ClearChat;
import Commands.ClearInventory;
import Commands.DelHome;
import Commands.Disposal;
import Commands.Enderchest;
import Commands.Experience;
import Commands.Feed;
import Commands.Freecam;
import Commands.Help;
import Commands.Home;
import Commands.Invsee;
import Commands.Jackpot;
import Commands.Kits;
import Commands.Lag;
import Commands.List;
import Commands.Message;
import Commands.MsgToggle;
import Commands.NightVision;
import Commands.Overwatch;
import Commands.PInfo;
import Commands.PatchNotes;
import Commands.Ping;
import Commands.Reset;
import Commands.Reward;
import Commands.Rules;
import Commands.Sandstone;
import Commands.Seen;
import Commands.Sell;
import Commands.SetHome;
import Commands.Shop;
import Commands.SpawnerGive;
import Commands.Stats;
import Commands.Suicide;
import Commands.TpToggle;
import Commands.Tpa;
import Commands.Tpaccept;
import Commands.Tpahere;
import Commands.TwitchBroadcast;
import Commands.YoutubeBroadcast;
import Crates.CrateEventListener;
import Crates.CrateGiveCommand;
import Crates.CrateTest;
import Crates.EventCrate;
import Crates.ExoticCrate;
import Crates.LegendaryCrate;
import CustomEnchants.Enchanter;
import CustomEnchants.Enchantments;
import CustomEnchants.Librarian;
import McMMO.Milestones;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener {
	public static ProtocolManager protocolManager;
	public static FileConfiguration pricesConfig;
	public static Economy econ;
	public static File CommandStore;
	public static JackpotEntity jackpot;

	public static int SupplyDropTask;
	public static int spawntask;
	
	public void onEnable() {
		saveDefaultConfig();
		PluginManager manager = Bukkit.getServer().getPluginManager();
		Main.protocolManager = ProtocolLibrary.getProtocolManager();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);
		manager.registerEvents(this, this);
		manager.registerEvents(new CrateEventListener(), this);
		manager.registerEvents(new Enchantments(), this);
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
		manager.registerEvents(new Milestones(), this);
		manager.registerEvents(new Kits(), this);
		manager.registerEvents(new Freecam(), this);
		manager.registerEvents(new Sell(), this);
		manager.registerEvents(new Shop(), this);
		manager.registerEvents(new DisguiseBuffs(), this);
		manager.registerEvents(new BossEggs(), this);
		manager.registerEvents(new Seen(), this);
		manager.registerEvents(new WitherEvent(), this);
		manager.registerEvents(new NoWaterRedstone(), this);
		manager.registerEvents(new PVPTimer(), this);
		manager.registerEvents(new Stats(), this);
		manager.registerEvents(new Reward(), this);
		manager.registerEvents(new Back(), this);
		manager.registerEvents(new BanCommand(), this);
		manager.registerEvents(new TNTPatches(), this);
		manager.registerEvents(new QuarterMaster(), this);
		manager.registerEvents(new CrateTest(), this);
		manager.registerEvents(new SupplyDropEvent(), this);

		getCommand("rules").setExecutor(new Rules());
		getCommand("q").setExecutor(new QuarterMaster());
		getCommand("ping").setExecutor(new Ping());
		getCommand("pinfo").setExecutor(new PInfo());
		getCommand("patchnotes").setExecutor(new PatchNotes());
		getCommand("ow").setExecutor(new Overwatch());
		getCommand("overwatch").setExecutor(new Overwatch());
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
		getCommand("sell").setExecutor(new Sell());
		getCommand("shop").setExecutor(new Shop());
		getCommand("feed").setExecutor(new Feed());
		getCommand("help").setExecutor(new Help());
		getCommand("list").setExecutor(new List());
		getCommand("suicide").setExecutor(new Suicide());
		getCommand("seen").setExecutor(new Seen());
		getCommand("witherspawn").setExecutor(new WitherEvent());
		getCommand("reset").setExecutor(new Reset());
		getCommand("sandstone").setExecutor(new Sandstone());
		getCommand("xpgive").setExecutor(new Experience());
		getCommand("xp").setExecutor(new Experience());
		getCommand("spawnergive").setExecutor(new SpawnerGive());
		getCommand("enchanter").setExecutor(new Enchanter());
		getCommand("librarian").setExecutor(new Librarian());
		getCommand("crategive").setExecutor(new CrateGiveCommand());
		getCommand("pvptimer").setExecutor(new PVPTimer());
		getCommand("lag").setExecutor(new Lag());
		getCommand("reward").setExecutor(new Reward());
		getCommand("delhome").setExecutor(new DelHome());
		getCommand("deletehome").setExecutor(new DelHome());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new SetHome());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("tpahere").setExecutor(new Tpahere());
		getCommand("tpyes").setExecutor(new Tpaccept());
		getCommand("tpaccept").setExecutor(new Tpaccept());
		getCommand("tptoggle").setExecutor(new TpToggle());
		getCommand("toggletp").setExecutor(new TpToggle());
		getCommand("back").setExecutor(new Back());
		getCommand("jackpot").setExecutor(new Jackpot());
		getCommand("escape").setExecutor(new EscapeCommand());
		getCommand("supplydrop").setExecutor(new SupplyDropEvent());

		ExoticCrate.load();
		LegendaryCrate.load();
		EventCrate.load();
		jackpot = new JackpotEntity();

		//Uploader.triggerDatabaseAutoUpdate(); //Triggers the auto-updater for the factions web-database. Every 5 mins player and faction data will be updated.

		//Creates default prices.yml file if one doesn't already exist
		File customYml = new File(getDataFolder()+"/prices.yml");
		pricesConfig = YamlConfiguration.loadConfiguration(customYml);	
		saveResource("prices.yml", false);

		//		Creates default commandstore file if it doesn't exist
		try {
			CommandStore = new File(getDataFolder() + "/CommandStore.txt");
			CommandStore.createNewFile(); // if it doesn't exist
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//		Gets the economy
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp != null) {
			econ = rsp.getProvider();
		}

		BukkitScheduler Broadcasts = getServer().getScheduler();
		Broadcasts.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Random rand = new Random();
				int index = rand.nextInt(19) + 1;
				if (index == 1) {
					Bukkit.broadcastMessage("§b§lINFO §7» Join our community Discord to chat with others and get suppport by clicking this message");
				} else if (index == 2) {
					Bukkit.broadcastMessage("§b§lINFO §7» There are many ways to gamble but Jackpot is the most fun! Participate using §b/jackpot§7.");
				} else if (index == 3) {
					Bukkit.broadcastMessage("§b§lINFO §7» Need a faction? Type §b/f find§7 to see if any factions are recruiting.");
				} else if (index == 4) {
					Bukkit.broadcastMessage("§b§lINFO §7» Need support? Send in a support ticket on the forums or join Discord for help.");
				} else if (index == 5) {
					Bukkit.broadcastMessage("§b§lINFO §7» Upgrade your faction using §b/f upgrade§7 to gain perks and progress faster!");
				} else if (index == 6) {
					Bukkit.broadcastMessage("§b§lINFO §7» Secure your account using §b/auth§7 and prevent hackers from accessing your account.");
				} else if (index == 7) {
					Bukkit.broadcastMessage("§b§lINFO §7» Fishing is fun and fast! McMMO is doubled in SafeZone and you may catch something valuable!");
				} else if (index == 8) {
					Bukkit.broadcastMessage("§b§lINFO §7» Find more players for your faction by marking your faction as recruiting with the command §b/f recruiting§7!");
				} else if (index == 9) {
					Bukkit.broadcastMessage("§b§lINFO §7» Complete achievements to gain rewards and show off using §b/achievements§7.");
				} else if (index == 10) {
					Bukkit.broadcastMessage("§b§lINFO §7» Quickly mine out areas for your base with the shockwave enchantment aquired from the Enchanter NPC or the store!");
				} else if (index == 11) {
					Bukkit.broadcastMessage("§b§lINFO §7» Spend coins on a large variety of prizes by visiting the Quartermaster found in Safezone! ");
				} else if (index == 12) {
					Bukkit.broadcastMessage("§b§lINFO §7» Found a bug? Submit it on our forums for a potential reward.");
				} else if (index == 13) {
					Bukkit.broadcastMessage("§b§lINFO §7» Boss eggs are dropped from killing mobs, summon them in Warzone for a battle & prize!");
				} else if (index == 14) {
					Bukkit.broadcastMessage("§b§lINFO §7» Protect your faction from betrayal by using faction regions and trusted permissions.");
				} else if (index == 15) {
					Bukkit.broadcastMessage("§b§lINFO §7» Reach the top of the mcmmo leaderboard in any skill for a unique prefix in chat and a unique kit every day!");
				} else if (index == 16) {
					Bukkit.broadcastMessage("§b§lINFO §7» Capture Castle for victory points, double spawner rates, an extra 10% on /sell, and cash every 10 minutes!");
				} else if (index == 17) {
					Bukkit.broadcastMessage("§b§lINFO §7» Test out your cannons and build freely using our cannon server! Connect by going to the hub.");
				} else if (index == 18) {
					Bukkit.broadcastMessage("§b§lINFO §7» Gunpowder can be converted to TNT using furnaces! Increase the speed using §b/f upgrade§7.");
				} else if (index == 19) {
					Bukkit.broadcastMessage("§b§lINFO §7» Fill cannons quickly and store TNT using §b/f tnt§7.");
				} else if (index == 20) {
					Bukkit.broadcastMessage("§b§lINFO §7» Want to sell items easier? Sell wands sell entire chests at a time. Obtain them from the Quartermaster.");
				} else if (index == 21) {
					Bukkit.broadcastMessage("§b§lINFO §7» Support the server by purchasing items on the Store. Visit at §bIcewynd.net/store§7.");
				} else if (index == 22) {
					Bukkit.broadcastMessage("§b§lINFO §7» Gain rewards from completing daily bounties, you can find these at Xavier in Safezone.");
				} else if (index == 23) {
					Bukkit.broadcastMessage("§b§lINFO §7» Find our more information about the server including all commands by using §b/info§7!");
				} else if (index == 24) {
					Bukkit.broadcastMessage("§b§lINFO §7» Check your ping by using §b/ping§b7. This command also works on other players.");
				} else if (index == 25) {
					Bukkit.broadcastMessage("§b§lINFO §7» Access your enderchest by using §b/echest§7. This only works for ranked players.");
				} else if (index == 26) {
					Bukkit.broadcastMessage("§b§lINFO §7» Check yours and other players statistics by using §b/statistics§7.");
				} else if (index == 27) {
					Bukkit.broadcastMessage("§b§lINFO §7» Get assistance from your faction members in PVP by using Faction Banners!");
				} else if (index == 28) {
					Bukkit.broadcastMessage("§b§lINFO §7» Do you stream on our server? Add your stream to our streamers tab on our website §bIcewynd.net§7!");
				} else if (index == 29) {
					Bukkit.broadcastMessage("§b§lINFO §7» Is someone being annoying? Toggle messages and teleports by using §b/msgtoggle §7and §b/tptoggle§7.");
				} else if (index == 30) {
					Bukkit.broadcastMessage("§b§lINFO §7» Broadcast a message to all online faction members by using §b/f announce§7.");
				} else if (index == 31) {
					Bukkit.broadcastMessage("§b§lINFO §7» Everything you do on our server levels you up. You can spend level up tokens on rewards in §b/skills");
				} else if (index == 32) {
					Bukkit.broadcastMessage("§b§lINFO §7» Purchase mob spawners and anything else you need in the §b/shop§7.");
				}
			}
		}, 0L, 3000L);

		UnbanCommand.triggerUnbanScheduler();
		Freecam.triggerFreecamCheck();

		//		Wither Boss Event
		BukkitScheduler WitherBossEvent = getServer().getScheduler();
		WitherBossEvent.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("§7§lThe §c§lWither King §7§lwas spotted north of Safezone!");
				Location WitherSpawn = (Location)(getConfig()).get(".WitherSpawn");
				Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn, EntityType.WITHER);
				mob.setCustomName("§4§l§nWither King");
				mob.setMaxHealth(500);
				mob.setHealth(500);
				mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
			}
		}, 0L, 3000L);

		//		Player Count
		BukkitScheduler PlayerCount = getServer().getScheduler();
		PlayerCount.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().size();
				getConfig().set(".PlayersOnline", Bukkit.getOnlinePlayers().size());
			}
		}, 0L, 300L);

		//		Clear Lag Announce
		BukkitScheduler ClearLagAnnounce = getServer().getScheduler();
		ClearLagAnnounce.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("§e§lClearLag§7 » §aEntities will be cleared in 1 minute!");
			}
		}, 0L, 4800L);

		//		Clear Lag
		BukkitScheduler ClearLag = getServer().getScheduler();
		ClearLag.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("§e§lClearLag§7 » §aEntities have been cleared!");
				for (Entity entity : Bukkit.getWorld("world").getEntities()){
					if (entity instanceof FallingBlock || entity instanceof TNTPrimed || entity instanceof ExperienceOrb || entity instanceof Player || entity instanceof Beacon ||  entity instanceof Hopper)  {
					}else {
						entity.remove();
					}
				}
			}
		}, 0L, 6000L);

		//		Supply Drop Event
		BukkitScheduler SupplyDrop = getServer().getScheduler();
		SupplyDrop.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Location SupplyDrop1 = (Location)(getConfig()).get(".SupplyDrop");
				Bukkit.broadcastMessage("§a§lSUPPLYDROP§7 » §eA Supply Drop is falling near X:" + SupplyDrop1.getBlockX() + " Z:" + SupplyDrop1.getBlockZ());
				spawnSupplyDrop(SupplyDrop1);
			}
		}, 0L, 108000L);
		
		SupplyDropTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Location SupplyDrop1 = (Location)(getConfig()).get(".SupplyDrop");
				Bukkit.getWorld("world").playEffect(SupplyDrop1, Effect.PORTAL, 5);
			}
		}, 0L, 1L);
	}

	public void spawnSupplyDrop(Location SupplyDrop1) {
		SupplyDrop1.add(0,75,0);
		spawntask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			int currentBlocksAbove = 75;
			public void run() {
				SupplyDrop1.getBlock().setType(Material.CHEST);
				SupplyDrop1.add(0,1,0).getBlock().setType(Material.AIR);
				SupplyDrop1.add(0,-2,0);
				System.out.println("Current Y: " + SupplyDrop1.getBlockY());
				currentBlocksAbove = currentBlocksAbove - 1;
				if(currentBlocksAbove == 0) {
					System.out.println("Spawning Supply Drops (falling phase finished)");
					SupplyDrop1.getBlock().setType(Material.CHEST);
					Chest chest = (Chest)SupplyDrop1.getBlock().getState();
					Inventory inv = chest.getInventory();
					inv.addItem(new ItemStack(SupplyDropEvent.SupplyDropItems()));
					inv.addItem(new ItemStack(SupplyDropEvent.SupplyDropItems()));
					inv.addItem(new ItemStack(SupplyDropEvent.SupplyDropItems()));
					inv.addItem(new ItemStack(SupplyDropEvent.SupplyDropItems()));
					inv.addItem(new ItemStack(SupplyDropEvent.SupplyDropItems()));
					SupplyDrop1.add(0,1,0).getBlock().setType(Material.OBSIDIAN);
					Bukkit.getScheduler().cancelTask(spawntask);
				}
			}
		}, 0L, 4L);
	}
	
	public void onDisable() {
		jackpot.disable();
	}
}