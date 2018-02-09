package Main;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class DisguiseBuffs implements Listener{
	
	/**
	 *  UNLIMITED POTION EFFECTS ARE APPLIED WITHIN THE CUSTOM ENCHANTS "ENCHANTMENT" CLASS (updateEnchantBuffs() method)
	 */
	
	//ENDERMAN
	@EventHandler
	public void onPlayerTeleportEvent(PlayerTeleportEvent event){
		//gives the player back their ender-pearl if they are disguises as an enderman!
		Player player = event.getPlayer();
		if(DisguiseAPI.isDisguised(player)){
			if(event.getCause().equals(TeleportCause.ENDER_PEARL) && DisguiseAPI.getDisguise(player).getType().equals(DisguiseType.ENDERMAN)){
				player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
				player.sendMessage("§3You are an §5enderman§6 and regenerated the pearl!");
			}
		}
	}
	
	//HORSE
	@EventHandler
	public void onPlayerInteractPlayer(PlayerInteractEntityEvent event){
    	if(event.getRightClicked().getType().equals(EntityType.PLAYER)){
    	    Player player = event.getPlayer();
    	    Player rightClickedPlayer = (Player) event.getRightClicked();
    	    if(DisguiseAPI.isDisguised(rightClickedPlayer)){
        	    if(DisguiseAPI.getDisguise(rightClickedPlayer).getType().equals(DisguiseType.HORSE)){
        	    	rightClickedPlayer.addPassenger(player);
        	    	player.sendMessage("§6You just mounted, §b" + rightClickedPlayer.getName() + "§6!");
        	    	rightClickedPlayer.sendMessage("§b" + player.getName() + "§6, just mounted you! Type §b/und §6to kick them off!");
        	    }
    	    }
    	}
    }
	
	//CHICKEN, COW, PIG
	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent event){
		if(event.getEntity().getType().equals(EntityType.PLAYER)){
			Player player = (Player) event.getEntity();
			if(DisguiseAPI.isDisguised(player)) {
				DisguiseType type = DisguiseAPI.getDisguise(player).getType();
				if(type.equals(DisguiseType.CHICKEN) || type.equals(DisguiseType.PIG) || type.equals(DisguiseType.COW)){
					event.setCancelled(true);
				}
			}
		}
	}
	
	//CHICKEN
	@EventHandler
    public void onEntityDamaged(EntityDamageEvent event){
    	Entity damaged = event.getEntity();
    	if(damaged.getType().equals(EntityType.PLAYER) && DisguiseAPI.isDisguised((Player) damaged) && event.getCause().equals(DamageCause.FALL)){
    		if(DisguiseAPI.getDisguise((Player) damaged).getType().equals(DisguiseType.CHICKEN)){
    			event.setCancelled(true);
    			((Player) damaged).sendMessage("§6Being a §fchicken§6 allowed you to absorb the fall!");
    		}
    	}
	}
	
	//BLAZE
	@EventHandler
	public void onPlayerInterract(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(DisguiseAPI.isDisguised(event.getPlayer()) && DisguiseAPI.getDisguise(event.getPlayer()).getType().equals(DisguiseType.BLAZE)) {
				if(event.getPlayer().getItemInHand().getType().equals(Material.BLAZE_ROD)) {
					event.getPlayer().launchProjectile(Fireball.class);
					event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() -1);
				}
			}
		}
	}
	
}
