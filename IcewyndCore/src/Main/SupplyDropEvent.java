package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.massivecore.ps.PS;

public class SupplyDropEvent implements CommandExecutor, Listener{
	Main plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("supplydrop")) {
			if (sender instanceof Player) {
				Location player = ((Player) sender).getPlayer().getLocation();
				plugin.getConfig().set(".SupplyDrop", player);
				plugin.saveConfig();
				sender.sendMessage("Location set");
			}
		}
		return false;
	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public static ItemStack SupplyDropItems() {
		int num = new Random().nextInt(20) + 1;
		if (num == 1) {
			return new ItemStack(Material.HOPPER, 16);
		} else if (num == 2) {
			ItemStack Item3 = new ItemStack(Material.BEDROCK, 4);
			ItemMeta Item3Meta = Item3.getItemMeta();
			List<String> lore3 = new ArrayList<String>();
			Item3Meta.setDisplayName("§8§lHardened Bedrock");
			lore3.add("§7Destructible block with 100 block durability!");
			Item3Meta.setLore(lore3);
			Item3.setItemMeta(Item3Meta);
			return Item3;
		} else if (num == 3) {
			ItemStack Item1 = new ItemStack(Material.TRAPPED_CHEST, 1);
			ItemMeta Item1Meta = Item1.getItemMeta();
			List<String> lore1 = new ArrayList<String>();
			Item1Meta.setDisplayName("§c§l§nCrate of TNT");
			lore1.add("§7Small box full of TNT!");
			Item1Meta.setLore(lore1);
			Item1.setItemMeta(Item1Meta);
			return Item1;
		} else if (num == 4) {
			ItemStack Item5 = new ItemStack(Material.MOB_SPAWNER, 1);
			ItemMeta Item5Meta = Item5.getItemMeta();
			List<String> lore5 = new ArrayList<String>();
			Item5Meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
			Item5Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			Item5Meta.setDisplayName("§d§l§nMystery Spawner");
			lore5.add("§7Rewards the player with 1 random spawner out of 5");
			Item5Meta.setLore(lore5);
			Item5.setItemMeta(Item5Meta);
			return Item5;
		} else if (num == 5) {
			ItemStack Item4 = new ItemStack(Material.MONSTER_EGG, 1);
			ItemMeta Item4Meta = Item4.getItemMeta();
			List<String> lore4 = new ArrayList<String>();
			Item4.setDurability(EntityType.CREEPER.getTypeId());
			Item4Meta.setLore(lore4);
			Item4.setItemMeta(Item4Meta);
			return Item4;
		} else if (num == 6) {
			return new ItemStack(Material.BEACON, 1);
		} else if (num == 7) {
			return new ItemStack(Material.DIAMOND_BLOCK, 16);
		} else if (num == 8) {
			ItemStack Item6 = new ItemStack(Material.PAPER);
			ItemMeta Item6Meta = Item6.getItemMeta();
			Item6Meta.setDisplayName("§6100 McMMO Credits");
			ArrayList<String> Item6Lore = new ArrayList<String>();
			Item6Lore.add("§7Right click this ticket for 100 mcmmo credits!");
			Item6Meta.setLore(Item6Lore);
			Item6.setItemMeta(Item6Meta);
			return Item6;
		} else if (num == 9) {
			return new ItemStack(Material.AIR);
		} else if (num == 10) {
			return new ItemStack(Material.AIR);
		} else if (num == 11) {
			return new ItemStack(Material.AIR);
		} else if (num == 12) {
			return new ItemStack(Material.AIR);
		} else if (num == 13) {
			return new ItemStack(Material.AIR);
		} else if (num == 14) {
			return new ItemStack(Material.AIR);
		} else if (num == 15) {
			return new ItemStack(Material.AIR);
		} else if (num == 16) {
			return new ItemStack(Material.AIR);
		} else if (num == 17) {
			return new ItemStack(Material.AIR);
		} else if (num == 18) {
			return new ItemStack(Material.AIR);
		} else if (num == 19) {
			ItemStack Item3 = new ItemStack(Material.PAPER);
			ItemMeta Item3Meta = Item3.getItemMeta();
			Item3Meta.setDisplayName("§6$150,000 Cash");
			ArrayList<String> Item3Lore = new ArrayList<String>();
			Item3Lore.add("§7Right click this ticket for $150,000!");
			Item3Meta.setLore(Item3Lore);
			Item3.setItemMeta(Item3Meta);
			return Item3;
		} else if (num == 20) {
			ItemStack Item18 = new ItemStack(Material.BOOK, 3);
			ItemMeta Item18Meta = Item18.getItemMeta();
			Item18Meta.setDisplayName("§5Legendary Book");
			ArrayList<String> Item18Lore = new ArrayList();
			Item18Lore.add("§7Right click to open the book!");
			Item18Meta.setLore(Item18Lore);
			Item18.setItemMeta(Item18Meta);
			return Item18;
		}
		return new ItemStack(Material.AIR);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onbreak(BlockBreakEvent event) {
		if(BoardColl.get().getFactionAt(PS.valueOf(event.getBlock())).getName().equalsIgnoreCase("Warzone")) {
			if (event.getBlock().getType() == Material.TRAPPED_CHEST) {
				if (event.getBlock().getLocation().add(0,1,0).getBlock().getType() == Material.OBSIDIAN) {
					event.setCancelled(true);
					event.getPlayer().sendMessage("§cYou must destroy the Obsidian to claim the Supply Drop!");
				}
			} else if (event.getBlock().getType() == Material.OBSIDIAN){
				Bukkit.broadcastMessage("§a§lSUPPLYDROP§7 » §bThe Supply Drop was claimed by " + event.getPlayer().getName() + "!");
				Bukkit.getScheduler().cancelTask(Main.SupplyDropTask);
				event.getBlock().setType(Material.AIR);
				
			}
		}
	}
}
