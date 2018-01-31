package McMMO;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.skills.repair.McMMOPlayerRepairCheckEvent;

public class Repair implements Listener {

	// prevents using the mcmmo anvil
	@EventHandler
	public void onPlayerRepairActivity(McMMOPlayerRepairCheckEvent event) {
		event.setCancelled(true);
	}

	// adds our own way of gaining repair xp
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onAnvilRepair(InventoryClickEvent e) {
		if (!e.isCancelled()) {
			HumanEntity ent = e.getWhoClicked();
			if (ent instanceof Player) {
				Player player = (Player) ent;
				Inventory inv = e.getInventory();
				if (inv instanceof AnvilInventory) {
					AnvilInventory anvil = (AnvilInventory) inv;
					int rawSlot = e.getRawSlot();
					if (rawSlot == 2) {
						ItemStack[] items = anvil.getContents();
						ItemStack item1 = items[0];
						ItemStack item2 = items[1];
						ItemStack item3 = e.getCurrentItem();
						if (item1 != null && item2 != null && item3 != null) {
							int id1 = item1.getTypeId();
							int id3 = item3.getTypeId();
							if (id1 != 0 && id1 == id3) {
								ItemMeta meta = item3.getItemMeta();
								if (meta != null) {
									if (meta instanceof Repairable) {
										Repairable repairable = (Repairable) meta;
										int repairCost = repairable.getRepairCost();
										if (player.getLevel() >= repairCost) {
											// ANVIL FIX EVENT...
											int xp = anvil.getRepairCost() * 500;
											ExperienceAPI.addRawXP(player, SkillType.REPAIR.getName(), xp);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
