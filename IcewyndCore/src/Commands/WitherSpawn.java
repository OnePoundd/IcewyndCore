package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import Main.Main;

public class WitherSpawn implements CommandExecutor, Listener {
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("witherspawn")) {
			if (sender instanceof Player) {
				Location Spawn = ((Player) sender).getPlayer().getLocation();
				plugin.getConfig().set(".WitherSpawn", Spawn);
				plugin.saveConfig();
			}
		}
		return false;
	}

	@EventHandler
	public void ondamage(EntityDamageEvent event) {
		Entity e = event.getEntity();

		if (plugin.getConfig().getBoolean(".WitherInvin") == true) {
			event.setCancelled(true);
			if (e.getCustomName().equals("§4§l§oWither King")) {
				if (plugin.getConfig().getBoolean(".WitherInvin") == true) {
					event.setCancelled(true);
				} else if (((LivingEntity) e).getHealth() < 50) {
					if (plugin.getConfig().getInt(".WitherPhase") == 0) {
						plugin.getConfig().set(".WitherPhase", 1);
						plugin.getConfig().set(".WitherInvin", true);
						Bukkit.broadcastMessage("50% Health, summoning Wither Guards");
						Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
						e.teleport(WitherSpawn);
						((LivingEntity) e).setAI(false);
						WitherSkeleton mob = (WitherSkeleton) Bukkit.getWorld("world")
								.spawnEntity(WitherSpawn.add(-4, 0, 0), EntityType.WITHER_SKELETON);
						WitherSkeleton mob2 = (WitherSkeleton) Bukkit.getWorld("world")
								.spawnEntity(WitherSpawn.add(4, 0, 4), EntityType.WITHER_SKELETON);
						WitherSkeleton mob3 = (WitherSkeleton) Bukkit.getWorld("world")
								.spawnEntity(WitherSpawn.add(2, 0, -2), EntityType.WITHER_SKELETON);
						mob.setCustomName("§c§lWithered Minion");
						mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
						mob2.setCustomName("§c§lWithered Minion");
						mob2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
						mob3.setCustomName("§c§lWithered Minion");
						mob3.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
						WitherSpawn.add(-2, 0, -2);
						plugin.saveConfig();
					}
				}
			} else if (((LivingEntity) e).getHealth() < 25) {
				if (plugin.getConfig().getInt(".WitherPhase") == 1) {
					plugin.getConfig().set(".WitherPhase", 2);
					plugin.saveConfig();
					Bukkit.broadcastMessage("25% Health, summoning Blaze Guards");
					Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
					@SuppressWarnings("unused")
					Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(+4, 0, +4),
							EntityType.BLAZE);
					plugin.getConfig().set(".WitherSkeletons", 0);
					plugin.saveConfig();
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ondeath(EntityDeathEvent event) {
		int totalKilled = plugin.getConfig().getInt(".WitherSkeletons");
		if (event.getEntity() instanceof Wither) {
			Bukkit.broadcastMessage("Wither");
			if (event.getEntity().getCustomName().equals("§4§l§oWither King")) {
				plugin.getConfig().set(".WitherPhase", 0);
				plugin.saveConfig();
				Bukkit.broadcastMessage("WITHER KING HAS BEEN SLAIN");
			}
		} else if (event.getEntity() instanceof WitherSkeleton) {
			Bukkit.broadcastMessage("Skeleton");
			plugin.getConfig().set(".WitherSkeletons", totalKilled + 1);
			plugin.saveConfig();
		}
		if (totalKilled == 3) {
			plugin.getConfig().set(".WitherInvin", false);
			plugin.saveConfig();
			for (Entity entity : Bukkit.getWorld("World").getEntities())
				if (entity.getType() == EntityType.WITHER) {
					entity.remove();
					Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
					Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn, EntityType.WITHER);
					mob.setCustomName("§4§l§oWither King");
					mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
					mob.setMaxHealth(50);
					mob.setHealth(50);
				}
		}
	}
}