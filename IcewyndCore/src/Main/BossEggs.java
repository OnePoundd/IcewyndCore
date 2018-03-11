package Main;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BossEggs implements Listener {

	/*@EventHandler
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
				Item5Meta.setDisplayName("§c§lPlagued Skeleton");
				lore5.add("§1");
				lore5.add("§c§lRare §7§lboss egg that can be summoned in the Warzone.");
				lore5.add("§7§lSlay this boss to be rewarded with a prize!");
				lore5.add("§1");
				lore5.add("§c§lDifficulty: §75 Players");
				Item5Meta.setLore(lore5);
				Item5.setItemMeta(Item5Meta);
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), Item5);
			}
		}
	}*/

	@EventHandler
	public void ondeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();

		if (entity instanceof Witch) {
			ItemStack Item1 = new ItemStack(Material.MONSTER_EGG, 1, (short)66);
			ItemMeta Item1Meta = Item1.getItemMeta();
			List<String> lore1 = new ArrayList<String>();
			Item1Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
			Item1Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			Item1Meta.setDisplayName("§5§l§nCorrupted Witch");
			lore1.add("§cRare §7boss egg that can be summoned in the Warzone.");
			lore1.add("§7Slay the witch to be rewarded with a prize!");
			lore1.add("§1");
			lore1.add("§c§lDifficulty: §73 Players");
			Item1Meta.setLore(lore1);
			Item1.setItemMeta(Item1Meta);
			event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), Item1);
		}
		else if (entity instanceof Skeleton) {
			ItemStack Item5 = new ItemStack(Material.MONSTER_EGG, 1, (short)58);
			ItemMeta Item5Meta = Item5.getItemMeta();
			List<String> lore5 = new ArrayList<String>();
			Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
			Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			Item5Meta.setDisplayName("§d§kiii§r §c§l§nPlagued Skeleton§r §d§kiii");
			lore5.add("§c§lRare §7boss egg that can be summoned in the Warzone.");
			lore5.add("§7Slay the skeleton to be rewarded with a prize!");
			lore5.add("§1");
			lore5.add("§c§lDifficulty: §75 Players");
			Item5Meta.setLore(lore5);
			Item5.setItemMeta(Item5Meta);
			event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), Item5);
		}
	}

}