package CustomEnchants;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPerm;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class Shockwave {

	public static void minePickaxe(Player player, Block block) {

		if (BoardColl.get().getFactionAt(PS.valueOf(block.getLocation())).getName().equals("Castle")) {
			player.getPlayer().getLocation().getWorld().playSound(player.getPlayer().getLocation(), Sound.CREEPER_HISS, 10, 1);
			player.sendMessage("§c§l(!)§7 You cannot use the shockwave effect in castle territory!");
			return;
		}

		float yaw = player.getLocation().getYaw();
		if (yaw < 0) {
			yaw += 360;
		}

		Block[] blocks = new Block[8];

		if (block.getY() > (player.getLocation().getY() + 2)) {
			// above
			if (yaw >= 315 || yaw < 45) {
				// south
				Block top = block.getRelative(BlockFace.SOUTH);
				Block bottom = block.getRelative(BlockFace.NORTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 135) {
				// West
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 225) {
				// North
				Block top = block.getRelative(BlockFace.NORTH);
				Block bottom = block.getRelative(BlockFace.SOUTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 315) {
				// East
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			}
		} else if (block.getY() <= player.getLocation().getY()) {
			// below
			if (yaw >= 315 || yaw < 45) {
				// south
				Block top = block.getRelative(BlockFace.SOUTH);
				Block bottom = block.getRelative(BlockFace.NORTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 135) {
				// West
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 225) {
				// North
				Block top = block.getRelative(BlockFace.SOUTH);
				Block bottom = block.getRelative(BlockFace.NORTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 315) {
				// East
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			}
		} else { // inlevel
			if (yaw >= 315 || yaw < 45) {
				// south
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.WEST);
				Block left = block.getRelative(BlockFace.EAST);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			} else if (yaw < 135) {
				// West
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.NORTH);
				Block left = block.getRelative(BlockFace.SOUTH);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			} else if (yaw < 225) {
				// North
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.EAST);
				Block left = block.getRelative(BlockFace.WEST);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			} else if (yaw < 315) {
				// East
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.SOUTH);
				Block left = block.getRelative(BlockFace.NORTH);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			}
		}

		for (Block currentBlock : blocks) {
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(currentBlock));
			MPlayer mPlayer = MPlayer.get(player);
			if (MPerm.getPermBuild().has(mPlayer, faction, true)) {
				Material material = currentBlock.getType();
				if (material.equals(Material.SANDSTONE) || material.equals(Material.STONE)
						|| material.equals(Material.REDSTONE_ORE) || material.equals(Material.LAPIS_ORE)
						|| material.equals(Material.COAL_ORE) || material.equals(Material.IRON_ORE)
						|| material.equals(Material.GOLD_ORE) || material.equals(Material.ENDER_STONE)
						|| material.equals(Material.HARD_CLAY) || material.equals(Material.STAINED_CLAY)
						|| material.equals(Material.COBBLESTONE)) {
					if (player.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
						if (material.equals(Material.DIAMOND_ORE)) {
							int fortune = player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							Random rand = new Random();
							int num = rand.nextInt(6) + 1;
							ItemStack extraDiamond = new ItemStack(Material.DIAMOND);
							if (fortune == 1) {
								if (num == 1) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							} else if (fortune == 2) {
								if (num == 1 || num == 2) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								} else if (num == 3) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							} else if (fortune == 3) {
								if (num == 1 || num == 2) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								} else if (num == 3 || num == 4) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							}
						} else if (material.equals(Material.REDSTONE_ORE)) {
							int fortune = player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							Random rand = new Random();
							int num = rand.nextInt(6) + 1;
							ItemStack extraDiamond = new ItemStack(Material.REDSTONE);
							if (fortune == 1) {
								if (num == 1) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							} else if (fortune == 2) {
								if (num == 1 || num == 2) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								} else if (num == 3) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							} else if (fortune == 3) {
								if (num == 1 || num == 2) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								} else if (num == 3 || num == 4) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							}
						} else if (material.equals(Material.COAL)) {
							int fortune = player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
							Random rand = new Random();
							int num = rand.nextInt(6) + 1;
							ItemStack extraDiamond = new ItemStack(Material.COAL);
							if (fortune == 1) {
								if (num == 1) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							} else if (fortune == 2) {
								if (num == 1 || num == 2) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								} else if (num == 3) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							} else if (fortune == 3) {
								if (num == 1 || num == 2) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								} else if (num == 3 || num == 4) {
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
									currentBlock.getWorld().dropItem(currentBlock.getLocation(), extraDiamond);
								}
							}
						}
					}
					currentBlock.breakNaturally();
				}
			}
		}
	}

	public static void mineShovel(Player player, Block block) {
		float yaw = player.getLocation().getYaw();
		if (yaw < 0) {
			yaw += 360;
		}

		Block[] blocks = new Block[8];

		if (block.getY() > (player.getLocation().getY() + 1)) {
			// above
			if (yaw >= 315 || yaw < 45) {
				// south
				Block top = block.getRelative(BlockFace.SOUTH);
				Block bottom = block.getRelative(BlockFace.NORTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 135) {
				// West
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 225) {
				// North
				Block top = block.getRelative(BlockFace.NORTH);
				Block bottom = block.getRelative(BlockFace.SOUTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 315) {
				// East
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			}
		} else if (block.getY() <= player.getLocation().getY()) {
			// below
			if (yaw >= 315 || yaw < 45) {
				// south
				Block top = block.getRelative(BlockFace.SOUTH);
				Block bottom = block.getRelative(BlockFace.NORTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 135) {
				// West
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 225) {
				// North
				Block top = block.getRelative(BlockFace.SOUTH);
				Block bottom = block.getRelative(BlockFace.NORTH);
				blocks[0] = top.getRelative(BlockFace.EAST);
				blocks[1] = top.getRelative(BlockFace.WEST);
				blocks[2] = bottom.getRelative(BlockFace.EAST);
				blocks[3] = bottom.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.WEST);
				blocks[6] = top;
				blocks[7] = bottom;
			} else if (yaw < 315) {
				// East
				Block top = block.getRelative(BlockFace.WEST);
				Block bottom = block.getRelative(BlockFace.EAST);
				blocks[0] = top.getRelative(BlockFace.NORTH);
				blocks[1] = top.getRelative(BlockFace.SOUTH);
				blocks[2] = bottom.getRelative(BlockFace.NORTH);
				blocks[3] = bottom.getRelative(BlockFace.SOUTH);
				blocks[4] = block.getRelative(BlockFace.NORTH);
				blocks[5] = block.getRelative(BlockFace.SOUTH);
				blocks[6] = top;
				blocks[7] = bottom;
			}
		} else { // inlevel
			if (yaw >= 315 || yaw < 45) {
				// south
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.WEST);
				Block left = block.getRelative(BlockFace.EAST);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			} else if (yaw < 135) {
				// West
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.NORTH);
				Block left = block.getRelative(BlockFace.SOUTH);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			} else if (yaw < 225) {
				// North
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.EAST);
				Block left = block.getRelative(BlockFace.WEST);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			} else if (yaw < 315) {
				// East
				blocks[0] = block.getRelative(BlockFace.UP);
				blocks[1] = block.getRelative(BlockFace.DOWN);
				Block right = block.getRelative(BlockFace.SOUTH);
				Block left = block.getRelative(BlockFace.NORTH);
				blocks[2] = right.getRelative(BlockFace.UP);
				blocks[3] = right.getRelative(BlockFace.DOWN);
				blocks[4] = left.getRelative(BlockFace.UP);
				blocks[5] = left.getRelative(BlockFace.DOWN);
				blocks[6] = right;
				blocks[7] = left;
			}
		}
		for (Block currentBlock : blocks) {
			Faction faction = BoardColl.get().getFactionAt(PS.valueOf(currentBlock));
			MPlayer mPlayer = MPlayer.get(player);
			if (MPerm.getPermBuild().has(mPlayer, faction, true)) {
				Material material = currentBlock.getType();
				if (material.equals(Material.SAND) || material.equals(Material.GRASS) || material.equals(Material.DIRT)
						|| material.equals(Material.GRAVEL) || material.equals(Material.CLAY)) {
					currentBlock.breakNaturally();
				}
			}
		}
	}

}