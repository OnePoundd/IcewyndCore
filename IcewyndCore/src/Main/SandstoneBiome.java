package Main;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SandstoneBiome {

	public static void convert(Player player) {
		player.sendMessage("Initiating conversion sequence...");
		for (Chunk chunk : player.getWorld().getLoadedChunks()) {
			Block randomBlock = chunk.getBlock(1, 10, 1);
			if (chunk.getWorld().getBiome(randomBlock.getX(), randomBlock.getZ()).equals(Biome.DESERT)) {
				for (int x = 0; x < 16; x++) {
					for (int z = 0; z < 16; z++) {
						for (int y = 80; y > 50; y--) {
							Block block = chunk.getBlock(x, y, z);
							if (block.getType().equals(Material.SAND) || block.getType().equals(Material.STONE)) {
								for (int newy = 1; newy < block.getY() - 1; newy++) {
									chunk.getBlock(x, newy, z).setType(Material.SANDSTONE);
								}
								break;
							}
						}
					}
				}
			}
		}
		player.sendMessage("Completed conversion sequence!");
	}

}
