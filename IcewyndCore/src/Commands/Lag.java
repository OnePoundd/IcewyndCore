package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import Main.Main;
import Main.TPS;

public class Lag implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("lag")) {
			if (sender instanceof Player) {
				double tps = TPS.getTPS();
				sender.sendMessage("Well the tps is; " + Math.round(tps * 100.0D) / 100.0D);
				sender.sendMessage("   §bTPS:§7 " + tps);
				sender.sendMessage("   §bEntities:§7 ");
				sender.sendMessage("   §bPlayers:§7 " + plugin.getConfig().getInt(".PlayersOnline"));
				sender.sendMessage(("   §bAllocated Memory:§7 " + (Runtime.getRuntime().totalMemory() / 1024 / 1024)));
				sender.sendMessage(("   §bFree Memory:§7 " + (Runtime.getRuntime().freeMemory() / 1024 / 1024)));
			}
		}
		return false;
	}
}