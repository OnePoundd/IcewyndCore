package Main;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class NoWaterRedstone implements Listener{
	
	@EventHandler
	public void onWaterBreak(BlockFromToEvent event) {
		if (event.getToBlock().getType() == Material.REDSTONE_WIRE) {
			event.setCancelled(true);
		}else if (event.getToBlock().getType() == Material.REDSTONE_COMPARATOR) {
			event.setCancelled(true);
		}else if (event.getToBlock().getType() == Material.REDSTONE_TORCH_OFF) {
			event.setCancelled(true);
		}else if (event.getToBlock().getType() == Material.REDSTONE_LAMP_ON) {
			event.setCancelled(true);
		}
	}

}
