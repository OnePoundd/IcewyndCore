package Commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import Main.Main;

public class ClearInventory implements CommandExecutor{
	Main plugin = Main.getPlugin(Main.class);

    public static String calculateTime(Long timeIn){
        int day = (int) TimeUnit.MILLISECONDS.toDays(timeIn);
        long hours = TimeUnit.MILLISECONDS.toHours(timeIn) - (day * 24);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeIn) - (TimeUnit.MILLISECONDS.toHours(timeIn) * 60);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeIn) - (TimeUnit.MILLISECONDS.toMinutes(timeIn) * 60);
   
        String time = String.valueOf(day) + " Days " + String.valueOf(hours) + " Hours " +
                String.valueOf(minutes) + " Minutes " + String.valueOf(seconds) + " Seconds.";
   
        return time;
      }
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack[] armorContents = player.getInventory().getArmorContents().clone();
				player.getInventory().clear();
				player.getInventory().setArmorContents(armorContents);
				player.updateInventory();
				player.sendMessage("§eYour inventory has been cleared");
			    Date now = new Date();
			    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm");
				player.sendMessage(format.format(now));
				}
			}
		return false;
		}
	}
