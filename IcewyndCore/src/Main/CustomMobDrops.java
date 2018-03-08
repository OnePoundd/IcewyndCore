package Main;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class CustomMobDrops implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		EntityType mob = e.getEntityType();
		if (mob.equals(EntityType.WITCH)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() != null) {
				int num = new Random().nextInt(20);
				Potion pot = null;
				if(num >= 19) {
					pot = new Potion(PotionType.FIRE_RESISTANCE);
					pot.setHasExtendedDuration(true);
				}else if(num >= 16) {
					pot = new Potion(PotionType.STRENGTH);
					pot.setLevel(2);
					pot.setSplash(true);
				}else if(num >= 13) {
					pot = new Potion(PotionType.SPEED);
					pot.setLevel(2);
					pot.setSplash(true);
				}else if(num >= 8) {
					pot = new Potion(PotionType.INSTANT_HEAL);
					pot.setLevel(2);
					pot.setSplash(true);
				}
				if(pot != null) {
					e.getDrops().add(pot.toItemStack(1));
				}
			}
		} else if (mob.equals(EntityType.PIG_ZOMBIE)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				int num = new Random().nextInt(1);
				if(num == 1) {
					e.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, 1));
				}
			} else {
				int num = new Random().nextInt(10);
				if(num == 10) {
					e.getDrops().add(new ItemStack(Material.GOLD_INGOT, 1));
				}else if(num >= 4) {
					e.getDrops().add(new ItemStack(Material.GOLD_NUGGET, 1));
				}
			}
		} else if (mob.equals(EntityType.CREEPER)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				int num = new Random().nextInt(20);
				if(num >= 19) {
					e.getDrops().add(new ItemStack(Material.TNT, 1));
				}else if(num >= 10){
					e.getDrops().add(new ItemStack(Material.SULPHUR, 1));
				}
			} else {
				int num = new Random().nextInt(2);
				if(num == 2) {
					e.getDrops().add(new ItemStack(Material.TNT, 1));
				}
			}
		} else if (mob.equals(EntityType.VILLAGER)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				int num = new Random().nextInt(20);
				if(num >= 19) {
					e.getDrops().add(new ItemStack(Material.DIAMOND, 1));
				}else if(num >= 10){
					e.getDrops().add(new ItemStack(Material.IRON_INGOT, 1));
				}
			} else {
				int num = new Random().nextInt(2);
				if(num == 2) {
					e.getDrops().add(new ItemStack(Material.DIAMOND, 1));
				}
			}
		}
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (event.getEntityType() == EntityType.ENDERMAN) {
			event.getEntity().setHealth(10);
		} else if (event.getEntityType() == EntityType.VILLAGER) {
			event.getEntity().setHealth(20);
		} else if (event.getEntityType() == EntityType.SQUID) {
			event.getEntity().setHealth(10);
		}
	}
}
