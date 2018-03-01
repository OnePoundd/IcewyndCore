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

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ondamage(EntityDamageEvent event) {
		Entity e = event.getEntity();
		Bukkit.broadcastMessage("Health: " + ((LivingEntity) e).getHealth());
		if (e.getCustomName().equals("§4§l§nWither King")) {
			if (((LivingEntity) e).getHealth() < 50) {
				if (plugin.getConfig().getInt(".WitherPhase") == 0) {
					plugin.getConfig().set(".WitherPhase", 1);
					plugin.saveConfig();
					Bukkit.broadcastMessage("50% Health, summoning Wither Guards");
					Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
					e.teleport(WitherSpawn);
					Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(0, 2, 0),EntityType.WITHER_SKELETON);
					mob.setCustomName("§4§l§nWither Minion");
					mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
					mob.setCanPickupItems(false);
				}
			}else if (((LivingEntity) e).getHealth() < 25) {
				if (plugin.getConfig().getInt(".WitherPhase") == 1) {
					Bukkit.broadcastMessage("25% Health, summoning Blaze Guards");
					plugin.getConfig().set(".WitherPhase", 0);
					plugin.saveConfig();
					Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
					Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(0, 2, 0),EntityType.BLAZE);
				}
			}
		}
	}
}