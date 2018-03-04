package Main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
}