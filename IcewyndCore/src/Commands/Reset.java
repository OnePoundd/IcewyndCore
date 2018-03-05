package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import Main.Main;

public class Reset implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reset")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				plugin.getConfig().set(".WitherSkeletons", 0);
				plugin.getConfig().set(".WitherInvin", false);
				plugin.getConfig().set(".WitherPhase", 0);
				plugin.saveConfig();
				player.sendMessage("§eReset Successful");
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lagg killmobs");
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sm removeall");
				Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
				Bukkit.getWorld("world").playEffect(WitherSpawn, Effect.FLAME,0);
				for (Entity entity1 : Bukkit.getWorld("World").getEntities())
					if (entity1.getType() == EntityType.WITHER) {
						entity1.remove();
					}else if (entity1.getType() == EntityType.SKELETON) {
						entity1.remove();
					}
			}
		}
		return false;
	}
}