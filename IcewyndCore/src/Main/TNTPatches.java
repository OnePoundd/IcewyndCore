package Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TNTPatches implements Listener {

	@EventHandler
	public void onexplode(EntityExplodeEvent event) {
		if (event.getEntityType() == EntityType.PRIMED_TNT) {
			int radius = (int) Math.ceil(3);
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {
						Location loc = new Location(Bukkit.getWorld("world"), x + event.getLocation().getX(),y + event.getLocation().getY(), z + event.getLocation().getZ());
						if (loc.getBlock().getType() == Material.LAVA) {
							loc.getBlock().breakNaturally();
						}
						if (loc.getBlock().getType() == Material.MOB_SPAWNER) {
							loc.getBlock().breakNaturally();
						}
						if (event.getLocation().getBlock().isLiquid()) {
							Bukkit.broadcastMessage("water");
						}
					}
				}
			}
		}
	}
}