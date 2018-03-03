package Main;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
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

	@EventHandler
	public void onBreed(EntityBreedEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onspawn(EntitySpawnEvent e) {
		if (e.getEntityType() == EntityType.ENDERMITE) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event) {
		LivingEntity entity = event.getEntity();
		noAI(entity);
	}
}
