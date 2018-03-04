package Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PVPTimer implements Listener, CommandExecutor {
	Main plugin = Main.getPlugin(Main.class);

	List<Player> PVPTimer = new ArrayList<Player>();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		// if (!event.getPlayer().hasPlayedBefore()) {
		PVPTimer.add(event.getPlayer());
		plugin.getConfig().set(".PVPTimerPlayers", PVPTimer);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("PVPTimer")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 1) {
					if (args[1].equalsIgnoreCase("disable")) {
						PVPTimer.remove(player);
					}
				} else
					player.sendMessage("§cUsage: '/pvptimer disable' - Removed new player protection");
			}
		}
		return false;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			if (((event.getEntity() instanceof Player)) && ((event.getDamager() instanceof Player))) {
				Player p = (Player) event.getEntity();
				Player d = (Player) event.getDamager();
				if (PVPTimer.contains(p)) {
					event.setCancelled(true);
					p.sendMessage("§c§l(!) §cYou have PVPTimer Enabled, disable it using /pvptimer disable");
				}
				if (PVPTimer.contains(d)) {
					event.setCancelled(true);
					d.sendMessage("§c§l(!) §cThat player has PVPTimer enabled, PVP is disabled for them temporarily.");
				}
			}
		}
	}
}