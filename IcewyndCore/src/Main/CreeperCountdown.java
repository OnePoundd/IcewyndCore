package Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class CreeperCountdown implements Listener {
	Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	// makes a timer appear above the creepers head when ignited with flint and steel
	public void onCreeperIgniteEvent(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Creeper) {
			Creeper creeper = (Creeper) event.getRightClicked();
			if (event.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) {
				String name = creeper.getName();
				if (name.equals("Creeper")) {
					event.setCancelled(true);
					creeper.setCustomName("§a§l***§b§l3.0§a§l***");
					final int task;
					task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
						double timer = 3.0;

						@Override
						public void run() {
							timer = timer - 0.1;
							String timerString = Double.toString(timer).substring(0, 3);
							if (timer >= 2.1) {
								creeper.setCustomName("§a§l***§b§l " + timerString + " §a§l***");
							} else if (timer >= 1.1) {
								creeper.setCustomName("§6§l***§b§l " + timerString + " §6§l***");
							} else if (timer >= 0.1) {
								creeper.setCustomName("§c§l***§b§l " + timerString + " §c§l***");
							} else {
								if (!creeper.isDead()) {
									creeper.setCustomName("§4§l***§b§l 0.0 §4§l***");
									creeper.damage(1000);
									creeper.getLocation().getWorld().createExplosion(creeper.getLocation(), 3F);
								}
							}
						}
					}, 0L, 2);

					Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
						public void run() {
							Bukkit.getScheduler().cancelTask(task);
						}
					}, 58);
				} else if (name.contains("§l***")) {
					event.setCancelled(true);
				}
			}
		}
	}
}

						}
					}, 58);
				} else if (name.contains("§l***")) {
					event.setCancelled(true);
				}
			}
		}
	}
}
