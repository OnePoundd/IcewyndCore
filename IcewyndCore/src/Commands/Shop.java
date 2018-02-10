package Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;


import Main.Main;

public class Shop implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("shop")) {
			Player player = (Player) sender;
			openHome(player);
		}
		return false;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getClickedInventory() != null) {
			String category = event.getClickedInventory().getName();
			if(category.equals("§c§l>> §8Shop §c§l<<")) {
				event.setCancelled(true);
				if(event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
					String name = event.getCurrentItem().getItemMeta().getDisplayName();
					if(name.equals("§b§lBlocks")) {
						openBlocks(player);
					}else if(name.equals("§b§lMaterials")) {
						openMaterials(player);
					}else if(name.equals("§b§lBrewing")) {
						openBrewing(player);
					}else if(name.equals("§b§lRedstone")) {
						openRedstone(player);
					}else if(name.equals("§b§lFood/Farming")) {
						openFoodFarming(player);
					}else if(name.equals("§b§lMiscellaneous")) {
						openMiscellaneous(player);
					}else if(name.equals("§b§lSpawners")) {
						openSpawners(player);
					}
				}
			}else if(category.equals("§c§l>> §8Blocks §c§l<<") || category.equals("§c§l>> §8Materials §c§l<<") || category.equals("§c§l>> §8Brewing §c§l<<")
			|| category.equals("§c§l>> §8Redstone §c§l<<") || category.equals("§c§l>> §8Food/Farming §c§l<<") || category.equals("§c§l>> §8Miscellaneous §c§l<<")) {
				event.setCancelled(true);
				ItemStack item = event.getCurrentItem();
				if(item != null && item.hasItemMeta()) {
					if(item.getItemMeta().hasDisplayName()) {
						String name = item.getItemMeta().getDisplayName();
						if(name.equals("§c§lBack")) {
							openHome(player);
						}
					}else {
						if(player.getInventory().firstEmpty() != -1) {
							double cost = Main.pricesConfig.getDouble(String.valueOf(item.getTypeId())+ ".Buy");
							int amount = 1;
							if(event.isRightClick()) {
								try {
									amount = Integer.valueOf(item.getItemMeta().getLore().get(2).split("§8§l » §7")[1].split(" ")[0]);
								}catch (Exception e) {}
							}
							cost = cost * amount;
							if(Main.econ.getBalance(player) >= cost) {
								Main.econ.withdrawPlayer(player, cost);
								ItemStack itemToGive = new ItemStack(item.getType());
								itemToGive.setDurability(item.getDurability());
								itemToGive.setAmount(amount);
								player.getInventory().addItem(itemToGive);
								player.sendMessage("§a§l(!)§7 You purchased " + amount + " " + Main.pricesConfig.getString(String.valueOf(item.getTypeId())+ ".Name") + " for $" + cost);
							}else {
								player.sendMessage("§c§l(!)§7 You cannot afford to make that purchase!");
							}
						}else {
							player.sendMessage("§c§l(!)§7 Your inventory is full, remove some items to make space!");
						}
					}
				}
			}else if(category.equals("§c§l>> §8Spawners §c§l<<")) {
				event.setCancelled(true);
				ItemStack item = event.getCurrentItem();
				if(item != null && item.hasItemMeta()) {
					if(item.getItemMeta().hasDisplayName()) {
						String name = item.getItemMeta().getDisplayName();
						if(name.equals("§c§lBack")) {
							openHome(player);
						}else {
							double cost = 0;
							if(name.equals("§eCreeper §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("CreeperSpawner.Buy"));
							}else if(name.equals("§eZombie Pigman §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("PigmanSpawner.Buy"));
							}else if(name.equals("§eWitch §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("WitchSpawner.Buy"));
							}else if(name.equals("§eEnderman §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("EndermanSpawner.Buy"));
							}else if(name.equals("§eBlaze §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("BlazeSpawner.Buy"));
							}else if(name.equals("§eSquid §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("SquidSpawner.Buy"));
							}else if(name.equals("§eMagma Cube §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("MagmaCubeSpawner.Buy"));
							}else if(name.equals("§eSpider §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("SpiderSpawner.Buy"));
							}else if(name.equals("§eZombie §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("ZombieSpawner.Buy"));
							}else if(name.equals("§eSkeleton §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("SkeletonSpawner.Buy"));
							}else if(name.equals("§ePig §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("PigSpawner.Buy"));
							}else if(name.equals("§eCow §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("CowSpawner.Buy"));
							}else if(name.equals("§eChicken §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("ChickenSpawner.Buy"));
							}else if(name.equals("§eSheep §fSpawner")) {
								cost = Main.pricesConfig.getDouble(String.valueOf("SheepSpawner.Buy"));
							}
							
							if(player.getInventory().firstEmpty() != -1) {
								int amount = 1;
								if(event.isRightClick()) {
									try {
										amount = Integer.valueOf(item.getItemMeta().getLore().get(2).split("§8§l » §7")[1].split(" ")[0]);
									}catch (Exception e) {}
								}
								cost = cost * amount;
								if(Main.econ.getBalance(player) >= cost) {
									Main.econ.withdrawPlayer(player, cost);
									ItemStack itemToGive = new ItemStack(item.getType());
									System.out.println(item.getItemMeta().getDisplayName());
									ItemMeta itemToGiveMeta = itemToGive.getItemMeta();
									itemToGiveMeta.setDisplayName(item.getItemMeta().getDisplayName());
									itemToGive.setItemMeta(itemToGiveMeta);
									itemToGive.setAmount(amount);
									player.getInventory().addItem(itemToGive);
									player.sendMessage("§a§l(!)§7 You purchased " + amount + " monster spawners for $" + cost);
								}else {
									player.sendMessage("§c§l(!)§7 You cannot afford to make that purchase!");
								}
							}else {
								player.sendMessage("§c§l(!)§7 Your inventory is full, remove some items to make space!");
							}
						}
					}
				}
			}
		}
	}
	
	public void openHome(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 9, "§c§l>> §8Shop §c§l<<");
		ItemStack Blocks = new ItemStack(Material.STONE);
		ItemStack Materials = new ItemStack(Material.DIAMOND);
		ItemStack Brewing = new ItemStack(Material.BREWING_STAND_ITEM);
		ItemStack Redstone = new ItemStack(Material.REDSTONE);
		ItemStack Food = new ItemStack(Material.COOKED_BEEF);
		ItemStack Miscellaneous = new ItemStack(Material.BEACON);
		ItemStack Spawners = new ItemStack(Material.MOB_SPAWNER);	

		ItemMeta BlocksMeta = Blocks.getItemMeta();
		BlocksMeta.setDisplayName("§b§lBlocks");
		Blocks.setItemMeta(BlocksMeta);
		
		ItemMeta MaterialsMeta = Materials.getItemMeta();
		MaterialsMeta.setDisplayName("§b§lMaterials");
		Materials.setItemMeta(MaterialsMeta);
		
		ItemMeta BrewingMeta = Brewing.getItemMeta();
		BrewingMeta.setDisplayName("§b§lBrewing");
		Brewing.setItemMeta(BrewingMeta);
		
		ItemMeta RedstoneMeta = Redstone.getItemMeta();
		RedstoneMeta.setDisplayName("§b§lRedstone");
		Redstone.setItemMeta(RedstoneMeta);

		ItemMeta FoodMeta = Food.getItemMeta();
		FoodMeta.setDisplayName("§b§lFood/Farming");
		Food.setItemMeta(FoodMeta);
		
		ItemMeta MiscellaneousMeta = Miscellaneous.getItemMeta();
		MiscellaneousMeta.setDisplayName("§b§lMiscellaneous");
		Miscellaneous.setItemMeta(MiscellaneousMeta);
		
		ItemMeta SpawnersMeta = Spawners.getItemMeta();
		SpawnersMeta.setDisplayName("§b§lSpawners");
		Spawners.setItemMeta(SpawnersMeta);
		
		GUI.setItem(0, Blocks);
		GUI.setItem(1, Materials);
		GUI.setItem(2, Brewing);
		GUI.setItem(3, Redstone);
		GUI.setItem(4, Food);
		GUI.setItem(5, Miscellaneous);
		GUI.setItem(6, Spawners);
		player.openInventory(GUI);
	}
	
	@SuppressWarnings("deprecation")
	public void openBlocks(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Blocks §c§l<<");
		
		ItemStack Stone = new ItemStack(Material.STONE);	
		ItemMeta StoneMeta = Stone.getItemMeta();
		ArrayList<String> StoneLore = new ArrayList<String>();
		StoneLore.add("§e§lCost:");
		StoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Stone.getTypeId())+ ".Buy")) + " (left click)");
		StoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Stone.getTypeId())+ ".Buy") * 64) + " (right click)");
		StoneMeta.setLore(StoneLore);
		Stone.setItemMeta(StoneMeta);
		GUI.setItem(0, Stone);
		
		ItemStack Grass = new ItemStack(Material.GRASS);
		ItemMeta GrassMeta = Grass.getItemMeta();
		ArrayList<String> GrassLore = new ArrayList<String>();
		GrassLore.add("§e§lCost:");
		GrassLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Grass.getTypeId())+ ".Buy")) + " (left click)");
		GrassLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Grass.getTypeId())+ ".Buy") * 64) + " (right click)");
		GrassMeta.setLore(GrassLore);
		Grass.setItemMeta(GrassMeta);
		GUI.setItem(1, Grass);
		
		ItemStack Dirt = new ItemStack(Material.DIRT);
		ItemMeta DirtMeta = Dirt.getItemMeta();
		ArrayList<String> DirtLore = new ArrayList<String>();
		DirtLore.add("§e§lCost:");
		DirtLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Dirt.getTypeId())+ ".Buy")) + " (left click)");
		DirtLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Dirt.getTypeId())+ ".Buy") * 64) + " (right click)");
		DirtMeta.setLore(DirtLore);
		Dirt.setItemMeta(DirtMeta);
		GUI.setItem(2, Dirt);
		
		ItemStack Cobblestone = new ItemStack(Material.COBBLESTONE);
		ItemMeta CobblestoneMeta = Cobblestone.getItemMeta();
		ArrayList<String> CobblestoneLore = new ArrayList<String>();
		CobblestoneLore.add("§e§lCost:");
		CobblestoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Cobblestone.getTypeId())+ ".Buy")) + " (left click)");
		CobblestoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Cobblestone.getTypeId())+ ".Buy") * 64) + " (right click)");
		CobblestoneMeta.setLore(CobblestoneLore);
		Cobblestone.setItemMeta(CobblestoneMeta);
		GUI.setItem(3, Cobblestone);
		
		ItemStack Sand = new ItemStack(Material.SAND);
		ItemMeta SandMeta = Sand.getItemMeta();
		ArrayList<String> SandLore = new ArrayList<String>();
		SandLore.add("§e§lCost:");
		SandLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sand.getTypeId())+ ".Buy")) + " (left click)");
		SandLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sand.getTypeId())+ ".Buy") * 64) + " (right click)");
		SandMeta.setLore(SandLore);
		Sand.setItemMeta(SandMeta);
		GUI.setItem(4, Sand);
		
		ItemStack RedSand = new ItemStack(Material.SAND);
		RedSand.setDurability((short) 1);
		ItemMeta RedSandMeta = RedSand.getItemMeta();
		ArrayList<String> RedSandLore = new ArrayList<String>();
		RedSandLore.add("§e§lCost:");
		RedSandLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedSand.getTypeId())+ ".Buy")) + " (left click)");
		RedSandLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedSand.getTypeId())+ ".Buy") * 64) + " (right click)");
		RedSandMeta.setLore(RedSandLore);
		RedSand.setItemMeta(RedSandMeta);
		GUI.setItem(5, RedSand);
		
		ItemStack Gravel = new ItemStack(Material.GRAVEL);
		ItemMeta GravelMeta = Gravel.getItemMeta();
		ArrayList<String> GravelLore = new ArrayList<String>();
		GravelLore.add("§e§lCost:");
		GravelLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gravel.getTypeId())+ ".Buy")) + " (left click)");
		GravelLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gravel.getTypeId())+ ".Buy") * 64) + " (right click)");
		GravelMeta.setLore(GravelLore);
		Gravel.setItemMeta(GravelMeta);
		GUI.setItem(6, Gravel);
		
		ItemStack OakWood = new ItemStack(Material.LOG);
		ItemMeta OakWoodMeta = OakWood.getItemMeta();
		ArrayList<String> OakWoodLore = new ArrayList<String>();
		OakWoodLore.add("§e§lCost:");
		OakWoodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(OakWood.getTypeId())+ ".Buy")) + " (left click)");
		OakWoodLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(OakWood.getTypeId())+ ".Buy") * 64) + " (right click)");
		OakWoodMeta.setLore(OakWoodLore);
		OakWood.setItemMeta(OakWoodMeta);
		GUI.setItem(7, OakWood);
		
		ItemStack SpruceWood = new ItemStack(Material.LOG);
		SpruceWood.setDurability((short) 1);
		ItemMeta SpruceWoodMeta = SpruceWood.getItemMeta();
		ArrayList<String> SpruceWoodLore = new ArrayList<String>();
		SpruceWoodLore.add("§e§lCost:");
		SpruceWoodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SpruceWood.getTypeId())+ ".Buy")) + " (left click)");
		SpruceWoodLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(SpruceWood.getTypeId())+ ".Buy") * 64) + " (right click)");
		SpruceWoodMeta.setLore(SpruceWoodLore);
		SpruceWood.setItemMeta(SpruceWoodMeta);
		GUI.setItem(8, SpruceWood);
		
		ItemStack BirchWood = new ItemStack(Material.LOG);
		BirchWood.setDurability((short) 2);
		ItemMeta BirchWoodMeta = BirchWood.getItemMeta();
		ArrayList<String> BirchWoodLore = new ArrayList<String>();
		BirchWoodLore.add("§e§lCost:");
		BirchWoodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(BirchWood.getTypeId())+ ".Buy")) + " (left click)");
		BirchWoodLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(BirchWood.getTypeId())+ ".Buy") * 64) + " (right click)");
		BirchWoodMeta.setLore(BirchWoodLore);
		BirchWood.setItemMeta(BirchWoodMeta);
		GUI.setItem(9, BirchWood);
		
		ItemStack JungleWood = new ItemStack(Material.LOG);
		JungleWood.setDurability((short) 3);
		ItemMeta JungleWoodMeta = JungleWood.getItemMeta();
		ArrayList<String> JungleWoodLore = new ArrayList<String>();
		JungleWoodLore.add("§e§lCost:");
		JungleWoodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(JungleWood.getTypeId())+ ".Buy")) + " (left click)");
		JungleWoodLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(JungleWood.getTypeId())+ ".Buy") * 64) + " (right click)");
		JungleWoodMeta.setLore(JungleWoodLore);
		JungleWood.setItemMeta(JungleWoodMeta);
		GUI.setItem(10, JungleWood);
		
		ItemStack AcaciaWood = new ItemStack(Material.LOG_2);
		ItemMeta AcaciaWoodMeta = AcaciaWood.getItemMeta();
		ArrayList<String> AcaciaWoodLore = new ArrayList<String>();
		AcaciaWoodLore.add("§e§lCost:");
		AcaciaWoodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(AcaciaWood.getTypeId())+ ".Buy")) + " (left click)");
		AcaciaWoodLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(AcaciaWood.getTypeId())+ ".Buy") * 64) + " (right click)");
		AcaciaWoodMeta.setLore(AcaciaWoodLore);
		AcaciaWood.setItemMeta(AcaciaWoodMeta);
		GUI.setItem(11, AcaciaWood);
		
		ItemStack DarkOakWood = new ItemStack(Material.LOG_2);
		DarkOakWood.setDurability((short) 1);
		ItemMeta DarkOakWoodMeta = DarkOakWood.getItemMeta();
		ArrayList<String> DarkOakWoodLore = new ArrayList<String>();
		DarkOakWoodLore.add("§e§lCost:");
		DarkOakWoodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DarkOakWood.getTypeId())+ ".Buy")) + " (left click)");
		DarkOakWoodLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(DarkOakWood.getTypeId())+ ".Buy") * 64) + " (right click)");
		DarkOakWoodMeta.setLore(DarkOakWoodLore);
		DarkOakWood.setItemMeta(DarkOakWoodMeta);
		GUI.setItem(12, DarkOakWood);
		
		ItemStack Sandstone = new ItemStack(Material.SANDSTONE);
		ItemMeta SandstoneMeta = Sandstone.getItemMeta();
		ArrayList<String> SandstoneLore = new ArrayList<String>();
		SandstoneLore.add("§e§lCost:");
		SandstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sandstone.getTypeId())+ ".Buy")) + " (left click)");
		SandstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sandstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		SandstoneMeta.setLore(SandstoneLore);
		Sandstone.setItemMeta(SandstoneMeta);
		GUI.setItem(13, Sandstone);
		
		ItemStack ChiseledSandstone = new ItemStack(Material.SANDSTONE);
		ChiseledSandstone.setDurability((short) 1);
		ItemMeta ChiseledSandstoneMeta = ChiseledSandstone.getItemMeta();
		ArrayList<String> ChiseledSandstoneLore = new ArrayList<String>();
		ChiseledSandstoneLore.add("§e§lCost:");
		ChiseledSandstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledSandstone.getTypeId())+ ".Buy")) + " (left click)");
		ChiseledSandstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledSandstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		ChiseledSandstoneMeta.setLore(ChiseledSandstoneLore);
		ChiseledSandstone.setItemMeta(ChiseledSandstoneMeta);
		GUI.setItem(14, ChiseledSandstone);
		
		ItemStack SmoothSandstone = new ItemStack(Material.SANDSTONE);
		SmoothSandstone.setDurability((short) 2);
		ItemMeta SmoothSandstoneMeta = SmoothSandstone.getItemMeta();
		ArrayList<String> SmoothSandstoneLore = new ArrayList<String>();
		SmoothSandstoneLore.add("§e§lCost:");
		SmoothSandstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SmoothSandstone.getTypeId())+ ".Buy")) + " (left click)");
		SmoothSandstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(SmoothSandstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		SmoothSandstoneMeta.setLore(SmoothSandstoneLore);
		SmoothSandstone.setItemMeta(SmoothSandstoneMeta);
		GUI.setItem(15, SmoothSandstone);
		
		ItemStack RedSandstone = new ItemStack(Material.RED_SANDSTONE);
		ItemMeta RedSandstoneMeta = RedSandstone.getItemMeta();
		ArrayList<String> RedSandstoneLore = new ArrayList<String>();
		RedSandstoneLore.add("§e§lCost:");
		RedSandstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedSandstone.getTypeId())+ ".Buy")) + " (left click)");
		RedSandstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedSandstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		RedSandstoneMeta.setLore(RedSandstoneLore);
		RedSandstone.setItemMeta(RedSandstoneMeta);
		GUI.setItem(16, RedSandstone);
		
		ItemStack ChiseledRedSandstone = new ItemStack(Material.RED_SANDSTONE);
		ChiseledRedSandstone.setDurability((short) 1);
		ItemMeta ChiseledRedSandstoneMeta = ChiseledRedSandstone.getItemMeta();
		ArrayList<String> ChiseledRedSandstoneLore = new ArrayList<String>();
		ChiseledRedSandstoneLore.add("§e§lCost:");
		ChiseledRedSandstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledRedSandstone.getTypeId())+ ".Buy")) + " (left click)");
		ChiseledRedSandstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledRedSandstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		ChiseledRedSandstoneMeta.setLore(ChiseledRedSandstoneLore);
		ChiseledRedSandstone.setItemMeta(ChiseledRedSandstoneMeta);
		GUI.setItem(17, ChiseledRedSandstone);
		
		ItemStack SmoothRedSandstone = new ItemStack(Material.RED_SANDSTONE);
		SmoothRedSandstone.setDurability((short) 2);
		ItemMeta SmoothRedSandstoneMeta = SmoothRedSandstone.getItemMeta();
		ArrayList<String> SmoothRedSandstoneLore = new ArrayList<String>();
		SmoothRedSandstoneLore.add("§e§lCost:");
		SmoothRedSandstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SmoothRedSandstone.getTypeId())+ ".Buy")) + " (left click)");
		SmoothRedSandstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(SmoothRedSandstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		SmoothRedSandstoneMeta.setLore(SmoothRedSandstoneLore);
		SmoothRedSandstone.setItemMeta(SmoothRedSandstoneMeta);
		GUI.setItem(18, SmoothRedSandstone);
		
		ItemStack Brick = new ItemStack(Material.SMOOTH_BRICK);
		ItemMeta BrickMeta = Brick.getItemMeta();
		ArrayList<String> BrickLore = new ArrayList<String>();
		BrickLore.add("§e§lCost:");
		BrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Brick.getTypeId())+ ".Buy")) + " (left click)");
		BrickLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Brick.getTypeId())+ ".Buy") * 64) + " (right click)");
		BrickMeta.setLore(BrickLore);
		Brick.setItemMeta(BrickMeta);
		GUI.setItem(19, Brick);
		
		ItemStack Bookshelf = new ItemStack(Material.BOOKSHELF);
		ItemMeta BookshelfMeta = Bookshelf.getItemMeta();
		ArrayList<String> BookshelfLore = new ArrayList<String>();
		BookshelfLore.add("§e§lCost:");
		BookshelfLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bookshelf.getTypeId())+ ".Buy")) + " (left click)");
		BookshelfLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bookshelf.getTypeId())+ ".Buy") * 64) + " (right click)");
		BookshelfMeta.setLore(BookshelfLore);
		Bookshelf.setItemMeta(BookshelfMeta);
		GUI.setItem(20, Bookshelf);
		
		ItemStack MossStone = new ItemStack(Material.MOSSY_COBBLESTONE);
		ItemMeta MossStoneMeta = MossStone.getItemMeta();
		ArrayList<String> MossStoneLore = new ArrayList<String>();
		MossStoneLore.add("§e§lCost:");
		MossStoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(MossStone.getTypeId())+ ".Buy")) + " (left click)");
		MossStoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(MossStone.getTypeId())+ ".Buy") * 64) + " (right click)");
		MossStoneMeta.setLore(MossStoneLore);
		MossStone.setItemMeta(MossStoneMeta);
		GUI.setItem(21, MossStone);
		
		ItemStack Obsidian = new ItemStack(Material.OBSIDIAN);
		ItemMeta ObsidianMeta = Obsidian.getItemMeta();
		ArrayList<String> ObsidianLore = new ArrayList<String>();
		ObsidianLore.add("§e§lCost:");
		ObsidianLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Obsidian.getTypeId())+ ".Buy")) + " (left click)");
		ObsidianLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Obsidian.getTypeId())+ ".Buy") * 64) + " (right click)");
		ObsidianMeta.setLore(ObsidianLore);
		Obsidian.setItemMeta(ObsidianMeta);
		GUI.setItem(22, Obsidian);
		
		ItemStack Ice = new ItemStack(Material.ICE);
		ItemMeta IceMeta = Ice.getItemMeta();
		ArrayList<String> IceLore = new ArrayList<String>();
		IceLore.add("§e§lCost:");
		IceLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Ice.getTypeId())+ ".Buy")) + " (left click)");
		IceLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Ice.getTypeId())+ ".Buy") * 64) + " (right click)");
		IceMeta.setLore(IceLore);
		Ice.setItemMeta(IceMeta);
		GUI.setItem(23, Ice);
		
		ItemStack SnowBlock = new ItemStack(Material.SNOW_BLOCK);
		ItemMeta SnowBlockMeta = SnowBlock.getItemMeta();
		ArrayList<String> SnowBlockLore = new ArrayList<String>();
		SnowBlockLore.add("§e§lCost:");
		SnowBlockLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SnowBlock.getTypeId())+ ".Buy")) + " (left click)");
		SnowBlockLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(SnowBlock.getTypeId())+ ".Buy") * 64) + " (right click)");
		SnowBlockMeta.setLore(SnowBlockLore);
		SnowBlock.setItemMeta(SnowBlockMeta);
		GUI.setItem(24, SnowBlock);
		
		ItemStack ClayBlock = new ItemStack(Material.CLAY);
		ItemMeta ClayBlockMeta = ClayBlock.getItemMeta();
		ArrayList<String> ClayBlockLore = new ArrayList<String>();
		ClayBlockLore.add("§e§lCost:");
		ClayBlockLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(ClayBlock.getTypeId())+ ".Buy")) + " (left click)");
		ClayBlockLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(ClayBlock.getTypeId())+ ".Buy") * 64) + " (right click)");
		ClayBlockMeta.setLore(ClayBlockLore);
		ClayBlock.setItemMeta(ClayBlockMeta);
		GUI.setItem(25, ClayBlock);
		
		ItemStack Netherrack = new ItemStack(Material.NETHERRACK);
		ItemMeta NetherrackMeta = Netherrack.getItemMeta();
		ArrayList<String> NetherrackLore = new ArrayList<String>();
		NetherrackLore.add("§e§lCost:");
		NetherrackLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Netherrack.getTypeId())+ ".Buy")) + " (left click)");
		NetherrackLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Netherrack.getTypeId())+ ".Buy") * 64) + " (right click)");
		NetherrackMeta.setLore(NetherrackLore);
		Netherrack.setItemMeta(NetherrackMeta);
		GUI.setItem(26, Netherrack);
		
		ItemStack SoulSand = new ItemStack(Material.SOUL_SAND);
		ItemMeta SoulSandMeta = SoulSand.getItemMeta();
		ArrayList<String> SoulSandLore = new ArrayList<String>();
		SoulSandLore.add("§e§lCost:");
		SoulSandLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SoulSand.getTypeId())+ ".Buy")) + " (left click)");
		SoulSandLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(SoulSand.getTypeId())+ ".Buy") * 64) + " (right click)");
		SoulSandMeta.setLore(SoulSandLore);
		SoulSand.setItemMeta(SoulSandMeta);
		GUI.setItem(27, SoulSand);
		
		ItemStack Glowstone = new ItemStack(Material.GLOWSTONE);
		ItemMeta GlowstoneMeta = Glowstone.getItemMeta();
		ArrayList<String> GlowstoneLore = new ArrayList<String>();
		GlowstoneLore.add("§e§lCost:");
		GlowstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Glowstone.getTypeId())+ ".Buy")) + " (left click)");
		GlowstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Glowstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		GlowstoneMeta.setLore(GlowstoneLore);
		Glowstone.setItemMeta(GlowstoneMeta);
		GUI.setItem(28, Glowstone);
		
		ItemStack StoneBrick = new ItemStack(Material.SMOOTH_BRICK);
		ItemMeta StoneBrickMeta = StoneBrick.getItemMeta();
		ArrayList<String> StoneBrickLore = new ArrayList<String>();
		StoneBrickLore.add("§e§lCost:");
		StoneBrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(StoneBrick.getTypeId())+ ".Buy")) + " (left click)");
		StoneBrickLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(StoneBrick.getTypeId())+ ".Buy") * 64) + " (right click)");
		StoneBrickMeta.setLore(StoneBrickLore);
		StoneBrick.setItemMeta(StoneBrickMeta);
		GUI.setItem(29, StoneBrick);
		
		ItemStack MossyStoneBrick = new ItemStack(Material.SMOOTH_BRICK);
		MossyStoneBrick.setDurability((short) 1);
		ItemMeta MossyStoneBrickMeta = MossyStoneBrick.getItemMeta();
		ArrayList<String> MossyStoneBrickLore = new ArrayList<String>();
		MossyStoneBrickLore.add("§e§lCost:");
		MossyStoneBrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(MossyStoneBrick.getTypeId())+ ".Buy")) + " (left click)");
		MossyStoneBrickLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(MossyStoneBrick.getTypeId())+ ".Buy") * 64) + " (right click)");
		MossyStoneBrickMeta.setLore(MossyStoneBrickLore);
		MossyStoneBrick.setItemMeta(MossyStoneBrickMeta);
		GUI.setItem(30, MossyStoneBrick);
		
		ItemStack CrackedStoneBrick = new ItemStack(Material.SMOOTH_BRICK);
		CrackedStoneBrick.setDurability((short) 2);
		ItemMeta CrackedStoneBrickMeta = CrackedStoneBrick.getItemMeta();
		ArrayList<String> CrackedStoneBrickLore = new ArrayList<String>();
		CrackedStoneBrickLore.add("§e§lCost:");
		CrackedStoneBrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CrackedStoneBrick.getTypeId())+ ".Buy")) + " (left click)");
		CrackedStoneBrickLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(CrackedStoneBrick.getTypeId())+ ".Buy") * 64) + " (right click)");
		CrackedStoneBrickMeta.setLore(CrackedStoneBrickLore);
		CrackedStoneBrick.setItemMeta(CrackedStoneBrickMeta);
		GUI.setItem(31, CrackedStoneBrick);
		
		ItemStack ChiseledStoneBrick = new ItemStack(Material.SMOOTH_BRICK);
		ChiseledStoneBrick.setDurability((short) 3);
		ItemMeta ChiseledStoneBrickMeta = ChiseledStoneBrick.getItemMeta();
		ArrayList<String> ChiseledStoneBrickLore = new ArrayList<String>();
		ChiseledStoneBrickLore.add("§e§lCost:");
		ChiseledStoneBrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledStoneBrick.getTypeId())+ ".Buy")) + " (left click)");
		ChiseledStoneBrickLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledStoneBrick.getTypeId())+ ".Buy") * 64) + " (right click)");
		ChiseledStoneBrickMeta.setLore(ChiseledStoneBrickLore);
		ChiseledStoneBrick.setItemMeta(ChiseledStoneBrickMeta);
		GUI.setItem(32, ChiseledStoneBrick);
		
		ItemStack NetherBrick = new ItemStack(Material.NETHER_BRICK);
		ItemMeta NetherBrickMeta = NetherBrick.getItemMeta();
		ArrayList<String> NetherBrickLore = new ArrayList<String>();
		NetherBrickLore.add("§e§lCost:");
		NetherBrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(NetherBrick.getTypeId())+ ".Buy")) + " (left click)");
		NetherBrickLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(NetherBrick.getTypeId())+ ".Buy") * 64) + " (right click)");
		NetherBrickMeta.setLore(NetherBrickLore);
		NetherBrick.setItemMeta(NetherBrickMeta);
		GUI.setItem(33, NetherBrick);
		
		ItemStack EndStone = new ItemStack(Material.ENDER_STONE);
		ItemMeta EndStoneMeta = EndStone.getItemMeta();
		ArrayList<String> EndStoneLore = new ArrayList<String>();
		EndStoneLore.add("§e§lCost:");
		EndStoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(EndStone.getTypeId())+ ".Buy")) + " (left click)");
		EndStoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(EndStone.getTypeId())+ ".Buy") * 64) + " (right click)");
		EndStoneMeta.setLore(EndStoneLore);
		EndStone.setItemMeta(EndStoneMeta);
		GUI.setItem(34, EndStone);
		
		ItemStack Quartz = new ItemStack(Material.QUARTZ_BLOCK);
		ItemMeta QuartzMeta = Quartz.getItemMeta();
		ArrayList<String> QuartzLore = new ArrayList<String>();
		QuartzLore.add("§e§lCost:");
		QuartzLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Quartz.getTypeId())+ ".Buy")) + " (left click)");
		QuartzLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Quartz.getTypeId())+ ".Buy") * 64) + " (right click)");
		QuartzMeta.setLore(QuartzLore);
		Quartz.setItemMeta(QuartzMeta);
		GUI.setItem(35, Quartz);
		
		ItemStack ChiseledQuartz = new ItemStack(Material.QUARTZ_BLOCK);
		ChiseledQuartz.setDurability((short) 1);
		ItemMeta ChiseledQuartzMeta = ChiseledQuartz.getItemMeta();
		ArrayList<String> ChiseledQuartzLore = new ArrayList<String>();
		ChiseledQuartzLore.add("§e§lCost:");
		ChiseledQuartzLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledQuartz.getTypeId())+ ".Buy")) + " (left click)");
		ChiseledQuartzLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(ChiseledQuartz.getTypeId())+ ".Buy") * 64) + " (right click)");
		ChiseledQuartzMeta.setLore(ChiseledQuartzLore);
		ChiseledQuartz.setItemMeta(ChiseledQuartzMeta);
		GUI.setItem(36, ChiseledQuartz);
		
		ItemStack PillarQuartz = new ItemStack(Material.QUARTZ_BLOCK);
		PillarQuartz.setDurability((short) 2);
		ItemMeta PillarQuartzMeta = PillarQuartz.getItemMeta();
		ArrayList<String> PillarQuartzLore = new ArrayList<String>();
		PillarQuartzLore.add("§e§lCost:");
		PillarQuartzLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(PillarQuartz.getTypeId())+ ".Buy")) + " (left click)");
		PillarQuartzLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(PillarQuartz.getTypeId())+ ".Buy") * 64) + " (right click)");
		PillarQuartzMeta.setLore(PillarQuartzLore);
		PillarQuartz.setItemMeta(PillarQuartzMeta);
		GUI.setItem(37, PillarQuartz);
		
		ItemStack Wool = new ItemStack(Material.WOOL);
		ItemMeta WoolMeta = Wool.getItemMeta();
		ArrayList<String> WoolLore = new ArrayList<String>();
		WoolLore.add("§e§lCost:");
		WoolLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Wool.getTypeId())+ ".Buy")) + " (left click)");
		WoolLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Wool.getTypeId())+ ".Buy") * 64) + " (right click)");
		WoolMeta.setLore(WoolLore);
		Wool.setItemMeta(WoolMeta);
		GUI.setItem(38, Wool);
		
		ItemStack HardenedClay = new ItemStack(Material.HARD_CLAY);
		ItemMeta HardenedClayMeta = HardenedClay.getItemMeta();
		ArrayList<String> HardenedClayLore = new ArrayList<String>();
		HardenedClayLore.add("§e§lCost:");
		HardenedClayLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(HardenedClay.getTypeId())+ ".Buy")) + " (left click)");
		HardenedClayLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(HardenedClay.getTypeId())+ ".Buy") * 64) + " (right click)");
		HardenedClayMeta.setLore(HardenedClayLore);
		HardenedClay.setItemMeta(HardenedClayMeta);
		GUI.setItem(39, HardenedClay);
		
		ItemStack Glass = new ItemStack(Material.GLASS);
		ItemMeta GlassMeta = Glass.getItemMeta();
		ArrayList<String> GlassLore = new ArrayList<String>();
		GlassLore.add("§e§lCost:");
		GlassLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Glass.getTypeId())+ ".Buy")) + " (left click)");
		GlassLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Glass.getTypeId())+ ".Buy") * 64) + " (right click)");
		GlassMeta.setLore(GlassLore);
		Glass.setItemMeta(GlassMeta);
		GUI.setItem(40, Glass);
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
	@SuppressWarnings("deprecation")
	public void openMaterials(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Materials §c§l<<");
		
		ItemStack Coal = new ItemStack(Material.COAL);	
		ItemMeta CoalMeta = Coal.getItemMeta();
		ArrayList<String> CoalLore = new ArrayList<String>();
		CoalLore.add("§e§lCost:");
		CoalLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Coal.getTypeId())+ ".Buy")) + " (left click)");
		CoalLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Coal.getTypeId())+ ".Buy") * 32) + " (right click)");
		CoalMeta.setLore(CoalLore);
		Coal.setItemMeta(CoalMeta);
		GUI.setItem(0, Coal);
		
		ItemStack Iron = new ItemStack(Material.IRON_INGOT);	
		ItemMeta IronMeta = Iron.getItemMeta();
		ArrayList<String> IronLore = new ArrayList<String>();
		IronLore.add("§e§lCost:");
		IronLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Iron.getTypeId())+ ".Buy")) + " (left click)");
		IronLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Iron.getTypeId())+ ".Buy") * 32) + " (right click)");
		IronMeta.setLore(IronLore);
		Iron.setItemMeta(IronMeta);
		GUI.setItem(1, Iron);
		
		ItemStack Gold = new ItemStack(Material.GOLD_INGOT);	
		ItemMeta GoldMeta = Gold.getItemMeta();
		ArrayList<String> GoldLore = new ArrayList<String>();
		GoldLore.add("§e§lCost:");
		GoldLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gold.getTypeId())+ ".Buy")) + " (left click)");
		GoldLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gold.getTypeId())+ ".Buy") * 32) + " (right click)");
		GoldMeta.setLore(GoldLore);
		Gold.setItemMeta(GoldMeta);
		GUI.setItem(2, Gold);
		
		ItemStack Diamond = new ItemStack(Material.DIAMOND);	
		ItemMeta DiamondMeta = Diamond.getItemMeta();
		ArrayList<String> DiamondLore = new ArrayList<String>();
		DiamondLore.add("§e§lCost:");
		DiamondLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Diamond.getTypeId())+ ".Buy")) + " (left click)");
		DiamondLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Diamond.getTypeId())+ ".Buy") * 32) + " (right click)");
		DiamondMeta.setLore(DiamondLore);
		Diamond.setItemMeta(DiamondMeta);
		GUI.setItem(3, Diamond);
		
		ItemStack Redstone = new ItemStack(Material.REDSTONE);	
		ItemMeta RedstoneMeta = Redstone.getItemMeta();
		ArrayList<String> RedstoneLore = new ArrayList<String>();
		RedstoneLore.add("§e§lCost:");
		RedstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Redstone.getTypeId())+ ".Buy")) + " (left click)");
		RedstoneLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Redstone.getTypeId())+ ".Buy") * 32) + " (right click)");
		RedstoneMeta.setLore(RedstoneLore);
		Redstone.setItemMeta(RedstoneMeta);
		GUI.setItem(4, Redstone);
		
		ItemStack Gunpowder = new ItemStack(Material.SULPHUR);	
		ItemMeta GunpowderMeta = Gunpowder.getItemMeta();
		ArrayList<String> GunpowderLore = new ArrayList<String>();
		GunpowderLore.add("§e§lCost:");
		GunpowderLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gunpowder.getTypeId())+ ".Buy")) + " (left click)");
		GunpowderLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gunpowder.getTypeId())+ ".Buy") * 32) + " (right click)");
		GunpowderMeta.setLore(GunpowderLore);
		Gunpowder.setItemMeta(GunpowderMeta);
		GUI.setItem(5, Gunpowder);
		
		ItemStack Leather = new ItemStack(Material.LEATHER);	
		ItemMeta LeatherMeta = Leather.getItemMeta();
		ArrayList<String> LeatherLore = new ArrayList<String>();
		LeatherLore.add("§e§lCost:");
		LeatherLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Leather.getTypeId())+ ".Buy")) + " (left click)");
		LeatherLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Leather.getTypeId())+ ".Buy") * 32) + " (right click)");
		LeatherMeta.setLore(LeatherLore);
		Leather.setItemMeta(LeatherMeta);
		GUI.setItem(6, Leather);
		
		ItemStack Brick = new ItemStack(Material.CLAY_BRICK);	
		ItemMeta BrickMeta = Brick.getItemMeta();
		ArrayList<String> BrickLore = new ArrayList<String>();
		BrickLore.add("§e§lCost:");
		BrickLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Brick.getTypeId())+ ".Buy")) + " (left click)");
		BrickLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Brick.getTypeId())+ ".Buy") * 32) + " (right click)");
		BrickMeta.setLore(BrickLore);
		Brick.setItemMeta(BrickMeta);
		GUI.setItem(7, Brick);
		
		ItemStack Glowstone = new ItemStack(Material.GLOWSTONE_DUST);	
		ItemMeta GlowstoneMeta = Glowstone.getItemMeta();
		ArrayList<String> GlowstoneLore = new ArrayList<String>();
		GlowstoneLore.add("§e§lCost:");
		GlowstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Glowstone.getTypeId())+ ".Buy")) + " (left click)");
		GlowstoneLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Glowstone.getTypeId())+ ".Buy") * 32) + " (right click)");
		GlowstoneMeta.setLore(GlowstoneLore);
		Glowstone.setItemMeta(GlowstoneMeta);
		GUI.setItem(8, Glowstone);
		
		ItemStack InkSac = new ItemStack(Material.INK_SACK);	
		ItemMeta InkSacMeta = InkSac.getItemMeta();
		ArrayList<String> InkSacLore = new ArrayList<String>();
		InkSacLore.add("§e§lCost:");
		InkSacLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(InkSac.getTypeId())+ ".Buy")) + " (left click)");
		InkSacLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(InkSac.getTypeId())+ ".Buy") * 32) + " (right click)");
		InkSacMeta.setLore(InkSacLore);
		InkSac.setItemMeta(InkSacMeta);
		GUI.setItem(9, InkSac);
		
		ItemStack RoseRed = new ItemStack(Material.INK_SACK);
		RoseRed.setDurability((short) 1);
		ItemMeta RoseRedMeta = RoseRed.getItemMeta();
		ArrayList<String> RoseRedLore = new ArrayList<String>();
		RoseRedLore.add("§e§lCost:");
		RoseRedLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(RoseRed.getTypeId())+ ".Buy")) + " (left click)");
		RoseRedLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(RoseRed.getTypeId())+ ".Buy") * 32) + " (right click)");
		RoseRedMeta.setLore(RoseRedLore);
		RoseRed.setItemMeta(RoseRedMeta);
		GUI.setItem(10, RoseRed);
		
		ItemStack CactusGreen = new ItemStack(Material.INK_SACK);
		CactusGreen.setDurability((short) 2);
		ItemMeta CactusGreenMeta = CactusGreen.getItemMeta();
		ArrayList<String> CactusGreenLore = new ArrayList<String>();
		CactusGreenLore.add("§e§lCost:");
		CactusGreenLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CactusGreen.getTypeId())+ ".Buy")) + " (left click)");
		CactusGreenLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(CactusGreen.getTypeId())+ ".Buy") * 32) + " (right click)");
		CactusGreenMeta.setLore(CactusGreenLore);
		CactusGreen.setItemMeta(CactusGreenMeta);
		GUI.setItem(11, CactusGreen);
		
		ItemStack CocoaBeans = new ItemStack(Material.INK_SACK);
		CocoaBeans.setDurability((short) 3);
		ItemMeta CocoaBeansMeta = CocoaBeans.getItemMeta();
		ArrayList<String> CocoaBeansLore = new ArrayList<String>();
		CocoaBeansLore.add("§e§lCost:");
		CocoaBeansLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CocoaBeans.getTypeId())+ ".Buy")) + " (left click)");
		CocoaBeansLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(CocoaBeans.getTypeId())+ ".Buy") * 32) + " (right click)");
		CocoaBeansMeta.setLore(CocoaBeansLore);
		CocoaBeans.setItemMeta(CocoaBeansMeta);
		GUI.setItem(12, CocoaBeans);
		
		ItemStack LapisLuzili = new ItemStack(Material.INK_SACK);
		LapisLuzili.setDurability((short) 4);
		ItemMeta LapisLuziliMeta = LapisLuzili.getItemMeta();
		ArrayList<String> LapisLuziliLore = new ArrayList<String>();
		LapisLuziliLore.add("§e§lCost:");
		LapisLuziliLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(LapisLuzili.getTypeId())+ ".Buy")) + " (left click)");
		LapisLuziliLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(LapisLuzili.getTypeId())+ ".Buy") * 32) + " (right click)");
		LapisLuziliMeta.setLore(LapisLuziliLore);
		LapisLuzili.setItemMeta(LapisLuziliMeta);
		GUI.setItem(13, LapisLuzili);
		
		ItemStack PurpleDye = new ItemStack(Material.INK_SACK);
		PurpleDye.setDurability((short) 5);
		ItemMeta PurpleDyeMeta = PurpleDye.getItemMeta();
		ArrayList<String> PurpleDyeLore = new ArrayList<String>();
		PurpleDyeLore.add("§e§lCost:");
		PurpleDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(PurpleDye.getTypeId())+ ".Buy")) + " (left click)");
		PurpleDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(PurpleDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		PurpleDyeMeta.setLore(PurpleDyeLore);
		PurpleDye.setItemMeta(PurpleDyeMeta);
		GUI.setItem(14, PurpleDye);
		
		ItemStack CyanDye = new ItemStack(Material.INK_SACK);
		CyanDye.setDurability((short) 6);
		ItemMeta CyanDyeMeta = CyanDye.getItemMeta();
		ArrayList<String> CyanDyeLore = new ArrayList<String>();
		CyanDyeLore.add("§e§lCost:");
		CyanDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CyanDye.getTypeId())+ ".Buy")) + " (left click)");
		CyanDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(CyanDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		CyanDyeMeta.setLore(CyanDyeLore);
		CyanDye.setItemMeta(CyanDyeMeta);
		GUI.setItem(15, CyanDye);
		
		ItemStack LightGrayDye = new ItemStack(Material.INK_SACK);
		LightGrayDye.setDurability((short) 7);
		ItemMeta LightGrayDyeMeta = LightGrayDye.getItemMeta();
		ArrayList<String> LightGrayDyeLore = new ArrayList<String>();
		LightGrayDyeLore.add("§e§lCost:");
		LightGrayDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(LightGrayDye.getTypeId())+ ".Buy")) + " (left click)");
		LightGrayDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(LightGrayDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		LightGrayDyeMeta.setLore(LightGrayDyeLore);
		LightGrayDye.setItemMeta(LightGrayDyeMeta);
		GUI.setItem(16, LightGrayDye);
		
		ItemStack GrayDye = new ItemStack(Material.INK_SACK);
		GrayDye.setDurability((short) 8);
		ItemMeta GrayDyeMeta = GrayDye.getItemMeta();
		ArrayList<String> GrayDyeLore = new ArrayList<String>();
		GrayDyeLore.add("§e§lCost:");
		GrayDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(GrayDye.getTypeId())+ ".Buy")) + " (left click)");
		GrayDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(GrayDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		GrayDyeMeta.setLore(GrayDyeLore);
		GrayDye.setItemMeta(GrayDyeMeta);
		GUI.setItem(17, GrayDye);
		
		ItemStack PinkDye = new ItemStack(Material.INK_SACK);
		PinkDye.setDurability((short) 9);
		ItemMeta PinkDyeMeta = PinkDye.getItemMeta();
		ArrayList<String> PinkDyeLore = new ArrayList<String>();
		PinkDyeLore.add("§e§lCost:");
		PinkDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(PinkDye.getTypeId())+ ".Buy")) + " (left click)");
		PinkDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(PinkDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		PinkDyeMeta.setLore(PinkDyeLore);
		PinkDye.setItemMeta(PinkDyeMeta);
		GUI.setItem(18, PinkDye);
		
		ItemStack LimeDye = new ItemStack(Material.INK_SACK);
		LimeDye.setDurability((short) 10);
		ItemMeta LimeDyeMeta = LimeDye.getItemMeta();
		ArrayList<String> LimeDyeLore = new ArrayList<String>();
		LimeDyeLore.add("§e§lCost:");
		LimeDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(LimeDye.getTypeId())+ ".Buy")) + " (left click)");
		LimeDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(LimeDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		LimeDyeMeta.setLore(LimeDyeLore);
		LimeDye.setItemMeta(LimeDyeMeta);
		GUI.setItem(19, LimeDye);
		
		ItemStack DandilionYellowDye = new ItemStack(Material.INK_SACK);
		DandilionYellowDye.setDurability((short) 11);
		ItemMeta DandilionYellowDyeMeta = DandilionYellowDye.getItemMeta();
		ArrayList<String> DandilionYellowDyeLore = new ArrayList<String>();
		DandilionYellowDyeLore.add("§e§lCost:");
		DandilionYellowDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DandilionYellowDye.getTypeId())+ ".Buy")) + " (left click)");
		DandilionYellowDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(DandilionYellowDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		DandilionYellowDyeMeta.setLore(DandilionYellowDyeLore);
		DandilionYellowDye.setItemMeta(DandilionYellowDyeMeta);
		GUI.setItem(20, DandilionYellowDye);
		
		ItemStack LightBlueDye = new ItemStack(Material.INK_SACK);
		LightBlueDye.setDurability((short) 12);
		ItemMeta LightBlueDyeMeta = LightBlueDye.getItemMeta();
		ArrayList<String> LightBlueDyeLore = new ArrayList<String>();
		LightBlueDyeLore.add("§e§lCost:");
		LightBlueDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(LightBlueDye.getTypeId())+ ".Buy")) + " (left click)");
		LightBlueDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(LightBlueDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		LightBlueDyeMeta.setLore(LightBlueDyeLore);
		LightBlueDye.setItemMeta(LightBlueDyeMeta);
		GUI.setItem(21, LightBlueDye);
		
		ItemStack MagentaDye = new ItemStack(Material.INK_SACK);
		MagentaDye.setDurability((short) 13);
		ItemMeta MagentaDyeMeta = MagentaDye.getItemMeta();
		ArrayList<String> MagentaDyeLore = new ArrayList<String>();
		MagentaDyeLore.add("§e§lCost:");
		MagentaDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(MagentaDye.getTypeId())+ ".Buy")) + " (left click)");
		MagentaDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(MagentaDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		MagentaDyeMeta.setLore(MagentaDyeLore);
		MagentaDye.setItemMeta(MagentaDyeMeta);
		GUI.setItem(22, MagentaDye);
		
		ItemStack OrangeDye = new ItemStack(Material.INK_SACK);
		OrangeDye.setDurability((short) 14);
		ItemMeta OrangeDyeMeta = OrangeDye.getItemMeta();
		ArrayList<String> OrangeDyeLore = new ArrayList<String>();
		OrangeDyeLore.add("§e§lCost:");
		OrangeDyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(OrangeDye.getTypeId())+ ".Buy")) + " (left click)");
		OrangeDyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(OrangeDye.getTypeId())+ ".Buy") * 32) + " (right click)");
		OrangeDyeMeta.setLore(OrangeDyeLore);
		OrangeDye.setItemMeta(OrangeDyeMeta);
		GUI.setItem(23, OrangeDye);
		
		ItemStack BoneMeal = new ItemStack(Material.INK_SACK);
		BoneMeal.setDurability((short) 15);
		ItemMeta BoneMealMeta = BoneMeal.getItemMeta();
		ArrayList<String> BoneMealLore = new ArrayList<String>();
		BoneMealLore.add("§e§lCost:");
		BoneMealLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(BoneMeal.getTypeId())+ ".Buy")) + " (left click)");
		BoneMealLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(BoneMeal.getTypeId())+ ".Buy") * 32) + " (right click)");
		BoneMealMeta.setLore(BoneMealLore);
		BoneMeal.setItemMeta(BoneMealMeta);
		GUI.setItem(24, BoneMeal);
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
	@SuppressWarnings("deprecation")
	public void openBrewing(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Brewing §c§l<<");
		
		ItemStack BrewingStand = new ItemStack(Material.BREWING_STAND_ITEM);	
		ItemMeta BrewingStandMeta = BrewingStand.getItemMeta();
		ArrayList<String> BrewingStandLore = new ArrayList<String>();
		BrewingStandLore.add("§e§lCost:");
		BrewingStandLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(BrewingStand.getTypeId())+ ".Buy")) + " (left click)");
		BrewingStandLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(BrewingStand.getTypeId())+ ".Buy") * 16) + " (right click)");
		BrewingStandMeta.setLore(BrewingStandLore);
		BrewingStand.setItemMeta(BrewingStandMeta);
		GUI.setItem(0, BrewingStand);
		
		ItemStack GlassBottle = new ItemStack(Material.GLASS_BOTTLE);	
		ItemMeta GlassBottleMeta = GlassBottle.getItemMeta();
		ArrayList<String> GlassBottleLore = new ArrayList<String>();
		GlassBottleLore.add("§e§lCost:");
		GlassBottleLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(GlassBottle.getTypeId())+ ".Buy")) + " (left click)");
		GlassBottleLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(GlassBottle.getTypeId())+ ".Buy") * 32) + " (right click)");
		GlassBottleMeta.setLore(GlassBottleLore);
		GlassBottle.setItemMeta(GlassBottleMeta);
		GUI.setItem(1, GlassBottle);
		
		ItemStack NetherWart = new ItemStack(Material.NETHER_STALK);	
		ItemMeta NetherWartMeta = NetherWart.getItemMeta();
		ArrayList<String> NetherWartLore = new ArrayList<String>();
		NetherWartLore.add("§e§lCost:");
		NetherWartLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(NetherWart.getTypeId())+ ".Buy")) + " (left click)");
		NetherWartLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(NetherWart.getTypeId())+ ".Buy") * 32) + " (right click)");
		NetherWartMeta.setLore(NetherWartLore);
		NetherWart.setItemMeta(NetherWartMeta);
		GUI.setItem(2, NetherWart);
		
		ItemStack BlazePowder = new ItemStack(Material.BLAZE_POWDER);	
		ItemMeta BlazePowderMeta = BlazePowder.getItemMeta();
		ArrayList<String> BlazePowderLore = new ArrayList<String>();
		BlazePowderLore.add("§e§lCost:");
		BlazePowderLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(BlazePowder.getTypeId())+ ".Buy")) + " (left click)");
		BlazePowderLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(BlazePowder.getTypeId())+ ".Buy") * 32) + " (right click)");
		BlazePowderMeta.setLore(BlazePowderLore);
		BlazePowder.setItemMeta(BlazePowderMeta);
		GUI.setItem(3, BlazePowder);
		
		ItemStack Sugar = new ItemStack(Material.SUGAR);	
		ItemMeta SugarMeta = Sugar.getItemMeta();
		ArrayList<String> SugarLore = new ArrayList<String>();
		SugarLore.add("§e§lCost:");
		SugarLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sugar.getTypeId())+ ".Buy")) + " (left click)");
		SugarLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sugar.getTypeId())+ ".Buy") * 32) + " (right click)");
		SugarMeta.setLore(SugarLore);
		Sugar.setItemMeta(SugarMeta);
		GUI.setItem(4, Sugar);
		
		ItemStack MagmaCream = new ItemStack(Material.MAGMA_CREAM);	
		ItemMeta MagmaCreamMeta = MagmaCream.getItemMeta();
		ArrayList<String> MagmaCreamLore = new ArrayList<String>();
		MagmaCreamLore.add("§e§lCost:");
		MagmaCreamLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(MagmaCream.getTypeId())+ ".Buy")) + " (left click)");
		MagmaCreamLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(MagmaCream.getTypeId())+ ".Buy") * 32) + " (right click)");
		MagmaCreamMeta.setLore(MagmaCreamLore);
		MagmaCream.setItemMeta(MagmaCreamMeta);
		GUI.setItem(5, MagmaCream);
		
		ItemStack GlisteringMelon = new ItemStack(Material.SPECKLED_MELON);	
		ItemMeta GlisteringMelonMeta = GlisteringMelon.getItemMeta();
		ArrayList<String> GlisteringMelonLore = new ArrayList<String>();
		GlisteringMelonLore.add("§e§lCost:");
		GlisteringMelonLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(GlisteringMelon.getTypeId())+ ".Buy")) + " (left click)");
		GlisteringMelonLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(GlisteringMelon.getTypeId())+ ".Buy") * 32) + " (right click)");
		GlisteringMelonMeta.setLore(GlisteringMelonLore);
		GlisteringMelon.setItemMeta(GlisteringMelonMeta);
		GUI.setItem(6, GlisteringMelon);
		
		ItemStack GoldenCarrot = new ItemStack(Material.GOLDEN_CARROT);	
		ItemMeta GoldenCarrotMeta = GoldenCarrot.getItemMeta();
		ArrayList<String> GoldenCarrotLore = new ArrayList<String>();
		GoldenCarrotLore.add("§e§lCost:");
		GoldenCarrotLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(GoldenCarrot.getTypeId())+ ".Buy")) + " (left click)");
		GoldenCarrotLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(GoldenCarrot.getTypeId())+ ".Buy") * 32) + " (right click)");
		GoldenCarrotMeta.setLore(GoldenCarrotLore);
		GoldenCarrot.setItemMeta(GoldenCarrotMeta);
		GUI.setItem(7, GoldenCarrot);
		
		ItemStack GhastTear = new ItemStack(Material.GHAST_TEAR);	
		ItemMeta GhastTearMeta = GhastTear.getItemMeta();
		ArrayList<String> GhastTearLore = new ArrayList<String>();
		GhastTearLore.add("§e§lCost:");
		GhastTearLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(GhastTear.getTypeId())+ ".Buy")) + " (left click)");
		GhastTearLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(GhastTear.getTypeId())+ ".Buy") * 32) + " (right click)");
		GhastTearMeta.setLore(GhastTearLore);
		GhastTear.setItemMeta(GhastTearMeta);
		GUI.setItem(8, GhastTear);
		
		ItemStack SpiderEye = new ItemStack(Material.SPIDER_EYE);	
		ItemMeta SpiderEyeMeta = SpiderEye.getItemMeta();
		ArrayList<String> SpiderEyeLore = new ArrayList<String>();
		SpiderEyeLore.add("§e§lCost:");
		SpiderEyeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SpiderEye.getTypeId())+ ".Buy")) + " (left click)");
		SpiderEyeLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(SpiderEye.getTypeId())+ ".Buy") * 32) + " (right click)");
		SpiderEyeMeta.setLore(SpiderEyeLore);
		SpiderEye.setItemMeta(SpiderEyeMeta);
		GUI.setItem(9, SpiderEye);
		
		ItemStack WaterLily = new ItemStack(Material.WATER_LILY);	
		ItemMeta WaterLilyMeta = WaterLily.getItemMeta();
		ArrayList<String> WaterLilyLore = new ArrayList<String>();
		WaterLilyLore.add("§e§lCost:");
		WaterLilyLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(WaterLily.getTypeId())+ ".Buy")) + " (left click)");
		WaterLilyLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(WaterLily.getTypeId())+ ".Buy") * 32) + " (right click)");
		WaterLilyMeta.setLore(WaterLilyLore);
		WaterLily.setItemMeta(WaterLilyMeta);
		GUI.setItem(10, WaterLily);
		
		ItemStack PufferFish = new ItemStack(Material.RAW_FISH);
		PufferFish.setDurability((short)3);
		ItemMeta PufferFishMeta = PufferFish.getItemMeta();
		ArrayList<String> PufferFishLore = new ArrayList<String>();
		PufferFishLore.add("§e§lCost:");
		PufferFishLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(PufferFish.getTypeId())+ ".Buy")) + " (left click)");
		PufferFishLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(PufferFish.getTypeId())+ ".Buy") * 32) + " (right click)");
		PufferFishMeta.setLore(PufferFishLore);
		PufferFish.setItemMeta(PufferFishMeta);
		GUI.setItem(11, PufferFish);
		
		ItemStack Gunpowder = new ItemStack(Material.SULPHUR);	
		ItemMeta GunpowderMeta = Gunpowder.getItemMeta();
		ArrayList<String> GunpowderLore = new ArrayList<String>();
		GunpowderLore.add("§e§lCost:");
		GunpowderLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gunpowder.getTypeId())+ ".Buy")) + " (left click)");
		GunpowderLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Gunpowder.getTypeId())+ ".Buy") * 32) + " (right click)");
		GunpowderMeta.setLore(GunpowderLore);
		Gunpowder.setItemMeta(GunpowderMeta);
		GUI.setItem(12, Gunpowder);
		
		ItemStack GlowstoneDust = new ItemStack(Material.GLOWSTONE_DUST);	
		ItemMeta GlowstoneDustMeta = GlowstoneDust.getItemMeta();
		ArrayList<String> GlowstoneDustLore = new ArrayList<String>();
		GlowstoneDustLore.add("§e§lCost:");
		GlowstoneDustLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(GlowstoneDust.getTypeId())+ ".Buy")) + " (left click)");
		GlowstoneDustLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(GlowstoneDust.getTypeId())+ ".Buy") * 32) + " (right click)");
		GlowstoneDustMeta.setLore(GlowstoneDustLore);
		GlowstoneDust.setItemMeta(GlowstoneDustMeta);
		GUI.setItem(13, GlowstoneDust);
		
		ItemStack RedstoneDust = new ItemStack(Material.REDSTONE);	
		ItemMeta RedstoneDustMeta = RedstoneDust.getItemMeta();
		ArrayList<String> RedstoneDustLore = new ArrayList<String>();
		RedstoneDustLore.add("§e§lCost:");
		RedstoneDustLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedstoneDust.getTypeId())+ ".Buy")) + " (left click)");
		RedstoneDustLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedstoneDust.getTypeId())+ ".Buy") * 32) + " (right click)");
		RedstoneDustMeta.setLore(RedstoneDustLore);
		RedstoneDust.setItemMeta(RedstoneDustMeta);
		GUI.setItem(14, RedstoneDust);
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
	@SuppressWarnings("deprecation")
	public void openRedstone(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Redstone §c§l<<");
		
		ItemStack Redstone = new ItemStack(Material.REDSTONE);	
		ItemMeta RedstoneMeta = Redstone.getItemMeta();
		ArrayList<String> RedstoneLore = new ArrayList<String>();
		RedstoneLore.add("§e§lCost:");
		RedstoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Redstone.getTypeId())+ ".Buy")) + " (left click)");
		RedstoneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Redstone.getTypeId())+ ".Buy") * 64) + " (right click)");
		RedstoneMeta.setLore(RedstoneLore);
		Redstone.setItemMeta(RedstoneMeta);
		GUI.setItem(0, Redstone);
		
		ItemStack Dispenser = new ItemStack(Material.DISPENSER);	
		ItemMeta DispenserMeta = Dispenser.getItemMeta();
		ArrayList<String> DispenserLore = new ArrayList<String>();
		DispenserLore.add("§e§lCost:");
		DispenserLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Dispenser.getTypeId())+ ".Buy")) + " (left click)");
		DispenserLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Dispenser.getTypeId())+ ".Buy") * 16) + " (right click)");
		DispenserMeta.setLore(DispenserLore);
		Dispenser.setItemMeta(DispenserMeta);
		GUI.setItem(1, Dispenser);
		
		ItemStack StickyPiston = new ItemStack(Material.PISTON_STICKY_BASE);	
		ItemMeta StickyPistonMeta = StickyPiston.getItemMeta();
		ArrayList<String> StickyPistonLore = new ArrayList<String>();
		StickyPistonLore.add("§e§lCost:");
		StickyPistonLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(StickyPiston.getTypeId())+ ".Buy")) + " (left click)");
		StickyPistonLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(StickyPiston.getTypeId())+ ".Buy") * 16) + " (right click)");
		StickyPistonMeta.setLore(StickyPistonLore);
		StickyPiston.setItemMeta(StickyPistonMeta);
		GUI.setItem(2, StickyPiston);
		
		ItemStack Piston = new ItemStack(Material.PISTON_BASE);	
		ItemMeta PistonMeta = Piston.getItemMeta();
		ArrayList<String> PistonLore = new ArrayList<String>();
		PistonLore.add("§e§lCost:");
		PistonLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Piston.getTypeId())+ ".Buy")) + " (left click)");
		PistonLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Piston.getTypeId())+ ".Buy") * 16) + " (right click)");
		PistonMeta.setLore(PistonLore);
		Piston.setItemMeta(PistonMeta);
		GUI.setItem(3, Piston);
		
		ItemStack TNT = new ItemStack(Material.TNT);	
		ItemMeta TNTMeta = TNT.getItemMeta();
		ArrayList<String> TNTLore = new ArrayList<String>();
		TNTLore.add("§e§lCost:");
		TNTLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(TNT.getTypeId())+ ".Buy")) + " (left click)");
		TNTLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(TNT.getTypeId())+ ".Buy") * 16) + " (right click)");
		TNTMeta.setLore(TNTLore);
		TNT.setItemMeta(TNTMeta);
		GUI.setItem(4, TNT);
		
		ItemStack Lever = new ItemStack(Material.LEVER);	
		ItemMeta LeverMeta = Lever.getItemMeta();
		ArrayList<String> LeverLore = new ArrayList<String>();
		LeverLore.add("§e§lCost:");
		LeverLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Lever.getTypeId())+ ".Buy")) + " (left click)");
		LeverLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Lever.getTypeId())+ ".Buy") * 16) + " (right click)");
		LeverMeta.setLore(LeverLore);
		Lever.setItemMeta(LeverMeta);
		GUI.setItem(5, Lever);
		
		ItemStack RedstoneTorch = new ItemStack(Material.REDSTONE_TORCH_ON);	
		ItemMeta RedstoneTorchMeta = RedstoneTorch.getItemMeta();
		ArrayList<String> RedstoneTorchLore = new ArrayList<String>();
		RedstoneTorchLore.add("§e§lCost:");
		RedstoneTorchLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedstoneTorch.getTypeId())+ ".Buy")) + " (left click)");
		RedstoneTorchLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedstoneTorch.getTypeId())+ ".Buy") * 16) + " (right click)");
		RedstoneTorchMeta.setLore(RedstoneTorchLore);
		RedstoneTorch.setItemMeta(RedstoneTorchMeta);
		GUI.setItem(6, RedstoneTorch);
		
		ItemStack StoneButton = new ItemStack(Material.STONE_BUTTON);	
		ItemMeta StoneButtonMeta = StoneButton.getItemMeta();
		ArrayList<String> StoneButtonLore = new ArrayList<String>();
		StoneButtonLore.add("§e§lCost:");
		StoneButtonLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(StoneButton.getTypeId())+ ".Buy")) + " (left click)");
		StoneButtonLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(StoneButton.getTypeId())+ ".Buy") * 16) + " (right click)");
		StoneButtonMeta.setLore(StoneButtonLore);
		StoneButton.setItemMeta(StoneButtonMeta);
		GUI.setItem(7, StoneButton);
		
		ItemStack TrapDoor = new ItemStack(Material.TRAP_DOOR);	
		ItemMeta TrapDoorMeta = TrapDoor.getItemMeta();
		ArrayList<String> TrapDoorLore = new ArrayList<String>();
		TrapDoorLore.add("§e§lCost:");
		TrapDoorLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(TrapDoor.getTypeId())+ ".Buy")) + " (left click)");
		TrapDoorLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(TrapDoor.getTypeId())+ ".Buy") * 16) + " (right click)");
		TrapDoorMeta.setLore(TrapDoorLore);
		TrapDoor.setItemMeta(TrapDoorMeta);
		GUI.setItem(8, TrapDoor);
		
		ItemStack RedstoneLamp = new ItemStack(Material.REDSTONE_LAMP_OFF);	
		ItemMeta RedstoneLampMeta = RedstoneLamp.getItemMeta();
		ArrayList<String> RedstoneLampLore = new ArrayList<String>();
		RedstoneLampLore.add("§e§lCost:");
		RedstoneLampLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedstoneLamp.getTypeId())+ ".Buy")) + " (left click)");
		RedstoneLampLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(RedstoneLamp.getTypeId())+ ".Buy") * 16) + " (right click)");
		RedstoneLampMeta.setLore(RedstoneLampLore);
		RedstoneLamp.setItemMeta(RedstoneLampMeta);
		GUI.setItem(9, RedstoneLamp);
		
		ItemStack TrappedChest = new ItemStack(Material.TRAPPED_CHEST);	
		ItemMeta TrappedChestMeta = TrappedChest.getItemMeta();
		ArrayList<String> TrappedChestLore = new ArrayList<String>();
		TrappedChestLore.add("§e§lCost:");
		TrappedChestLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(TrappedChest.getTypeId())+ ".Buy")) + " (left click)");
		TrappedChestLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(TrappedChest.getTypeId())+ ".Buy") * 16) + " (right click)");
		TrappedChestMeta.setLore(TrappedChestLore);
		TrappedChest.setItemMeta(TrappedChestMeta);
		GUI.setItem(10, TrappedChest);
		
		ItemStack Dropper = new ItemStack(Material.DROPPER);	
		ItemMeta DropperMeta = Dropper.getItemMeta();
		ArrayList<String> DropperLore = new ArrayList<String>();
		DropperLore.add("§e§lCost:");
		DropperLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Dropper.getTypeId())+ ".Buy")) + " (left click)");
		DropperLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Dropper.getTypeId())+ ".Buy") * 16) + " (right click)");
		DropperMeta.setLore(DropperLore);
		Dropper.setItemMeta(DropperMeta);
		GUI.setItem(11, Dropper);
		
		ItemStack Repeater = new ItemStack(Material.DIODE);	
		ItemMeta RepeaterMeta = Repeater.getItemMeta();
		ArrayList<String> RepeaterLore = new ArrayList<String>();
		RepeaterLore.add("§e§lCost:");
		RepeaterLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Repeater.getTypeId())+ ".Buy")) + " (left click)");
		RepeaterLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Repeater.getTypeId())+ ".Buy") * 16) + " (right click)");
		RepeaterMeta.setLore(RepeaterLore);
		Repeater.setItemMeta(RepeaterMeta);
		GUI.setItem(12, Repeater);
		
		ItemStack Comparator = new ItemStack(Material.REDSTONE_COMPARATOR);	
		ItemMeta ComparatorMeta = Comparator.getItemMeta();
		ArrayList<String> ComparatorLore = new ArrayList<String>();
		ComparatorLore.add("§e§lCost:");
		ComparatorLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Comparator.getTypeId())+ ".Buy")) + " (left click)");
		ComparatorLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Comparator.getTypeId())+ ".Buy") * 16) + " (right click)");
		ComparatorMeta.setLore(ComparatorLore);
		Comparator.setItemMeta(ComparatorMeta);
		GUI.setItem(13, Comparator);		
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
	@SuppressWarnings("deprecation")
	public void openFoodFarming(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Food/Farming §c§l<<");
		
		ItemStack Apple = new ItemStack(Material.APPLE);	
		ItemMeta AppleMeta = Apple.getItemMeta();
		ArrayList<String> AppleLore = new ArrayList<String>();
		AppleLore.add("§e§lCost:");
		AppleLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Apple.getTypeId())+ ".Buy")) + " (left click)");
		AppleLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Apple.getTypeId())+ ".Buy") * 64) + " (right click)");
		AppleMeta.setLore(AppleLore);
		Apple.setItemMeta(AppleMeta);
		GUI.setItem(0, Apple);
		
		ItemStack CookedPork = new ItemStack(Material.GRILLED_PORK);	
		ItemMeta CookedPorkMeta = CookedPork.getItemMeta();
		ArrayList<String> CookedPorkLore = new ArrayList<String>();
		CookedPorkLore.add("§e§lCost:");
		CookedPorkLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CookedPork.getTypeId())+ ".Buy")) + " (left click)");
		CookedPorkLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(CookedPork.getTypeId())+ ".Buy") * 64) + " (right click)");
		CookedPorkMeta.setLore(CookedPorkLore);
		CookedPork.setItemMeta(CookedPorkMeta);
		GUI.setItem(1, CookedPork);
		
		ItemStack CookedFish = new ItemStack(Material.COOKED_FISH);	
		ItemMeta CookedFishMeta = CookedFish.getItemMeta();
		ArrayList<String> CookedFishLore = new ArrayList<String>();
		CookedFishLore.add("§e§lCost:");
		CookedFishLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CookedFish.getTypeId())+ ".Buy")) + " (left click)");
		CookedFishLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(CookedFish.getTypeId())+ ".Buy") * 64) + " (right click)");
		CookedFishMeta.setLore(CookedFishLore);
		CookedFish.setItemMeta(CookedFishMeta);
		GUI.setItem(2, CookedFish);
		
		ItemStack Steak = new ItemStack(Material.COOKED_BEEF);	
		ItemMeta SteakMeta = Steak.getItemMeta();
		ArrayList<String> SteakLore = new ArrayList<String>();
		SteakLore.add("§e§lCost:");
		SteakLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Steak.getTypeId())+ ".Buy")) + " (left click)");
		SteakLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Steak.getTypeId())+ ".Buy") * 64) + " (right click)");
		SteakMeta.setLore(SteakLore);
		Steak.setItemMeta(SteakMeta);
		GUI.setItem(3, Steak);
		
		ItemStack Chicken = new ItemStack(Material.COOKED_CHICKEN);	
		ItemMeta ChickenMeta = Chicken.getItemMeta();
		ArrayList<String> ChickenLore = new ArrayList<String>();
		ChickenLore.add("§e§lCost:");
		ChickenLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Chicken.getTypeId())+ ".Buy")) + " (left click)");
		ChickenLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Chicken.getTypeId())+ ".Buy") * 64) + " (right click)");
		ChickenMeta.setLore(ChickenLore);
		Chicken.setItemMeta(ChickenMeta);
		GUI.setItem(4, Chicken);
		
		ItemStack Carrot = new ItemStack(Material.CARROT_ITEM);	
		ItemMeta CarrotMeta = Carrot.getItemMeta();
		ArrayList<String> CarrotLore = new ArrayList<String>();
		CarrotLore.add("§e§lCost:");
		CarrotLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Carrot.getTypeId())+ ".Buy")) + " (left click)");
		CarrotLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Carrot.getTypeId())+ ".Buy") * 64) + " (right click)");
		CarrotMeta.setLore(CarrotLore);
		Carrot.setItemMeta(CarrotMeta);
		GUI.setItem(5, Carrot);
		
		ItemStack Potato = new ItemStack(Material.POTATO_ITEM);	
		ItemMeta PotatoMeta = Potato.getItemMeta();
		ArrayList<String> PotatoLore = new ArrayList<String>();
		PotatoLore.add("§e§lCost:");
		PotatoLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Potato.getTypeId())+ ".Buy")) + " (left click)");
		PotatoLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Potato.getTypeId())+ ".Buy") * 64) + " (right click)");
		PotatoMeta.setLore(PotatoLore);
		Potato.setItemMeta(PotatoMeta);
		GUI.setItem(6, Potato);
		
		ItemStack Seeds = new ItemStack(Material.SEEDS);	
		ItemMeta SeedsMeta = Seeds.getItemMeta();
		ArrayList<String> SeedsLore = new ArrayList<String>();
		SeedsLore.add("§e§lCost:");
		SeedsLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Seeds.getTypeId())+ ".Buy")) + " (left click)");
		SeedsLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Seeds.getTypeId())+ ".Buy") * 64) + " (right click)");
		SeedsMeta.setLore(SeedsLore);
		Seeds.setItemMeta(SeedsMeta);
		GUI.setItem(7, Seeds);
		
		ItemStack Sugarcane = new ItemStack(Material.SUGAR_CANE);	
		ItemMeta SugarcaneMeta = Sugarcane.getItemMeta();
		ArrayList<String> SugarcaneLore = new ArrayList<String>();
		SugarcaneLore.add("§e§lCost:");
		SugarcaneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sugarcane.getTypeId())+ ".Buy")) + " (left click)");
		SugarcaneLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sugarcane.getTypeId())+ ".Buy") * 64) + " (right click)");
		SugarcaneMeta.setLore(SugarcaneLore);
		Sugarcane.setItemMeta(SugarcaneMeta);
		GUI.setItem(8, Sugarcane);
		
		ItemStack MelonSeeds = new ItemStack(Material.MELON_SEEDS);	
		ItemMeta MelonSeedsMeta = MelonSeeds.getItemMeta();
		ArrayList<String> MelonSeedsLore = new ArrayList<String>();
		MelonSeedsLore.add("§e§lCost:");
		MelonSeedsLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(MelonSeeds.getTypeId())+ ".Buy")) + " (left click)");
		MelonSeedsLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(MelonSeeds.getTypeId())+ ".Buy") * 64) + " (right click)");
		MelonSeedsMeta.setLore(MelonSeedsLore);
		MelonSeeds.setItemMeta(MelonSeedsMeta);
		GUI.setItem(9, MelonSeeds);
		
		ItemStack PumpkinSeeds = new ItemStack(Material.PUMPKIN_SEEDS);	
		ItemMeta PumpkinSeedsMeta = PumpkinSeeds.getItemMeta();
		ArrayList<String> PumpkinSeedsLore = new ArrayList<String>();
		PumpkinSeedsLore.add("§e§lCost:");
		PumpkinSeedsLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(PumpkinSeeds.getTypeId())+ ".Buy")) + " (left click)");
		PumpkinSeedsLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(PumpkinSeeds.getTypeId())+ ".Buy") * 64) + " (right click)");
		PumpkinSeedsMeta.setLore(PumpkinSeedsLore);
		PumpkinSeeds.setItemMeta(PumpkinSeedsMeta);
		GUI.setItem(10, PumpkinSeeds);
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
	@SuppressWarnings("deprecation")
	public void openMiscellaneous(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Miscellaneous §c§l<<");
		
		ItemStack Beacon = new ItemStack(Material.BEACON);	
		ItemMeta BeaconMeta = Beacon.getItemMeta();
		ArrayList<String> BeaconLore = new ArrayList<String>();
		BeaconLore.add("§e§lCost:");
		BeaconLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Beacon.getTypeId())+ ".Buy")) + " (left click)");
		BeaconLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf(Beacon.getTypeId())+ ".Buy") * 8) + " (right click)");
		BeaconMeta.setLore(BeaconLore);
		Beacon.setItemMeta(BeaconMeta);
		GUI.setItem(0, Beacon);
		
		ItemStack Hopper = new ItemStack(Material.HOPPER);	
		ItemMeta HopperMeta = Hopper.getItemMeta();
		ArrayList<String> HopperLore = new ArrayList<String>();
		HopperLore.add("§e§lCost:");
		HopperLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Hopper.getTypeId())+ ".Buy")) + " (left click)");
		HopperLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf(Hopper.getTypeId())+ ".Buy") * 8) + " (right click)");
		HopperMeta.setLore(HopperLore);
		Hopper.setItemMeta(HopperMeta);
		GUI.setItem(1, Hopper);
		
		ItemStack Bucket = new ItemStack(Material.BUCKET);	
		ItemMeta BucketMeta = Bucket.getItemMeta();
		ArrayList<String> BucketLore = new ArrayList<String>();
		BucketLore.add("§e§lCost:");
		BucketLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bucket.getTypeId())+ ".Buy")) + " (left click)");
		BucketLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bucket.getTypeId())+ ".Buy") * 16) + " (right click)");
		BucketMeta.setLore(BucketLore);
		Bucket.setItemMeta(BucketMeta);
		GUI.setItem(2, Bucket);
		
		ItemStack WaterBucket = new ItemStack(Material.WATER_BUCKET);	
		ItemMeta WaterBucketMeta = WaterBucket.getItemMeta();
		ArrayList<String> WaterBucketLore = new ArrayList<String>();
		WaterBucketLore.add("§e§lCost:");
		WaterBucketLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(WaterBucket.getTypeId())+ ".Buy")) + " (left click)");
		WaterBucketLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(WaterBucket.getTypeId())+ ".Buy") * 16) + " (right click)");
		WaterBucketMeta.setLore(WaterBucketLore);
		WaterBucket.setItemMeta(WaterBucketMeta);
		GUI.setItem(3, WaterBucket);
		
		ItemStack MilkBucket = new ItemStack(Material.MILK_BUCKET);	
		ItemMeta MilkBucketMeta = MilkBucket.getItemMeta();
		ArrayList<String> MilkBucketLore = new ArrayList<String>();
		MilkBucketLore.add("§e§lCost:");
		MilkBucketLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(MilkBucket.getTypeId())+ ".Buy")) + " (left click)");
		MilkBucketLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(MilkBucket.getTypeId())+ ".Buy") * 16) + " (right click)");
		MilkBucketMeta.setLore(MilkBucketLore);
		MilkBucket.setItemMeta(MilkBucketMeta);
		GUI.setItem(4, MilkBucket);
		
		ItemStack Book = new ItemStack(Material.BOOK);	
		ItemMeta BookMeta = Book.getItemMeta();
		ArrayList<String> BookLore = new ArrayList<String>();
		BookLore.add("§e§lCost:");
		BookLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Book.getTypeId())+ ".Buy")) + " (left click)");
		BookLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Book.getTypeId())+ ".Buy") * 32) + " (right click)");
		BookMeta.setLore(BookLore);
		Book.setItemMeta(BookMeta);
		GUI.setItem(5, Book);
		
		ItemStack SlimeBall = new ItemStack(Material.SLIME_BALL);	
		ItemMeta SlimeBallMeta = SlimeBall.getItemMeta();
		ArrayList<String> SlimeBallLore = new ArrayList<String>();
		SlimeBallLore.add("§e§lCost:");
		SlimeBallLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(SlimeBall.getTypeId())+ ".Buy")) + " (left click)");
		SlimeBallLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(SlimeBall.getTypeId())+ ".Buy") * 32) + " (right click)");
		SlimeBallMeta.setLore(SlimeBallLore);
		SlimeBall.setItemMeta(SlimeBallMeta);
		GUI.setItem(6, SlimeBall);
		
		ItemStack Bone = new ItemStack(Material.BONE);	
		ItemMeta BoneMeta = Bone.getItemMeta();
		ArrayList<String> BoneLore = new ArrayList<String>();
		BoneLore.add("§e§lCost:");
		BoneLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bone.getTypeId())+ ".Buy")) + " (left click)");
		BoneLore.add("§8§l » §732 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bone.getTypeId())+ ".Buy") * 32) + " (right click)");
		BoneMeta.setLore(BoneLore);
		Bone.setItemMeta(BoneMeta);
		GUI.setItem(7, Bone);
		
		ItemStack Pearl = new ItemStack(Material.ENDER_PEARL);	
		ItemMeta PearlMeta = Pearl.getItemMeta();
		ArrayList<String> PearlLore = new ArrayList<String>();
		PearlLore.add("§e§lCost:");
		PearlLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Pearl.getTypeId())+ ".Buy")) + " (left click)");
		PearlLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Pearl.getTypeId())+ ".Buy") * 16) + " (right click)");
		PearlMeta.setLore(PearlLore);
		Pearl.setItemMeta(PearlMeta);
		GUI.setItem(8, Pearl);
		
		ItemStack DiamondHelmet = new ItemStack(Material.DIAMOND_HELMET);	
		ItemMeta DiamondHelmetMeta = DiamondHelmet.getItemMeta();
		ArrayList<String> DiamondHelmetLore = new ArrayList<String>();
		DiamondHelmetLore.add("§e§lCost:");
		DiamondHelmetLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondHelmet.getTypeId())+ ".Buy")) + " (left click)");
		DiamondHelmetMeta.setLore(DiamondHelmetLore);
		DiamondHelmet.setItemMeta(DiamondHelmetMeta);
		GUI.setItem(9, DiamondHelmet);
		
		ItemStack DiamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);	
		ItemMeta DiamondChestplateMeta = DiamondChestplate.getItemMeta();
		ArrayList<String> DiamondChestplateLore = new ArrayList<String>();
		DiamondChestplateLore.add("§e§lCost:");
		DiamondChestplateLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondChestplate.getTypeId())+ ".Buy")) + " (left click)");
		DiamondChestplateMeta.setLore(DiamondChestplateLore);
		DiamondChestplate.setItemMeta(DiamondChestplateMeta);
		GUI.setItem(10, DiamondChestplate);
		
		ItemStack DiamondLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);	
		ItemMeta DiamondLeggingsMeta = DiamondLeggings.getItemMeta();
		ArrayList<String> DiamondLeggingsLore = new ArrayList<String>();
		DiamondLeggingsLore.add("§e§lCost:");
		DiamondLeggingsLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondLeggings.getTypeId())+ ".Buy")) + " (left click)");
		DiamondLeggingsMeta.setLore(DiamondLeggingsLore);
		DiamondLeggings.setItemMeta(DiamondLeggingsMeta);
		GUI.setItem(11, DiamondLeggings);
		
		ItemStack DiamondBoots = new ItemStack(Material.DIAMOND_BOOTS);	
		ItemMeta DiamondBootsMeta = DiamondBoots.getItemMeta();
		ArrayList<String> DiamondBootsLore = new ArrayList<String>();
		DiamondBootsLore.add("§e§lCost:");
		DiamondBootsLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondBoots.getTypeId())+ ".Buy")) + " (left click)");
		DiamondBootsMeta.setLore(DiamondBootsLore);
		DiamondBoots.setItemMeta(DiamondBootsMeta);
		GUI.setItem(12, DiamondBoots);
		
		ItemStack DiamondSword = new ItemStack(Material.DIAMOND_SWORD);	
		ItemMeta DiamondSwordMeta = DiamondSword.getItemMeta();
		ArrayList<String> DiamondSwordLore = new ArrayList<String>();
		DiamondSwordLore.add("§e§lCost:");
		DiamondSwordLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondSword.getTypeId())+ ".Buy")) + " (left click)");
		DiamondSwordMeta.setLore(DiamondSwordLore);
		DiamondSword.setItemMeta(DiamondSwordMeta);
		GUI.setItem(13, DiamondSword);
		
		ItemStack DiamondAxe = new ItemStack(Material.DIAMOND_AXE);	
		ItemMeta DiamondAxeMeta = DiamondAxe.getItemMeta();
		ArrayList<String> DiamondAxeLore = new ArrayList<String>();
		DiamondAxeLore.add("§e§lCost:");
		DiamondAxeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondAxe.getTypeId())+ ".Buy")) + " (left click)");
		DiamondAxeMeta.setLore(DiamondAxeLore);
		DiamondAxe.setItemMeta(DiamondAxeMeta);
		GUI.setItem(14, DiamondAxe);
		
		ItemStack DiamondPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);	
		ItemMeta DiamondPickaxeMeta = DiamondPickaxe.getItemMeta();
		ArrayList<String> DiamondPickaxeLore = new ArrayList<String>();
		DiamondPickaxeLore.add("§e§lCost:");
		DiamondPickaxeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondPickaxe.getTypeId())+ ".Buy")) + " (left click)");
		DiamondPickaxeMeta.setLore(DiamondPickaxeLore);
		DiamondPickaxe.setItemMeta(DiamondPickaxeMeta);
		GUI.setItem(15, DiamondPickaxe);
		
		ItemStack DiamondHoe = new ItemStack(Material.DIAMOND_HOE);	
		ItemMeta DiamondHoeMeta = DiamondHoe.getItemMeta();
		ArrayList<String> DiamondHoeLore = new ArrayList<String>();
		DiamondHoeLore.add("§e§lCost:");
		DiamondHoeLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(DiamondHoe.getTypeId())+ ".Buy")) + " (left click)");
		DiamondHoeMeta.setLore(DiamondHoeLore);
		DiamondHoe.setItemMeta(DiamondHoeMeta);
		GUI.setItem(16, DiamondHoe);
		
		ItemStack Bow = new ItemStack(Material.BOW);	
		ItemMeta BowMeta = Bow.getItemMeta();
		ArrayList<String> BowLore = new ArrayList<String>();
		BowLore.add("§e§lCost:");
		BowLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Bow.getTypeId())+ ".Buy")) + " (left click)");
		BowMeta.setLore(BowLore);
		Bow.setItemMeta(BowMeta);
		GUI.setItem(16, Bow);
		
		ItemStack Arrow = new ItemStack(Material.ARROW);	
		ItemMeta ArrowMeta = Arrow.getItemMeta();
		ArrayList<String> ArrowLore = new ArrayList<String>();
		ArrowLore.add("§e§lCost:");
		ArrowLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Arrow.getTypeId())+ ".Buy")) + " (left click)");
		ArrowLore.add("§8§l » §764 = $" + (Main.pricesConfig.getDouble(String.valueOf(Arrow.getTypeId())+ ".Buy") * 64) + " (right click)");
		ArrowMeta.setLore(ArrowLore);
		Arrow.setItemMeta(ArrowMeta);
		GUI.setItem(17, Arrow);
		
		ItemStack FishingRod = new ItemStack(Material.FISHING_ROD);	
		ItemMeta FishingRodMeta = FishingRod.getItemMeta();
		ArrayList<String> FishingRodLore = new ArrayList<String>();
		FishingRodLore.add("§e§lCost:");
		FishingRodLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(FishingRod.getTypeId())+ ".Buy")) + " (left click)");
		FishingRodMeta.setLore(FishingRodLore);
		FishingRod.setItemMeta(FishingRodMeta);
		GUI.setItem(18, FishingRod);
		
		ItemStack Chest = new ItemStack(Material.CHEST);	
		ItemMeta ChestMeta = Chest.getItemMeta();
		ArrayList<String> ChestLore = new ArrayList<String>();
		ChestLore.add("§e§lCost:");
		ChestLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Chest.getTypeId())+ ".Buy")) + " (left click)");
		ChestLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Chest.getTypeId())+ ".Buy") * 16) + " (right click)");
		ChestMeta.setLore(ChestLore);
		Chest.setItemMeta(ChestMeta);
		GUI.setItem(19, Chest);
		
		ItemStack CraftingTable = new ItemStack(Material.WORKBENCH);	
		ItemMeta CraftingTableMeta = CraftingTable.getItemMeta();
		ArrayList<String> CraftingTableLore = new ArrayList<String>();
		CraftingTableLore.add("§e§lCost:");
		CraftingTableLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(CraftingTable.getTypeId())+ ".Buy")) + " (left click)");
		CraftingTableLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(CraftingTable.getTypeId())+ ".Buy") * 16) + " (right click)");
		CraftingTableMeta.setLore(CraftingTableLore);
		CraftingTable.setItemMeta(CraftingTableMeta);
		GUI.setItem(20, CraftingTable);
		
		ItemStack Furnace = new ItemStack(Material.FURNACE);	
		ItemMeta FurnaceMeta = Furnace.getItemMeta();
		ArrayList<String> FurnaceLore = new ArrayList<String>();
		FurnaceLore.add("§e§lCost:");
		FurnaceLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Furnace.getTypeId())+ ".Buy")) + " (left click)");
		FurnaceLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Furnace.getTypeId())+ ".Buy") * 16) + " (right click)");
		FurnaceMeta.setLore(FurnaceLore);
		Furnace.setItemMeta(FurnaceMeta);
		GUI.setItem(21, Furnace);
		
		ItemStack EnchantmentTable = new ItemStack(Material.ENCHANTMENT_TABLE);	
		ItemMeta EnchantmentTableMeta = EnchantmentTable.getItemMeta();
		ArrayList<String> EnchantmentTableLore = new ArrayList<String>();
		EnchantmentTableLore.add("§e§lCost:");
		EnchantmentTableLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(EnchantmentTable.getTypeId())+ ".Buy")) + " (left click)");
		EnchantmentTableLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(EnchantmentTable.getTypeId())+ ".Buy") * 16) + " (right click)");
		EnchantmentTableMeta.setLore(EnchantmentTableLore);
		EnchantmentTable.setItemMeta(EnchantmentTableMeta);
		GUI.setItem(22, EnchantmentTable);
		
		ItemStack EnderChest = new ItemStack(Material.ENDER_CHEST);	
		ItemMeta EnderChestMeta = EnderChest.getItemMeta();
		ArrayList<String> EnderChestLore = new ArrayList<String>();
		EnderChestLore.add("§e§lCost:");
		EnderChestLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(EnderChest.getTypeId())+ ".Buy")) + " (left click)");
		EnderChestLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(EnderChest.getTypeId())+ ".Buy") * 16) + " (right click)");
		EnderChestMeta.setLore(EnderChestLore);
		EnderChest.setItemMeta(EnderChestMeta);
		GUI.setItem(23, EnderChest);
		
		ItemStack Anvil = new ItemStack(Material.ANVIL);	
		ItemMeta AnvilMeta = Anvil.getItemMeta();
		ArrayList<String> AnvilLore = new ArrayList<String>();
		AnvilLore.add("§e§lCost:");
		AnvilLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Anvil.getTypeId())+ ".Buy")) + " (left click)");
		AnvilLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Anvil.getTypeId())+ ".Buy") * 16) + " (right click)");
		AnvilMeta.setLore(AnvilLore);
		Anvil.setItemMeta(AnvilMeta);
		GUI.setItem(24, Anvil);
		
		ItemStack Sign = new ItemStack(Material.SIGN);	
		ItemMeta SignMeta = Sign.getItemMeta();
		ArrayList<String> SignLore = new ArrayList<String>();
		SignLore.add("§e§lCost:");
		SignLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sign.getTypeId())+ ".Buy")) + " (left click)");
		SignLore.add("§8§l » §716 = $" + (Main.pricesConfig.getDouble(String.valueOf(Sign.getTypeId())+ ".Buy") * 16) + " (right click)");
		SignMeta.setLore(SignLore);
		Sign.setItemMeta(SignMeta);
		GUI.setItem(25, Sign);
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
	public void openSpawners(Player player) {
		Inventory GUI = Bukkit.createInventory(null, 45, "§c§l>> §8Spawners §c§l<<");
		
		ItemStack CreeperSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta CreeperSpawnerMeta = CreeperSpawner.getItemMeta();
		CreeperSpawnerMeta.setDisplayName("§eCreeper §fSpawner");
		ArrayList<String> CreeperSpawnerLore = new ArrayList<String>();
		CreeperSpawnerLore.add("§e§lCost:");
		CreeperSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("CreeperSpawner.Buy")) + " (left click)"));
		CreeperSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("CreeperSpawner.Buy")) * 8) + " (right click)");
		CreeperSpawnerMeta.setLore(CreeperSpawnerLore);
		CreeperSpawner.setItemMeta(CreeperSpawnerMeta);
		GUI.setItem(0, CreeperSpawner);
		
		ItemStack PigmanSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta PigmanSpawnerMeta = PigmanSpawner.getItemMeta();
		PigmanSpawnerMeta.setDisplayName("§eZombie Pigman §fSpawner");
		ArrayList<String> PigmanSpawnerLore = new ArrayList<String>();
		PigmanSpawnerLore.add("§e§lCost:");
		PigmanSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("PigmanSpawner.Buy")) + " (left click)"));
		PigmanSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("PigmanSpawner.Buy")) * 8) + " (right click)");
		PigmanSpawnerMeta.setLore(PigmanSpawnerLore);
		PigmanSpawner.setItemMeta(PigmanSpawnerMeta);
		GUI.setItem(1, PigmanSpawner);
		
		ItemStack WitchSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta WitchSpawnerMeta = WitchSpawner.getItemMeta();
		WitchSpawnerMeta.setDisplayName("§eWitch §fSpawner");
		ArrayList<String> WitchSpawnerLore = new ArrayList<String>();
		WitchSpawnerLore.add("§e§lCost:");
		WitchSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("WitchSpawner.Buy")) + " (left click)"));
		WitchSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("WitchSpawner.Buy")) * 8) + " (right click)");
		WitchSpawnerMeta.setLore(WitchSpawnerLore);
		WitchSpawner.setItemMeta(WitchSpawnerMeta);
		GUI.setItem(2, WitchSpawner);
		
		ItemStack EndermanSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta EndermanSpawnerMeta = EndermanSpawner.getItemMeta();
		EndermanSpawnerMeta.setDisplayName("§eEnderman §fSpawner");
		ArrayList<String> EndermanSpawnerLore = new ArrayList<String>();
		EndermanSpawnerLore.add("§e§lCost:");
		EndermanSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("EndermanSpawner.Buy")) + " (left click)"));
		EndermanSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("EndermanSpawner.Buy")) * 8) + " (right click)");
		EndermanSpawnerMeta.setLore(EndermanSpawnerLore);
		EndermanSpawner.setItemMeta(EndermanSpawnerMeta);
		GUI.setItem(3, EndermanSpawner);
		
		ItemStack BlazeSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta BlazeSpawnerMeta = BlazeSpawner.getItemMeta();
		BlazeSpawnerMeta.setDisplayName("§eBlaze §fSpawner");
		ArrayList<String> BlazeSpawnerLore = new ArrayList<String>();
		BlazeSpawnerLore.add("§e§lCost:");
		BlazeSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("BlazeSpawner.Buy")) + " (left click)"));
		BlazeSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("BlazeSpawner.Buy")) * 8) + " (right click)");
		BlazeSpawnerMeta.setLore(BlazeSpawnerLore);
		BlazeSpawner.setItemMeta(BlazeSpawnerMeta);
		GUI.setItem(4, BlazeSpawner);
		
		ItemStack SquidSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta SquidSpawnerMeta = SquidSpawner.getItemMeta();
		SquidSpawnerMeta.setDisplayName("§eSquid §fSpawner");
		ArrayList<String> SquidSpawnerLore = new ArrayList<String>();
		SquidSpawnerLore.add("§e§lCost:");
		SquidSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("SquidSpawner.Buy")) + " (left click)"));
		SquidSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("SquidSpawner.Buy")) * 8) + " (right click)");
		SquidSpawnerMeta.setLore(SquidSpawnerLore);
		SquidSpawner.setItemMeta(SquidSpawnerMeta);
		GUI.setItem(5, SquidSpawner);
		
		ItemStack MagmaCubeSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta MagmaCubeSpawnerMeta = MagmaCubeSpawner.getItemMeta();
		MagmaCubeSpawnerMeta.setDisplayName("§eMagma Cube §fSpawner");
		ArrayList<String> MagmaCubeSpawnerLore = new ArrayList<String>();
		MagmaCubeSpawnerLore.add("§e§lCost:");
		MagmaCubeSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("MagmaCubeSpawner.Buy")) + " (left click)"));
		MagmaCubeSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("MagmaCubeSpawner.Buy")) * 8) + " (right click)");
		MagmaCubeSpawnerMeta.setLore(MagmaCubeSpawnerLore);
		MagmaCubeSpawner.setItemMeta(MagmaCubeSpawnerMeta);
		GUI.setItem(6, MagmaCubeSpawner);
		
		ItemStack SpiderSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta SpiderSpawnerMeta = SpiderSpawner.getItemMeta();
		SpiderSpawnerMeta.setDisplayName("§eSpider §fSpawner");
		ArrayList<String> SpiderSpawnerLore = new ArrayList<String>();
		SpiderSpawnerLore.add("§e§lCost:");
		SpiderSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("SpiderSpawner.Buy")) + " (left click)"));
		SpiderSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("SpiderSpawner.Buy")) * 8) + " (right click)");
		SpiderSpawnerMeta.setLore(SpiderSpawnerLore);
		SpiderSpawner.setItemMeta(SpiderSpawnerMeta);
		GUI.setItem(7, SpiderSpawner);
		
		ItemStack ZombieSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta ZombieSpawnerMeta = ZombieSpawner.getItemMeta();
		ZombieSpawnerMeta.setDisplayName("§eZombie §fSpawner");
		ArrayList<String> ZombieSpawnerLore = new ArrayList<String>();
		ZombieSpawnerLore.add("§e§lCost:");
		ZombieSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("ZombieSpawner.Buy")) + " (left click)"));
		ZombieSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("ZombieSpawner.Buy")) * 8) + " (right click)");
		ZombieSpawnerMeta.setLore(ZombieSpawnerLore);
		ZombieSpawner.setItemMeta(ZombieSpawnerMeta);
		GUI.setItem(8, ZombieSpawner);
		
		ItemStack SkeletonSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta SkeletonSpawnerMeta = SkeletonSpawner.getItemMeta();
		SkeletonSpawnerMeta.setDisplayName("§eSkeleton §fSpawner");
		ArrayList<String> SkeletonSpawnerLore = new ArrayList<String>();
		SkeletonSpawnerLore.add("§e§lCost:");
		SkeletonSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("SkeletonSpawner.Buy")) + " (left click)"));
		SkeletonSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("SkeletonSpawner.Buy")) * 8) + " (right click)");
		SkeletonSpawnerMeta.setLore(SkeletonSpawnerLore);
		SkeletonSpawner.setItemMeta(SkeletonSpawnerMeta);
		GUI.setItem(9, SkeletonSpawner);
		
		ItemStack PigSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta PigSpawnerMeta = PigSpawner.getItemMeta();
		PigSpawnerMeta.setDisplayName("§ePig §fSpawner");
		ArrayList<String> PigSpawnerLore = new ArrayList<String>();
		PigSpawnerLore.add("§e§lCost:");
		PigSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("PigSpawner.Buy")) + " (left click)"));
		PigSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("PigSpawner.Buy")) * 8) + " (right click)");
		PigSpawnerMeta.setLore(PigSpawnerLore);
		PigSpawner.setItemMeta(PigSpawnerMeta);
		GUI.setItem(10, PigSpawner);
		
		ItemStack CowSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta CowSpawnerMeta = CowSpawner.getItemMeta();
		CowSpawnerMeta.setDisplayName("§eCow §fSpawner");
		ArrayList<String> CowSpawnerLore = new ArrayList<String>();
		CowSpawnerLore.add("§e§lCost:");
		CowSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("CowSpawner.Buy")) + " (left click)"));
		CowSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("CowSpawner.Buy")) * 8) + " (right click)");
		CowSpawnerMeta.setLore(CowSpawnerLore);
		CowSpawner.setItemMeta(CowSpawnerMeta);
		GUI.setItem(11, CowSpawner);
		
		ItemStack ChickenSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta ChickenSpawnerMeta = ChickenSpawner.getItemMeta();
		ChickenSpawnerMeta.setDisplayName("§eChicken §fSpawner");
		ArrayList<String> ChickenSpawnerLore = new ArrayList<String>();
		ChickenSpawnerLore.add("§e§lCost:");
		ChickenSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("ChickenSpawner.Buy")) + " (left click)"));
		ChickenSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("ChickenSpawner.Buy")) * 8) + " (right click)");
		ChickenSpawnerMeta.setLore(ChickenSpawnerLore);
		ChickenSpawner.setItemMeta(ChickenSpawnerMeta);
		GUI.setItem(12, ChickenSpawner);
		
		ItemStack SheepSpawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta SheepSpawnerMeta = SheepSpawner.getItemMeta();
		SheepSpawnerMeta.setDisplayName("§eSheep §fSpawner");
		ArrayList<String> SheepSpawnerLore = new ArrayList<String>();
		SheepSpawnerLore.add("§e§lCost:");
		SheepSpawnerLore.add("§8§l » §71 = $" + (Main.pricesConfig.getDouble(String.valueOf("SheepSpawner.Buy")) + " (left click)"));
		SheepSpawnerLore.add("§8§l » §78 = $" + (Main.pricesConfig.getDouble(String.valueOf("SheepSpawner.Buy")) * 8) + " (right click)");
		SheepSpawnerMeta.setLore(SheepSpawnerLore);
		SheepSpawner.setItemMeta(SheepSpawnerMeta);
		GUI.setItem(13, SheepSpawner);
		
		ItemStack backItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta backItemMeta = backItem.getItemMeta();
		backItemMeta.setDisplayName("§c§lBack");
		backItem.setItemMeta(backItemMeta);
		GUI.setItem(44, backItem);
		
		player.openInventory(GUI);
	}
	
}
