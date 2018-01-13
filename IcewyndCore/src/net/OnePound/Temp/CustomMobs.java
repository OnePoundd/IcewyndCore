package Test;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CustomMobs implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		EntityType mob = e.getEntityType();
		if (mob.equals(EntityType.WITCH)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				// Natural Death
				e.getDrops().add(new ItemStack(Material.SPIDER_EYE, 1));
				e.getDrops().add(new ItemStack(Material.REDSTONE, 1));
				e.getDrops().add(new ItemStack(Material.GLOWSTONE_DUST, 1));
				e.getDrops().add(new ItemStack(Material.SULPHUR, 1));
				e.getDrops().add(new ItemStack(Material.SUGAR, 1));
			} else {
				// Player Death
				e.getDrops().add(new ItemStack(Material.POTION, 1, (short) 8259));
				e.getDrops().add(new ItemStack(Material.POTION, 1, (short) 8259));
				e.getDrops().add(new ItemStack(Material.POTION, 1, (short) 8233));
			}
		} else if (mob.equals(EntityType.PIG_ZOMBIE)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				// Natural Death
				e.getDrops().add(new ItemStack(Material.GOLD_NUGGET, 1));
				e.getDrops().add(new ItemStack(Material.ROTTEN_FLESH, 1));
			} else {
				// Player Death
				e.getDrops().add(new ItemStack(Material.GOLD_INGOT, 1));
			}
		} else if (mob.equals(EntityType.CREEPER)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				// Natural Death
				e.getDrops().add(new ItemStack(Material.TNT, 1));
				e.getDrops().add(new ItemStack(Material.SULPHUR, 1));
			} else {
				// Player Death
				e.getDrops().add(new ItemStack(Material.TNT, 1));
			}
		} else if (mob.equals(EntityType.VILLAGER)) {
			e.getDrops().clear();
			if (e.getEntity().getKiller() == null) {
				// Natural Death
				e.getDrops().add(new ItemStack(Material.EMERALD, 1));
			} else {
				// Player Death
				e.getDrops().add(new ItemStack(Material.EMERALD, 1));
				e.getDrops().add(new ItemStack(Material.DIAMOND, 1));
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