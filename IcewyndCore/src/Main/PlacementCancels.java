package Main;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlacementCancels implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType().equals(Material.SLIME_BLOCK));
		event.setCancelled(true);
	}

}
