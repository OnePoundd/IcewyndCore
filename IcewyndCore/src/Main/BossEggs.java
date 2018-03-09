package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BossEggs implements Listener {

	@EventHandler
	public void ondeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		
		if(entity instanceof Skeleton || entity instanceof Zombie || entity instanceof PigZombie || entity instanceof Villager ||
		entity instanceof Enderman || entity instanceof Witch || entity instanceof Blaze || entity instanceof Creeper) {
			
			int num = new Random().nextInt(1500);
			if(num == 1500) {
				ItemStack Item5 = new ItemStack(Material.MONSTER_EGG, 1, (short)50);
				ItemMeta Item5Meta = Item5.getItemMeta();
				List<String> lore5 = new ArrayList<String>();
				Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
				Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				Item5Meta.setDisplayName("�c�lPlagued Skeleton");
				lore5.add("�1");
				lore5.add("�c�lRare �7�lboss egg that can be summoned in the Warzone.");
				lore5.add("�7�lSlay this boss to be rewarded with a prize!");
				lore5.add("�1");
				lore5.add("�c�lDifficulty: �75 Players");
				Item5Meta.setLore(lore5);
				Item5.setItemMeta(Item5Meta);
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), Item5);
			}
		}
	}
}