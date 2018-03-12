package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class ClearInventory implements CommandExecutor{

	Main plugin = Main.getPlugin(Main.class);
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.getInventory().clear();
				player.getInventory().setArmorContents(player.getInventory().getArmorContents().clone());
				player.updateInventory();
				player.sendMessage("§eYour inventory has been cleared");
	            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	                @Override
	                public void run() {
	                	Bukkit.getWorld("world").playEffect(((Player) sender).getLocation(), Effect.STEP_SOUND, Material.BEDROCK, 1);
	                }
	            }, 0L, 2);
			}
		}
		return false;
	}
}