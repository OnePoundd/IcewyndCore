package Main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Stacker implements Listener{
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (event.getPlayer().getItemInHand().getType() == Material.STONE) {
				Location loc = event.getClickedBlock().getLocation().add(0,1,0);
				while(loc.getY() < 256){
					if(loc.getBlock().getType() == Material.AIR) {
						loc.getBlock().setType(Material.DIAMOND_BLOCK);
						
					}
					break;
				}
			}
		}
	}
}