package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Main.Main;

public class NightVision implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("nightvision")) {
			Player player = (Player) sender;
			if (sender instanceof Player) {
				if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
					player.removePotionEffect(PotionEffectType.NIGHT_VISION);
					player.sendMessage("§cNightVision Disabled!");
				}else {
					player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
					player.sendMessage("§aNightVision Enabled!");
					}
				}
			}
		return false;
}
}