package Main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class Cancels implements Listener {
	
	public void noAI(org.bukkit.entity.Entity bukkitEntity)
	{
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);
	}

	//@EventHandler
	//public void onBreed(EntityBreedEvent e) {
		//e.setCancelled(true);
	//}
	
	@EventHandler
	public void onspawn(EntitySpawnEvent e) {
		if (e.getEntityType() == EntityType.ENDERMITE) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event) {
		if (!(event.getEntityType() == EntityType.WITHER)) {
			LivingEntity entity = event.getEntity();
			noAI(entity);
		}
	}
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK)
            return;
       
        Block b = e.getClickedBlock();
       
        if (b.getType() != Material.MOB_SPAWNER)
            return;
    }
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onSpread(BlockSpreadEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent event) {
		event.setCancelled(true);
	}
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent event) {
		if (event.getEntity() instanceof Enderman) {
			event.setCancelled(true);
		}
	}
	@EventHandler
    public void snow(EntityBlockFormEvent event) {
        if (event.getEntity() instanceof Snowman) {
            if (event.getNewState().getType() == Material.SNOW) {
                event.setCancelled(true);
            }
        }
	}
	@EventHandler
	public void enderdragonDamage(EntityExplodeEvent event) {
		if (event.getEntity() instanceof Wither) {
			event.blockList().clear();
		}
		else if (event.getEntity() instanceof EnderDragon) {
			event.blockList().clear();
		}
		else if (event.getEntity() instanceof WitherSkull) {
			event.blockList().clear();
		}
	}
}