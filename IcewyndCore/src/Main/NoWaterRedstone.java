package Main;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class NoWaterRedstone implements Listener{
	
	@EventHandler
	public void onWaterBreak(BlockFromToEvent event) {
		if (event.getBlock().getType() == Material.REDSTONE) {
			event.setCancelled(true);
		}
	}

}
