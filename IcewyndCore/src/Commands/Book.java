package Commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftMetaBook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;

public class Book implements CommandExecutor{
Main plugin = Main.getPlugin(Main.class);

	@SuppressWarnings("unchecked")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("book")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta bookMeta = (BookMeta) book.getItemMeta();
				List<IChatBaseComponent> pages;

				//get the pages
				try {
				    pages = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(bookMeta);
				} catch (ReflectiveOperationException ex) {
				    ex.printStackTrace();
				    return false;
				}

				TextComponent text = new TextComponent("§8§l> §9§lICEWYND FACTIONS \n §8§l§m---------------- \n Use this guide to get informed on server features! \n \n §rPage 2 §7-§9 Features \n §rPage 3 §7-§9 Rules ");
				text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://spigotmc.org"));
				text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Goto the spigot website!").create()));

				IChatBaseComponent page = ChatSerializer.a(ComponentSerializer.toString(text));
				TextComponent text2 = new TextComponent("§9§lCustom/Optimized: \nCoded almost completely from the ground up. \n§9§lOptimized PVP: \nResponsive potions and hit registration. \n§9§lTalent Trees: \nEarn ingame features for playing! \n§9§lConstant Events: \n24/7 Castle, Koths, and 5+ other events. \n§8§l§m------- >§r§c§l NEXT PAGE");
				IChatBaseComponent page2 = ChatSerializer.a(ComponentSerializer.toString(text2));
				pages.add(page);
				pages.add(page2);

				bookMeta.setTitle("§a§lNew Player Guide");
				bookMeta.setAuthor("§fICEWYND §bNETWORK");
				book.setItemMeta(bookMeta);
				player.getInventory().addItem(book);
				}
			}
		return false;
		}
	}
