package Commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import Main.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class WitherEvent implements CommandExecutor, Listener {
	Main plugin = Main.getPlugin(Main.class);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("witherspawn")) {
			if (sender instanceof Player) {
				Location Spawn = ((Player) sender).getPlayer().getLocation();
				plugin.getConfig().set(".WitherSpawn", Spawn);
				plugin.saveConfig();
			}
		}
		return false;
	}

	public void noAI(org.bukkit.entity.Entity bukkitEntity)
	{
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);
	}

	@EventHandler
	public void ondamage(EntityDamageEvent event) {
		Entity e = event.getEntity();
		if (e.getType() == EntityType.WITHER) {
			if (e instanceof LivingEntity) {
				if (plugin.getConfig().getBoolean(".WitherInvin") == true) {
					event.setCancelled(true);
				}else if (plugin.getConfig().getBoolean(".WitherInvin") == false) {
					if (((LivingEntity) e).getHealth() < 150) {
						if (plugin.getConfig().getInt(".WitherPhase") == 0) {
							plugin.getConfig().set(".WitherPhase", 1);
							plugin.getConfig().set(".WitherInvin", true);
							Bukkit.broadcastMessage("50% Health, summoning Wither Guards");
							Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
							e.teleport(WitherSpawn);
							LivingEntity entity = (LivingEntity) event.getEntity();
							noAI(entity);
							
							Bukkit.getWorld("world").playEffect(WitherSpawn, Effect.PORTAL, 50);

							Skeleton mob = (Skeleton) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(-4, 0, 0), EntityType.SKELETON);
							Skeleton mob2 = (Skeleton) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(4, 0, 4), EntityType.SKELETON);
							Skeleton mob3 = (Skeleton) Bukkit.getWorld("world").spawnEntity(WitherSpawn.add(2, 0, -2), EntityType.SKELETON);

							//Helmet
							ItemStack Item1 = new ItemStack(Material.LEATHER_HELMET, 1);
							LeatherArmorMeta Item1Meta = (LeatherArmorMeta) Item1.getItemMeta();
							Item1Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1000, true);
							Item1Meta.addEnchant(Enchantment.DURABILITY, 1, true);
							Item1Meta.setColor(Color.BLACK);
							Item1.setItemMeta(Item1Meta);
							
							//Chesplate
							ItemStack Item2 = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
							LeatherArmorMeta Item2Meta = (LeatherArmorMeta) Item2.getItemMeta();
							Item2Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1000, true);
							Item2Meta.addEnchant(Enchantment.DURABILITY, 1, true);
							Item2Meta.setColor(Color.BLACK);
							Item2.setItemMeta(Item2Meta);

							//Leggings
							ItemStack Item3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
							LeatherArmorMeta Item3Meta = (LeatherArmorMeta) Item3.getItemMeta();
							Item3Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1000, true);
							Item3Meta.addEnchant(Enchantment.DURABILITY, 1, true);
							Item3Meta.setColor(Color.BLACK);
							Item3.setItemMeta(Item3Meta);
							
							//Boots
							ItemStack Item4 = new ItemStack(Material.LEATHER_BOOTS, 1);
							LeatherArmorMeta Item4Meta = (LeatherArmorMeta) Item4.getItemMeta();
							Item4Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1000, true);
							Item4Meta.addEnchant(Enchantment.DURABILITY, 1, true);
							Item4Meta.setColor(Color.BLACK);
							Item4.setItemMeta(Item4Meta);
							
							//Sword
							ItemStack Item5 = new ItemStack(Material.DIAMOND_SWORD, 1);
							ItemMeta Item5Meta = Item5.getItemMeta();
							Item5Meta.addEnchant(Enchantment.DAMAGE_ALL, 20, true);
							Item5.setItemMeta(Item5Meta);
							
							mob.setCustomName("�c�lWithered Minion");
							mob.setSkeletonType(Skeleton.SkeletonType.WITHER);
							mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
							mob.getEquipment().setHelmet(Item1);
							mob.getEquipment().setChestplate(Item2);
							mob.getEquipment().setLeggings(Item3);
							mob.getEquipment().setBoots(Item4);
							mob.getEquipment().setItemInHand(Item5);

							mob2.setCustomName("�c�lWithered Minion");
							mob2.setSkeletonType(Skeleton.SkeletonType.WITHER);
							mob2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
							mob2.getEquipment().setHelmet(Item1);
							mob2.getEquipment().setChestplate(Item2);
							mob2.getEquipment().setLeggings(Item3);
							mob2.getEquipment().setBoots(Item4);
							mob2.getEquipment().setItemInHand(Item5);

							mob3.setCustomName("�c�lWithered Minion");
							mob3.setSkeletonType(Skeleton.SkeletonType.WITHER);
							mob3.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
							mob3.getEquipment().setHelmet(Item1);
							mob3.getEquipment().setChestplate(Item2);
							mob3.getEquipment().setLeggings(Item3);
							mob3.getEquipment().setBoots(Item4);
							mob3.getEquipment().setItemInHand(Item5);

							WitherSpawn.add(-2, 0, -2);
							plugin.saveConfig();
						}

					if (((LivingEntity) e).getHealth() < 100) {
						if (plugin.getConfig().getInt(".WitherPhase") == 1) {
							Bukkit.broadcastMessage("2");
							plugin.getConfig().set(".WitherPhase", 2);
							plugin.saveConfig();
							Bukkit.broadcastMessage("25% Health");
							Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
							e.teleport(WitherSpawn);
							LivingEntity entity = (LivingEntity) event.getEntity();
							noAI(entity);
							plugin.getConfig().set(".WitherSkeletons", 0);
							plugin.saveConfig();
						}
					}
					}
				}
			}
		}
	}

	@EventHandler
	public void ondeath(EntityDeathEvent event) {
		int totalKilled = plugin.getConfig().getInt(".WitherSkeletons");
		if (event.getEntity() instanceof Wither) {
			if (event.getEntity().getCustomName().equals("�4�l�oWither King")) {
				plugin.getConfig().set(".WitherPhase", 0);
				plugin.saveConfig();
				Bukkit.broadcastMessage("WITHER KING HAS BEEN SLAIN");
			}
		}
		if (event.getEntity() instanceof Skeleton) {
			Entity entity = event.getEntity();
			Skeleton skeleton = (Skeleton) entity;
			if (skeleton.getSkeletonType() == SkeletonType.WITHER) {
				plugin.getConfig().set(".WitherSkeletons", totalKilled + 1);
				plugin.saveConfig();
				if (plugin.getConfig().getInt(".WitherSkeletons") == 3) {
					plugin.getConfig().set(".WitherInvin", false);
					plugin.saveConfig();
					for (Entity entity1 : Bukkit.getWorld("World").getEntities())
						if (entity1.getType() == EntityType.WITHER) {
							entity1.remove();
							Location WitherSpawn = (Location) (plugin.getConfig()).get(".WitherSpawn");
							Wither mob = (Wither) Bukkit.getWorld("world").spawnEntity(WitherSpawn, EntityType.WITHER);
							mob.setCustomName("�4�l�oWither King");
							mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 1));
							mob.setMaxHealth(150);
							mob.setHealth(150);
						}
				}
			}
		}
	}
}