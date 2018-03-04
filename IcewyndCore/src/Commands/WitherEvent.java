package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import Main.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class WitherEvent implements CommandExecutor, Listener {
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

	public void noAI(org.bukkit.entity.Entity bukkitEntity)
	{
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);
	}

	/*@SuppressWarnings("deprecation")
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
						LivingEntity entity = (LivingEntity) event.getEntity();
						noAI(entity);
						@SuppressWarnings("deprecation")
						WitherSkeleton mob = (EntityType.fromId(5)); Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(-4, 0, 0), EntityType.fromId(5));
						WitherSkeleton mob2 = (EntityType.fromId(5)); Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(4, 0, 4), EntityType.fromId(5));
						WitherSkeleton mob3 = (EntityType.fromId(5)); Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(2, 0, -2), EntityType.fromId(5));
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
					Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(+4, 0, +4),EntityType.BLAZE);
					plugin.getConfig().set(".WitherSkeletons", 0);
					plugin.saveConfig();
				}
			}
		}
	}*/

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
		}
		Entity entity = event.getEntity();
		Skeleton skeleton = (Skeleton) entity;
		if (event.getEntity() instanceof Skeleton) {
			if (skeleton.getSkeletonType() == SkeletonType.WITHER) {
				Bukkit.broadcastMessage("Skeleton");
				plugin.getConfig().set(".WitherSkeletons", totalKilled + 1);
				plugin.saveConfig();
			}
			if (totalKilled == 3) {
				plugin.getConfig().set(".WitherInvin", false);
				plugin.saveConfig();
				for (Entity entity1 : Bukkit.getWorld("World").getEntities())
					if (entity1.getType() == EntityType.WITHER) {
						entity1.remove();
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
}