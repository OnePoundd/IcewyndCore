package Main;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class SellWands implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && (event.getItem() != null)
		&& (event.getItem().hasItemMeta()) && (event.getItem().getItemMeta().hasDisplayName())
		&& (event.getItem().getItemMeta().getDisplayName().equals("§c§lSell Wand"))
		&& ((event.getClickedBlock().getType().equals(Material.CHEST))
		|| (event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)))) {
			if (BoardColl.get().getFactionAt(PS.valueOf(event.getClickedBlock().getLocation()))
			.equals(MPlayer.get(event.getPlayer()).getFaction())) {
				Chest chest = (Chest) event.getClickedBlock().getState();
				ItemStack[] chestcontents = chest.getInventory().getContents();
				
				double totalValue = 0;
				for(ItemStack item : chestcontents) {
					if(item != null) {
						double value = Main.pricesConfig.getDouble(String.valueOf(item.getTypeId())+ ".Sell") * item.getAmount();
						if(value > 0) {
							item.setAmount(0);
							totalValue = totalValue + value;
						}else {
							player.getLocation().getWorld().dropItem(player.getLocation(), item);
							item.setAmount(0);
						}
					}
				}
				if(MPlayer.get(player).getFaction().getOwnsCastle()) {
					totalValue = totalValue * 1.1;
				}
				Main.econ.depositPlayer(player, totalValue);
				player.sendMessage("§a§l(!)§7 You sold all your sellable items for §a$" + totalValue);
				
				event.getPlayer().performCommand("sell all");
				event.setCancelled(true);
			} else {
				event.getPlayer().sendMessage("§c§l(!)§7 You cannot use sell wands on other faction's chests!");
			}
		}
	}
	
}
