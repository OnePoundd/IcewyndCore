package Main;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class Cancels implements Listener {

	@EventHandler
	public void onBreed(EntityBreedEvent e) {
		e.setCancelled(true);
	}
	public void onspawn(EntitySpawnEvent e) {
		if (e.getEntityType() == EntityType.ENDERMITE);
		e.setCancelled(true);
	}
}