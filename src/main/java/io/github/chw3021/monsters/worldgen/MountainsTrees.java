package io.github.chw3021.monsters.worldgen;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

public class MountainsTrees extends BlockPopulator {
	@Override
	public void populate(WorldInfo world, Random random, int chunkX, int chunkZ, LimitedRegion limitedRegion) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(5)+1;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; limitedRegion.getBlockState(X, Y, Z).getType() == Material.AIR; Y--);// Find the highest block of the (X,Z) coordinate chosen.
		        limitedRegion.getBlockState(X, Y, Z).setType(Material.GRASS_BLOCK);	
		        limitedRegion.generateTree(limitedRegion.getBlockState(X, Y+1, Z).getLocation(), random, TreeType.JUNGLE);
		    }
		}
	}
}
